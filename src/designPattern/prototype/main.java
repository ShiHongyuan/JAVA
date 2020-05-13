package designPattern.prototype;

import designPattern.prototype.framework.Manager;
import designPattern.prototype.framework.Product;

/**
 * 原型模型：通过复制生成实例
 * 我们先会生成很多个原型实例，把它们注册到 manager上，注册的时候分别给它们一个名字作区分
 * 后面我们要获取某个原型的复制实例时，通过名字取 manager上获取并复制
 * 实例实现接口用implement，接口继承接口用extends
 *
 * 两种情况需要用到原型模式复制实例：
 *      1. 有些实例一旦创建后经历了一系列复杂的操作后，要重新通过new创建一个新实例再操作成这个实例一样的状态需要大费周折，
 *          不如直接复制这个实例，得到一个一样状态的实例。
 *      2. 用到了原型模式的 manager的注册实例方式，有一些需要被独立出来作为组件复用的类不适合用类名去new创建实例，
 *          比如说某个组件类需要经常被替换，如果在代码中通过类名new了很多实例出来，一旦被替换，就需要到处更改新的类名创建实例，
 *          如果用原型模式，事先将创建的实例通过一个字符串名字注册到 manager上，代码中只需要通过名字获取实例，
 *          需要更改类时，只需要更改 manager上名字对应的实例，名字都不用改，那么代码中就不用做任何修改了。
 */
public class main {
    public static void main(String[] args) {
        // 准备阶段：注册原型实例
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('~');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        manager.register("strong message", upen);
        manager.register("warning box", mbox);
        manager.register("slash box", sbox);

        // 运用阶段：获取原型并复制生成新的实例
        Product p1 = manager.create("strong message");
        p1.use("Hello, world.");
        Product p2 = manager.create("warning box");
        p2.use("Hello, world.");
        Product p3 = manager.create("slash box");
        p3.use("Hello, world.");

    }
}
