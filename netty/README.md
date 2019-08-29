近年来，Netty社区的发展如火如荼，无论是大数据领域，还是微服务架构，底层都需要一个高效的分布式通信框架作为基础组件。我所在的项目组IM系统后台，其核心基于Openfire，随着用户量和业务量的增长Openfire的架构已然不适用，首选改造方案便是基于Netty开发自己的长连接管理服务，而我也有幸参与到了Openfire的重构和自研项目中，不仅需要用到Netty而且还要做非常多性能优化的工作，因此无论从现实和未来的角度，掌握和开发Netty的能力都是一个高阶开发者的亮点技能，基于此，我在博客中开启一个Netty专栏进行相关积累和总结。

作为一个开发小白，我在这里将从零开始深入学习Netty，项目的重构过程持续两个月，那么这两个月我的学习和开发重点也在这里，计划学习和总结的大纲如下：
#### 1.网络编程基础
- [计算机网络基础](https://www.jianshu.com/p/a4ff242fdfb6)
- [网络编程之IO模型与Epoll](https://www.jianshu.com/p/21869b8faea7)

#### 2.JDK NIO
- [java进阶之NIO](https://www.jianshu.com/p/f8454c6be204)
- [Reactor模型的Java NIO实现](https://blog.csdn.net/TheLudlows/article/details/81136359)
- [网络编程之IO、NIO和Netty](https://www.jianshu.com/p/6639a37c88fd)

#### 3.Netty入门
- [Netty关键组件Channel、EventLoop 、ChannelFuture](https://www.jianshu.com/p/333cc5975330)
- [Netty关键组件ChannelHandler、ChannelPipeline、Ctx](https://www.jianshu.com/p/2bf0de153a19)
- [Netty关键组件ByteBuf、BootStrap](https://www.jianshu.com/p/1646c4596058)

#### 4.Netty深入
#### 5.Netty实战

此处提前预留一张思维导图，不断更新中：

#### 主要参考：
***
1、TheLudlows.[Netty相关文章目录汇总](https://blog.csdn.net/TheLudlows/article/details/84993154)
2、thinking_fioa.[Netty专栏 ](https://blog.csdn.net/thinking_fioa/column/info/22861)
3、lhrimperial.[Netty整体架构](https://blog.csdn.net/u013857458/article/details/82527722)
4、并发编程网.[Netty系列博客](https://ifeve.com/tag/netty/)