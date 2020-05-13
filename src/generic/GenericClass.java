package generic;

import java.io.Serializable;
import java.util.*;

/**
 * 泛型类：
 *      声明为一个泛型类：
 *          单个泛型参数：类名后紧跟<T>
 *          多个泛型参数：类名<T, S>等
 *          有界泛型参数：类名<T extends Number>，<T extends Number & Comparable & Cloneable>等
 *
 *      声明后，可以使用声明的泛型参数的地方：
 *          成员变量：private T var;
 *          成员方法：public T func();
 *          构造函数：public classname(T var1)
 *
 *          因为使用的是已被类声明的泛型参数 T，所以成员变量和成员方法这里的 T 必须与创建对象时指定的类型相同
 *          成员方法里使用被类声明的泛型参数 T，不代表就是泛型方法，泛型方法需要额外声明的，这里的成员变量和成员方法只是泛型类应有的一个表现而已
 *
 *      指定具体类型的时候：创建一个泛型类的实例对象的时候
 *                      创建有声明实例：在前面声明泛型类的后面<具体类型>，后面new的泛型类的后面<>
 *                      创建匿名实例：因为匿名，只能在new的泛型类的后面<具体类型>
 *
 *      编译阶段：
 *          泛型类中所有的泛型参数（T）会被替换成创建实例时声明的具体类型，再进行类型规范检查，不通过的编译阶段就会失败
 *
 *      运行阶段：
 *          所有编译阶段替换的具体类型都会被类型擦除，替换为Object，所以可能会存在通过反射方法绕过编译阶段类型检查的不匹配类型，在运行时强制转换出错
 *
 *      泛型参数范围的限制
 *          无界类型参数：单独声明的参数，如 T，没有限制
 *          有界类型参数：
 *              extends 关键字：用于限制泛型参数的上界，创建实例时，指定类型必须是父类或者父类的子类，或者继承一个或多个接口
 *                            与普通类继承一样，只能继承一个父类，但是可以同时实现多个接口
 *
 *          注意：泛型类的泛型参数 T 只有extends，没有super，而且泛型类不能声明类型通配符
 */

public class GenericClass {
    public static void main(String[] args) throws Exception {

        /****************************  泛型类  **********************************/

        MyBox<Integer, String> myBox = new MyBox<>();
        myBox.add(Integer.valueOf(199), "Hello World");
        System.out.printf("MyBox First Value :%d\n", myBox.getFirst());//MyBox First Value :199
        System.out.printf("MyBox Second Value :%s\n", myBox.getSecond());//MyBox Second Value :Hello World

        MyPair<String, Integer> myPair = new MyPair<>();
        myPair.addKeyValue("1", Integer.valueOf(100));
        System.out.printf("MyPair Value :%d\n", myPair.getValue("1"));//MyPair Value :100

        MyList<MyBox> myList = new MyList<>();
        myList.addItem(myBox);
        System.out.printf("MyList Value :%d\n", myList.getItem(0).getFirst());//MyList Value :199

        //指定参数类型为具体类型时，也可以指定后参数化类型（本身就是泛型类的类型）
        MyBox<Integer, List<String>> myBox2 = new MyBox<>();

        List<String> list = new ArrayList<>();
        list.add("shi");
        list.add("hong");
        list.add("yuan");

        myBox2.add(1,list);
        System.out.printf("MyBox2 First Value :%d\n", myBox2.getFirst());//MyBox First Value :1
        System.out.printf("MyBox2 Second Value :%s\n", myBox2.getSecond());//MyBox2 Second Value :[shi, hong, yuan]

        /***************  通过反射方法绕过编译阶段类型检查的不匹配类型，结果在运行时强制转换出错  ***************/

        Class myListClass =  Class.forName("generic.GenericClass$MyList");

        MyList<String> myListReflect = (MyList<String>) myListClass.getConstructor().newInstance();
        myListReflect.addItem("aaa");
        myListReflect.addItem("bbb");

        // 通过反射获取方法，可以自己再次指定方法的泛型参数 T 为Object，绕过了编译阶段的类型检查，所以实参传递整型也没有报错
        myListClass.getMethod("addItem", Object.class).invoke(myListReflect, 100);

//        for (int i = 0; i < 3; i++) {
//            String s = (String) myListReflect.getItem(i);
//            System.out.println(i + ":" + s);
//            //0:aaa
//            //1:bbb
//            //ClassCastException: java.lang.Integer cannot be cast to java.lang.String
//            // 以为myListReflect是String类型的，结果通过反射方法赋值的整型，在运行时强转出错了
//        }

        // 注意：！：Integer能转换成String，但是不能转换，只能通过方法转换
        String s = String.valueOf(100);
        System.out.println(s);//100



        /****************************  限制泛型参数范围  **********************************/

        // ******************  无界类型参数  ****************
        // 在没限制范围时，所有引用类型都可以被指定

        // String不是Number的子类，在方法内部强转时报错
//        NumberCompare<String> numberCompare1 = new NumberCompare<>();
//        numberCompare1.getMax(new String[]{"1","2","3"});//java.lang.String cannot be cast to java.lang.Number

        // Integer是Number的子类，对的
        NumberCompare<Integer> numberCompare2 = new NumberCompare<>();
        numberCompare2.getMax(new Integer[]{1,2,3});//3


        // ******************  有界类型参数  ****************
        // extends限制为子类

        //限制了类型，编译不通过
//        NumberCompare2<String> numberCompare3 = new NumberCompare2<>();

        // 满足限制，对的
        NumberCompare2<Integer> numberCompare4 = new NumberCompare2<>();
        numberCompare4.getMax(new Integer[]{1,2,3});//3

    }


