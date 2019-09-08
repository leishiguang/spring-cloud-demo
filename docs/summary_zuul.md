### zuul 小结


后端的服务提供者越来越多了，各个服务者之间调用倒是挺开心的。但是，对于外部来说呢？

- 外部客户端种类多，可能就没有集成服务注册发现的机制，无法发现服务；
- 出于安全和 ip 资源考虑，服务提供方的地址并不会直接对公网进行开放；
- 安全认证方面，是不是每个服务都需要实现权限认证呢...

zuul 网关应运而生，专门用以解决类似于上述典型问题。所有的外部请求先通过这个微服务网关，客户端只和网关进行交互，然后由网关进行各个微服务的调用。

这个过程就有点儿类似于 nginx 了，而它的特性有：

1. 统一接入：为各种应用提供统一接入服务，高性能，高并发，高可靠，负载均衡，容灾切换；
2. 协议适配：适配前端系统（http,websocket），后端业务系统 RPC，长短连接都支持。依据前端请求路由到相应的服务执行，并返回结果；
3. 流量管控：限流、熔断、降级，路由一切容灾切换的应用；
4. 安全防护：IP 黑名单，URL 黑名单，权限校验，防刷，恶意攻击等...

如果把整个微服务当作我们的家，网关就相当于是大门。

这儿容灾切换，指的是异地存在多个服务器，比如华南区、华北区，这种如果有一个区域宕机了，将自动把服务切换过去。尽量减少一个区域崩了之后，就完全无法用了。

zuul 本质上就是一个 web 应用，是一个 API Gateway 服务器。zuul 可以提供动态路由，监控，弹性，安全等边缘服务的框架。相当于是服务和所有 web 请求的大门。

zuul 能做什么？

1. 认证和安全：识别每个需要认证的资源，拒绝不符合要求的请求；
2. 性能监测：追踪并统计数据，提供精确的运行时状态数据；
3. 动态路由：根据需要将请求动态路由（转发）到后端集群；
4. 静态资源处理：直接负载均衡，预先为每种类型的请求分配容量，当请求超过容量时自动丢弃。

如何使用（集成）呢？

1. 引入 spring-cloud-starter-netflix-zuul 相关依赖；
2. 在启动类中添加 @EnableZuulProxy 注解开启 zuul 网关代理功能；或者添加 @EnableZuulServer 开启非代理模式的网关服务。

```
@EnableZuulProxy，实际上也继承了 @EnableZuulServer 的自动装配，扩展了 Server 的功能，主要区别是 Proxy 用动态代理的方式去转发路由。
```

3. 配置，除了很通常的端口、应用名、注册中心外。也有 zuul 独有的，如：

```
zuul.host.connect-timeout-millis: http请求超时时间
zuul.host.socket-timeout-millis：socket请求超时时间
zuul.host.max-total-connections：最大连接数（连接池大小）
zuul.host.max-per-route-connections：每个 host 的最大连接数
zuul.ribbon-isolation-strategy: 隔离模式
zuul.semaphore.max-semaphores：最大并发数
zuul.routes... 路由配置
```

那么，zuul 的核心知识：

1. zuul 的初始化和执行流程
2. zuul 的路由定位流程
3. .http 的执行流程
4. 拓展应用：用户登陆，权限校验

![image](/docs/images/zuul_process_flow.png)

三大类的 filter：

1. 前置 filters，
2. routing filters，用来执行请求，比如用 ribbon 封装，就是有一个 ribbon 的 routing filters。
3. post filters，在请求之后执行，用于返回数据。
4. 通常，如果有任意 filter 出错了，都会进到 error filter。

zuul 的初始化过程：

![image](/docs/images/zuul_init_flow.png)

zuul 的 filter 执行流程：

![image](/docs/images/zuul_filter_process_flow.png)

zuul 的路由定位流程：


![image](/docs/images/zuul_route.png)

zuul 的请求流程：

![image](/docs/images/zuul_request_flow.png)

具体的源代码分析，下回分解咯~
如果要扩展 zuul 功能，比如登陆验证，就看实践代码...