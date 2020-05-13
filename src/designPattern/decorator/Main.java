package designPattern.decorator;

/**
 * 装饰器模式：每一个装饰类都是一个在装饰内容上实现的功能，而装饰内容可以是原始对象，也可以是已经被装饰过的对象，
 *         有时候需要一种功能，有时候需要多种功能，有时候又需要几种自定义的功能组合，所以使用装饰器模式，就可以在原始物品的基础上不断地嵌套自定义的功能，得到最终的效果。
 *         就像BIO的字节流和处理流一样。
 *
 *         装饰类们是可以被自由组合的，也可以随意新增和修改装饰类，所以装饰类之间是独立的，与原始对象的类之间也是独立的，
 *         每个装饰类都可以作为可复用的组件来用。
 *
 *         所以这就要求每个装饰类实现的功能之间是独立的，不能互相有依赖。
 *
 */
public class Main {
    public static void main(String[] args) {
        // 修饰单行字符串的叶子
        Display b1 = new StringDisplay("Hello, world.");
        Display b2 = new SideBorder(b1, '#');
        Display b3 = new FullBorder(b2);
        Display b4 = new SideBorder(
                new FullBorder(
                        new FullBorder(
                                new SideBorder(
                                        new FullBorder(
                                                new StringDisplay("Hello, world.")
                                        )
                                ,'*')
                        )
                )
        , '/');

        b1.show();
        b2.show();
        b3.show();
        b4.show();


        // 修饰多行字符串的叶子
        Display d1 = new MultiStringDisplay();
        ((MultiStringDisplay)d1).add("Hello, world.");
        ((MultiStringDisplay)d1).add("Good Morning.");
        ((MultiStringDisplay)d1).add("Good Afternoon.");

        Display d2 = new SideBorder(d1, '#');
        d2.show();

        Display d3 = new FullBorder(d1);
        d3.show();

        Display d4 = new UpDownBorder(d1, '*');

    }
}
