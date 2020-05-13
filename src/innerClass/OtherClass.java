package innerClass;

public class OtherClass {
    public static void main(String[] args){
        OtherClass otherClass = new OtherClass();
        otherClass.printMain();
    }


    public void printMain() {
        // *****************  创建内部类
        // 创建非静态的内部类
        InnerClass outer = new InnerClass();
        InnerClass.InnerNonStatic innerNonStatic = outer.new InnerNonStatic();


        // 创建静态的内部类
        InnerClass.InnerStatic innerStatic = new InnerClass.InnerStatic();


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


        // 因为实例和内部类定义在一个外部类内，所以可以访问private的字段和方法，但是在实例在除外部类的其他类中，就得遵循普通类的访问规则
                // 编译不通过
//        System.out.println("非静态内部类实例的 private 字段: " + innerNonStatic.innerNonStaticPrivateFinalVar );
//        System.out.println("非静态内部类实例的 private 方法: " + innerNonStatic.getInnerNonStaticPublicVar() );


        // 静态内部类直接访问它自己的静态字段和静态方法
        System.out.println("静态内部类直接访问自己的public static字段: " + InnerClass.InnerStatic.innerStaticPublicStaticVar );
        System.out.println("静态内部类直接访问自己的public static方法: " + InnerClass.InnerStatic.getInnerStaticPublicStaticVar() );


        // 静态内部类实例访问它自己的字段和方法
        System.out.println("静态内部类实例的public static字段: " + innerStatic.innerStaticPublicStaticVar );
        System.out.println("静态内部类实例的public static方法: " + innerStatic.getInnerStaticPublicStaticVar() );

        System.out.println("静态内部类实例的 public 字段: " + innerStatic.innerStaticPublicStaticVar );
        System.out.println("静态内部类实例的 protected 字段: " + innerStatic.innerStaticProtectedFinalVar );
        System.out.println("静态内部类实例的 default 字段: " + innerStatic.innerStaticDefaultVar );

        System.out.println("静态内部类实例的 public 方法: " + innerStatic.getInnerStaticPublicStaticVar() );
        System.out.println("静态内部类实例的 protected 方法: " + innerStatic.getInnerStaticProtectedFinalVar() );
        System.out.println("静态内部类实例的 default 方法: " + innerStatic.getInnerStaticDefaultVar() );


        // 因为实例和内部类定义在一个外部类内，所以可以访问private的字段和方法，但是在实例在除外部类的其他类中，就得遵循普通类的访问规则
                // 编译不通过
//        System.out.println("静态内部类实例的 private 字段: " + innerStatic.innerStaticPrivateVar );
//        System.out.println("静态内部类实例的 private 方法: " + innerStatic.getInnerStaticPrivateVar() );


    }

}
