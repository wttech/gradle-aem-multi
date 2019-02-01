First of all init Docker properly:
`docker swarm init`

Then setup the local environment:
`docker stack deploy -c docker-compose.yml local-environment` 

Task to stop the local environment:
`docker stack rm local-environment`
