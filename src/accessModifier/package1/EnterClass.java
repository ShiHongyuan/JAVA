package accessModifier.package1;

import accessModifier.package2.*;

/**
 * 同一个包的类是不需要 import 的，因为 package 已经包括了这个包里的所有的类和资源
 */

/**
 * 构造函数不能被继承，所以在抽象类里面定义abstract的构造函数没有意义，也不被允许。
 */

/**
 * 实例实现接口用implement，接口继承接口用extends
 */

public class EnterClass {
    public static void main(String... args) {

        /********************  普通类修饰符  ************************/

        // **************  public类

        // 不同包可以创建实例，也可以被不同包的类继承，也可以在继承的子类中被创建实例
        PublicClass2 publicClass2 = new PublicClass2();


        // **************  package类（默认就是package，不能写，写了不认识）

        // 不同包不能创建实例，也不能被不同包的类继承，不能被继承就不能是子类，就不能在子类中被创建实例
//    PackageClass2 packageClass2 = new PackageClass2();

        // 同一个包能创建实例，也可以被同一包的类继承，也可以在继承的子类中被创建实例
        PackageClass1 packageClass1 = new PackageClass1();


        // **************  final类

        // 不同包的public final类也不可以被继承，但是可以创建实例
        FinalPublicClass2 finalPublicClass2 = new FinalPublicClass2();

        // 同一个包的package final类也不可以被继承，但是可以创建实例
        FinalPackageClass1 finalPackageClass1 = new FinalPackageClass1();

        // **************  abstract类
        // 类中只要有 abstract 方法，类名就必须加上 abstract 修饰符，类就是一个抽象类
        // 类中没有 abstract 方法，类名也可以加上 abstract 修饰符，类也是一个抽象类

        /********************  普通类字段修饰符  ************************/
        // 对于普通类来说，字段修饰符与它的类修饰符完全没有关系

        // ChildCommonClass1是同包类FatherCommonClass1的子类
        // ChildCommonClass2是不同包类FatherCommonClass2的子类
        FatherCommonClass1 fatherCommonClass1 = new FatherCommonClass1();
        FatherCommonClass2 fatherCommonClass2 = new FatherCommonClass2();
        ChildCommonClass1 childCommonClass1 = new ChildCommonClass1();
        ChildCommonClass2 childCommonClass2 = new ChildCommonClass2();


        // **************  public字段
        // 能够被不同包的实例访问
        System.out.println("同包父类 + public 变量: " + fatherCommonClass1.publicVar );//同包父类 + public 变量: publicVar
        System.out.println("同包父类 + public final 变量: " + fatherCommonClass1.finalPublicVar );//同包父类 + public final 变量: finalPublicVar

        System.out.println("不同包父类 + public 变量: " + fatherCommonClass2.publicVar );//不同包父类 + public 变量: publicVar
        System.out.println("不同包父类 + public final 变量: " + fatherCommonClass2.finalPublicVar );//不同包父类 + public final 变量: finalPublicVar

        // 被不同包的子类继承
        System.out.println("同包子类 + public 变量: " + childCommonClass1.publicVar );//同包子类 + public 变量: 这是子类里创建的和父类同名的变量，实际上与父类是两个变量，父类变量藏起来了
        System.out.println("同包子类  + public final 变量: " + childCommonClass1.finalPublicVar );//同包子类  + public final 变量: finalPublicVar

        System.out.println("不同包子类  + public 变量: " + childCommonClass2.publicVar );//不同包子类  + public 变量: 这是子类里创建的和父类同名的变量，实际上与父类是两个变量，父类变量藏起来了
        System.out.println("不同包子类  + public final 变量: " + childCommonClass2.finalPublicVar );//不同包子类  + public final 变量: finalPublicVar


        // **************  protected字段
        // 不同包的实例不能访问，同一个包的实例才能访问
        System.out.println("同包父类 + protected 变量: " + fatherCommonClass1.protectedVar );//同包父类 + protected 变量: protectedVar
        System.out.println("同包父类 + protected 变量: " + fatherCommonClass1.finalProtectedVar );//同包父类 + protected 变量: finalProtectedVar
            // 编译不通过
//        System.out.println("不同包父类 + protected 变量: " + fatherCommonClass2.protectedVar );
//        System.out.println("不同包父类 + protected 变量: " + fatherCommonClass2.finalProtectedVar );

        // 不同包的子类也可以继承
        System.out.println("同包子类  + protected 变量: " + childCommonClass1.protectedVar );//同包子类  + protected 变量: protectedVar
        System.out.println("同包子类  + protected final 变量: " + childCommonClass1.finalProtectedVar );//同包子类  + protected final 变量: finalProtectedVar

        /**
         * 1. 直接通过子类访问不同包的父类的protected变量，会编译不通过，因为不同包的父类的实例不能访问它的protected变量
         * 2. 子类声明变量去继承不同包的父类的protected变量，然后再通过子类访问其声明的变量，通过。
         * 说明：子类声明变量去继承父类的变量（super.父类变量）的路径与子类可以继承但是没有声明去继承的父类变量，直接通过子类实例访问父类变量的路径不一样
         */
//        System.out.println("不同包子类  + protected 变量: " + childCommonClass2.protectedVar );//
//        System.out.println("不同包子类  + protected final 变量: " + childCommonClass2.finalProtectedVar );//

        System.out.println("不同包子类  + protected 变量: " + childCommonClass2.protectedVar_child );//不同包子类  + protected 变量: protectedVar
        System.out.println("不同包子类  + protected final 变量: " + childCommonClass2.finalProtectedVar_child );//不同包子类  + protected final 变量: finalProtectedVar


        // **************  default字段（默认就是default，不能写，写了不认识）
        // 不同包的实例不能访问，同一个包的实例才能访问
        System.out.println("同包父类 + default 变量: " + fatherCommonClass1.defaultVar );//同包父类 + default 变量: defaultVar
        System.out.println("同包父类 + default 变量: " + fatherCommonClass1.finalDefaultVar );//同包父类 + default 变量: finalDefaultVar
        // 编译不通过
//        System.out.println("不同包父类 + default 变量: " + fatherCommonClass2.defaultVar );
//        System.out.println("不同包父类 + default 变量: " + fatherCommonClass2.finalDefaultVar );

        // 不同包的子类不能继承，同一个包的子类才能继承
        System.out.println("同包子类  + default 变量: " + childCommonClass1.defaultVar );//同包子类  + default 变量: defaultVar
        System.out.println("同包子类  + default final 变量: " + childCommonClass1.finalDefaultVar );//同包子类  + default final 变量: finalDefaultVar

        // 编译不通过
//        System.out.println("不同包子类  + default 变量: " + childCommonClass2.defaultVar );
//        System.out.println("不同包子类  + public final 变量: " + childCommonClass2.finalDefaultVar );



        // **************  private字段
        // 不能被所有实例访问，不能被所有子类继承
//        System.out.println("同包父类 + private 变量: " + fatherCommonClass1.privateVar );
//        System.out.println("同包父类 + private 变量: " + fatherCommonClass1.finalPrivateVar );
//
//        System.out.println("同包子类  + private 变量: " + childCommonClass1.privateVar );
//        System.out.println("同包子类  + private final 变量: " + childCommonClass1.finalPrivateVar );


        // **************  final字段
        // 能够被所有实例（包括不同包）访问
        System.out.println("同包父类 + public final 变量: " + fatherCommonClass1.finalPublicVar );//同包父类 + public final 变量: finalPublicVar
        System.out.println("不同包父类 + public final 变量: " + fatherCommonClass2.finalPublicVar );//不同包父类 + public final 变量: finalPublicVar

        // 能够被所有子类（包括不同包的子类）继承
        System.out.println("同包子类  + public final 变量: " + childCommonClass1.finalPublicVar );//同包子类  + public final 变量: finalPublicVar
        System.out.println("不同包子类  + public final 变量: " + childCommonClass2.finalPublicVar );//不同包子类  + public final 变量: finalPublicVar


        // **************  static字段：不管是类直接访问还是通过实例访问，同样受public/protected/default/private的范围限制
        // 能够被类直接访问，也可以被所有实例访问
        System.out.println("同包父类实例 + public static 变量: " + fatherCommonClass1.staticPublicVar );//同包父类实例 + public static 变量: staticPublicVar
        System.out.println("不同包父类实例 + public static 变量: " + fatherCommonClass2.staticPublicVar );//不同包父类实例 + public static 变量: staticPublicVar

        System.out.println("同包父类直接访问 + public static 变量: " + FatherCommonClass1.staticPublicVar );//同包父类直接访问 + public static 变量: staticPublicVar
        System.out.println("不同包父类直接访问 + public static 变量: " + FatherCommonClass2.staticPublicVar );//不同包父类直接访问 + public static 变量: staticPublicVar

        // 能够被所有子类继承，被子类直接访问，也可以被子类的实例访问
        System.out.println("同包子类实例  + public static 变量: " + childCommonClass1.staticPublicVar );//同包子类实例  + public static 变量: staticPublicVar
        System.out.println("不同包子类实例  + public static 变量: " + childCommonClass2.staticPublicVar );//不同包子类实例  + public static 变量: staticPublicVar

        System.out.println("同包子类直接访问  + public static 变量: " + ChildCommonClass1.staticPublicVar );//同包子类直接访问  + public static 变量: staticPublicVar
        System.out.println("不同包子类直接访问  + public static 变量: " + ChildCommonClass2.staticPublicVar );//不同包子类直接访问  + public static 变量: staticPublicVar


        // ************** 字段都不能被子类修改，子类如果有声明相同类型和相同名的字段，父类字段只是被隐藏，不是被修改
        System.out.println("子类自己的与父类同名的publicVar变量: " + childCommonClass2.publicVar );//子类自己的与父类同名的publicVar变量: 这是子类里创建的和父类同名的变量，实际上与父类是两个变量，父类变量藏起来了



        /********************  普通类方法修饰符  ************************/
        // 对于普通类来说，方法修饰符与它的类修饰符完全没有关系

        // **************  public方法
        // 能够被不同包的实例调用
        System.out.println("同包父类 + public 方法: " + fatherCommonClass1.getPublicFunction() );//同包父类 + public 方法: publicFunction
        System.out.println("不同包父类 + public 方法: " + fatherCommonClass2.getPublicFunction());//不同包父类 + public 方法: publicFunction

        // 被不同包的子类继承并重写
        System.out.println("同包子类 + public 方法: " + childCommonClass1.getPublicFunction() );//同包子类 + public 方法: publicFunction
        System.out.println("不同包子类  + public 方法: " + childCommonClass2.getPublicFunction() );//不同包子类  + public 方法: publicFunction


        // **************  protected方法
        // 不同包的实例不能调用，同一个包的实例才能调用
        System.out.println("同包父类 + protected 方法: " + fatherCommonClass1.getProtectedFunction() );//同包父类 + protected 方法: protectedFunction
            // 编译不通过
//        System.out.println("不同包父类 + protected 方法: " + fatherCommonClass2.getProtectedFunction() );

        // 不同包的子类也可以继承并重写
        System.out.println("同包子类  + protected 方法: " + childCommonClass1.getProtectedFunction() );//同包子类  + protected 方法: protectedFunction

        /**
         * 1. 直接通过子类访问不同包的父类的protected方法，会编译不通过，因为不同包的父类的实例不能访问它的protected方法
         * 2. 子类继承并重写不同包的父类的protected方法，然后再通过子类访问其重写的方法，通过。
         * 说明：子类继承并重写不同包的父类的方法（override）的路径与子类没有重写不同包的父类的方法，而是直接通过子类实例访问父类方法的路径不一样
         */
        System.out.println("不同包子类  + protected 方法: " + childCommonClass2.getProtectedFunction() );//不同包子类  + protected 方法: protectedFunction_child


        // **************  default方法（默认就是default，不能写，写了不认识）
        // 不同包的实例不能调用，同一个包的实例才能调用
        System.out.println("同包父类 + default 方法: " + fatherCommonClass1.getDefaultFunction() );//同包父类 + default 方法: defaultFunction
        // 编译不通过
//        System.out.println("不同包父类 + default 方法: " + fatherCommonClass2.getDefaultFunction() );

        // 不同包的子类不能继承，同一个包的子类才能继承并重写
        System.out.println("同包子类  + default 方法: " + childCommonClass1.getDefaultFunction() );//同包子类  + default 方法: defaultFunction
        // 编译不通过
//        System.out.println("不同包子类  + default 方法: " + childCommonClass2.getDefaultFunction() );



        // **************  private方法
        // 不能被所有实例调用，不能被所有子类继承
            // 编译不通过
//        System.out.println("同包父类 + private 方法: " + fatherCommonClass1.getPrivateFunction() );



        // **************  final方法： final的方法必须有方法体，不能是abstract的方法
        // 能够被所有实例（包括不同包）调用
        System.out.println("同包父类 + public final 方法: " + fatherCommonClass1.getFinalPublicFunction() );//同包父类 + public final 方法: finalPublicFunction
        System.out.println("不同包父类 + public final 方法: " + fatherCommonClass2.getFinalPublicFunction() );//不同包父类 + public final 方法: finalPublicFunction

        // 能够被所有子类（包括不同包的子类）继承，但是不能被重写
        System.out.println("同包子类  + public final 方法: " + childCommonClass1.getFinalPublicFunction() );//同包子类  + public final 方法: finalPublicFunction
        System.out.println("不同包子类  + public final 方法: " + childCommonClass2.getFinalPublicFunction() );//不同包子类  + public final 方法: finalPublicFunction


        // **************  static方法： static的方法必须有方法体，不能是abstract的方法
        // 不管是类直接访问还是通过实例访问，同样受public/protected/default/private的范围限制
        // 能够被类直接调用，也可以被所有实例调用
        System.out.println("同包父类实例 + public static 方法: " + fatherCommonClass1.getStaticPublicFunction() );//同包父类实例 + public static 方法: staticPublicFunction
        System.out.println("不同包父类实例 + public static 方法: " + fatherCommonClass2.getStaticPublicFunction() );//不同包父类实例 + public static 方法: staticPublicFunction

        System.out.println("同包父类直接访问 + public static 方法: " + FatherCommonClass1.getStaticPublicFunction() );//同包父类直接访问 + public static 方法: staticPublicFunction
        System.out.println("不同包父类直接访问 + public static 方法: " + FatherCommonClass2.getStaticPublicFunction() );//不同包父类直接访问 + public static 方法: staticPublicFunction

        // 能够被所有子类继承，但是不能被重写，能够被子类直接调用，也可以被子类的实例调用
        System.out.println("同包子类实例  + public static 方法: " + childCommonClass1.getStaticPublicFunction() );//同包子类实例  + public static 方法: staticPublicFunction
        System.out.println("不同包子类实例  + public static 方法: " + childCommonClass2.getStaticPublicFunction() );//不同包子类实例  + public static 方法: staticPublicFunction

        System.out.println("同包子类直接访问  + public static 方法: " + ChildCommonClass1.getStaticPublicFunction() );//同包子类直接访问  + public static 方法: staticPublicFunction
        System.out.println("不同包子类直接访问  + public static 方法: " + ChildCommonClass2.getStaticPublicFunction() );//不同包子类直接访问  + public static 方法: staticPublicFunction



        // ************** 父类的方法只要可以被子类继承，就可以被子类重写，重写只要方法签名（方法名、形参）一样，修饰符不一样也是重写，子类调用时调用自己重写的方法

        // getProtectedFunction是重写的父类的方法，返回的和原父类方法不一样
        System.out.println("重写父类getProtectedFunction方法，返回的东西不一样: " + childCommonClass2.getProtectedFunction() );//重写父类getProtectedFunction方法，返回的东西不一样: protectedFunction_child




        /********************  抽象类修饰符（接口字段 & 接口方法）  ************************/

        // ****************  抽象类都不能创建实例
//        FatherAbstracClass1 fatherAbstracClass1 = new FatherAbstracClass1();

        // ****************  抽象类的子类，必须重写父类的所有抽象方法，才能不是抽象类
        ChildAbstractClass1 childAbstractClass1 = new ChildAbstractClass1();
        ChildAbstractClass2 childAbstractClass2 = new ChildAbstractClass2();


        // ****************  字段没有 abstract 修饰符，static、final、public、protected、default、private 与 字段的关系与普通类一致

        // ****************  final: final方法不能是 abstract，final的方法和字段的访问和继承规则与普通类一致
        // 能够被所有子类（包括不同包的子类）继承，但是不能被重写
        System.out.println("同包子类  + public final 变量: " + childAbstractClass1.finalPublicVar );//同包子类  + public final 变量: finalPublicVar
        System.out.println("不同包子类  + public final 变量: " + childAbstractClass2.finalPublicVar );//不同包子类  + public final 变量: finalPublicVar

        System.out.println("同包子类  + public final 方法: " + childAbstractClass1.getFinalPublicFunction() );//同包子类  + public final 方法: finalPublicFunction
        System.out.println("不同包子类  + public final 方法: " + childAbstractClass2.getFinalPublicFunction() );//不同包子类  + public final 方法: finalPublicFunction


        // ****************  static: static方法不能是 abstract，static的方法和字段的访问和继承规则与普通类一致
        // static字段 能够被类直接调用，也可以被所有子类调用
        System.out.println("同包子类实例  + public static 变量: " + childAbstractClass1.staticPublicVar );//同包子类实例  + public static 变量: staticPublicVar
        System.out.println("不同包子类实例  + public static 变量: " + childAbstractClass2.staticPublicVar );//不同包子类实例  + public static 变量: staticPublicVar

        System.out.println("同包子类直接访问  + public static 变量: " + ChildAbstractClass1.staticPublicVar );//同包子类直接访问  + public static 变量: staticPublicVar
        System.out.println("不同包子类直接访问  + public static 变量: " + ChildAbstractClass2.staticPublicVar );//不同包子类直接访问  + public static 变量: staticPublicVar

        // static方法 能够被所有子类继承，但是不能被重写，能够被子类直接调用，也可以被子类的实例调用
        System.out.println("同包子类实例  + public static 方法: " + childAbstractClass1.getStaticPublicFunction() );//同包子类实例  + public static 方法: staticPublicFunction
        System.out.println("不同包子类实例  + public static 方法: " + childAbstractClass2.getStaticPublicFunction() );//不同包子类实例  + public static 方法: staticPublicFunction

        System.out.println("同包子类直接调用  + public static 方法: " + ChildAbstractClass1.getStaticPublicFunction() );//同包子类直接调用  + public static 方法: staticPublicFunction
        System.out.println("不同包子类直接调用  + public static 方法: " + ChildAbstractClass2.getStaticPublicFunction() );//不同包子类直接调用  + public static 方法: staticPublicFunction


        // ****************  同一个包的子类可以继承并重写 public、protected、default方法，其访问和继承规则都与普通类一致
        System.out.println("同包子类继承并实现  + public 方法: " + childAbstractClass1.getPublicFunction() );//同包子类继承并实现  + public 方法: publicAbstractFunction_child
        System.out.println("同包子类继承并实现  + protected 方法: " + childAbstractClass1.getProtectedFunction() );//同包子类继承并实现  + protected 方法: protectedAbstractFunction_child
        System.out.println("同包子类继承并实现  + default 方法: " + childAbstractClass1.getDefaultFunction() );//同包子类继承并实现  + default 方法: defaultAbstractFunction_child


        // ****************  不在同一个包的子类可以继承并重写 public、protected方法，其访问和继承规则都与普通类一致
        System.out.println("不同包子类继承并实现  + public 方法: " + childAbstractClass2.getPublicFunction() );//不同包子类继承并实现  + public 方法: publicAbstractFunction_child
        System.out.println("不同包子类继承并实现  + protected 方法: " + childAbstractClass2.getProtectedFunction() );//不同包子类继承并实现  + protected 方法: protectedAbstractFunction_child





        /********************  接口修饰符（接口字段 & 接口方法）  ************************/

        // **************  public接口 (实际上是public abstract)
        // 不同包可以访问或者继承public接口
        // public接口的字段实际上是 public final static的，字段值不能被修改，接口不能创建实例，通过接口直接访问
        // 即使只写了字段类型 int var = 1;，也表示public final static int var = 1。必须在声明时赋值。
        System.out.println("public接口的变量: " + PublicInterface2.var );//public接口的变量: 1

        // public接口的方法实际上都是 public 的
        // 即使只写了int getVar(); ，实际上是public abstract int getVar();，不能被接口直接访问

        // 因为static的方法是共享的，可以被接口直接访问，所以应该有方法体，如果没有方法体的抽象方法就不能是static的
        // 但是如果是有方法体的方法可以是static，实际上是 public static int getVarStatic() {return var;}，通过接口直接访问
        System.out.println("static的方法getVarStatic(): " + PublicInterface2.getVarStatic() );//static的方法getVarStatic(): 1

        // default也要求方法必须有方法体，实际上是 public default int getVarDefault() {return var;}，default是JDK1.8新增的接口方法修饰符，它被该接口的所有实现类继承，不能通过接口直接访问，但是其所有实现类都可以访问default方法
        ExtendsClass5 extendsClass5 = new ExtendsClass5();//extendsClass5是PublicInterface2的实现类，其实例可以调用已经在接口中实现的getVarDefault方法
        System.out.println("default的方法getVarDefault(): " + extendsClass5.getVarDefault() );//default的方法getVarDefault(): 1

        // 接口定义的default方法，可以在接口的实现类中被重写，然后被其实例访问
        ExtendsClass6 extendsClass6 = new ExtendsClass6();//ExtendsClass6是PublicInterface2的实现类，并且重写了getVarDefault方法，其实例可以调用自己重写的getVarDefault方法
        System.out.println("被实现类重写的getVarDefault(): " + extendsClass6.getVarDefault() );//被实现类重写的getVarDefault(): 2

        // 如果是抽象方法，还要被实现类具体实现，final不能跟abstract一起修饰，所以不能是final的
        // 如果有方法体，方法必是static或者default修饰的，final不能跟static或者default一起修饰

        // 接口中static的变量可以被实现类继承（只是如果实现类中有相同变量，接口的变量会被隐藏）并访问，可以通过实现类直接访问，也可以通过实现类实例访问
        int i = ExtendsClass5.var;
        System.out.println("接口的变量var被实现类直接访问：" + i);//接口的变量var被实现类直接访问：1
        i = extendsClass5.var;
        System.out.println("接口的变量var被实现类的实例访问：" + i);//接口的变量var被实现类的实例访问：1

        // 接口中static的方法不能被其实现类继承或者访问，不能通过实现类直接访问，也不能通过实现类实例访问
//        ExtendsClass5.getVarStatic();  // 编译不通过
//        extendsClass5.getVarStatic();  // 编译不通过


        // public接口的方法不允许用protected、private、final修饰，可以单独用static、default、默认修饰


        // **************  friendly接口（默认就是friendly，不能写，写了不认识）
        // friendly接口不同包不可以访问，只有同一个包的才能访问或者实现

        // friendly接口的字段都是 public final static的，字段值不能被修改，接口不能创建实例，通过接口直接访问
        System.out.println("friendly接口的变量: " + FriendlyInterface1.var );//friendly接口的变量: 1
        System.out.println("friendly接口的实现类直接访问变量: " + ExtendsClass7.var );//friendly接口的实现类直接访问变量: 1 ExtendsClass7是FriendlyInterface1的实现类，继承了接口的static变量

        // friendly接口的方法也都是 public的，不允许用protected、private、final修饰，可以单独用static、default、默认修饰，同public接口的一样



    }


}

