### sleuth 小结

微服务集群里面，会有很复杂的调用链条，如果这里面有一环出现了问题，那要怎么去定位到底是哪个服务出现问题呢？

主要解决问题：收集一个请求经过多个服务处理的过程中，每一个服务处理的具体执行情况。
目前的开源相关实现有：Zipkin、Dapper、HTrace、SkyWalking 等，也有类似 OpenTracing 这样的被大量支持的统一规范。

Spring Cloud Sleuth 为 Spring Cloud 系统实现了分布式跟踪解决方案（主要结合 Zipkin 开展本次实践）

分布式链路追踪的核心架构：

![image](/docs/images/sleuth_architecture.png)

在客户端发起请求时，生成相应的日志，交给统一的日志收集器进行收集，最终在 UI 界面进行查看。

那么，搭建步骤：

1. 下载 zipkin 之后启动；
2. 应用程序中引入spring-cloud-starter-sleuth 和 spring-cloud-starter-zipkin 依赖；
3. 配置，比如 spring-zipkin.baseUrl

其中的一些核心概念有：

![image](/docs/images/sleuth_core.png)

sleuth 和 zipkin 搭配：

![image](/docs/images/sleuth_with_zipkin.png)

Sleuth 采样之后，会发送类似的一个数据格式，包含 traceId，实践，包含其它一系列操作。每个服务请求，到了某个阶段，就会生成数据包传递到 Zipkin 里面。

Sleuth 数据采用链路：

![image](/docs/images/sleuth_trace.png)

当请求进来时，会生成 TraceId，生成 SpanId。到达下一个服务时，TraceId 不变，记录请求的过程。SpanId 不断发生变化，记录每个服务发生的事情。

![image](/docs/images/sleuth_trace_flow.png)

核心思路：以 span 的形式记录一个处理步骤。利用 filter、拦截器、aop 等机制针对具体的操作进行记录。

具体定义 span 的方式，代码示例...