    /****************************  泛型类  **********************************/

    //自定义一个具有多个类型参数的类，多个类型参数以，分割
    static class MyBox <T, S> {
        private T t;
        private S s;

        public void add(T t, S s) {
            this.t = t;
            this.s = s;
        }


        public T getFirst() {
            return t;
        }

        public S getSecond() {
            return s;
        }
    }

    //自定义一个映射类
    static class MyPair<K, V> {
        private Map<K, V> map = new HashMap();

        public void addKeyValue(K key, V value) {
            map.put(key, value);
        }

        public V getValue(K key) {
            return map.get(key);
        }
    }

    //自定义一个列表类
    static class MyList<E> {
        private List<E> list = new ArrayList();

        public MyList(){}

        public void addItem(E value) {
            list.add(value);
        }

        public E getItem(int index) {
            return list.get(index);
        }
    }

    /****************************  限制泛型参数范围  **********************************/

    // ******************  无界类型参数  ****************

    //定义一个比较数字数组，选出最大值的类，这里的泛型参数没有范围限制，所以对象实例也可以不指定为Number的子类，那么就有可能在强转时出错

    static class NumberCompare<T> {

        public T getMax(T[] array) {
            T max = null;
            for (T num : array) {
                // 因为doubleValue() 是Number类的成员方法，所以必须强转为Number后调用，否则编译不通过
                max = ((Number)num).doubleValue() > ((Number)max).doubleValue() ? num : max;
            }
            return max;
        }
    }

    // ******************  有界类型参数  ****************

    // extends 关键字：限制泛型参数的上界，用 extends 统一的表示了原有的 extends 和 implements 的概念
    // 创建实例时，指定类型必须是父类或者父类的子类，或者为一个或多个接口的实现类
    // 泛型参数是extends 父类的类或者子类，只能继承一个父类

    // 上面没有限制的泛型类，本身就是只针对Number类的子类设计的，在类名声明泛型参数时限制类型的范围，必须指定为Number类或者Number类的子类
    static class NumberCompare2<T extends Number> {

        public T getMax(T[] array) {
            T max = null;
            for (T num : array) {
                // 因为这里的 T 继承自Number，肯定有doubleValue()方法，所以就不需要强转了，实参直接调用就好
                max = num.doubleValue() > max.doubleValue() ? num : max;
            }
            return max;
        }
    }


    // 泛型参数是extends的接口的实现类，可以实现多个接口，用&分隔
    // 实现比较，克隆和序列化的接口
    class NumberCompare3<T extends Comparable<T> & Cloneable & Serializable> {

        public T getMax(T[] array) {
            T max = null;
            for (T num : array) {
                // 因为这里的 T 继承自Number，肯定有doubleValue()方法，所以就不需要强转了，实参直接调用就好
                max = ((Number)num).doubleValue() > ((Number)max).doubleValue() ? num : max;
            }
            return max;
        }
    }

    // 泛型参数是extends的子类和接口的实现类，只能继承一个父类，但是多个接口，并且类写在第一位，接口列在后面，类和接口也用&分隔
    // 既继承了Number父类，也实现了比较，克隆和序列化的接口
    class NumberCompare4<T extends Number & Comparable<T> & Cloneable & Serializable> {

        public T getMax(T[] array) {
            T max = null;
            for (T num : array) {
                // 因为这里的 T 继承自Number，肯定有doubleValue()方法，所以就不需要强转了，实参直接调用就好
                max = num.doubleValue() > max.doubleValue() ? num : max;
            }
            return max;
        }
    }
}
