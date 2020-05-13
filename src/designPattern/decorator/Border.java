package designPattern.decorator;

/**
 * 装饰器模式：继承一致性抽象父类，装饰器容器的抽象父类
 */
public abstract class Border extends Display {
    protected Display display;
    protected Border(Display display) {
        this.display = display;
    }
}
