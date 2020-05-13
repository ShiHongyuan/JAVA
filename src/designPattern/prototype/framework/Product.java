package designPattern.prototype.framework;

/**
 * 原型模式的原型共同的接口
 *
 * 实例实现接口用implement，接口继承接口用extends
 */

public abstract class Product implements Cloneable{
    public abstract void use(String s);
    public Product createClone() {
        Product p = null;
        try {
        p = (Product) this.clone();
        } catch (CloneNotSupportedException e) {
        e.printStackTrace();
        }
        return p;
        }
}
