import java.util.Date;

public class ClassDefined {
    /**
     * 类的设计原则
     * 1、内聚性：成员变量和方法都是用于描述这个对象的，不要关联其他对象含义
     * 2、一致性：定义时，一般成员变量都在方法前面，一般优惠声明一个无参构造方法
     * 3、封装性：擅于用访问器和修改器来访问成员变量
     * 4、清晰性：声明命名参数等要清晰易懂
     * 5、完整性：要提供给使用者完整的类方法
     * 6、实例和静态：分清楚，该实例实例，该静态静态
     * */
    int varInt;
    String varString;
    private java.util.Date date = new Date();
    static int varStatic;
    /**
     * 测试无参构造方法
     * */
    private ClassDefined(int varInt) {}
    /**
     * 测试通过访问器修改成员变量的值
     * */
    java.util.Date getDate() {
        return this.date;
    }
    /**
     * 测试一个类中声明顺序和访问无关
     * */
    void testOder1() {
        testOder2();
    }
    void testOder2() {
        System.out.println("in testOder2");
    }
    /**
     * 测试访问隐藏的成员变量的方法
     * */
    void getvars(int varInt, int varStatic) {
        System.out.println(varInt);
        System.out.println(varStatic);
        System.out.println(this.varInt);                  // 0
        System.out.println(ClassDefined.varStatic);       // 0
    }

    /**
     * 静态内部类
     * 静态类只能在类内部定义，外部类不允许静态
     * */
    static class staticInnerClass {
        public staticInnerClass(){}
    }

    /**
     * 非静态内部类
     * */
    class nonstaticInnerClass {
        public nonstaticInnerClass(){}
    }

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
         * 但是在方法内，没有初始化的基本数据类型和对象，在使用时编译都通不过，连null都没有赋予，根本没有分配内存，创建基本数据类型或对象
         * */
        // 对象只要new出来，成员变量会赋予默认值
        ClassDefined class1 = new ClassDefined(1);
        System.out.println(class1.varInt);        // 0
        System.out.println(class1.varString);     // null


//        // 但是方法里声明的变量，不会被赋予默认值，使用的时候就编译报错
//        int n;
//        String s;
//        String s1 = s;             // 编译报错
//        System.out.println(n);     // 编译报错
//        System.out.println(s);     // 编译报错

        /**
         * 一个数据域是私有的，没有修改器（setter），并不意味着它就是不可变的，只要可以获取对象引用，就能改变对象
         * */
        System.out.println(class1.getDate());    // Sat Mar 23 10:15:05 CST 2019
        java.util.Date date1 = class1.getDate();
        date1.setTime(200000);
        System.out.println(class1.getDate());    // Thu Jan 01 08:03:20 CST 1970

        /**
         * 类的成员变量和成员方法的作用域是整个类，与声明顺序无关
         * */
        class1.testOder1();                      // in testOder2

        /**
         * 方法中声明了重名的变量，原成员变量就会被隐藏，访问隐藏的成员变量的方法
         * */
        class1.getvars(1, 2);        // 1 2 0 0

        /**
         * 静态内部类
         * */
        staticInnerClass staticInner = new ClassDefined.staticInnerClass();

        ClassDefined outter = new ClassDefined(1);
        nonstaticInnerClass nonstaticInner = outter.new nonstaticInnerClass();
        nonstaticInnerClass nonstaticInner2 = new ClassDefined(2).new nonstaticInnerClass();

        /**
         * 自定义类重写equals方法，但是方法参数写错了，相当于没有重写方法，重新扩展了一个方法，到底执行哪一个方法呢
         * */
        Circle1 circle1 = new Circle1(1);
        Circle1 circle11 = new Circle1(1);
        System.out.println(circle1.equals(circle11));

        Circle2 circle2 = new Circle2(1);
        Circle2 circle22 = new Circle2(1);
        System.out.println(circle2.equals(circle22));

        /**
         * 对于内部类，算作类的成员，static的方法里面只能调用static的成员，static成员属于类，非static成员属于实例，static成员不能不能调用未实例化的非static成员，所以static方法只能创建static的内部类
         * 对于外部类，不算成员，static方法可以创建非static的外部类
         * */
    }

    static class Circle1 {
        double radius;
        public Circle1 (int radius) {
            this.radius = radius;
        }

        public boolean equals (Circle1 circle) {
            System.out.println("Circle1");
            return this.radius == circle.radius;
        }
    }
    static class Circle2 {
        double radius;

        public Circle2 (int radius) {
            this.radius = radius;
        }

        public boolean equals (Object circle) {
            if (circle instanceof Circle2) {
                System.out.println("Circle2");
                return this.radius == ((Circle2) circle).radius;
            }
            else
                return false;
        }
    }



}
