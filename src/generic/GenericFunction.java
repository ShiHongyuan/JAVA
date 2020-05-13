package generic;


import java.util.ArrayList;
import java.util.List;

/**
 * 超级复杂的一个东西。。。。。。烦烦烦
 *
 * 泛型方法：
 *       声明为一个泛型方法：
 *          单个泛型参数：方法修饰符和返回值之间<T>
 *          多个泛型参数：方法修饰符和返回值之间<T, S>等
 *          有界泛型参数：方法修饰符和返回值之间<T extends Number>，<T extends Number & Comparable & Cloneable>，<T super Number>等
 *                      注意：有界泛型参数只能在声明泛型方法的<>中声明，不能在后面形参才声明
 *
 *       声明后，可以使用声明的泛型参数的地方：方法的形参中，方法体里面
 *
 *
 *       泛型方法与类成员方法的关系：
 *          泛型方法与它所在的类没有关系：泛型方法可以在泛型类，也可以在非泛型类中定义
 *          在泛型类中的泛型方法：注意不是包含了泛型类声明的泛型参数的方法，而是显式声明了自己的泛型参数的泛型方法
 *                            泛型方法自己声明的泛型参数即使符号与泛型类声明的泛型参数相同，但可以指定为不同的类型（但是其他包含了泛型类声明的泛型参数的方法，的T和泛型类的T是同一个类型）
 *          在非泛型类中的泛型方法：方法自己声明泛型参数，被调用时才替换
 *
 *
 *       泛型方法指定具体类型的时候：
 *          泛型方法没有类型擦除这一说。
 *          在方法被调用时，传入具体类型的参数的时候，自动指明类型。方法会在编译和运行时使用声明的泛型参数替换为具体类型
 *
 *
 *       泛型参数范围的限制：
 *          无界泛型参数：单独声明的参数，如 T，没有限制
 *          有界泛型参数：
 *              extends 关键字：用于限制泛型参数的上界，方法被调用时，参数类型必须是父类或者父类的子类，或者是继承一个或多个接口的实现类
 *                             与普通类继承一样，只能继承一个父类，但是可以同时实现多个接口
 *
 *          注意：泛型方法的泛型参数 T 只有extends，没有super
 *
 *       泛型方法与static修饰符的关系：
 *          在类中，因为static方法是共享的，不属于实例，创建泛型类实例时指定的具体类型各异，无法与static共存，所以类中的static普通成员方法无法包含泛型参数
 *          但是，声明为泛型方法的成员方法在调用时才指明具体类型，与实例创建时指定的类型无关，虽然是共享的，但是在Class对象中泛型方法也保留了泛型的特征，不会被类型擦除
 *          所以，声明为泛型方法的成员方法可以与static共存，无法包含泛型参数的普通成员方法无法与static共存
 *
 *
 * 类型通配符：
 *       ? 不能用于声明泛型方法，只是可以用于匹配各种引用类型
 *       ? 不能作为单独的类型使用，编译不通过，只能作为匹配泛型实例的具体类型使用
 *       ? 是一种类型实参，可以看做为Number等所有类的父类，接受所有引用类型，只能用在方法的形参里面，不能作为返回值
 *
 *       jvm处理的的方式：
 *          在方法被调用时，传入具体类型的参数，类似于方法形参定义为父类，但是实际调用时传入的是子类的感觉，它们在jvm中处理的方式应该是差不多的
 *
 *       类型通配符范围的限制：
 *          不用Object代替通配符的原因大概就是，通配符有Object没有的范围限制了
 *
 *          无界通配符：单独声明的通配符，如 ?，没有限制
 *          有界通配符：
 *               extends 关键字：用于限制通配符的上界，方法被调用时，实参类型必须是父类或者父类的子类，或者是一个接口的实现类
 *               super 关键字：用于限制通配符的下界，方法被调用时，实参类型必须是限制类或者限制类的父类（超类）
 *
 *          注意：通配符不能继承多个接口，要么继承一个父类，要么继承一个接口
 *
 *       方法形参选择通配符还是泛型参数的区别：
 *           1. 类型通配符代替的参数只能是只读 ，不能修改，但是泛型参数代替的参数可以修改，只读的通用参数可以用？代表
 *           2. 类型在形参中只出现一次 & 没有返回值依赖它：
 *              例如：<T, E extends T> void func(List<T> l1, List<E> l2);
 *                   这里E只在形参中出现了一次，并且没有返回值依赖它，那么就可以把E换成?
 *                   最终结果就是：<T> void func(List<T> l1, List<? extends T> l2);
 *
 *
 *  最后总结一句话：泛型参数和类型通配符都是为了 实现方法的通用参数
 *
 */

