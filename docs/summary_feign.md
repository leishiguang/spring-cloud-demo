### feign 小结

用 RestTemplte 可能还有些不顺手，那么，是不是可以有类似于普通的 java 接口调用呢？这就是 Feign 的来源了，它让 web 接口调用和普通 java 接口一样便携。

spring cloud 对 Feign 的增强和改造：

1. 将 Spring MVC 的注解与 Feign 客户端的定义进行了结合；
2. 支持使用 Spring Web 中的 HttpMessageConverters 对请求和响应进行编解码工作。

那么，要怎么使用（集成）呢？

1. 引入 spring-cloud-starter-openfeign 依赖；
2. 启动类中添加 @EnableFeignClients 注解；
3. 定义一个接口，加上 @FeignClient 注解，把每个要 http 请求的方法，用接口方法替代；
4. 在需要用 restTemplate 的地方，改用定义好的接口方法；

tips：在定义接口的时候，也可以与 hystrix 集成，添加 fallback 哦，在配置文件中加入 `feign.hystrix.enabled=true` 即可，具体的参见代码实践...


关于 Feign 的使用，接口定义的示例：
```java
@Component
@FeignClient(name="helloserver",fallback = HelloDemoFallback.class)
public interface HelloDemoService {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index();
}
```

使用示例：

```java
@RestController
public class CustomerController {

    @Autowired
    HelloDemoService helloDemoService;


    @GetMapping("index")
    public Object getIndex(){
        return helloDemoService.index();
     }
}
```

那么，核心原理，比如如何初始化？

![image](/docs/images/feign_init.png)

其本质是使用 FeignClientFactoryBean 生成代理对象，并交给 spring 托管...