/********************  在PublicClass1外部定义的类，相当于同一个包的类  ************************/

/********************  普通类修饰符  ************************/

// **************  public类
// public类在不同也可以被不同包的类继承
class ExtendsClass1 extends PublicClass2{
    void fun (){};
}


// **************  package类（默认就是package，不能写，写了不认识）
// package类（默认类）不可以被不同包的类继承，但是可以被同一包的类继承
class ExtendsClass2 extends PackageClass1{
}


// **************  final类
// 不同包的public final类也不可以被继承
//class ExtendsClass3 extends FinalPublicClass2{
//}

// 同一个包的package final类也不可以被继承
//class ExtendsClass4 extends FinalPackageClass1{
//}

/********************  普通类字段和方法修饰符  ************************/
// 继承同一个包的类，关系default和protected的继承
class ChildCommonClass1 extends FatherCommonClass1 {

    /********************  字段  ************************/

    // public: 能够被不同包的子类继承
    public String publicVar_child = super.publicVar;
    public String finalPublicVar_child = super.finalPublicVar;


    // protected: 不同包的子类也可以继承
    public String protectedVar_child = super.protectedVar;
    public String finalProtectedVar_child = super.finalProtectedVar;

    // default: 不同包的子类不能继承，同一个包的子类才能继承
    public String defaultVar_child = super.defaultVar;
    public String finalDefaultVar_child = super.finalDefaultVar;

