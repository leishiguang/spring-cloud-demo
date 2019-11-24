## dockers network
#docker network create mysql-official
#docker network create elasticsearch
#docker network create redis
#docker network create rabbitmq
#docker network create sentinel-dashboard
#docker network create nacos
docker network create one-window

## mysql
mkdir -p /root/mianyang/infrastructure/mysql/data
mkdir -p /root/mianyang/infrastructure/mysql/config

## elasticsearch-docker
mkdir -p /root/mianyang/infrastructure/elasticsearch/data
chmod 777 /root/mianyang/infrastructure/elasticsearch/data
mkdir -p /root/mianyang/infrastructure/elasticsearch/config
cp /root/docker/elasticsearch-docker/config/elasticsearch.yml /root/mianyang/infrastructure/elasticsearch/config/elasticsearch.yml

#    mysql_data: /root/mianyang/infrastructure/mysql/data
#    mysql_config: /root/mianyang/infrastructure/mysql/config
#    nacos_logs: /root/mianyang/logs/nacos/logs