public class GenericFunction {
    public static void main(String[] args){

        /************************  在类外部定义的泛型方法（通用方法）  **************************/

        //构造传入泛型方法的各种引用类型的实参
        Integer[] integers = {1,2,3};
        String[] strings = {"shi","hong","yuan"};
        //传入具体类型的实参调用泛型方法
        System.out.print("Array integerArray contains:");
        printArray(integers);//1 2 3
        System.out.print("Array stringArray contains:");
        printArray(strings);//shi hong yuan


        /*************  在类内部定义的泛型方法（注意与泛型类或泛型接口的成员方法区别开）  ******************/

        // 通过类中的泛型方法获取位置
        Point<Integer, Integer> pointInt = new Point<>(100,200);
        Point<String, String> pointStr = new Point<>("东京180度","北纬210度");

        Point.printPoint(pointInt);//GPS: 100，200
        Point.printPoint(pointStr);//GPS: 东京180度，北纬210度


        /******************  泛型方法与类成员方法，通配符方法的关系   *******************/
        // 见下面定义的例子就好


        /*****************************  限制泛型参数范围   ******************************/

        //重新定义printArrayNum的泛型参数的范围为 Number 类或者其子类
        // 所以传入Integer[]，对的
        System.out.print("Array integerArray contains:");
        printArrayNum(integers);//1 2 3
        // 所以传入String[]，编译不通过
//        printArrayNum(strings);


        /**************** 类型通配符 & 通过类型通配符限制参数范围（只有方法才能使用通配符） ***************/

        // ******************  类型通配符  ****************

        // 外部的通配符方法printPoint1(Point<?, ?> p)，？用来匹配Point实例的各种具体类型，也能起到打印各种类型的Point的作用
        // pointInt是一个Point<Integer, Integer>，pointStr是一个Point<String, String>
        printPoint1(pointInt);//GPS: 100，200
        printPoint1(pointStr);//GPS: 东京180度，北纬210度

        // ？在一个方法的声明中，不代表必须是同一种类型，？在一个方法里可以代表不同类型
        // printTwoPoints(Point<?, ?> p1,Point<?, ?> p2)
        printTwoPoints(pointInt, pointStr);//GPS: p1 x=100，p2 y=北纬210度


        // ******************  有界类型通配符  ****************

        // extends 关键字：？匹配继承 Number类和其子类
        // pointInt 是 Point<Integer, Integer>，对的
        printNumPoint(pointInt);//GPS: 100， 200

        // pointStr 是 Point<String, String>，编译不通过
//        printNumPoint(point2);

        // ？匹配继承 String 类和其子类
        printStrPoint(pointStr);//GPS: 东京180度，北纬210度

        // super 关键字：？匹配 Double类和其超类
        Point<Double, Double> pointDouble = new Point<>(100.5,200.6);
        printDoublePoint(pointDouble);//GPS: 100.5， 200.6

        // pointInt 是 Point<Integer, Integer>，编译不通过
//        printDoublePoint(pointInt);


        /*******************  方法形参选择通配符还是泛型参数的区别  *********************/

        // 1. 类型通配符代替的参数只能是只读 ，不能修改，但是泛型参数代替的参数可以修改，只读的通用参数可以用？代表

        List<String> list = new ArrayList<>();

        // 使用通配符的方法，不能修改实参
        func(list);
        System.out.println(list);//[]

        // 使用泛型参数的泛型方法，可以修改实参
        funcT(list, "addItem");
        System.out.println(list);//[addItem]


        // 2. 类型在形参中只出现一次 & 没有返回值依赖它：
        //    例如：<T, E extends T> void func(List<T> l1, List<E> l2);
        //         这里E只在形参中出现了一次，并且没有返回值依赖它，那么就可以把E换成?
        //         最终结果就是：<T> void func(List<T> l1, List<? extends T> l2);
    }


