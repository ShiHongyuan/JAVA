package designPattern.abstractFactory.factory;

/**
 * 抽象工厂模式：抽象工厂的其中一种抽象零件
 */
public abstract class Item implements Htmlable {
    protected String caption;
    public Item (String caption) {
        this.caption = caption;
    }
}
