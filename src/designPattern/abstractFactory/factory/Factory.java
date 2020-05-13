package designPattern.abstractFactory.factory;

/**
 * 抽象工厂模式：抽象工厂，根据传入的具体工厂类名，通过反射返回具体工厂的实例，定义具体工厂产生怎么样的零件的抽象方法
 */
public abstract class Factory {
    public static Factory getFactory(String classname) {
        Factory factory = null;
        // Class.newInstance()调用的是默认构造函数创建实例，适合没有定义构造函数的类
        try {
            factory = (Factory) Class.forName(classname).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    public abstract Link createLink(String caption, String url);
    public abstract Tray createTray(String caption);
    public abstract Page createPage(String title, String author);

    public Page createYahooPage() {
        Link link = createLink("Yahoo!", "http://www.yahoo.com");
        Page page = createPage("Yahoo!", "Yahoo!");
        page.add(link);
        return page;
    }
}
