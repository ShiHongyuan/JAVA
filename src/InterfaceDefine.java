import java.util.ArrayList;
import java.util.Date;

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
         * 实现接口的类必须重写该方法，而且把可视性修饰符改为public，否则实例调用不了
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



}
