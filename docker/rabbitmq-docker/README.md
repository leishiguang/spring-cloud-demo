# RabbitMQ


Management 是 rabbitMQ 的管理后台；

## Start

快速启动：

```bash
docker run -d -p 5672:5672 \
  -p 15672:15672 \
  --name rabbitmq \
  --privileged=true \
  --restart=always \
  -e RABBITMQ_DEFAULT_USER=guest \
  -e RABBITMQ_DEFAULT_PASS=guest \
  rabbitmq:3.8-management
```