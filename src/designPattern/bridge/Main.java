package designPattern.bridge;

import designPattern.bridge.function.CountDisplay;
import designPattern.bridge.function.Display;
import designPattern.bridge.function.IncreaseDisplay;
import designPattern.bridge.function.RandomDisplay;
import designPattern.bridge.impl.CharDisplayImpl;
import designPattern.bridge.impl.FileDisplayImpl;
import designPattern.bridge.impl.StringDisplayImpl;

/**
 * 桥梁模式：具体东西的实现是一个类，在实现层次上面，实现层次的类包括了一些操作自己的基本的方法。
 *         持有具体实现类的实例，并且利用实现类的基本操作实现各种各样功能的类是功能层次上面的类。
 *         功能层次上，往往以一个基本功能的类为父类，继承得到不同功能的子类。
 *
 *         分离实现层次和功能层次，方便增加新的类似的实现层次类，也方便给这些实现类增加新的功能，只需要在功能层次上增加新的类
 *          使用委托的方式，将具体实现类的实例传递给功能类持有，功能类就可以对实现类实例进行一些功能操作。
 *
 */
public class Main {
    public static void main (String[] args) {
        Display d1 = new Display(new StringDisplayImpl("Hello, World."));
        CountDisplay d2 = new CountDisplay(new StringDisplayImpl("Hello, China."));
        RandomDisplay d3 = new RandomDisplay(new StringDisplayImpl("Hello, China."));

        d1.display();//打印一次
        d2.multiDisplay(5);//打印5次
        d3.randomDisplay(5);//打印 [0~5) 中的随机次

        CountDisplay d4 = new CountDisplay(new FileDisplayImpl("/Users/Tinashy/IdeaProjects/mygithub/java/JAVA/src/designPattern/bridge/impl/Star.txt"));
        d4.multiDisplay(2);


        IncreaseDisplay d5 = new IncreaseDisplay(new CharDisplayImpl('<','*','>'));
        d5.increaseDisplay(1, 4);
        IncreaseDisplay d6 = new IncreaseDisplay(new CharDisplayImpl('|','#','-'));
        d6.increaseDisplay(2, 6);

    }
}
