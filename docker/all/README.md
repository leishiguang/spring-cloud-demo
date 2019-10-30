## 基础服务的部署顺序及注意事项

前置说明：
- 待稳定之后，可提供全部组件的 docker-compose.yml 合集；
- 待项目运行稳定后，可增加集群方式的部署，保证服务高可用；
- 目前在执行这些步骤之前，需要先执行 bin/init.sh 初始化基础环境；

1、mysql

- 版本号：5.7.28
- 运行模式：单机模式 + 数据存储
- 数据文件的存储位置：/home/mysql/5.7/official/data
- 配置文件的存储位置：/home/mysql/5.7/official/config/
- root默认密码：eVoATqQM1VL4ysos
- 默认端口号：3306
- 部署方式，`docker-compose -f mysql-docker/5.7/official/example/docker-compose.yaml up -d`

2、redis

- 版本号：5.0.6
- 运行模式：单机模式、内存、不进行持久化
- 默认连接密码：redispassword
- 默认端口号：6379
- 部署方式 `docker-compose -f redis-docker/docker-compose.yaml up -d`

3、rabbitmq

- 版本号：3.8
- 运行模式：单机模式 + management
- 默认连接用户：rabbitmq
- 默认连接密码：rabbitmqpassword
- 开放端口：5672、15672
- 部署方式 `docker-compose - f rabbitmq-docker/docker-compose.yaml up -d`


4、elasticsearch

- 版本号：6.4.0
- 运行模式：单机模式 + 数据存储
- 数据文件的存储位置：/home/elasticsearch/data
- 配置文件的存储位置：/home/elasticsearch/config/elasticsearch.yml
- 开放端口：9200、9800(客户端)
- 部署方式 `docker-compose -f elasticsearch-docker/docker-compose.yaml up -d`

5、nacos

- 版本号：1.1.3
- 运行模式：单机模式 + 单机 mysql + grafana + prometheus
- 注意修改数据库连接参数
- 开放端口：8848，3000，9090
- 部署方式 `docker-compose -f nacos-docker/example/standalone-mysql-mianyang.yaml up -d`

6、sentinel-dashboard

- 版本号：
- 运行模式：单机模式 + nacos
- 在 docker-compose 文件的 java_opts 中配置账号密码
- 开放端口：8718，8719
- 部署方式 `docker-compose -f sentinel-dashboard-docker/docker-compose.yml up -d`

7、zipkin

- 版本号：2.18
- 运行模式：单机模式 + rabbitMQ + elasticsearch
- 开放端口：9411
- 部署方式 `docker-compose -f zipkin-docker/docker-compose.yaml up -d`

## 其它

- 关于容器之间的互相访问，如果是再同一个宿主里面，只要在同一个网络中，直接使用容器名就可以了。这儿也建立了多个dockers network，详见：bin/init.sh
- 全部部署完毕之后，可以使用命令 docker stats 查看各个容器的资源占用情况；

## 一些注意的坑：

- 在部署之前，要创建网络；
- 在部署之前，要创建数据卷；
- mysql 第一次部署，要创建 nacos 数据库；
- 配置文件中的host，设置为了本地onewindow，要在host中添加docker的网卡地址