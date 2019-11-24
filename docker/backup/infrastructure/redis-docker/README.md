# Redis

- [https://github.com/docker-library/redis](https://github.com/docker-library/redis)
- [https://hub.docker.com/_/redis](https://hub.docker.com/_/redis)

## Start

快速启动：

start a redis instance
```bash
docker run -d -p 6379:6379 \
  --name redis \
  --privileged=true \
  --restart=always \
  redis:5.0 \
  --requirepass "redispassword" \
  --appendonly yes
```

start with persistent storage（持久存储）
```bash
docker run -d -p 6379:6379 \
  --name redis \
  --privileged=true \
  --restart=always \
  redis:5.0 redis-server \
  --requirepass "redispassword" \
  --appendonly yes
```

connecting via redis-cli（客户端连接）

```
# 这里需要提前配置一个 network ...
docker run -it --network some-network --rm redis redis-cli -h some-redis

# 或者使用 dockers 的容器互联技术进行网络桥接
docker run -it --rm --link redis:redis redis redis-cli -h redis

# 连接之后，输入命令进行登陆
auth redispassword

# 再次进行键值设置即可
set testkey hello
```

## redis 可视化客户端工具

目前没有比较好用免费的 redis 管理工具：

1. ledis3.0，idea 插件，收费
2. Redis Desktop Manager，从 0.9.4 版本开始收费，可以使用最后一个免费版本：0.9.3
3. 使用github上的一个开源小工具 [RedisStudio](https://github.com/cinience/RedisStudio/releases)

