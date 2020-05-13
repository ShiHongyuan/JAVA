package designPattern.abstractFactory;

import designPattern.abstractFactory.factory.Factory;
import designPattern.abstractFactory.factory.Link;
import designPattern.abstractFactory.factory.Page;
import designPattern.abstractFactory.factory.Tray;

/**
 * 抽象工厂模式：不管实现多少个具体工厂，使用者编程只需要用到抽象工厂和抽象产品，知道具体工厂的类名，就可以用具体工厂来创造对应的具体产品了
 *            所以这里只需要引入 factory.* 包就可以了
 *
 *            抽象工厂：Factory
 *            抽象零件：Page、Item（Item下又分为Link、Tray）
 *
 *            具体工厂：ListFactory
 *            具体零件：ListPage、ListLink、ListTray
 *
 *     为什么要用工厂？
 *          1. 要生成一个复杂的对象，通过工厂把这些杂乱繁多的零件统一在一起，让它们都能通过一个工厂创建，
 *              那么创建复杂对象的代码就清晰可见了，也不用记住那么多细节，
 *              可以把零件的一些相同的属性特征给提取出来成为抽象类或抽象接口去被继承实现，也减少了代码的冗余。
 *          2. 你产生的对象都通过工厂，工厂就能做统一管理，比如根据生产的顺序加个序号，
 *              把产生的对象都注册在一起，方便日后管理等等。
 *          3. 收紧权限，可以设置其他外部类无法直接new创建对象，只能通过工厂才能创建和操作实例就可以防止父类和其他具体类耦合。
 *             比如说，具体类升级了，需要替换类的时候，不需要一个一个的替换类名，而是只需要替换具体工厂里生成实例的具体实现即可。
 *             或者说升级了具体类的一些方法，也不需要一个一个的替换对象调用的方法名，而是只需要替换具体工厂里被操作实例的方法的具体实现即可。
 */
public class Main {
    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println(Main.class);//designPattern.abstractFactory.Main
            System.out.println("Usage: java Main class.name.of.ConcreteFactory");
            System.out.println("Example 1: java Main designPattern.abstractFactory.listfactory.ListFactory");
            System.out.println("Example 2: java Main designPattern.abstractFactory.tablefactory.TableFactory");
            System.exit(0);
        }

        Factory factory = Factory.getFactory(args[0]);

        Link people = factory.createLink("人民日报", "http://www/people.com.cn/");
        Link gmw = factory.createLink("光明日报","http://www/gmw.cn/");

        Tray traynews = factory.createTray("日报");
        traynews.add(people);
        traynews.add(gmw);

        Page page = factory.createPage("LinkPage", "石鸿瑗");
        page.add(traynews);
        page.output();


        Page page1 = factory.createYahooPage();
        page1.output();
    }
}
