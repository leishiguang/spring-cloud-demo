#### eureka 小结


嗯，先说说注册中心，假如没有注册中心，如何管理多个服务？我们可能会在客户端提前配置服务端的地址。

但这样，没有注册中心，带来的问题有：

1. 无法监控服务提供方的健康情况，接口服务器可能不固定，可能随时增删机器；
2. 接口调用方无法知晓提供方的具体 ip 和 port 地址，除非手动调整接口调用者的代码；

那么，注册中心的存在意义，就是为了：

1. 服务方当前是什么状态，是否健康？
2. 服务方的地址有没有迁移，最新的是什么？
3. 让修改客户端服务方列表的过程，变成自动进行；

这也是 eureka 的作用，它的执行流程如：

![image](/docs/images/eureka_process_flow.png)

流程说明：

1. 服务提供者启动时，定时向 EurekaServer 注册自己的服务信息（服务名、IP、端口...等等）
2. 服务消费者启动时，后台定时拉取 EurekaServer 中存储的服务信息

集成和配置的一些 tips：

1. 引入 spring-boot-starter-actuator，用以监控 spring boot 的一些端点；
2. 引入 spring-boot-starter-web，可以通过 http 方式访访问监控主页；
3. 引入 spring-cloud-starter-netflix-eureka-server，这样就有了 eureka 核心组件，就可以具有 eureka server 功能，供其它客户端进行接入了；
4. 在启动类添加 @EnableEurkaServer 注解，这时就会自动的读取 EurekaServerMarkerConfiguration.class 配置，帮助我们实例化 eureka 服务；
5. 在配置文件中执行 eureka 的相关配置，这就有许多了，常用比如：

```
spring.application.name，服务名称
server.port，服务端口
eureka.instance.hostname，当前实例名称
eureka.instance.intance-id，实例的id
eureka.client.fetch-registry，是否要拉取 eureka 的服务列表？
eureka.client.register-with-eureka，是否要把自己注册在eureka?
eureka.client.service-url.defalutZone，要选择的注册中心？

eureka.server.wait-time-in-ms-when-sync-empty，服务要同步数据的等待时间
eureka.server.enable-self-reservation，自我保护机制，是不是防止网络颤动时的服务下线，这一步虽然确保了自身的健壮性，但可能会保留已宕机的服务，好在有别的组件弥补了这部分的不足
eureka.server.peer-eureka-nods-update-interval-ms，服务同步时间，可以稍微设置长一些，因为我们不需要它那么快速的去同步

```

defaultZone 是一个区域的概念，可以指定对应的地区，比较适用于较大范围的服务器，这样客户端会优先调用同地区的服务，比如这么一个配置：

```
eureka.client.service-url.defalutZone = http://localhost:10000/eureka
eureka.client.service-url.zone1 = http://localhost:10002/eureka
eureka.client.availability-zones.china
:zone1

这样，在 china 节点，就会优先调用 10002 上注册的服务，如果调用不到，才会调用 10000 上注册的
```

嗯，顺利启动之后，便可以在配置好的服务地址，看到服务信息哦，如：

![image](/docs/images/eureka_index_page.png)

这时，只是 eureka 服务端启动了，如果要进行服务，还需要有：

1. 服务提供方，即 eureka 服务节点
2. 服务调用方，就是客户端，要发起服务的调用

那么，eureka 客户端的集成：

1. 客户端引入 spring-cloud-starter-netfilx-eureka-client，便可以连接已存在的 eureka 服务，进行服务注册或者服务调用；
2. 服务方注意配置 spring-application.name 服务名称
3. 配置 eureka.client.service-url.defaultZone，或是其它的注册中心，把自己提供的服务，注册到 eureka 中去。服务调用方也这么配置，从该 eureka 中拉取服务
4. 在调用时，使用 `http://服务名/` 替代 ip 和 port，如：

```
@Autowired
private RestTemplate restTemplate;

@GetMapping("index")
public Object getIndex(){
    return restTemplate.getForObject("http://HELLOSERVER/",String.class,"");
}
```

那么，eureka 的核心知识有哪些呢？

1. eureka 如何注册服务？
2. 如何发现服务？
3. 服务断开了怎么办？
4. 服务名是怎么变成 ip+port 呢？
5. 自我保护机制，网络颤动怎么办？

![image](/docs/images/eureka_init.png)

hashMap 保存服务信息之后，再拦截对应的服务调用，替换为具体的服务实例~

详细源码解析，下回分解 :D