    // private: 不能被所有子类继承
//    public String privateVar_child = super.privateVar;
//    public String finalPrivateVar_child = super.finalPrivateVar;

    // final: 能够被所有子类继承
    public String finalPublicVar_child2 = super.finalPublicVar;

    // static: 能够被所有子类（包括不同包的子类）继承
    public String staticPublicVar_child = super.staticPublicVar;

    // ****************  字段都不能被子类修改，子类如果有声明相同类型和相同名的字段，父类字段只是被隐藏，不是被修改
    public String publicVar = "这是子类里创建的和父类同名的变量，实际上与父类是两个变量，父类变量藏起来了";


    /********************  方法  ************************/

    // public: 能够被不同包的子类继承并重写
//    @Override
//    public String getPublicFunction() {
//        return "publicFunction_child";
//    }

    // protected: 不同包的子类也可以继承并重写
//    @Override
//    public String getProtectedFunction() {
//        return "protectedFunction_child";
//    }

    // default: 不同包的子类不能继承，同一个包的子类才能继承并重写
//    @Override
//    public String getDefaultFunction() {
//        return "defaultFunction_child";
//    }

    // private: 不能被所有子类继承。
    // 要定义就是一个子类扩展的新方法，不是重写父类的方法
//    @Override
//    public String getPrivateFunction() {
//        return "protectedFunction";
//    }



