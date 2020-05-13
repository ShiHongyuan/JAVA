package designPattern.observer;

import designPattern.observer.framework.NumberGenerator;
import designPattern.observer.framework.Observer;

/**
 * 观察者模式：将被观察者对象的状态变化通知给所有注册到被观察者对象上的观察者
 *          被观察者的观察者们列表保存的观察者类型是Observer接口类型，被观察者不需要知道具体的观察者是谁，注册的观察者的数量也没有限制，新增的观察者可以添加进去，不需要的观察者也可以从列表中删除
 *          观察者接收的消息类型是被观察者的抽象父类，观察者也不需要知道具体的被观察者是谁，也可以通过add和delete改变与被观察对象的绑定，
 *          所以，把观察者和被观察者分离开了，它们都是独立的可复用的组件。
 */
public class Main {
    public static void main (String[] args) {
        NumberGenerator generator = new RandomNumberGenerator();
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();


        // 给被观察者对象添加观察者们
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        // 被观察者开启观察模式
        generator.excute();


        // 新增被观察者
        NumberGenerator generator1 = new IncrementalNumberGenerator(10, 50, 5);
        generator1.addObserver(observer1);
        generator1.addObserver(observer2);
        generator1.excute();



    }
}
