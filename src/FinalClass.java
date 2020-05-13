public class FinalClass {
    /**
     * final 修饰局部变量，必须在一条语句内显示地赋值
     * 基本数据类型值或变量的引用y不可变
     */
    final double PI = 3.1415;

    /**
     * final修饰非静态成员变量
     *      1. 在声明时初始化
     *      2. 在构造函数中赋值
     */
    // 1. 在声明时初始化
    final int value  = 123;

    // 2. 在构造函数中赋值
    final int value1;
    public FinalClass () {
        this.value1 = 123;
    }

    /**
     * final 修饰静态成员变量
     *      1. 在声明时初始化
     *      2. 在静态初始化代码块中赋值
     */
    // 1. 在声明时初始化
    static final int value2  = 123;

    // 2. 在静态初始化代码块中赋值
    static final int value3;
    static {
        value3 = 123;
    }

    /**
     * final 修饰非静态成员方法
     * 1、第一个原因是把方法锁定，任何继承类只能继承，不能修改它的内容，不能被子类重写
     * 2、早期Java实现版本会将final方法转为内嵌调用，提高效率，现在Java版本已经不需要这些优化了。类中所有的private方法都隐式地指定为final。
     */
    final void myFinalFuntion() {}


    /**
     * final 修饰静态成员方法
     *      表示该方法不会被子类相同签名的静态方法隐藏。
     *      否则只有static的静态方法，虽然不能被子类重写，不能写 @override，但是可以有相同签名的静态子类方法，那么父类的静态方法会被隐藏
     */
    // static 的方法不能被子类重写，不能写 @override，但是可以有相同签名的子类方法，那么父类的静态方法会被隐藏
    static void myStaticFunction() {}

    static final void myStaticFinalFunction() {}




    /**
     * final 参数
     *      final 作为参数，不可以赋值，因为在调用方法的时候，才会对final 参数赋值。
     */


    /**
     * final 修饰类
     * 表明这个类不能被继承，final类中的所有成员方法都会被隐式地指定为final方法
     */
    public final class finalClass {
        finalClass() {}
        public void fun() { }
    }

//    public class finalChildClass extends finalClass { } // 编译报错，cannot inherit from final class "FinalClass.finalClass"

}

class childClass extends FinalClass {
    public void fun() {
        this.myFinalFuntion();
    }

    // static 的方法不能被子类重写，不能写 @override，但是可以有相同签名的静态子类方法，那么父类的静态方法会被隐藏
    public static void myStaticFunction() {}


    // final static 方法不能被子类隐藏，final 不能被重写和隐藏
//        public void myStaticFinalFunction() {}


    /**
     * 匿名内部类里面要用到的外部变量，在外部声明时必须是final类型。
     */
}
