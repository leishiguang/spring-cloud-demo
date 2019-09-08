### ribbon 小结

前面说到，eureka 中，当服务提供方有多个的时候，要按什么样的规则去调用呢？

好比 nginx 可以对请求进行分流。当请求已经进入服务端之后，该服务端又要作为服务的调用方，去调用其它服务。这时候，要怎么做负载均衡呢？

这就是 ribbon 为我们做的事情，客户端负载均衡。

如何集成呢？这儿先看看如何在脱离 eureka 的情况下，进行 ribbon 负载均衡...

1. 引入 spring-cloud-starter-netflix-ribbon，之后开始配置...
2. （注意这儿没有与 eureka 集成）改造配置，声明可负载均衡的服务列表，声明 @RibbonClients 注解，声明 @LoadBlance 注解，调用时使用 LoadBanlancerClient，具体的配置看之后的代码示例咯...
3. 通过 RibbonLoadBlanceClient 的 choose 方法获取到服务实例
4. 通过服务实例得到的 ip + port 去调用服务...

那么，这里涉及到的 ribbon 核心知识：

1. ribbon的结构和初始化过程？
2. 如何发现有哪些服务实例？
3. 怎么实现负载均衡策略？
4. 容错机制有吗？

源码解析的部分靠后，这儿先简单的概括一些：

![image](/docs/images/ribbon_process_flow.png)

- RibbonAutoConfiguration：自动装配
- LoadBlancerClient：定义了各种方法，如 URL 获取，服务实例选择等。默认是 RibbonLoadBalancerClient
- SpringClientFactory：内部包含了各种获取对象，如 loadbalancer、instance 等方法

ribbon 中，如果要自定义策略，实现 IRule 接口即可，官方提供了这么些负载均衡策略：

![image](/docs/images/ribbon_custom_balance.png)

嗯，Ribbon 与 RestTemplate，前面介绍时，使用的是 LoadBanlancerClient，实际上，Ribbon 可以与 RestTemplate 很好的结合：

```
@Bean
@LoadBalanced
public RestTemplate template(){ return new RestTemplte() }
```
这样，就可以和 eureka 一样，通过服务名进行调用了。
详细源码解析，下回分解 :D
