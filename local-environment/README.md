
# Local AEM Environment Setup with Docker

This setup consists of a dispatcher connected by default with AEM publish and AEM author deployed via [gradle-aem-plugin](https://github.com/Cognifide/gradle-aem-plugin). Dispatcher runs on docker and its image is build based on [dispatcher/Dockerfile](dispatcher/Dockerfile)

## Configuration
Most of the configuration steps are automated. However, there are two manual steps:

1. [Install docker](https://docs.docker.com/install/)
2. Extend your `/etc/hosts` (`c:\Windows\System32\Drivers\etc\hosts` for Windows 10) with entries:
```bash
127.0.0.1       example.com
127.0.0.1       demo.example.com
127.0.0.1	author.example.com
```
3. Setup dispatcher flush on author
  * go to [/etc/replication/agents.author/flush.html](http://localhost:4502/etc/replication/agents.author/flush.html)
  * Edit
  * Check `Enabled`
  * Go to `Transport` tab, set `URI` to http://example.com/dispatcher/invalidate.cache
  
## Starting

Run: `gradlew aemEnvUp`

It takes few seconds to start, you can check status via: `docker service ls`

## Updating httpd/dispatcher configuration

If you need to make changes to httpd/dispatcher configuration:
1. edit files in the [dispatcher](dispatcher) directory 
2. `cd dispatcher`
3. build your new image using `docker build --tag=image_name:version .`
4. update [docker-compose.yml](docker-compose.yml) to use your dispatcher image 

## Using Docker Stack directly 
1. First of all init Docker properly:
`docker swarm init`

2. Then setup the local environment:
`docker stack deploy -c docker-compose.yml local-environment` 

3. To stop it run:
`docker stack rm local-setup`
