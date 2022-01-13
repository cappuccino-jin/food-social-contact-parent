# food-social-contact-parent
redis-study
# 1、项目初始化内容
初始化项目，搭建基于Spring Cloud的微服务项目架构。采用Eureka作为注册中心，Gateway作为网关，启动顺序RegistryApplication -> 网关 GatewayApplication -> DinersApplication
```
food-social-contact-parent    ----- 项目名称
    ├─ms-diners               ----- 食客微服务
    │  └─src
    │      └─main
    │          ├─java
    │          │  └─com        ----- 代码目录
    │          │      └─imooc
    │          │          └─diners
    │          │              └─controller
    │          └─resources      ----- 资源目录
    ├─ms-gateway                ----- 网关微服务
    │  └─src
    │      └─main
    │          ├─java
    │          │  └─com
    │          │      └─imooc
    │          │          └─gateway
    │          └─resources
    └─ms-registry               ----- 注册中心微服务
        └─src
            └─main
                ├─java
                │  └─com
                │      └─imooc
                │          └─registry
                └─resources
```
