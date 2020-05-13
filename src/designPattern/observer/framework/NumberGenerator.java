package designPattern.observer.framework;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 观察者模式：被观察者对象的抽象父类
 */
public abstract class NumberGenerator {
    // 给被观察者注册观察者们
    private ArrayList observers = new ArrayList();
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void deleteObserver(Observer observer){
        observers.remove(observer);
    }


    public void notifyObserver() {
        Iterator it = observers.iterator();
        while (it.hasNext()) {
            Observer o = (Observer) it.next();
            o.update(this);
        }
    }

    // 被观察者开启观察模式
    public abstract void excute();

    public abstract int getNumber();

}

