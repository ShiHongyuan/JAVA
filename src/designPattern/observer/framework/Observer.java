package designPattern.observer.framework;

/**
 * 观察者模式：观察者们的抽象父类接口
 */
public interface Observer {
    public abstract void update(NumberGenerator generator);
}
