#!/bin/bash

ACCESS_TOKEN=$(curl --silent -X POST -H 'Accept: application/json' -H 'Content-Type: application/json; charset=UTF-8' -d '{"username":"'"$USER"'", "password":"'"$PASSWORD"'", "client_id":"'"$CLIENT_ID"'", "client_secret":"'"$CLIENT_SECRET"'", "audience":"https://api.rock-star.io/", "grant_type":"password" }' https://rockstar.auth0.com/oauth/token | jq -r .access_token)

# FOLDERS=featured/*
declare -a FOLDERS=(
  "restapi-java-springboot-cassandra"
  "restapi-java-springboot-elasticsearch"
  "restapi-java-springboot-mysql"
  "restapi-java-springboot-mongodb"
)

for product_folder in "${FOLDERS[@]}"
#for product_folder in $FOLDERS
do
  echo "uploading product info"
  RESPONSE_HEADER=$(curl -i -s -X POST $API_ENDPOINT_URL/products --data @$product_folder/product.json --header "Accept: application/json" --header "Content-Type: application/json;charset=UTF-8" --header "Authorization: Bearer $ACCESS_TOKEN")
  LOCATION_HEADER=`echo "$RESPONSE_HEADER" | grep Location`
  LOCATION_HEADER=$(echo "$LOCATION_HEADER" | tr -d '\r')
  PRODUCT_HREF=`echo ${LOCATION_HEADER:9}`

  echo "uploading product media items"
  MEDIA_FILES=$product_folder/mediaitems/*.json
  for media_file in $MEDIA_FILES
  do
    echo "$PRODUCT_HREF/mediaitems"
    MEDIA_ITEMS_RESPONSE_STATUS=$(curl -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $ACCESS_TOKEN" --data @$media_file $PRODUCT_HREF/mediaitems)
  done

  echo "uploading product attributes"
  ATTRIBUTE_FILES=$product_folder/attributes/*.json
  for attribute_file in $ATTRIBUTE_FILES
  do
    echo "$PRODUCT_HREF/attributes"
    curl -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $ACCESS_TOKEN" --data @$attribute_file $PRODUCT_HREF/attributes
  done

  echo "uploading product options"
  OPTION_FILES=$product_folder/options/*/*.json
  for option_file in $OPTION_FILES
  do
    echo "$PRODUCT_HREF/options"
    curl -X POST -H 'Content-Type: application/json' -H "Authorization: Bearer $ACCESS_TOKEN" --data @$option_file $PRODUCT_HREF/options
  done
done
