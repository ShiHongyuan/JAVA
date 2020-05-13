package designPattern.abstractFactory.listfactory;

import designPattern.abstractFactory.factory.Factory;
import designPattern.abstractFactory.factory.Link;
import designPattern.abstractFactory.factory.Page;
import designPattern.abstractFactory.factory.Tray;

/**
 * 抽象工厂模式：具体工厂，实现抽象工厂产生零件的方法，具体产生什么样子的零件又具体工厂定
 */
public class ListFactory extends Factory {

    @Override
    public Link createLink(String caption, String url) {
        return new ListLink(caption, url);
    }

    @Override
    public Tray createTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page createPage(String title, String author) {
        return new ListPage(title, author);
    }
}
