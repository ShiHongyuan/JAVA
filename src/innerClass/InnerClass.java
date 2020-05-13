package innerClass;

public class InnerClass {
    private String outterPrivateVar = "outterPrivateVar";
    private static String outterStaticPrivateVar = "outterStaticPrivateVar";

    private String getOutterPrivateVar() {
        return outterPrivateVar;
    }

    private static String getOutterStaticPrivateVar() {
        return outterStaticPrivateVar;
    }

    /**
     * ***************  在成员变量区或者非静态方法里访问内部类，非静态和静态的内部类都可以直接创建
     */
    // 创建非静态的内部类
    InnerNonStatic innerNonStatic = new InnerNonStatic();

    // 创建静态的内部类
    InnerStatic innerStatic = new InnerStatic();

    public static void main(String[] args){
        // *****************  在静态方法 main 里访问内部类
        // 创建非静态的内部类，因为一个类的静态方法只能访问它自己的静态变量和方法，如果是非静态的，只能通过实例访问。
        InnerClass outer = new InnerClass();
        InnerNonStatic innerNonStatic = outer.new InnerNonStatic();

        // 创建静态的内部类
        InnerStatic innerStatic = new InnerStatic();


        // *****************  访问内部类的字段和方法
        // 非静态内部类实例访问它自己的字段和方法
        System.out.println("非静态内部类实例的 public 字段: " + innerNonStatic.innerNonStaticPublicVar );
        System.out.println("非静态内部类实例的 protected 字段: " + innerNonStatic.innerNonStaticPublicVar );
        System.out.println("非静态内部类实例的 default 字段: " + innerNonStatic.innerNonStaticPublicVar );
        System.out.println("非静态内部类实例的 public final 方法: " + innerNonStatic.getInnerNonStaticPrivateVar() );

        System.out.println("非静态内部类 public 方法 可以访问外部类的private字段: " + innerNonStatic.getOutterPrivateVar() );
        System.out.println("非静态内部类 protected 方法 可以访问外部类的static字段: " + innerNonStatic.getOutterStaticPrivateVar() );

        System.out.println("非静态内部类 default 方法 可以访问外部类的private方法: " + innerNonStatic.getOutterPrivateFuction() );
        System.out.println("非静态内部类 public 方法 可以访问外部类的static方法: " + innerNonStatic.getOutterStaticPrivateFuntion() );

        // 因为实例和内部类定义在一个外部类内，所以可以访问private的字段和方法
        System.out.println("非静态内部类实例的 private 字段: " + innerNonStatic.innerNonStaticPrivateFinalVar );
        System.out.println("非静态内部类实例的 private 方法: " + innerNonStatic.getInnerNonStaticPublicVar() );


        // 静态内部类直接访问它自己的静态字段和静态方法
        System.out.println("静态内部类直接访问自己的public static字段: " + InnerStatic.innerStaticPublicStaticVar );
        System.out.println("静态内部类直接访问自己的public static方法: " + InnerStatic.getInnerStaticPublicStaticVar() );


        // 静态内部类实例访问它自己的字段和方法
        System.out.println("静态内部类实例的public static字段: " + innerStatic.innerStaticPublicStaticVar );
        System.out.println("静态内部类实例的public static方法: " + innerStatic.getInnerStaticPublicStaticVar() );

        System.out.println("静态内部类实例的 public 字段: " + innerStatic.innerStaticPublicStaticVar );
        System.out.println("静态内部类实例的 protected 字段: " + innerStatic.innerStaticProtectedFinalVar );
        System.out.println("静态内部类实例的 default 字段: " + innerStatic.innerStaticDefaultVar );

        System.out.println("静态内部类实例的 public 方法: " + innerStatic.getInnerStaticPublicStaticVar() );
        System.out.println("静态内部类实例的 protected 方法: " + innerStatic.getInnerStaticProtectedFinalVar() );
        System.out.println("静态内部类实例的 default 方法: " + innerStatic.getInnerStaticDefaultVar() );


        // 因为实例和内部类定义在一个外部类内，所以可以访问private的字段和方法
        System.out.println("静态内部类实例的 private 字段: " + innerStatic.innerStaticPrivateVar );
        System.out.println("静态内部类实例的 private 方法: " + innerStatic.getInnerStaticPrivateVar() );


    }

    /***************************  非静态的内部类  *****************************/
    class InnerNonStatic{
        // 非静态的内部类，只能定义非静态的字段和方法（因为非静态内部类不能通过类名直接得到，必须要创建实例才能访问其字段和方法，所以它内部定义的静态对象就没有意义）
        public String innerNonStaticPublicVar = "innerNonStaticPublicVar";
        protected final String innerNonStaticProtectedFinalVar = "innerNonProtectedFinalVar";
        String innerNonStaticDefaultVar = "innerNonStaticDefaultVar";
        private final String innerNonStaticPrivateFinalVar = "innerNonStaticPrivateFinalVar";

        public final String getInnerNonStaticPrivateVar() {
            return innerNonStaticPrivateFinalVar;
        }

        private String getInnerNonStaticPublicVar() {
            return innerNonStaticPublicVar;
        }


        // 非静态的内部类，可以访问其父类的任意字段和方法（因为非静态内部类要访问这些方法，必须创建实例，那么外部类也必须创建实例，所以外部类的静态和非静态对象都有意义）
        public String getOutterPrivateVar() {
            return outterPrivateVar;
        }