    /************************  在类外部定义的泛型方法（通用方法） **************************/

    //是否拥有泛型方法，与其所在的类是否泛型没有关系，GenericFunction就不是泛型类。
    //要定义泛型方法，只需将泛型参数列表置于返回值前。泛型参数可用于声明返回值类型和形参类型
    public static <E> void printArray(E[] inputArray) {

        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }


    /*************  在类内部定义的泛型方法（注意与泛型类或泛型接口的成员方法区别开）  ******************/

    // 定义一个描述地图位置的类，T1 T2可能是描述度数的数字，也可能是包含经纬度的字符串
    static class Point<T1, T2>{
        T1 x;
        T2 y;

        public Point(T1 x, T2 y) {
            this.x = x;
            this.y = y;
        }

        public T1 getX() {
            return x;
        }
        public void setX(T1 x) {
            this.x = x;
        }
        public T2 getY() {
            return y;
        }
        public void setY(T2 y) {
            this.y = y;
        }

        // 定义一个泛型方法，打印位置
        public static <T1, T2> void printPoint(Point<T1, T2> p){
            System.out.println("GPS: " + p.getX() + "，" + p.getY());
        }
    }

    /*****************************  泛型方法与类成员方法，通配符方法的关系  ******************************/

    // 用一个例子说明
    public class Generic<T>{
        private T key;

        //虽然在方法中使用了泛型，但是这并不是一个泛型方法。
        //这只是类中的一个普通成员方法，只不过它包含了泛型类已经声明过的泛型，所以在这个方法中才可以继续使用 T 这个泛型。
        public Generic(T key) {
            this.key = key;
        }

        public T getKey(){
            return key;
        }

        //这个方法是有问题的，因为在类的声明中并未声明泛型E，而它也没有声明为一个泛型方法<E>，编译器会无法识别
        // 错误信息"cannot reslove symbol E"
//        public E setKey(E key){
//            this.key = key
//        }

        // 这才是一个真正的泛型方法。在返回值前声明了<T>，这个T可以出现在这个泛型方法的任意位置，但是这个T与泛型类已经声明的T没有关系
        public <T> T showKeyName(Generic<T> obj){
            System.out.println("container key :" + obj.getKey());
            T test = obj.getKey();
            return test;
        }

        // 这是一个泛型方法，在返回值前声明了<E>，虽然E未在泛型类后声明，但是泛型方法声明的泛型参数和泛型类声明的本来就无关
        public <E> E showKeyName2(Generic<E> obj){
            System.out.println("container key :" + obj.getKey());
            E test = obj.getKey();
            return test;
        }

        // 这也是一个泛型方法，声明了<T>
        // 注意：List<?> list中的？与泛型声明没有关系，只是表示接收一个类型不限的List，类似于声明为 List<Object>
        public <T> T showKeyName3(Generic<T> obj, List<?> list){
            System.out.println("container key :" + obj.getKey());
            T test = obj.getKey();
            return test;
        }

        //这不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
        public void showKeyValue1(Generic<Number> obj){
            System.out.println("泛型测试, key value is " + obj.getKey());
        }

        //这不是一个泛型方法，这也是一个普通的方法，只不过使用了类型通配符?
        //同时这也印证了?是一种类型实参，可以看做为Number等所有类的父类
        public void showKeyValue2(Generic<?> obj){
            System.out.println("泛型测试, key value is " + obj.getKey());
        }

    }


