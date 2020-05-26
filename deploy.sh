
mvn clean package -Dmaven.test.skip=true

docker build -t rockstar/coreapi-product .
docker tag rockstar/coreapi-product:latest $DOCKER_REGISTRY_URL/rockstar/coreapi-product:0.0.1
docker login -u $DOCKER_USER -p $DOCKER_PASSWORD https://$DOCKER_REGISTRY_URL
docker push $DOCKER_REGISTRY_URL/rockstar/coreapi-product:0.0.1

oc login $OPENSHIFT_URL --token=$OPENSHIFT_TOKEN
oc project rockstar
oc delete -f kubernetes/
oc create -f kubernetes/

POD_ID=$(oc get pods --selector name=mysql --namespace=rockstar -o json | jq -r '.items[].metadata.name')
oc cp src/main/resources/schema.sql $POD_ID:/tmp/schema.sql --namespace=rockstar
oc exec $POD_ID --namespace=rockstar -- bash -c "mysql --user=rockstar --password=rockstar123 --database=rockstardb -f /tmp/schema.sql"