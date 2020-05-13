package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型有一些需要特别注意的地方：
 *
 *      1. 泛型参数只能是引用类型，不能是基本数据类型
 *
 *      2. 泛型参数与static修饰符的关系：
 *          在类中，因为static方法是共享的，不属于实例，创建泛型类实例时指定的具体类型各异，无法与static共存，所以类中的static成员变量，static普通成员方法无法包含泛型参数
 *          但是，声明为泛型方法的成员方法在调用时才指明具体类型，与实例创建时指定的类型无关，虽然是共享的，但是在Class对象中泛型方法也保留了泛型的特征，不会被类型擦除，所以，声明为泛型方法的成员方法可以与static共存，无法包含泛型参数的普通成员方法无法与static共存
 *
 *          总结：（1）泛型类和泛型接口内，如果成员变量和普通成员方法要使用泛型的话，就不能定义成static
 *               （2）泛型类和泛型接口内，如果静态方法要使用泛型的话，必须将静态方法定义成泛型方法
 *                   public static <T> void show(T t){}
 *
 *      3. 泛型类的实例对象是不兼容的，即使是继承的父子类关系，强转会出错；但是通过通配符传递后强转不会出错
 *
 *      4. 因为类型擦除，不能使用 instanceOf 运算符验证指定了具体类型的泛型类的实例对象
 *
 *      5. 因为类型擦除，不能在一个类中定义形参是相同泛型类的重载方法，两个方法使用相同泛型类作为形参（即使指明不同的具体类型），是不对的
 *
 *      6. 泛型数组：以泛型实例对象作为元素的数组
 *
 *      7. 泛型方法中，形参中的泛型参数不能用于在方法中实例化其对象
 *
 *      8. 泛型类与异常类的关系：
 *          泛型类不允许直接或间接扩展Throwable类
 *          在一个方法中，不允许捕获一个泛型参数的实例
 *          throws子句中允许使用泛型参数
 */

public class GenericSpecial {

    public static void main(String[] args) {

        /*********  泛型类的实例对象是不兼容的，即使是继承的父子类关系，强转会出错；但是通过无界通配符传递后强转不会出错  **********/
        Box<Integer> integerBox = new Box<Integer>();
        Box<Number> numberBox = new Box<Number>();

        //编译不通过， Cannot cast from Box<Number> to Box<Integer>
//        integerBox = (Box<Integer>)numberBox;

        //但是通过无界通配符传递后强转不会出错
        Box<Integer> result = change(numberBox);


        /**************  因为类型擦除，不能使用 instanceOf 运算符验证指定了具体类型的泛型类的实例对象  ******************/

        // instanceOf 是在运行时检查的，但是运行时Box<Integer>会类型擦除，都会变成Box<Object>，所以用instanceOf时没有意义的
        //编译不通过
//        if (integerBox instanceof Box<Integer>) {
//        }
//        if (integerBox instanceof Box<Object>) {
//        }

        //
        if (integerBox instanceof Box) {
            System.out.println("integerBox instanceof Box: true");//integerBox instanceof Box: true
        }


        /***************************  以泛型实例对象作为元素的数组  **************************/


        // *****************  不能创建一个确切的泛型类型的数组
        // 编译错误，前面声明了String类型，后面也new了一个String类型，后面带泛型参数的类型检查不能通过编译
//        List<String>[] ls1 = new ArrayList<>[10];
//        List<String>[] ls2 = new ArrayList<String>[10];


        // *****************  使用通配符创建泛型数组是可以的
        List<?>[] list1 = new List<?>[10]; // ？相当于所有引用类型的父类

        List<Integer> li11 = new ArrayList<>();
        li11.add(new Integer(100));
        list1[0] = li11; // 赋值时相当于指定 list1[1] 这个ArrayList的具体类型是Integer，？代表接受所有类型，所以接受Integer不会报错
        Integer list1li1 = (Integer) list1[0].get(0);//OK
        System.out.println(list1li1);//100

        List<String> li12 = new ArrayList<>();
        li12.add(new String("shy"));
        list1[1] = li12; // 赋值时相当于指定 list1[1] 这个ArrayList的具体类型是String，？代表接受所有类型，所以接受String不会报错
        String list1li2 = (String) list1[1].get(0);//OK
        System.out.println(list1li2);//shy


        // *****************  new 后创建没有指定确切类型的范型数组是可以的
        List<String>[] list2 = new ArrayList[10];

        Object o = list2;
        Object[] oa = (Object[]) o;

        List<String> li21 = new ArrayList<>();
        li21.add(new String("shy"));
        list2[0] = li21;// 只能赋值List<String>，其他类型在编译不通过，类型检查不通过
        String list2li1 = list2[0].get(0);//OK
        System.out.println(list2li1);//shy

    }

    /*********  泛型类的实例对象是不兼容的，即使是继承的父子类关系，强转会出错；但是通过无界通配符传递后强转不会出错  **********/
    static class Box<T> {
        private T t;

        public void add(T t) {
            this.t = t;
        }

        public T get() {
            return t;
        }
    }

    //只有通过无界通配符传递后强转不会出错
    private static Box<Integer> change(Box<?> box){
        Box<Integer> integerBox = (Box<Integer>)box;
        return integerBox;
    }


    /**************  因为类型擦除，不能在一个类中定义形参是相同泛型类的重载方法 ******************/
    class BoxCommon  {
        //编译不通过，print(List<String>) and print(List<Integer>) 被看做是相同的形参签名
        //method print(List<String>) and print(List<Integer>) has the same Erasure
//        public void print(List<String> stringList) { }
//        public void print(List<Integer> integerList) { }
    }


    /**************  泛型方法中，形参中的泛型参数不能用于在方法中实例化其对象  ******************/

    // 泛型方法的方法体内，不能创建泛型参数的实例对象
    public static <T> void add(Box<T> box) {
        //编译错误 Cannot instantiate the type T
//        T item = new T();
//        box.add(item);
    }

    //如果需要创建泛型参数的实例对象，只能用反射
    public static <T> void add(Box<T> box, Class<T> clazz)
            throws InstantiationException, IllegalAccessException{
        //传递一个反射获取的Class对象，可以用它来创建实例对象
        T item = clazz.newInstance();
        box.add(item);
        System.out.println("Item added.");
    }


    /***********************  泛型类与异常类的关系  *************************/

    // ***********  泛型类不允许直接或间接扩展Throwable类
    //编译不通过  The generic class Box<T> may not subclass java.lang.Throwable
//    class BoxException<T> extends Exception {}
//    class BoxThrowable<T> extends Throwable {}


    // ***********  在一个泛型方法中，不允许捕获一个泛型参数的实例
    public static <T extends Exception, J> void execute(List<J> jobs) {
//        try {
//            for (J job : jobs){}
//        }
//        //编译不通过
//        //Cannot use the type parameter T in a catch block
//        catch (T e) {
//            // ...
//        }
    }


    // ***********  throws子句中允许使用泛型参数
    class BoxThrows<T extends Exception>  {
        private int t;

        public void add(int t) throws T {
            this.t = t;
        }

        public int get() {
            return t;
        }
    }
}

