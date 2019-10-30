# sentinel

[https://github.com/alibaba/Sentinel](https://github.com/alibaba/Sentinel)

## 概要

Sentinel 的使用可以分为两个部分:

- 核心库（Java 客户端）：不依赖任何框架/库，能够运行于 Java 7 及以上的版本的运行时环境，同时对 Dubbo / Spring Cloud 等框架也有较好的支持（见 主流框架适配）。
- 控制台（Dashboard）：Dashboard主要负责管理推送规则；监控；管理机器信息等。

参考：[新手指南](https://github.com/alibaba/Sentinel/wiki/%E6%96%B0%E6%89%8B%E6%8C%87%E5%8D%97#%E5%85%AC%E7%BD%91-demo)


## 部署

这儿主要是部署 Dashboard 控制台，具体的核心库。

### 获取 sentinel-dashboard.jar

可以使用官方的，也可以适当修改，以适配 nacos。
[官网](https://github.com/alibaba/Sentinel/tree/master/sentinel-dashboard)



参考：
- [Sentinel-Dashboard](https://github.com/alibaba/Sentinel/wiki/Dashboard)
- [sentinel-dashboard安装、运行(docker)](https://www.cnblogs.com/wintersoft/p/11235192.html)
- [Sentinel Dashboard 中修改规则同步到 Nacos](https://blog.csdn.net/j3T9Z7H/article/details/90439701)
- [Sentinel使用Nacos存储规则](http://blog.didispace.com/spring-cloud-alibaba-sentinel-2-1/)

### docker-compose

步骤：
- 生成 docker 镜像
- 从 docker 镜像扩展成 docker-compose

可以通过-Dcsp.sentinel.log.dir=/opt/sentinel-dashboard/logs 设置日志目录

一些配置的说明：
```
-Dserver.port=8718 控制台端口，sentinel控制台是一个spring boot程序。客户端配置文件需要填对应的配置，如：spring.cloud.sentinel.transport.dashboard=192.168.1.102:8718
-Dcsp.sentinel.dashboard.server=localhost:8718 控制台的地址，指定控制台后客户端会自动向该地址发送心跳包。
-Dproject.name=sentinel-dashboard 指定Sentinel控制台程序的名称
-Dcsp.sentinel.api.port=8719 (默认8719) 客户端提供给Dashboard访问或者查看Sentinel的运行访问的参数

注：csp.sentinel.dashboard.server这个配置是用在客户端，这里Sentinel控制台也使用是用于自己监控自己程序的api(sentinel-dashboard是服务端的同时，若对自己进行监控，那么自己也是一个客户端)，否则无法显示控制台的api情况，当然这个也可以根据情况不显示。

注：csp.sentinel.api.port=8719是客户端的端口，需要把客户端设置的端口穿透防火墙，可在控制台的“机器列表”中查看到端口号，这里Sentinel控制台也使用是用于自己程序的api传输，由于是默认端口所以控制台也可以不设置。

注：客户端需向控制台提供端口，配置文件配置，如：spring.cloud.sentinel.transport.port=8720

控制台推送规则的日志在 ：${user.home}/logs/csp/sentinel-dashboard.log 中，
客户端接收规则日志在 ${user.home}/logs/csp/record.log 中
启动配置wiki： https://github.com/alibaba/Sentinel/wiki/启动配置项
spring cloud alibaba配置、整合feign、动态数据源支持 等的wiki：https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Sentinel
``` 