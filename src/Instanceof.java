import java.util.ArrayList;
import java.util.List;

/**
 * java的八种基本数据类型，三种引用类型，一种特殊类型
 *      1. 八种基本数据类型：byte  short  int  long  float  double  char boolean
 *      2. 三种引用类型：类，接口，数组
 *      3. 一种特殊类型：null：该类型没有名字，所以不可能声明为 null 类型的变量或者转换为 null 类型，null 引用是 null 类型表达式唯一可能的值，null 引用也可以转换为任意引用类型
 *
 *
 * instanceof 的实现策略（S instanceof T，S是实例对象或者数组，必须是三种引用类型的实例或者null，不能是基本数据类型，T是类或接口或数组类型，不能是实例)
 *      返回就是(T)S强转报错吗？不报错是true，报错是false
 *      1. S 为 null都返回false
 *      2. S 为 T 类的实例对象，看S是不是T的实例对象。
 *      3. S 为 T 接口的实现类，遍历S里记录的它所实现的接口，看有没有跟T一致的。
 *      4. S 为 T 类的直接或间接子类的实例对象，遍历S的super链（继承链）一直到Object，看有没有跟T一致的。
 *      5. S 为 数组，要是true的话，如果T是类，T必须是Object；如果T是接口，T必须是该数组实现的接口之一；如果T是数组类型，T必须是S的类型或者父类型的数组类型。
 *
 * instanceof 不合法参数：
 *      1. S 不能是类，只能是实例或者数组或者null
 *      2. S 不能是基本数据类型
 *      3. T 不能是实例
 *      4. T 不能是null
 *      5. T 如果是泛型类或者泛型接口，instanceof 后面必须是泛型，不能指定为具体类或者具体接口
 *
 * instanceof 的实现
        boolean result;

        if (obj == null) {
            result = false;
        }
        else {
            try {
                T temp = (T) obj; // checkcast
                result = true;
            } catch (ClassCastException e) {
            result = false;
            }
        }
 *
 *
 *
 **/
public class Instanceof {
    public static void main(String[] args) throws Exception {
        // S 必须为引用类型的实例，不能是基本类型
//        System.out.println(1 instanceof Integer);//编译不通过
        int i = 1;
//        System.out.println(i instanceof Integer);//编译不通过
//        System.out.println(i instanceof Object);//编译不通过
        System.out.println((Integer) i instanceof Integer);//true
        System.out.println((Integer) i instanceof Object);//true

        // S 不能是类或者接口
//        System.out.println(Integer instanceof Integer);//编译不通过
//        System.out.println(List instanceof List);//编译不通过

        // T 不能是NULL
//        System.out.println((Integer) i instanceof null);//编译不通过

        // T 不能是实例
        Object obj = new Object();
//        System.out.println(obj instanceof obj);//编译不通过


        // S 为 null都返回false
        System.out.println(null instanceof Object);//false

        // S 为 T 类的实例对象，看S是不是T的实例对象
        Integer integer = new Integer(6);
        System.out.println(integer instanceof Integer);//true

        // S 为 T 接口的实现类，遍历S里记录的它所实现的接口，看有没有跟T一致的
        /**
         * public class ArrayList<E> extends AbstractList<E>
                implements List<E>, RandomAccess, Cloneable, java.io.Serializable

            ArrayList是一个类，List是一个接口，ArrayList实现了List
         */

        ArrayList arrayList = new ArrayList();
        System.out.println(arrayList instanceof List);//true
        System.out.println(arrayList instanceof ArrayList);//true

            //注意list实际对象还是ArrayList
            List list = new ArrayList();
            System.out.println(list instanceof List);//true
            System.out.println(list instanceof ArrayList);//true


            // 如果 T 是一个泛型类或者泛型接口，instanceof 后面只能是泛型类或者泛型接口，而是一个具体的类，所以不对
            ArrayList<Integer> integerArrayList = new ArrayList<>();
            List<Integer> integerList = new ArrayList<>();
//            System.out.println(integerArrayList instanceof List<Integer>);//编译不通过
//            System.out.println(integerArrayList instanceof ArrayList<Integer>));//编译不通过


        // S 为 T 类的直接或间接子类的实例对象，遍历S的super链（继承链）一直到Object，看有没有跟T一致的
        System.out.println(integer instanceof Object);//true

        // S 为 数组，要是true的话，如果T是类，T必须是Object；如果T是接口，T必须是该数组实现的接口之一；如果T是数组类型，T必须是S的类型或者父类型的数组类型
        int[] intArray = new int[]{1, 2, 3};
        Integer[] integerArray = new Integer[]{1, 2, 3};

        System.out.println(intArray instanceof Object);//true 注意任何数组，包括基本数据类系的数组时引用类型，都是Object的子类
        System.out.println(integerArray instanceof Object);//true


        System.out.println(intArray instanceof int[]);//true
        System.out.println(integerArray instanceof Integer[]);//true
        System.out.println(integerArray instanceof Object[]);//true

    }

}
