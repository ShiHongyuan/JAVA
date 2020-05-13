package designPattern.visitor;

/**
 * 访问者模式：具有访问者模式的被处理类（被访问类）的共同继承的接口
 */
public interface Element {
    // 传入的就是任意继承了Visitor的具体访问者实例，用这个访问者实例来访问被访问者类
    public abstract void accept(Visitor v);
}
