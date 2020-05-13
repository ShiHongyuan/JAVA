package generic;

import java.io.Serializable;
import java.util.Random;

/**
 * 泛型接口（与泛型类差不多）：
 *      声明为一个泛型接口：
 *          单个泛型参数：接口名后紧跟<T>
 *          多个泛型参数：接口名<T, S>等
 *          有界泛型参数：接口名<T extends Comparable>，<T extends Number & Comparable & Cloneable>等
 *
 *      声明后，可以使用声明的泛型参数的地方：
 *          成员变量：private T var;
 *          成员方法：public T func();
 *
 *          因为使用的是已被类声明的泛型参数 T，所以成员变量和成员方法这里的 T 必须与创建对象时指定的类型相同
 *          成员方法里使用被类声明的泛型参数 T，不代表就是泛型方法，泛型方法需要额外声明的，这里的成员变量和成员方法只是泛型类应有的一个表现而已
 *
 *
 *      指定具体类型的时候：定义一个泛型接口的实现类的时候
 *                      定义已经指定类型接口的实现类：在implements 接口名的后面<具体类型>，实现类就是普通类了，创建实例的时候用普通类的方式
 *                      创建一个匿名实现接口的实例：因为匿名，只能在new的接口名的后面<具体类型>，同时实现接口抽象方法，产生的实例引用赋值给前面声明时指明类型的接口引用
 *
 *      泛型参数范围的限制
 *          无界类型参数：单独声明的参数，如 T，没有限制
 *          有界类型参数：
 *              extends 关键字：用于限制泛型参数的上界，创建实例时，指定类型必须是父类或者父类的子类，或者继承一个或多个接口
 *                            与普通类继承一样，只能继承一个父类，但是可以同时实现多个接口
 *
 *          注意：泛型接口的泛型参数 T 只有extends，没有super，而且泛型接口不能声明类型通配符
 *
 *
 *      类实现泛型接口：
 *          泛型类实现泛型接口：保留泛型接口的泛型参数声明，即保留<T>，并且泛型类名后也声明同样的泛型参数<T>
 *          非泛型类实现泛型接口：泛型接口的泛型参数声明必须被指定为具体类型，并且实现类是普通类，没有声明泛型参数
 */


public class GenericInterface {
    public static void main(String[] args){

        /****************************  泛型接口（通用接口）& 指定具体类型的时候  **********************************/

        //接口不能实例化，这是创建一个实现了接口的匿名类，然后让接口引用指定匿名类
        // 由于匿名类没有实例声明可以指定泛型的具体类型，所以在new List<String>必须在<>内指定具体类型
        List<String> list = new List<String>() {


            @Override
            public void set(String item) {

            }

            @Override
            public String get(int index) {
                return null;
            }
        };

        /****************************  限制泛型参数范围  **********************************/
        // 见下

        /****************************  类实现泛型接口   **********************************/
        String[] fruits = new String[]{"Apple", "Banana", "Pear"};
        Integer[] IDs = new Integer[]{1, 2, 3};

        // 在实现泛型接口时，没有指定泛型接口的具体类，则实现类是泛型类，需要在实例化是指定具体类，更具通用性
        GenricGenerator<String> generator1 = new GenricGenerator<>();
        System.out.println(  generator1.next(fruits) );//Banana

        GenricGenerator<Integer> generator2 = new GenricGenerator<>();
        System.out.println(  generator2.next(IDs) );//2

        // 在实现泛型接口时，指定了泛型接口的具体类，则实现类就是普通类，不用指定泛型参数了
        FruitGenerator fruitGenerator = new FruitGenerator();
        System.out.println( fruitGenerator.next(fruits) );//Apple

        IDGenerator idGenerator = new IDGenerator();
        System.out.println( idGenerator.next(IDs) );//3

    }

    /****************************  泛型接口（通用接口）  **********************************/

    interface List<T> {
    //编译报错，因为接口的变量默认是静态最终变量public final static，T在不同实现类中类型不一样，与final冲突，所以泛型接口中不能定义泛型参数的成员变量
//    T var;

    // 接口的方法中，未实现方法体的抽象方法默认只有public，所以可以定义包含泛型参数的抽象方法
    // 但是在接口中已实现方法体的static方法，由于static与泛型参数不能共存，同样也是因为static方法是实现类共享的，但是实现类的类型不一样，所以泛型接口中不能定义包含泛型参数的static方法
    public void set(T item);
    public T get(int index);
}

    /****************************  限制泛型参数范围  **********************************/

    // ******************  无界类型参数  ****************
        // 单独的 T，同上


    // extends 关键字：限制泛型参数类或者泛型接口的上界，用 extends 统一的表示了原有的 extends 和 implements 的概念
    // 元素只能是数字类型的List
    public interface ListNumber<T extends Number> {
        public void set(T item);
        public T get(int index);
    }

    // 泛型参数是extends的接口的实现类，可以实现多个接口，用&分隔
    // 元素是实现了比较，克隆和序列化接口的类型
    public interface ListNumber1<T extends Comparable<T> & Cloneable & Serializable> {
        public void set(T item);
        public T get(int index);
    }

    // 泛型参数是extends的子类和接口的实现类，只能继承一个父类，但是多个接口，并且类写在第一位，接口列在后面，类和接口也用&分隔
    // 元素是既继承了Number父类，也实现了比较，克隆和序列化接口的类型
    public interface ListNumber2<T extends Number & Comparable<T> & Cloneable & Serializable> {
        public void set(T item);
        public T get(int index);
    }



    /****************************  类实现泛型接口   **********************************/
    public interface Generator<T> {
        public T next(T[] array);
    }

    // 如果实现泛型接口时，没有确定类型，其实现类也必须是同样声明的泛型类，否则编译不通过
    static class GenricGenerator<T> implements Generator<T>{

        @Override
        public T next(T[] array) {
            Random rand = new Random();
            return array[rand.nextInt(3)];
        }
    }

    // 如果实现泛型接口时，确定了具体类型，实现类就是一个有明确类型的普通类了
    static public class FruitGenerator implements Generator<String> {

        @Override
        public String next(String[] fruits) {
            Random rand = new Random();
            return fruits[rand.nextInt(3)];
        }
    }

    static public class IDGenerator implements Generator<Integer> {

        @Override
        public Integer next(Integer[] IDs) {
            Random rand = new Random();
            return IDs[rand.nextInt(3)];
        }
    }



}