    // final: 能够被所有子类（包括不同包的子类）继承，但是不能被重写
//    @Override
//    public String getFinalPublicFunction() {
//        return "finalPublicFunction_child";
//    }


    // static: 能够被所有子类（包括不同包的子类）继承，但是不能被重写，能够被子类直接调用，也可以被子类的实例调用
//    public String getStaticPublicFunction() {
//        return "staticPublicFunction_child";
//    }

}


// 继承不同包的类，关系default和protected的继承
class ChildCommonClass2 extends FatherCommonClass2 {

    /********************  字段  ************************/

    // public: 能够被不同包的子类继承
    public String publicVar_child = super.publicVar;
    public String finalPublicVar_child = super.finalPublicVar;


    // protected: 不同包的子类也可以继承
    public String protectedVar_child = super.protectedVar;
    public String finalProtectedVar_child = super.finalProtectedVar;

    // default: 不同包的子类不能继承，同一个包的子类才能继承
//    public String defaultVar_child = super.defaultVar;
//    public String finalDefaultVar_child = super.finalDefaultVar;

    // private: 不能被所有子类继承
//    public String privateVar_child = super.privateVar;
//    public String finalPrivateVar_child = super.finalPrivateVar;

    // final: 能够被所有子类（包括不同包的子类）继承
    public String finalPublicVar_child2 = super.finalPublicVar;

