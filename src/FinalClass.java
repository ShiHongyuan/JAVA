public class FinalClass {
    /**
     * final 修饰局部变量和成员变量，必须在一条语句内显示地赋值（成员变量如果没有被赋初值:则会自动以类型的默认值而赋值）
     * 基本数据类型值或变量的引用不可变
     */
    final double PI = 3.1415;

    /**
     * final 修饰方法和成员方法
     * 1、第一个原因是把方法锁定，任何继承类只能继承，不能修改它的内容
     * 2、早期Java实现版本会将final方法转为内嵌调用，提高效率，现在Java版本已经不需要这些优化了。类中所有的private方法都隐式地指定为final。
     */
    final void myFinalFuntion() {}


    public class childClass extends FinalClass {
        public void fun() {
            this.myFinalFuntion();
        }
    }



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
