## Product API

| API | Method | Endpoint |
|-----|--------|----------|
| Create Product | POST | /products |
| Get Products | GET | /products |
| Get Specific Product | GET | /products/{id} |
| Update Specific Product | PATCH | /products/{id} |
| Delete Specific Product | DELETE | /products/{id} |
| Create Product Attribute | POST | /products/{id}/attributes |
| Get Product Attributes | GET | /products/{id}/attributes |
| Get Specific Product Attribute | GET | /products/{id}/attributes/{attribute_id} |
| Update Specific Product Attribute | PUT | /products/{id}/attributes/{attribute_id} |
| Delete Specific Product Attribute | DELETE | /products/{id}/attributes/{attribute_id} |
| Create Product Option | POST | /products/{id}/options |
| Get Product Options | GET | /products/{id}/options |
| Get Specific Product Option | GET | /products/{id}/options/{option_id} |
| Update Specific Product Option | PATCH | /products/{id}/options/{option_id} |
| Delete Specific Product Option | DELETE | /products/{id}/options/{option_id} |

### Getting Started


#### Install Docker Toolbox
* Download & Install [Docker Toolbox](https://www.docker.com/products/docker-toolbox) on your computer.
* Click Docker Quickstart Terminal icon to open command line console

#### Configure Environment

```
$ export DOCKER_USER=xxxxx
$ export DOCKER_PASSWORD=xxxx
$ export DOCKER_NAMESPACE=rockstarproject
```

#### Download Code

```
$ git clone https://github.com/rockstar-project/coreapi-product
$ cd coreapi-product
```

#### Start virtual machine

```
$ docker-machine create -d virtualbox --virtualbox-memory 4096 coreapi-product-vb-node
$ eval $(docker-machine env coreapi-product-vb-node)
```

#### Run MySQL database

```
$ docker-compose up -d productmysql
```

#### Install Product Schema

```
$ docker-compose -f docker-compose.develop.yml exec productmysql /bin/bash
$ mysql --user=rockstar --password=rockstar123 --database=rockstar_db_product < product/schema.sql
```

#### Run Discovery Service

```
$ docker-compose -f docker-compose.develop.yml up -d discovery
```

#### Run Product API microservice

```
$ docker-compose -f docker-compose.develop.yml up -d productapi
```

#### Connect to the API endpoint

```
curl http://$(docker-machine ip coreapi-product-vb-node):8080/products | jq .
```

#### Redeploy with code changes

```
docker-compose stop productapi && docker-compose rm -f productapi
docker rmi $DOCKER_NAMESPACE/coreapi-product
mvn clean package
docker build -t $DOCKER_NAMESPACE/coreapi-product .
docker-compose up -d productapi
docker-compose logs --follow productapi
```
