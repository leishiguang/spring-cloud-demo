### stream 小结

stream 当前还只是处于一个萌芽阶段，也有许多替代方案。它的本质，是利用了 mq 消息推送、订阅的一些。

![image](/docs/images/stream_process_flow.png)

在一些场景中，服务 A 和 服务 B 用 MQ 进行解耦。这两个服务就在不断的消费消息中间件，而反复这么操作，代码难免冗余...

Spring Cloud Stream 是一个用于构建消息驱动的微服务应用程序框架（消息的传递时借助了消息中间件）。具体的实现中，用到了其它基于 Spring 拓展而来的框架：

1. Spring Boot 专注于快速开发的框架；
2. Spring Integration 专注数据集成的框架；

其中，Spring Integration 是基于 Spring 框架实现对 EIP（）企业集成模式的支持，通过消息传递通道与外部系统集成一种多系统数据集成（处理）机制：数据源-读取-处理（过滤，路由，转换，适配，拆分和聚合）-结束（数据终点）

![image](/docs/images/stream_integration.png)

为了方便理解，先分析 Spring Integration 是怎么代替我们去和 MQ 进行通信的。将我们的数据，直接丢到 Spring integration 中去，由它内部的一些机制让我们消费MQ的过程解耦。在整个过程中，还可以方便的加入中段处理。

![image](/docs/images/stream_integration_middle.png)

Stream 针对 Interation 进行了重要改进，是将 Channel 内部的消息容器升级为分布式消息中间件来实现，而非之前的本地容器，之前的 Integration 就变成这样了：

![image](/docs/images/stream_application.png)

我们无需关心怎么和去开启通道，只需要定义好就可以了。


如何集成？

1. 引入 spring-cloud-stream
2. 引入通道载体，比如 Rabbitmq，spring-cloud-stream-binder-rabbit
3. 通过 @Input @Output 定义通道
4. Spring 配置文件中配置通道与中间件的绑定关系
5. @EnableBinding 启用指定通道的配置

具体使用，看示例代码咯~