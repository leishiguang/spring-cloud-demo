## eureka-server

用于启动一个 eureka server，供服务中的各个 eureka client 进行连接（注册、订阅服务）

## 启动方式

1. 在 application.yml 中配置对应的端口，如 10001；
2. 启动 springBoot 类，控制台输出 Completed 即可；

注意在启动过程中，可能会出现端口占用的错误。这时，需要在配置文件中调整端口。
启动之后，访问 http://localhost:10001/ 即可看到 eureka server 的 UI 界面。

