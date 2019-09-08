### config 小结


微服务中，由很多个服务构成，我们的各个服务通过可插拔方式去够成完整的生态：

1. 微服务出现问题的时候，可以使用链路追踪系统
2. 事件通知的时候呢，有 stream

那么，如果微服务多起来了，要怎么做配置呢？如果已经在线上发布了，却发现配置错误了怎么办？为了解决这些问题，spring cloud 提供了 config 分布式配置中心。

![image](/docs/images/config_process_flow.png)

最终的结果是，可以通过 configServer 读取配置中心。如何集成呢？

1. 服务端应用引入依赖：spring-cloud-config-server
2. 创建配置文件，比如存储到 git 仓库
3. 修改 config server 中关于 git 仓库的配置
4. 启动 config Server
5. 客户端应用中增加依赖：spring-cloud-config-client
6. 修改应用程序中关于 config server 的配置，比如用 bootstrap.yml 替代 application.yml

除了简单的配置以外，config 还为我们提供了：

1. 访问控制，防止无权限人员查看到配置内容；引入 spring-boot-starter-security，为 config server 服务的访问设置用户名和密码校验机制；
2. 配置内容加密，防止敏感信息被配置文件的修改人员看到；config server 提供 /encrypt 和 /decrypt 两个接口来实现密文生成和解密的功能。
3. 密钥配置，加解密所用的密钥，对称加密的 key 配置：encrypt.key=change，非对称加密通过 encrypt.keyStore.* 进行相关配置；
4. 远程覆盖，客户端个性化配置。默认情况下，客户端自己的配置不能覆盖配置中心的配置，可以通过在远程配置文件中进行开启。

具体使用呢参考代码示例。