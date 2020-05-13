package designPattern.visitor;

/**
 * 访问者器模式：很多个具体处理类（访问者类）的共同继承的抽象父类
 */
public abstract class Visitor {
    // 访问者访问不同类型的被访问者元素的不同处理方法
    public abstract void visit(File file);
    public abstract void visit(Directory directory);
}
