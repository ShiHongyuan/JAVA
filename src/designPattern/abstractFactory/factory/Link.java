package designPattern.abstractFactory.factory;

/**
 * 抽象工厂模式：抽象工厂的其中一种抽象零件下的一种，一种分类下的再分类
 */
public abstract class Link extends Item {
    protected String url;
    public Link (String caption, String url) {
        super(caption);
        this.url = url;
    }
}
