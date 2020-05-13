import java.util.*;

public class InterfaceDefine {
    public static void main(String[] args) {
        /**
         * 类有共同的根Object
         * 接口没有共同的根
         * 一个类实现了一个接口，类似于该接口是这个类的父类
         */

        /**
         * java.lang.Comparable<T>  比较接口
         * public int compareTo(T o);
         */


        /**
         * java.io.Serializable  可序列化，标记性接口
         * 网络传输和读写文件的时候
         */

        /**
         * java.lang.Cloneable  可克隆，标记性接口
         * 复制对象
         * protected native Object clone() throws CloneNotSupportedException;  native说明不是java写的，不是核心api，是JVM自身平台实现的
         * 实现接口的类是抽象类，可以不重写实现接口的方法，否则必须重写实现接口方法，而且把可视性修饰符改为public，否则实例调用不了
         */
        // Date ArrayList Calendar都实现了Cloneable接口
        java.util.Date date = new Date();
        Date date1 = date;
        Date date2 = (Date) date.clone();
        java.util.ArrayList list = new ArrayList();
        ArrayList list1 = list;
        ArrayList list2 = (ArrayList)list.clone();

        System.out.println(date1 == date);       // true      指向引用一样
        System.out.println(date2 == date);       // false     引用不一样，只是复制内容
        System.out.println(date2.equals(date));  // true      内容相等

        int[] a = new int[]{1,2};
        int[] a1 = a.clone();
        System.out.println(a == a1);            // false
        System.out.println(a.equals(a1));       // false  数组调用Object的原始equals方法，用的是绝对相等 return (this == obj);
        a1[0] = 3;
        System.out.println(a[0]);               // 1      clone不是原对象，不会改变原对象
        System.out.println(a1[0]);              // 3

        // 自定义类实现接口
        InterfaceDefine outterClass =  new InterfaceDefine();
        MyInterface myInterface = outterClass.new MyInterface(2);
        MyInterface myInterface1 = outterClass.new MyInterface(3);

        System.out.println(myInterface.compareTo(myInterface1));                      // -1
        try {
            MyInterface copy = (MyInterface)myInterface.clone();
            System.out.println(copy == myInterface);                                  // false
            /** clone实现的是浅复制，对象的引用数据域是相同的引用，深复制需要自己重写clone实现，对象的引用数据域也是新创建的 **/
            System.out.println(copy.obj == myInterface.obj);                          // true
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }

        MyInterface2 myInterface2 = outterClass.new MyInterface2(2);
        MyInterface2 myInterface3 = outterClass.new MyInterface2(3);

        System.out.println(myInterface2.compareTo(myInterface3));                     // -1
        try {
            System.out.println( (MyInterface2)myInterface2.clone() == myInterface2);  // false
        }
        catch (CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }

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


    }

    class MyInterface implements Cloneable, Comparable<MyInterface>{
        int value;
        MyInterface2 obj = new MyInterface2(6);

        public MyInterface(int value){
            this.value = value;
        }

        public Object clone() throws CloneNotSupportedException {
            /** 是JVM自身平台实现的，所以只需调用父类的super.clone() **/
            return super.clone();
        }

        @Override
        public int compareTo(MyInterface o) {
            if (o.value < value) return 1;
            else if (o.value > value) return -1;
            else return 0;
        }
    }

    class MyInterface2 implements Cloneable, Comparable{
        int value;

        public MyInterface2(int value){
            this.value = value;
        }

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof MyInterface2) {
                if (((MyInterface2)o).value < value) return 1;
                else if (((MyInterface2)o).value > value) return -1;
                else return 0;
            }
            return 0;
        }
    }

    /**
     * java.util.Comparator<T> 比较任意两个对象的比较器，用于没有实现Comparable接口的对象
     */
    class MyInterface3<T>  implements java.util.Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            // 针对对象的具体的比较方法的实现
            return 0;
        }
    }

    /**
     * 接口继承接口，可以多继承，继承类不能多继承
     */
    interface childinterface extends Comparable, Cloneable{

    }

    /**
     * comparable<T> 和 Comparator<T>的区别
     */
    // comparable<T>接口是出自java.lang包 它有一个 compareTo(T obj)方法用来排序
    // comparator<T>接口是出自java.util包，它有一个compare(T obj1, T obj2)方法用来排序
    // 一般排序算法中有默认的自然排序，其排序对象就必须实现了comparable<T>接口的compareTo(T obj)方法
    // 一般排序算法中有自定义的比较器形参，可以创建一个实现了comparator<T>接口的compare(T obj1, T obj2)方法的匿名对象作为比较器实参
    // 如果一个类型实现了comparable<T>接口的compareTo(T obj)方法，那么在它的比较器compare(T obj1, T obj2)方法中可以直接使用compareTo方法来简化排序定义

}
