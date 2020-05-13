import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Java8 {
    //接口里可以通过default关键词创建已实现的默认方法，实现接口的类或者接口的实例都不需要再实现default方法了
    //接口interface没有静态修饰符，跟内部类不一样，内部可以创建接口的引用
    interface Calculator{
        double calculate(int a);

        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    abstract class CalculatorAbstract implements Calculator{
        int b = 5;
    }

    public static void main(String[] args) {
        //可以直接通过接口创建匿名对象，同时实现接口中的默认方法即可
        //不能通过抽象类或者接口直接创建对象，但是可以在静态方法里创建非静态的接口的匿名对象，可以理解成一个内部类实现了接口里的抽象方法并且返回一个内部类对象，之后我们让接口的引用来指向这个对象
        Calculator calculator = new Calculator() {
            @Override
            public double calculate(int a) {
                return a;
            }
        };
        System.out.println(calculator.calculate(11));//11.0
        System.out.println(calculator.sqrt(16));//4.0



        //lambda表达式主要用于定义内联执行的功能的接口，即只有一个单一的方法接口。Lambda表达式消除匿名类的需求，提供函数式编程，替代匿名类的成员方法的功能


        /**
         * 在1.8之前Java中是如何排列字符串的：
         * 需要给静态方法Collections.sort 传入一个 List 对象以及一个比较器来按指定顺序排列。
         */

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        // 通常做法都是创建一个匿名的比较器对象然后将其传递给 sort 方法
        // java.util.Comparator<T> 是一个比较任意两个对象的比较器的接口，注意和java.lang.Comparable<T>接口区分开，这是两个东西
        Collections.sort(names, new Comparator<String>() {//这里第二个参数需要一个实现List的比较方法compare的比较器，默认用默认的比较器，也可以创建匿名比较器自定义比较的方式
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);//因为String本身实现了Comparable接口，所以在比较器里可以直接调用String的比较方法compareTo来比较
            }
        });
        System.out.println(names);//[xenia, peter, mike, anna]

        Collections.sort(names);//不传第二个参数，使用默认的比较器，默认的比较器的compare方法默认调用Comparable接口的a.compareTo(b)，如果对象没有实现Comparable接口就会报错，但是String实现了Comparable接口，所以这里不传比较器也可以
        System.out.println(names);//[anna, mike, peter, xenia]  默认比较器调用的是a.compareTo(b)，自定义的是b.compareTo(a)，所以顺序不一样
        names.sort(new Comparator<String>() {//List自身也有sort方法，需要传参一个比较器，即创建匿名比较器可以自定义比较的方式
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);//因为String本身实现了Comparable接口，所以在比较器里可以直接调用String的比较方法compareTo来比较
            }
        });
        System.out.println(names);//[xenia, peter, mike, anna]


            //1.8加入了 lambda表达式的方式
        Collections.sort(names, (String a, String b) -> b.compareTo(a));//它会自己映射到java.util.Comparator<T>的compare方法，ab作为compare方法的参数，b.compareTo(a)作为compare方法的方法体
        names.sort((a, b) -> b.compareTo(a));//同理













    }


}
