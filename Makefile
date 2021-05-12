# defaul shell
SHELL = /bin/bash

# Rule "help"
.PHONY: help
.SILENT: help
help:
	echo "Use make [rule]"
	echo "Rules:"
	echo ""
	echo "build-app 		- build application"
	echo "build-image 		- build application and create docker image"
	echo "services-up		- run application on docker"
	echo "services-down	    - stop application on docker"
	echo ""
	echo "check		    - check tools versions"
	echo "help		    - show this message"


build-app:
	./mvnw clean package -DskipTests; \

build-image:
	./mvnw clean package -DskipTests; \
	docker build --force-rm -t person-api-service:latest -f docker/Dockerfile .

services-up:
	docker-compose -f docker/docker-compose.yaml -p person-api up

services-down:
	docker-compose -f docker/docker-compose.yaml -p person-api down

check:
	echo "make version " && make --version && echo
