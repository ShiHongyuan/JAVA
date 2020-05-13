package designPattern.observer;

import designPattern.observer.framework.NumberGenerator;

import java.util.Random;

/**
 * 观察者模式：被观察者对象的具体实现类
 */
public class RandomNumberGenerator extends NumberGenerator {
    private Random random = new Random();
    private int number;

    // 被观察者开启观察模式
    @Override
    public void excute() {
        for (int i = 0; i < 20; i++) {
            // number 改变了就通知观察者
            number = random.nextInt(20);
            notifyObserver();
        }
    }

    @Override
    public int getNumber() {
        return number;
    }
}
