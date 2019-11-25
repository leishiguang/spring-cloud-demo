基础服务部署步骤：

1、执行 init.sh
2、执行 docker-compose-graylog.xml up，部署完 graylog
3、到 graylog 的 web 页面添加 input；
4、执行 docker-compose-monitor.xml up，部署完 influxdb
5、到 influxdb 的 cli 增加数据库和用户；
6、执行 docker-compose-infrastructure.xml up，部署完基础应用
7、进入 mysql-master 数据库，初始化数据库；

应用服务部署步骤：

1、编译Dockerfile，生成应用镜像
2、执行对应的 docker-compose.xml up，将自动更新容器为最新

如，先执行 bootstrap 

备忘：
```
docker-compose -f docker-compose-bootstrap.yml down
docker-compose -f docker-compose-bootstrap.yml up
docker-compose -f docker-compose-bootstrap.yml up -d

docker-compose -f docker-compose-application.yml down 
docker-compose -f docker-compose-application.yml up
docker-compose -f docker-compose-application.yml up -d

# 快速启动（在稳定运行后可以如此重试）
docker-compose -f docker-compose-bootstrap.yml up -d && \
docker-compose -f docker-compose-application.yml up -d 


```

其它说明：

1、第一次部署时，建议直接使用 up 命令，并且不采用 with-gaylog；
2、正式运行时，建议采用 with-gaylog，并添加 -d 参数，后台静默运行；