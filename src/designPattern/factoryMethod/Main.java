package designPattern.factoryMethod;

import designPattern.factoryMethod.framework.Factory;
import designPattern.factoryMethod.framework.Product;
import designPattern.factoryMethod.idcard.IDCardFactory;

/**
 * 工厂方法：工厂方法是模板方法的一种典型应用，创建实例的方法 create就是模板方法，
 *         模板方法是专门用来生成实例的，但是只是决定了实例的生成方式，只是定义了生成实例的框架，
 *         并不决定所要生成的具体的类是什么，所以需要有不同的具体工厂，生成不同的实例，具体工厂都用抽象工厂父类的模板方法创建实例。
 *
 *         每增加一个具体的工厂和产品时，只需要导入抽象工厂的包即可使用抽象工厂中创建实例的模板方法，
 *         没有必要修改抽象工厂和产品包中的任何内容，就可以创建出其他新的具体工厂和产品了。
 *
 *         利用工厂的方法来生成实例，而不是new一个实例，就可以防止父类和其他具体类耦合。
 *         替换类的时候，不需要一个一个的替换类名，而是只需要替换具体工厂里生成实例的具体实现即可。
 */
public class Main {
    public static void main (String[] args) {
        // 不同的工厂，但是引用的都是抽象工厂父类，创建实例使用的是抽象工厂的模板方法
        // 不同的产品实例，但是引用的都是产品的抽象父类
        // 因为IDCard的构造函数是default的，所以idcard包外的类只能通过具体工厂的public create方法创建产品实例，并且通过工厂统一控制产生产品的编号，以免重复。
        Factory factory = new IDCardFactory();
        Product card1 = factory.Create("小明");
        Product card2 = factory.Create("小红");
        Product card3 = factory.Create("小刚");

        card1.use();
        card2.use();
        card3.use();
    }
}

