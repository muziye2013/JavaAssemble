观察者模式还有很多其他的称谓，如发布-订阅(Publish/Subscribe)模式、源-监听器(Source/Listener)模式或从属者(Dependents)模式。观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。如图所示，当这个主题对象在状态上发生变化时，会通知所有观察者对象，使它们能够自动更新自己。

![设计者模型示例图](https://upload-images.jianshu.io/upload_images/8926909-a639738803128a4a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

观察者模式所抽象出来的角色有：
- **抽象主题(Subject)角色**：即被观察者（Observable）的角色，主题的抽象类，抽象主题角色把所有观察者对象的引用保存在一个集合里，每个主题都可以有任意数量的观察者，抽象主题提供一个接口，可以增加和删除观察者对象。

- **具体主题(ConcreteSubject)角色**：即具体被观察者（ConcreteObservable），此角色将有关状态存入具体观察者对象，在具体主题的内部状态发生改变时，给所有注册过的观察者发出通知。

- **抽象观察者(Observer)角色**：此角色是观察者的接口类，它定义了一个更新接口，在得到主题的通知时更新自己。

- **具体观察者(ConcreteObserver)角色**：该主题实现抽象观察者角色所定义的更新接口，以便在主题的状态发生变化时更新自身的状态。

针对观察者模式，Java API和Spring分别提供了各自的实现，对比来看，被观察者在JDK中为Observable而Spring中为ApplicationEvent，观察者在JDK中为Observer而Spring中则为ApplicationListener。首先来看JDK中对于观察者模式的实现

###  一、JDK中的观察者模式
在Java语言的java.util包下，提供了一个Observable类以及一个Observer接口，构成Java语言对观察者模式的支持。JDK中对于观察者模式的实现并不复杂，通过阅读源码也能入手，类图设计如下：

![JDK的观察者](https://upload-images.jianshu.io/upload_images/8926909-bdc8a48006be150a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

通过实战案例来展示一下使用原生JDK对观察者模式的使用：
#### 1.实现观察者对象
具体的观察者需要实现一个Observable接口类，同时必须实现update方法（获取更新消息）
```java
public class NotifyReceiver implements Observer {

    private String name;

    public NotifyReceiver(String name){
        this.name = name;
    }

    @Override
    public void update(Observable observable, Object notify) {
        System.out.println("观察者["+name+"]已监测到来自["+observable.toString()+"]的更新："+notify);
    }
}
```
#### 2.被观察者对象NotifySender 
需要实现一个接口类Observable，需要写一个消息通知方法（publishnewInfo）,标志对象的状态变化发生，同时发送该变化的通知。
```
public class NotifySender extends Observable {

    private String name;

    public NotifySender(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }

    /**
     * @param notify 通知内容
     */
    public void sendNotify(String notify){
        //标识这个Observable对象已经改变了，更具体来将就是把Observable中属性changed置为true.
        setChanged();
        //在通知所有观察者之前，需要判断Observable中属性changed是否为true，如若不为则不会发出通知。
        notifyObservers(notify);
    }
}
```
#### 3.测试主类
在main方法中，只要用户主动触发被观察者的变更，那么相应的通知就会即刻送达到观察者那里。
```
public class ObserveTest {

    public static void main(String[] args) {

        //定义被观察者
        NotifySender sender = new NotifySender("Sender");

        //定义三个观察者
        NotifyReceiver receiver1 = new NotifyReceiver("NO.1");
        NotifyReceiver receiver2 = new NotifyReceiver("NO.2");
        NotifyReceiver receiver3 = new NotifyReceiver("NO.3");

        //观察者注册关联
        sender.addObserver(receiver1);
        sender.addObserver(receiver2);
        sender.addObserver(receiver3);

        System.out.println("已成功注册关联，receiver可以接收到sender的变更推送！");

        Scanner scanner = new Scanner(System.in);
        //循环监听并推送
        while(true){
            String notify = scanner.nextLine();
            sender.sendNotify(notify);
        }
    }
}
```

### 二、Spring中观察者模式
spring的观察者模式，更场景化一点的说法就是事件机制，从java的事件机制拓展而来，其类图结构如下：

![Spring事件监听结构](https://upload-images.jianshu.io/upload_images/8926909-12e3056fc7e91c77.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

核心三大角色：
-  **ApplicationEvent事件**，是所有事件对象的父类。ApplicationEvent继承自jdk的EventObject,所有的事件都需要继承ApplicationEvent，并且通过source得到事件源。
- **ApplicationListener事件监听器**，也就是观察者。继承自jdk的EventListener，该类中只有一个方法onApplicationEvent。当监听的事件发生后该方法会被执行。
- **ApplicationContext**是Spring中的核心容器，在事件监听中ApplicationContext可以作为事件的发布者，也就是事件源。因为ApplicationContext继承自ApplicationEventPublisher。在ApplicationEventPublisher中定义了事件发布的方法。

捋起来眼花缭乱，按照真实案例show code就能依葫芦写出自己所需要的监听事件，以订单下单为例，如果用户下单成功，需要发送短信、邮件以及IM消息，我们可能会按照如下逻辑来写：
```
    /**
     * 下单成功处理事件
     * @param order
     */
    public void placeOrder(Order order) {
        //1.发送短信
        sendShortMessage(order,user);
        //2.发送IM消息
        sendIMessage(order,user);
        //3.发送邮件通知
        sendEmail(order,user);
    }
```
如果需要增加一个发送通知，则需要在如上代码中继续添加代码，导致处理的链路越来越长，且还会因为同步导致返回给用户的响应越来越长，这个地方就适合采用发布-订阅模式来处理，把下单成功当成一个事件，当事件完成，就触发对这个事件感兴趣的订阅者来处理消息，处理的逻辑很简单，只需要分为三步来做：

#### 1.自定义事件
自定义事件只需要继承ApplicationEvent类，而事件中需要传递的数据对象，则可以设置为私有变量成员，方便发布者去set进去（通过构造方法），而订阅者get出来，我们这里的订单事件传播的就是订单对象Order。
```
@Data
@Builder(toBuilder = true)
public class Order {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 订单时间
     */
    private long orderTime;

}

/**
 * @author Tison
 * @date 2019-08-03
 * @description 订单事件
 */
public class OrderEvent extends ApplicationEvent {

    private Order order;

    public OrderEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder(){
        return this.order;
    }

}
```
#### 2.定义监听者
监听者可以直接加上`@Component`交给Spring托管，而具体的监听处理方法则需要加上`@EventListener`注解，方法的输入参数定义为监听者需要监听的事件（OrderEvent），这样就完成了监听者对具体事件的注册绑定，方法里就可以写用户自己的处理逻辑了。
```
/**
 * @author Tison
 * @date 2019-08-03
 * @description 提供短信服务的监听器
 */
@Component
@Slf4j
public class ShortMessageListener {

    /**
     * 注册监听：处理订单事件
     * @param orderEvent
     */
    @EventListener
    @Async
    public void handlerOrderEvt(OrderEvent orderEvent){
        log.info("sms listener begin handle order:{}", orderEvent.getOrder());
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            log.error("sms handlerOrderEvt e:", e);
        }
        log.info("sms listener finished handle orderId:{}", orderEvent.getOrder().getOrderId());
    }

    /**
     * 处理其他事件如仓储、支付、物流都可以继续拓展
     * 如handleWarehouseEvt、handleWarehouseEvt等，只需要建立好事件对象，并注册监听
     */
}
```
可以看到我们这里是一个提供短信服务的监听器，在这个短信服务里面，如果需要监听别的事件则只需要顺序往下顺序加处理逻辑，可扩展性就非常好。订单完成除了走短信服务，还可以走邮件服务，那么依葫芦画瓢来提供一个邮件监听器：
```
/**
 * @author Tison
 * @date 2019-08-03
 * @description 提供E-mail服务的监听器
 */
@Component
@Slf4j
public class EMailListener {

    /**
     * 注册监听：处理订单事件
     * @param orderEvent
     */
    @EventListener
    @Async
    public void handlerOrderEvt(OrderEvent orderEvent){
        log.info("email-listener begin handle order:{}", orderEvent.getOrder());
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            log.error("email handlerOrderEvt e:", e);
        }
        log.info("email-listener finished handle orderId:{}", orderEvent.getOrder().getOrderId());
    }
}
```
#### 3.发布事件
可以采用`ApplicationContext`上下文对象来进行发布，因为`ApplicationContext`继承自`ApplicationEventPublisher`，在需要触发事件发布的地方执行`publishEvent()`方法。比如在下单的正常逻辑走完了以后，加一个发布方法就可以完成了发布-订阅逻辑。
```java
/**
 * @author Tison
 * @date 2019-08-03
 * @description 订单服务类
 */
@Service
@Slf4j
public class OrderService implements IOrderService {

    @Resource
    private ApplicationContext context;

    /**
     * 下单请求
     * @param order
     */
    @Override
    public void placeOrder(Order order) {
        //1.模拟订单处理
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("ordering finished, begin send message");

        //2.处理完毕，发布订单事件
        OrderEvent orderEvent = new OrderEvent(this, order);
        context.publishEvent(orderEvent);
    }
}

```
每当下单完成以后，任何对该事件感兴趣的监听器都会进入到各自的handle()方法中。

### 总结
抽象的来讲：对一个对象状态的更新，需要其他对象同步更新，而且其他对象的数量动态可变；对象仅需要将自己的更新通知给其他对象而不需要知道其他对象的细节。大家可以根据以上两点作为基本准则，某个场景是否满足观察者模式。具体应用场景就有很多了，比如文中的事件机制、公众号订阅，tomcat源码中也有很多地方用到了。

JDK事件的实现是基于观察者模式，而spring事件又是在jdk事件的基础上进行了拓展。而本文的所有代码都已经上传至github库中，**本文代码地址，请戳[JavaAssemble-Obesever](https://github.com/tisonkong/JavaAssemble/tree/master/designPatterns/src/main/java/com/tison/kong/Observer)**。

>source: [设计模式之观察者模式，事件机制的底层原理]([https://www.cnblogs.com/youzhibing/p/9593788.html](https://www.cnblogs.com/youzhibing/p/9593788.html)
)