package reflect;

import java.lang.reflect.Method;
/*
    获取反射出来的Student类的main方法，不要与当前类的main方法搞混了
* */
public class Main {
    public static void main(String[] args) {
        try {
            //1、获取Student对象的字节码
            Class stuClass = Class.forName("reflect.Reflect$Student");

            //2、获取main方法
            Method methodMain = stuClass.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型的class对象，
            //3、调用main方法
            methodMain.invoke(null, new String[]{"a","b","c"});//java.lang.IllegalArgumentException: wrong number of arguments
            //第一个参数，对象类型，因为方法是static静态的，不管是哪个实例调用都有幂等性，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数String... args

            /**注意因为invoke(Object obj, Object... args)的第二个参数是一个可变参数，要么传递多个独立的形参类型的参数，要么传递一个形参类型的数组，这个就是要么传递多个独立的Object，要么传递Object的数组，反正传进去都是组合成数组args，然后再拆分成一个一个的数组元素作为方法的实参传递给方法，
             * 如果传递args=new String[]{"a","b","c"} ，args会被拆分成3个实参传递给方法  。。但是前面.getMethod定义的形参类型String[].class)是一个参数，所以需要将String[]强转成一个Object，整个数组作为一个实参传递
             * 如果传递args=new Object[]{new String[]{"a","b","c"}}，args会被拆分成1个实参new String[]{"a","b","c"}，正好符合方法的形参
             *
             *
             *
             * 注意：args的类型是Object，为什么可以传递String类型呢，因为String类是Object的子类，根据多态性，子类可以隐式转换成父类
             **/
            methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一  Student的main方法执行了。。。
            //注意任何数组（包括Object[]数组）都是一个Object的子类，这里拆完数组后，只有一个元素，就还是一个方法实参，是对的
            methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二  Student的main方法执行了。。。

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
