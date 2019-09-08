### gate 小结


除了 zuul 以外，也有 gateway，另外一个网关，这儿主要是对比 zuul 和 geteway 的区别。这两的功能是完全一样的，只是在细节上有些区别...

gateway 解决了 zuul 的什么东西吗？

核心流程：

![image](/docs/images/gateway_core.png)

多了一个 predicate 判定器，内置路由也有许多。

Zuul 1 基于 servlet 2.5（与 3.x 一起使用）构建，使用阻塞 API，它不支持任何长期连接，如 websockets。
GateWay 基于 Spring Framework 5，Project Reactor 和 Spring Boot 2 构建，使用非阻塞 API。支持 Websockets。与 Spring 紧密集成，所以开发人员体验更好（未来 Gateway 成为 Spring cloud 网关主力）

嗯，以后 netfix 这一套会被逐渐的淘汰，只进行 bug 修复，而不再增加新功能咯。不过，设计思路是还会保留的~具体应用就看代码示例咯~