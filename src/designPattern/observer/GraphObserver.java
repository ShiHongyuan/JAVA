package designPattern.observer;

import designPattern.observer.framework.NumberGenerator;
import designPattern.observer.framework.Observer;

/**
 * 观察者模式：观察者中的其中一个具体实现类
 */
public class GraphObserver implements Observer {
    @Override
    public void update(NumberGenerator generator) {
        System.out.print("GraphObserver:");
        int count = generator.getNumber();
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//
//        }
    }
}
