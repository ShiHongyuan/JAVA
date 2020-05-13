package designPattern.adapter.extendsMethod;

/**
 * 继承被适配类的方式：
 *
 * 目标接口：因为继承的方法，适配器类需要继承被适配器类和目标类，但是java类是单继承，所以目标类只能定义为接口，
 *         否则用不了继承方法，只能用委托的方法
 * 目标方法：
 */
public interface Print {
    public abstract void printWeak();
    public abstract void printStrong();
}
