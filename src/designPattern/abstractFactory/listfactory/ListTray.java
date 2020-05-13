package designPattern.abstractFactory.listfactory;

import designPattern.abstractFactory.factory.Item;
import designPattern.abstractFactory.factory.Tray;

import java.util.Iterator;

/**
 * 抽象工厂模式：具体的一种小类的零件
 */
public class ListTray extends Tray{
    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<li>\n");
        buffer.append(caption + "\n");
        buffer.append("<ul>\n");
        Iterator it = getTray().iterator();
        while (it.hasNext()) {
            Item item = (Item) it.next();
            buffer.append(item.makeHTML());
        }
        buffer.append("</ul>\n");
        buffer.append("</li>\n");
        return buffer.toString();
    }
}