    /*****************************  限制泛型参数范围   ******************************/

    // ******************  无界泛型参数  ****************
    // 单独的 T，同上


    // ******************  有界泛型参数  ****************

    // extends 关键字：限制泛型参数的上界，用 extends 统一的表示了原有的 extends 和 implements 的概念
    // 方法被调用时，参数类型必须是父类或者父类的子类，或者是继承一个或多个接口的实现类
    // 与普通类继承一样，只能继承一个父类，但是可以同时实现多个接口

    // 重新上面定义的外部泛型方法，把参数类型限制为数字类，并且是实现了Comparable接口的数字类型
    public static <T extends Number & Comparable<T>> void printArrayNum(T[] inputArray) {
        for (T element : inputArray) {
            System.out.printf("%d ", element);
        }
        System.out.println();
    }


    /**************** 类型通配符 & 通过类型通配符限制参数范围（只有方法才能使用通配符） ***************/

    // ******************  类型通配符  ****************

    // ? 不能作为单独的类型使用，编译不通过，只能作为匹配泛型实例的具体类型使用
//    public void printP(? p){
//        System.out.println("P: " + p);
//    }

    // 重新定义打印地图位置的泛型方法为外部的通配符方法，？用来匹配Point实例的各种具体类型，也能起到打印各种类型的Point的作用
    public static void printPoint1(Point<?, ?> p){
        System.out.println("GPS: " + p.getX() + "，" + p.getY());
    }

    // ？在一个方法的声明中，不代表必须是同一种类型，？在一个方法里可以代表不同类型
    public static void printTwoPoints(Point<?, ?> p1,Point<?, ?> p2){
        System.out.println("GPS: p1 x=" + p1.getX() + "，p2 y=" + p2.getY());
    }


    // ******************  无界类型通配符  ****************
    // 单独的 ?，同上


    // ******************  有界类型通配符  ****************

    // extends 关键字：限制通配符类型的上界，用 extends 统一的表示了原有的 extends 和 implements 的概念
    // 方法被调用时，通配符类型必须是父类或者父类的子类，或者是一个接口的实现类
    // 注意：通配符不能继承多个接口，要么继承一个父类，要么继承一个接口

    // 重新定义上面打印地图的外部方法，把参数类型限制为数字类
    public static void printNumPoint(Point<? extends Number, ? extends Number> p){
        System.out.println("GPS: " + p.getX() + "， " + p.getY());
    }

    // 重新定义上面打印地图的外部方法，把参数类型限制为字符串类
    public static void printStrPoint(Point<? extends String, ? extends String> p){
        System.out.println("GPS: " + p.getX() + "，" + p.getY());
    }

    // super 关键字：与extends类似，只是是限制通配符类型的下限，接口没有super的限制
    // 通配符类型为super的超类（父类），下限只能有一个子类

    // 重新定义上面打印地图的外部方法，把参数类型限制为浮点类，不能是整形类
    public static void printDoublePoint(Point<? super Double, ? super Double> p){
        System.out.println("GPS: " + p.getX() + "， " + p.getY());
    }


    /*******************  方法形参选择通配符还是泛型参数的区别  *********************/

    // 1. 类型通配符代替的参数只能是只读 ，不能修改，但是泛型参数代替的参数可以修改，只读的通用参数可以用？代表

    public static <T> void funcT(List<T> list, T t) {
        list.add(t);
    }

    // 首先不能声明 ? t 作为参数，不能给list传递add的对象，其次 ？可以是任意类型，add也不知道要添加什么类型的对象
    public static void func(List<?> list) {
//        list.add();
    }

    // 2. 类型在形参中只出现一次 & 没有返回值依赖它：
    //    例如：<T, E extends T> void func(List<T> l1, List<E> l2);
    //         这里E只在形参中出现了一次，并且没有返回值依赖它，那么就可以把E换成?
    //         最终结果就是：<T> void func(List<T> l1, List<? extends T> l2);

}