    // static: 能够被所有子类（包括不同包的子类）继承
    public String staticPublicVar_child = super.staticPublicVar;

    // ****************  字段都不能被子类修改，子类如果有声明相同类型和相同名的字段，父类字段只是被隐藏，不是被修改
    public String publicVar = "这是子类里创建的和父类同名的变量，实际上与父类是两个变量，父类变量藏起来了";


    /********************  方法  ************************/

    // public: 能够被不同包的子类继承并重写
//    @Override
//    public String getPublicFunction() {
//        return "publicFunction_child";
//    }

    // protected: 不同包的子类也可以继承并重写
    @Override
    public String getProtectedFunction() {
        return "protectedFunction_child";
    }

    // default: 不同包的子类不能继承，同一个包的子类才能继承并重写。
    // 要定义就是一个子类扩展的新方法，不是重写父类的方法
//    @Override
//    public String getDefaultFunction() {
//        return "defaultFunction_child";
//    }

    // private: 不能被所有子类继承。
    // 要定义就是一个子类扩展的新方法，不是重写父类的方法
//    @Override
//    public String getPrivateFunction() {
//        return "protectedFunction";
//    }



    // final: 能够被所有子类（包括不同包的子类）继承，但是不能被重写
//    @Override
//    public String getFinalPublicFunction() {
//        return "finalPublicFunction_child";
//    }



