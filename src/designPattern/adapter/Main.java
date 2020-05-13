package designPattern.adapter;

import designPattern.adapter.proxyMethod.Print;
import designPattern.adapter.proxyMethod.Printer;

/**
 * 适配器模式：
 *      通过一个本来不相关的适配器类，让适配器调用我们需要的类（目标类）的新方法，但是方法的具体实现是调用另一个已经测试完成的没有bug的类（被适配的类）的方法
 *      让适配器调用我们需要调用的新的方法：
 *              1. 继承被适配类的方法：让适配继承目标接口和被适配的类，从而继承了目标接口我们需要调用的目标方法和被适配类的方法，实现目标方法为调用被适配类的方法
 *              2. 委托被适配类的方法：让适配继承目标类，持有被适配类的实例，从而继承了目标类我们需要调用的目标方法，重写目标方法的实现为调用被适配类的实例的方法
 *
 *      使用适配器模式，可以用新编写的类的方法里复用以前已有的类的方法，一方面已有的类经过了测试和验证阶段，不用太担心有大的bug，
 *      一方面可以复用以前的代码以前的功能，不需要再编写冗余的代码，将已有的功能作为组件使用。
 */
public class Main {
    public static void main (String[] args) {
        // 继承被适配类的方法
        designPattern.adapter.extendsMethod.Print print = new designPattern.adapter.extendsMethod.Printer("Hello");
        System.out.println("继承被适配类的方法 ------------------");
        print.printWeak();
        print.printStrong();

        // 委托被适配类的方法
        Print print1 = new Printer("Hello");
        System.out.println("委托被适配类的方法 ------------------");
        print1.printWeak();
        print1.printStrong();

    }

}
