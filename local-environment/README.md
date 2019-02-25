
# Local AEM Environment Setup with Docker

This setup consists of a dispatcher connected by default with AEM publish and AEM author deployed via [gradle-aem-plugin](https://github.com/Cognifide/gradle-aem-plugin). Dispatcher runs on docker and its image is build based on [dispatcher/Dockerfile](dispatcher/Dockerfile).

AEM author and publish instances need to be up.

## Environment in details
1. AEM author at [http://localhost:4502](http://localhost:4502)
2. AEM publish at [http://localhost:4503](http://localhost:4503)
3. Dispatcher available under three different domains:
  * http://example.com -> which maps to `/content/example/live` content root on publish
  * http://demo.example.com -> which maps to `/content/example/demo` content root on publish
  * http://author.example.com -> which is proxy to the author instance

## Configuration
Most of the configuration steps are automated. However, there are three manual steps to make this setup fully operating:

1. [Install docker](https://docs.docker.com/install/)
2. Extend your `/etc/hosts` (`c:\Windows\System32\Drivers\etc\hosts` for Windows 10) with entries: 
    ```bash
    127.0.0.1       invalidation-only
    127.0.0.1       example.com
    127.0.0.1       demo.example.com
    127.0.0.1	author.example.com
    ```
3. Setup dispatcher flush on author for cache invalidation (make sure your author and publish instances are up)
    * go to [http://localhost:4502/etc/replication/agents.author/flush.html](http://localhost:4502/etc/replication/agents.author/flush.html)
    * Edit
    * check `Enabled`
    * go to `Transport` tab, set `URI` to http://example.com/dispatcher/invalidate.cache
    
## Starting

Run `gradlew aemEnvSetup` to start both dispatcher and AEM instances and deploy the app.

Run `gradlew aemEnvUp` to start dispatcher when AEM instances are up and application is deployed.

In case of the dispatcher it takes few seconds to start. Service heath check is described in [../aem/build.gradle.kts](../aem/build.gradle.kts). By default it will wait for all three domains to be available:

```kotlin
environment {
    healthChecks {
        "http://example.com/en-us.html" respondsWith {
            status = 200
            text = "English"
        }
        "http://demo.example.com/en-us.html" respondsWith {
            status = 200
            text = "English"
        }
        "http://author.example.com/libs/granite/core/content/login.html?resource=%2F&\$\$login\$\$=%24%24login%24%24&j_reason=unknown&j_reason_code=unknown" respondsWith {
            status = 200
            text = "AEM Sign In"
        }
    }
}
```

You can also check docker services status using `docker service ls`:
```
ID                  NAME                   MODE                REPLICAS            IMAGE                       PORTS
ukdohhbfvxm8        local-setup_dispatcher   replicated          1/1                 mierzwid/dispatcher:0.0.1   *:80->80/tcp
```

## Updating httpd/dispatcher configuration

If you need to make changes to httpd/dispatcher configuration:
1. edit files in the [dispatcher](dispatcher) directory 
2. `cd dispatcher`
3. build your new image using `docker build --tag=image_name:version .`
4. update [docker-compose.yml](docker-compose.yml) to use your dispatcher image 

## Using Docker Stack directly 
1. First of all init Docker Swarm:
`docker swarm init`

2. Then setup the local environment:
`docker stack deploy -c docker-compose.yml local-setup` 

3. To stop it run:
`docker stack rm local-setup`
