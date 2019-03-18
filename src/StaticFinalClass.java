/**
 * 声明类所在的包    package
 * 没有声明 在默认包
 * 引入其他包       import package.*
 * 引入其他包的类    import package.classname
 * public，default 可以用在类和类成员
 * private，protected 只能用在类成员
 *
 * 数据域封装：
 * 1、声明变量是private
 * 2、get，set方法访问，boolean用is定义方法
 * */
class StaticFinalClass {
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

    public static void main(String[] args) {
        System.out.println(StaticFinalClass.finalVar);            // 123.0
        System.out.println(StaticFinalClass.staticVar);           // 0
        System.out.println(StaticFinalClass.testStatic());        // 0

        StaticFinalClass instance = new StaticFinalClass();
        /**
         * 可以用实例访问静态变量和静态方法，但是最好用类名的方式访问静态变量和静态方法
         */
        System.out.println(instance.finalVar);                    // 123.0
        System.out.println(instance.staticVar);                   // 0
        System.out.println(instance.testStatic());                // 0
        System.out.println(instance.instanceVar);                 // 0
        System.out.println(instance.testFunction());              // 0

        testObjectParameter(instance);
        System.out.println(instance.instanceVar);                 // 2 在方法中被改了
    }

    /**
     * 对象实参，引用值传递
     * java只有一种传递方式：值传递（只是分基本类型值传递，引用值传递）
     * */
    public static void testObjectParameter(StaticFinalClass x) {
        System.out.println(x.instanceVar);                 // 0
        System.out.println(x.testFunction());              // 0
        x.instanceVar = 2;
    }

}