    // static: 能够被所有子类（包括不同包的子类）继承，但是不能被重写，能够被子类直接调用，也可以被子类的实例调用
//    public String getStaticPublicFunction() {
//        return "staticPublicFunction_child";
//    }


}




/********************  抽象类修饰符（接口字段 & 接口方法）  ************************/

// 继承同一个包的抽象类，必须重写父类的所有抽象方法，才能不是抽象类
class ChildAbstractClass1 extends FatherAbstracClass1 {

    // public: 能够被不同包的子类继承并重写
    @Override
    public String getPublicFunction() {
        return "publicAbstractFunction_child";
    }

    // protected: 不同包的子类也可以继承并重写
    @Override
    protected String getProtectedFunction() {
        return "protectedAbstractFunction_child";
    }

    // default: 不同包的子类不能继承，同一个包的子类才能继承并重写
    // 这是同一个包的子类，所以可以继承并实现default方法
    @Override
    String getDefaultFunction() {
        return "defaultAbstractFunction_child";
    }

}


// 继承不在同一个包的抽象类，必须重写父类的所有抽象方法，才能不是抽象类
class ChildAbstractClass2 extends FatherAbstracClass2 {
    // public: 能够被不同包的子类继承并重写
    @Override
    public String getPublicFunction() {
        return "publicAbstractFunction_child";
    }

    // protected: 不同包的子类也可以继承并重写
    @Override
    protected String getProtectedFunction() {
        return "protectedAbstractFunction_child";
    }

    // default: 不同包的子类永远无法继承实现父抽象类的default方法，所以子类只能是抽象类。
    // 要实现不同包的子类的具体实现类，父抽象类就不能有default方法

}





/********************  接口修饰符  ************************/
// 不同包可以访问或者继承public接口
class ExtendsClass5 implements PublicInterface2 {
    @Override
    public int getVar() {
        return var;
    }
}

class ExtendsClass6 implements PublicInterface2 {
    @Override
    public int getVar() {
        return var;
    }
    // 重写 & 重载：与方法的修饰符没有关系，只与形参有关系。方法签名是指方法名、形参相同，不包括方法修饰符。
    // 并且子类重写还必须保持或者扩大修饰符的范围，所以在原来是public，现在也必须是public，不能是不写的default
    @Override
    public final int getVarDefault() {
        return 2;
    }

    // 接口中static的方法不能被实现类继承或重写
//    @Override
//    public int getVarStatic() {
//        return 2;
//    }
}

// friendly默认接口不同包不可以访问，只有同一个包的才能访问或者实现
class ExtendsClass7 implements FriendlyInterface1{

}







