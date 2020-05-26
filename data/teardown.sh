#!/bin/bash

export FEATURED_CRITERIA=true
export ARCHITECTURE_CRITERIA=restapi
export STATE_CRITERIA=publish

ACCESS_TOKEN=$(curl --silent -X POST -H 'Accept: application/json' -H 'Content-Type: application/json;charset=UTF-8' -d '{"username":"'"$USER"'", "password":"'"$PASSWORD"'", "client_id":"'"$CLIENT_ID"'", "client_secret":"'"$CLIENT_SECRET"'", "audience":"https://api.rock-star.io/", "grant_type":"password" }' https://rockstar.auth0.com/oauth/token | jq -r .access_token)

MEDIA_ITEMS_RESPONSE_JSON=$(curl --silent -X GET -H "Authorization: Bearer $ACCESS_TOKEN" -H "Accept: application/hal+json;charset=UTF-8" -H "Content-Type:application/hal+json;charset=UTF-8" "$API_ENDPOINT_URL/products/search?featured=true&state=${STATE_CRITERIA}&architecture=${ARCHITECTURE_CRITERIA}")
if [ -n "$MEDIA_ITEMS_RESPONSE_JSON" ]; then
  echo "media items found"
  MEDIAITEM_RESOURCE_URLS=$("$MEDIA_ITEMS_RESPONSE_JSON" | jq -r ._embedded.productResourceList[].media_items[]._links.self.href)
  if [ -n "$MEDIAITEM_RESOURCE_URLS" ]; then
    echo "deleting mediaitem resources"
    for current_url in $MEDIAITEM_RESOURCE_URLS
    do
      curl --silent -X DELETE $current_url -H "Authorization: Bearer $ACCESS_TOKEN"
    done
  fi
fi

echo "deleting attribute resources"
ATTRIBUTE_RESOURCE_URLS=$(curl -s -X GET --header "Authorization: Bearer $ACCESS_TOKEN" --header "Accept:application/hal+json" --header "Content-Type:application/hal+json" "$API_ENDPOINT_URL/products/search?featured=${FEATURED_CRITERIA}'&'state=${STATE_CRITERIA}'&'architecture=${ARCHITECTURE_CRITERIA}" | jq -r ._embedded.productResourceList[].attributes[]._links.self.href)
if [ -n "$ATTRIBUTE_RESOURCE_URLS" ]; then
  for current_url in $ATTRIBUTE_RESOURCE_URLS
  do
    curl --silent -X DELETE $current_url -H "Authorization: Bearer $ACCESS_TOKEN"
  done
fi

echo "deleting option resources"
OPTIONITEM_RESOURCE_URLS=$(curl -s -X GET --header "Authorization: Bearer $ACCESS_TOKEN" --header "Accept:application/hal+json" --header "Content-Type:application/hal+json" "$API_ENDPOINT_URL/products/search?featured=${FEATURED_CRITERIA}'&'state=${STATE_CRITERIA}'&'architecture=${ARCHITECTURE_CRITERIA}" | jq -r ._embedded.productResourceList[].options[]._links.self.href)
if [ -n "$OPTIONITEM_RESOURCE_URLS" ]; then
for current_url in $OPTIONITEM_RESOURCE_URLS
  do
    curl --silent -X DELETE $current_url -H "Authorization: Bearer $ACCESS_TOKEN"
  done
fi

echo "deleting product resources"
PRODUCT_RESOURCE_URLS=$(curl -X GET --header "Accept:application/hal+json" --header "Content-Type:application/json;" $API_ENDPOINT_URL/products/search?featured=${FEATURED_CRITERIA}'&'state=${STATE_CRITERIA}'&'architecture=${ARCHITECTURE_CRITERIA} | jq -r ._embedded.productResourceList[]._links.self.href)
if [ -n "$PRODUCT_RESOURCE_URLS" ]; then
  for current_url in $PRODUCT_RESOURCE_URLS
  do
    curl --silent -X DELETE $current_url -H "Authorization: Bearer $ACCESS_TOKEN"
  done
fi
