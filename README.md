# atm

#### 介绍
OOAD课程综合性实验示例项目
本项目不完整，为脚手架项目。包含界面类、控制器类、硬件模拟类(含接口)。
其他领域类由各组自行设计完成。

本程序由SCAU邓老师设计，仅供内部课程教学使用，请勿上传git，对外传播。

#### 软件架构
程序分为4个包。

1.view

界面类包。界面类使用Swing实现。含1个Frame，5个Panel
5个Panel分为别欢迎(welcome)、输入密码(login)、交易服务菜单(service)、取款(withdraw)、打印询问(print)
为简化设计，5个Panel均继承AbstractDisplayPanel。

2.physical

硬件模拟包。硬件根据特点选用Swing组件进行模拟。
读卡器使用TextField模拟，钞箱使用Button模拟，打印机使用TextArea模拟

3.remote

远程服务包。ATM实际为客户端系统，需要远程账户交易系统作为后台。
本程序简化直接使用了本地类。

4.domain

领域包(业务逻辑包)，即OOAD课程重点关注层。
目前设计了一个控制器类ATM和3个硬件接口。
已有类需要同学自行修改完善。该包的其他类全部交由各组自行设计。
本项目设计要求所有界面类，只允许调用Atm对象，不可调用自行设计的其他对象。

#### 使用说明

1.程序启动

运行view包App类即可。

2.测试查看界面效果

程序启动默认加载欢迎面板。查看其他界面效果，去除App类中的注释即可查看

3.已有类操作

目前已有类操作名称、返回类型、参数需要各组自行设计。方法实现由各组自行完成。

4.设计修改说明

本项目部分代码仅为了方便界面演示，实际代码并非如此，各组需要自行设计。

#### 程序扩展
针对有兴趣和开发能力的小组，可尝试做以下扩展：

1.服务器程序

将远程服务单独设计服务程序，搭建C/S架构。通讯可以使用socket、web service soap或restful Api接口等。

2.消息队列 

有一定开发能力的小组也可以加入消息队列，常用框架有ActiveMQ、RabbitMQ、Kafka等。

3.Session安全

ATM系统设计金钱安全非常重要。为了更形象，推荐实现为session加上定时器(每步骤秒倒数)，限时未操作关闭session、退卡。可考虑加入其他安全机制。
