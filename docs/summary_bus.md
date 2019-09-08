### bus 小结

在 stream 中，将数据通过 mq 进行传播。而在 spring 运行状态里面，实际上也是有一个事件机制的。

在 spring 容器中发布了一个事件，那在任何一个地方，都可以使用 @EventListener 监听到这个事件。

![image](/docs/images/bus_listen.png)

那么，把 stream 和 事件机制融合在一起，是不是就可以把事件散步到系统中的每一个实例呢？

如何集成？

1. 继承 RemoteApplicationEvent
2. 通过 @RemoteApplicationEventScan 注解定义新事件类型所在的包
3. 定义相应的监听器
4. 通过 applicationContext.publisEvent 发布事件
5. 检查 MQ 中对应消息通道是否有新消息。
6. 本地监听 SentApplicationEvent 和 AckRemoteApplicationEvent 这两个可用于触发消息发送完毕，ACK 之后的逻辑处理

具体使用呢，还是看代码示例咯~