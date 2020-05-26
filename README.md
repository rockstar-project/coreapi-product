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


#### Download Code

```
$ git clone https://github.com/rockstar-project/coreapi-product
$ cd coreapi-product
```

