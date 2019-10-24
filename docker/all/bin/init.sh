## dockers network
docker network create mysql-official
docker network create elasticsearch
docker network create redis
docker network create rabbitmq
docker network create sentinel-dashboard
docker network create nacos

## mysql
mkdir -p /home/mysql/5.7/official/data
mkdir -p /home/mysql/5.7/official/config/

## elasticsearch-docker
mkdir -p /home/elasticsearch/data
chmod 777 /home/elasticsearch/data
mkdir -p /home/elasticsearch/config/
cp ../../elasticsearch-docker/config/elasticsearch.yml /home/elasticsearch/config/elasticsearch.yml

