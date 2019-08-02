# Tomcat容器 invalid LOC header 错误排查工具

* 用于排查在Tomcat启动时报ZipException: invalid LOC header (bad signature)错误的问题
* 原理: 利用SkyWalking Agent实现埋点日志输出(实际上只要用javaagent就能实现, 只是利用SW比较简单)
* 错误原因: 工程依赖的一个JAR包有问题无法解析, 我们要想办法找到是哪个JAR包

# 用法

* 编译打包: gradlew shadowJar
* 提取插件: tomcat-bad-jar-fix-plugin/build/libs/tomcat-bad-jar-fix-plugin-1.0-all.jar
* 放入: <Your-Path>/apache-skywalking-apm-bin/agent/plugins/目录下 ([下载](http://skywalking.apache.org/downloads/) 解压 提取agent目录)
* 问题应用添加启动参数: -javaagent:<Your-Path>/apache-skywalking-apm-bin/agent/skywalking-agent.jar -DSW_AGENT_NAME=any
* 查看日志: <Your-Path>/apache-skywalking-apm-bin/agent/logs/skywalking-api.log
* 日志中搜索: `FIX!!! Bad Jar Found`, 程序输出了`JarResourceSet`类的结构和成员变量值, 可以在里面找到JAR包路径
