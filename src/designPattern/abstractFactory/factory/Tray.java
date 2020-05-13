package designPattern.abstractFactory.factory;

import java.util.ArrayList;

/**
 * 抽象工厂模式：抽象工厂模式：抽象工厂的其中一种抽象零件下的一种，一种分类下的再分类
 */
public abstract class Tray extends Item {
    // tray 为private更加安全，但是需要额外添加访问private字段的方法
    private ArrayList tray = new ArrayList();

    public Tray (String caption) {
        super(caption);
    }

    protected ArrayList getTray() {
        return tray;
    }

    public void add(Item item) {
        tray.add(item);
    }
}
