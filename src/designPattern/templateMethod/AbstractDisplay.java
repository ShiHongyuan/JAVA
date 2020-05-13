package designPattern.templateMethod;

/**
 * 模板方法的抽象类
 */
public abstract class AbstractDisplay {
    public abstract void open();
    public abstract void print();
    public abstract void close();
    // display定义了模板方法的处理流程，不管子类如何实现，处理流程不会变
    public final void display() {
        open();
        for (int i = 0; i < 5; i++) {
            print();
        }
        close();
    };
}
