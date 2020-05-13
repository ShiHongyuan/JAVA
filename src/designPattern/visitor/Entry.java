package designPattern.visitor;

import java.util.Iterator;

/**
 * 访问者器模式：这里实现的具体被访问者类的共同属性抽象出抽象父类，不是访问者模式中必须的角色
 */
public abstract class Entry implements Element {
    public abstract String getName();
    public abstract int getSize();


    // 让抽象方法实现为抛出异常，如果子类没有重写 add 方法，则会抛出异常
    public Entry add(Entry entry) throws FileTreatmentException {
        throw new FileTreatmentException();
    }

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }

    public Iterator iterator() throws FileTreatmentException {
        throw new FileTreatmentException();
    }
}
