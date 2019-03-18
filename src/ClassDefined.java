public class ClassDefined {
    int varInt;
    String varString;
    ClassDefined(int varInt) {}
    public static void main(String[] args) {
        /**
         * 只有当类中没有定义任何构造方法时，才会隐含提供一个无参的，方法体为空的默认构造方法
         * 构造方法：
         * 1、与类同名
         * 2、没有返回值，没有void，不然就是普通成员方法了
         * 3、new 一个实例的时候才会调用
         * */
        // 编译报错，因为类中已经定义了一个构造方法，就不会提供默认构造方法了，但是定义的构造方法中没有匹配的，所以报错
//        ClassDefined classDefined1 = new ClassDefined();

        /**
         * 匿名对象
         * */
        System.out.println(new ClassDefined(5).varInt);    // 0

        /**
         * 对象默认值 & 方法默认值
         * */
        // 对象只要new出来，成员变量会赋予默认值
        ClassDefined x1 = new ClassDefined(1);
        System.out.println(x1.varInt);        // 0
        System.out.println(x1.varString);     // null

//        // 但是方法里声明的变量，不会被赋予默认值，使用的时候就编译报错
//        int n;
//        String s;
//        String s1 = s;             // 编译报错
//        System.out.println(n);     // 编译报错
//        System.out.println(s);     // 编译报错
    }
}