        protected String getOutterStaticPrivateVar() {
            return outterStaticPrivateVar;
        }

        String getOutterPrivateFuction() {
            return getOutterPrivateVar();
        }

        public String getOutterStaticPrivateFuntion() {
            return getOutterStaticPrivateVar();
        }


    }



    /***************************  静态的内部类  *****************************/
    // 只有内部类才能被声明为 static，外部类不能被static修饰

    static class InnerStatic{

        // 静态的内部类，可以定义任意字段和方法，静态的非静态的都可以（因为静态内部类外面可以通过类名直接访问其静态对象，所以定义静态对象也有意义）

        public static String innerStaticPublicStaticVar = "innerStaticPublicStaticVar";
        protected final String innerStaticProtectedFinalVar = "innerStaticProtectedFinalVar";
        String innerStaticDefaultVar = "innerStaticDefaultVar";
        private String innerStaticPrivateVar = "innerStaticPrivateVar";

        public static String getInnerStaticPublicStaticVar() {
            return innerStaticPublicStaticVar;
        }

        protected String getInnerStaticProtectedFinalVar() {
            return innerStaticProtectedFinalVar;
        }

        final String getInnerStaticDefaultVar() {
            return innerStaticDefaultVar;
        }

        private String getInnerStaticPrivateVar() {
            return innerStaticPrivateVar;
        }

        // 非静态的内部类，只能访问其父类的静态字段和静态方法（因为静态内部类可以在不创建外部类实例的情况下自己创建实例，访问其对象，如果这里面涉及外部类的非静态对象的话，就没有权限访问了）

        String getOutterStaticPrivateVar() {
            return outterStaticPrivateVar;
        }

        public String getOutterStaticPrivateFuntion() {
            return getOutterStaticPrivateVar();
        }

    }

    // 非静态内部类在外部类外面可以被继承，静态内部类也可以被继承
    class ExtendsNonStaticClass extends InnerNonStatic {

        // ******* 字段继承规则：所有字段，public、protected、default、private、final都可以被继承
        String innerNonStaticPublicVar_child = super.innerNonStaticPublicVar;
        String innerNonStaticProtectedFinalVar_child = super.innerNonStaticProtectedFinalVar;
        String innerNonStaticDefaultVar = super.innerNonStaticDefaultVar;

        // 因为继承类和内部类定义在一个外部类内，所以可以继承private的字段，但是不能继承private的方法
        String innerNonStaticPrivateFinalVar = super.innerNonStaticPrivateFinalVar;



        // ******* 方法继承规则：除了final方法可以被继承但是不能被重写，private方法不能被继承和重写外。其他方法都可以被继承和重写

        // final方法可以被继承，但是不能被重写
//        @Override
//        public String getInnerNonStaticPrivateVar() {
//            return innerNonStaticPrivateVar;
//        }

        // private方法不能被继承和重写
//        @Override
//        String getInnerNonStaticPublicVar() {
//            return innerNonStaticPublicVar;
//        }

        // 其他public、protected、default方法都可以被继承和重写
        @Override
        public String getOutterPrivateVar() {
            return outterPrivateVar;
        }
        @Override
        protected String getOutterStaticPrivateVar() {
            return outterStaticPrivateVar;
        }

        @Override
        String getOutterPrivateFuction() {
            return getOutterPrivateVar();
        }

    }

    class ExtendsStaticClass extends InnerStatic {

        // ******* 字段继承规则：所有字段，public、protected、default、private、final都可以被继承
        String innerStaticPublicStaticVar = super.innerStaticPublicStaticVar;
        String innerStaticProtectedFinalVar = super.innerStaticProtectedFinalVar;
        String innerStaticDefaultVar = super.innerStaticDefaultVar;

        // 因为继承类和内部类定义在一个外部类内，所以可以继承private的字段，但是不能继承private的方法
        String innerStaticPrivateVar = super.innerStaticPrivateVar;


        // ******* 方法继承规则：除了final、static方法可以被继承但是不能被重写，private方法不能被继承和重写外。其他方法都可以被继承和重写
        // static方法可以被继承，但是不能被重写
//        @Override
//        public String getInnerStaticPublicStaticVar() {
//            return innerStaticPublicStaticVar;
//        }

        // final方法可以被继承，但是不能被重写
//        @Override
//        final String getInnerStaticDefaultVar() {
//            return innerStaticDefaultVar;
//        }

        // private方法不能被继承和重写
//        @Override
//        String getInnerStaticPrivateVar() {
//            return innerStaticPrivateVar;
//        }


        // 其他的 public、protected、default 都可以被继承和重写
        @Override
        protected String getInnerStaticProtectedFinalVar() {
            return innerStaticProtectedFinalVar;
        }

        @Override
        String getOutterStaticPrivateVar() {
            return outterStaticPrivateVar;
        }

        @Override
        public String getOutterStaticPrivateFuntion() {
            return getOutterStaticPrivateVar();
        }

    }

    /**
     * 内部类是类的成员，所以可以修饰内部类为private、protected等，其对外的被访问权限同其他类成员一样
     */
    private class innerClass {}
    protected class innerClass1 {}
    public class innerClass2 {}
    final protected class innerClass3 {}
}


// 非静态内部类在外部类外面不能被继承，静态内部类可以被继承
class ExtendsClass extends InnerClass.InnerStatic {

}






