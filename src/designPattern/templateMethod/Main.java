package designPattern.templateMethod;

/**
 * 模板方法：
 *      抽象父类，定义了模板方法，模板方法规定了方法执行的处理流程，并且用到了其他的抽象方法，但是抽象方法的具体实现由继承父类的子类实现
 *      display 定义了模板方法的处理流程，不管子类如何实现，处理流程不会变，而子类实现具体处理。
 *
 *      优点是在父类的模板方法中编写了算法流程，不需要在每个子类中在编写一次。如果算法有bug，我们只需要在一个地方修改，而不需要每个子类都修改一遍。
 *      而且父类也不关心实现的具体子类是谁，父类和子类们解耦，可以随时替换新的子类来实现功能。子类们就可以作为组件使用。
 *
 */
public class Main {
    public static void main (String[] args) {
        AbstractDisplay d1 = new CharDisplay('H');
        AbstractDisplay d2 = new StringDisplay("Hello World.");
        AbstractDisplay d3 = new StringDisplay("你好，世界。");

        d1.display();
        d2.display();
        d3.display();
    }
}
