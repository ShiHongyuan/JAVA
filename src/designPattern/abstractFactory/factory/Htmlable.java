package designPattern.abstractFactory.factory;

/**
 * 抽象工厂模式：抽象零件的共同特征，让所有抽象零件都实现这个接口，那么所有具体两件就要实现这个共同特征的方法了
 */
public interface Htmlable {
    public abstract String makeHTML();
}
