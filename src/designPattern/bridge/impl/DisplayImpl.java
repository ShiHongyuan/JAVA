package designPattern.bridge.impl;

/**
 * 桥梁模式：被操作的具体实现类的统一抽象父类，属于实现层次。
 */
public abstract class DisplayImpl {
    public abstract void rawOpen();
    public abstract void rawPrint();
    public abstract void rawClose();
}
