package designPattern.abstractFactory.listfactory;

import designPattern.abstractFactory.factory.Link;

/**
 * 抽象工厂模式：具体的一种小类的零件
 */
public class ListLink extends Link{

    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<li><a href=\"" + url + "\">" + caption + "</a></li>\n";
    }
}
