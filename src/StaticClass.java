import static java.lang.Math.*;
import static java.lang.Math.max;
class StaticClass {
    /**
     * 类中的常量必须是static的，因为要类的所有实例共享
     * */
    // 对于final，成员变量也不会赋予默认值，必须声明的时候初始化
    final static double finalVar = 123;

    /**
     * 类中的静态变量和静态方法，可以被实例调用，也可以被类直接调用
     * 在类中，一个方法体内部如果不依赖于实例变量或者实例方法，就应该定义为静态方法，而不应该定义为实例方法
     * */
    // 对于static，成员变量声明时不初始化，会赋予默认值
    static int staticVar;        // 默认值 0
    static int testStatic() {
        /**
         * 静态方法里必须使用静态变量和静态方法
         */
        System.out.println(finalVar);
        System.out.println(staticVar);
        //  System.out.println(instanceVar);  错的
        //  System.out.println(testFunction());  错的
        return staticVar;
    }

    /**
     * 类中的实例变量和实例方法，只能被实例调用
     * */

    int instanceVar;            // 默认值 0
    int testFunction() {
        /**
         * 实例方法里可以使用静态变量和静态方法，也可以使用实例变量和实例方法
         */
        System.out.println(finalVar);
        System.out.println(staticVar);
        System.out.println(instanceVar);
        System.out.println(testStatic());
        return instanceVar;

    }

    /**
     * 对象实参，引用值传递
     * java只有一种传递方式：值传递（只是分基本类型值传递，引用值传递）
     * */
    public static void testObjectParameter(StaticClass x) {
        System.out.println(x.instanceVar);                 // 0
        System.out.println(x.testFunction());              // 0
        x.instanceVar = 2;
    }

    public static void main(String[] args) {
        System.out.println("测试成员变量默认值开始----------------");
        System.out.println(StaticClass.finalVar);            // 123.0
        System.out.println(StaticClass.staticVar);           // 0
        System.out.println(StaticClass.testStatic());        // 0
        System.out.println("测试成员变量默认值结束----------------");

        System.out.println("测试实例调用开始----------------");
        StaticClass instance = new StaticClass();
        /**
         * 可以用实例访问静态变量和静态方法，但是最好用类名的方式访问静态变量和静态方法
         */
        System.out.println(instance.finalVar);                    // 123.0
        System.out.println(instance.staticVar);                   // 0
        System.out.println(instance.testStatic());                // 0
        System.out.println(instance.instanceVar);                 // 0
        System.out.println(instance.testFunction());              // 0
        System.out.println("测试实例调用结束----------------");

        System.out.println("测试对象引用值传递开始----------------");
        testObjectParameter(instance);
        System.out.println(instance.instanceVar);                 // 2 在方法testObjectParameter中被改了
        System.out.println("测试对象引用值传递结束----------------");

        /**
         * static的使用场景
         */
        System.out.println("测试代码块执行开始----------------");
        // 编译报错找不到，因为是在代码块里定义的变量，不算成员变量，访问不了
//        System.out.println(StaticClass.blockStaticValue);
//        System.out.println(instance.blockStaticValue);
//        System.out.println(instance.blockValue);
        StaticClass instance2 = new StaticClass();   // 第二次建立对象，非静态代码块也执行了一次
        System.out.println("测试代码块执行结束----------------");





    }



    /**
     * static的使用场景
     */

    /**
     * static 修饰成员变量、成员方法
     * 属于类，不属于某个实例，类和实例共享，建议调用格式：类名调用
     * 静态成员变量 存放在 Java 内存区域的方法区：方法区与 Java 堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。虽然Java虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却有一个别名叫做 Non-Heap（非堆），目的应该是与 Java 堆区分开来。
     *
     * this、super不能用在static方法中
     * this 代表对本类对象的引用，指向本类对象
     * super 代表对父类对象的引用，指向父类对象，在建立子类对象的时候，其实相当于同时建立了父类对象，这个实例既是子类的实例，也是父类的实例，多态性啊
     * this和super是属于对象范畴的东西，而静态方法是属于类范畴的东西
     *
     * */

    /**
     * static 修饰代码块
     * 在类中定义在方法外的代码块：静态代码块 + 非静态代码块，执行顺序：静态代码块 > 非静态代码块 > 构造方法
     * 静态代码块与实例无关，只在JVM加载类的时候只执行一次，即使还没有建立对象，或者建立多少对象都没有关系
     * 非静态代码块只在建立对象的时候构造方法前执行，每次建立对象都会执行一次
     * 代码块，可以有多个，位置可以随便放，多个按先后顺序执行
     * */

    // 静态代码块
    static {
        System.out.println("类里方法外定义的静态代码块开始-------");
        int blockStaticValue = 1;
        testStatic();
        for(int i = 0; i < 1; i++) {
            System.out.println("类里方法外定义的静态代码块" + i);
        }
        System.out.println("类里方法外定义的静态代码块结束-------");
    }

    // 非静态代码块
    {
        System.out.println("类里方法外定义的非静态代码块开始-------");
        int blockValue = 2;
        testStatic();
        testFunction();
        for(int i = 0; i < 2; i++) {
            System.out.println("类里方法外定义的非静态代码块" + i);
        }
        System.out.println("类里方法外定义的非静态代码块结束-------");
    }

    // 代码块对于定义在它之后的变量，可以赋值，但是不能访问
    static
    {
        i = 3;
//        System.out.println(i);  // 编译错误，不能在变量定义前访问，超前访问
    }
    private static int i;
    {
        j = 3;
//        System.out.println(j);  // 编译错误，不能在变量定义前访问，超前访问
    }
    private int j;

    // 类里方法外，没在代码块里定义，不允许，编译报错
//    for(int i = 0; i < 3; i++) {
//        System.out.println("类里方法外定义的代码块" + i);
//    }


    /**
     * static 修饰内部类
     * 只能修饰内部类，不能修饰外部类
     *
     * */

    /**
     * static 导包，导入静态资源
     * 导入某个类中的指定静态资源，不需要使用类名调用类中静态成员，直接使用类中静态成员变量和成员方法
     * 导入Math中的所有静态资源：import static java.lang.Math.*;
     * 只导入某个静态方法：import static java.lang.Math.max;
     * */
    // 直接调用方法，不需要类名，看import部分
    public static class Demo {
        public static void main(String[] args) {

            int max = max(1,2);
            System.out.println(max);   // 2
        }
    }

}
