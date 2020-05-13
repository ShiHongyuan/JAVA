package designPattern.adapter.proxyMethod;

/**
 * 委托被适配类的方式：
 * 目标类：适配器持有被适配类的实例，就可以继承目标类了，所以目标类可以是个抽象类
 */
public abstract class Print {
    public abstract void printWeak();
    public abstract void printStrong();
}

