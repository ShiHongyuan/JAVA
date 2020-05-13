package collectionFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * java.util.ArrayList
 * 遍历：
 *      1. 集合类都有迭代器对象java.util.Iterator
 */

public class ListCollection {
    public static void main(String... args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        System.out.printf("Before add: arrayList.size() = %d\n", arrayList.size());//Before add: arrayList.size() = 0

        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(7);
        arrayList.add(9);
        System.out.printf("After add: arrayList.size() = %d\n", arrayList.size());//After add: arrayList.size() = 5


        /*********************** 三种遍历方式  *****************************/
        System.out.println("Printing elements of arrayList");

        // **************  第一种：通过迭代器遍历
        System.out.print("通过迭代器遍历:");
        Iterator<Integer> it = arrayList.iterator();
        while(it.hasNext()){
            System.out.print(it.next() + " ");//通过迭代器遍历:1 3 5 7 9
        }
        System.out.println();

        // **************  第二种：通过索引值遍历
        System.out.print("通过索引值遍历:");
        for(int i = 0; i < arrayList.size(); i++){
            System.out.print(arrayList.get(i) + " ");//通过索引值遍历:1 3 5 7 9
        }
        System.out.println();

        // **************  第三种：for循环遍历
        System.out.print("for循环遍历:");
        for(Integer number : arrayList){
            System.out.print(number + " ");//for循环遍历:1 3 5 7 9
        }
        System.out.println();


        /*********************** 两种转换成数组的方法 —— 都是toArray的用法  *****************************/

        // 有一个泛型方法：<T> T[] toArray(T[] a);
        //          当a的长度小于ArrayList数组的长度时，返回一个新数组引用return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        //          当a的长度等于ArrayList数组的长度时，直接复制到a中返回a，System.arraycopy(elementData, 0, a, 0, size);
        //          当a的长度大于ArrayList数组的长度时，返回a=null的引用，if (a.length > size); a[size] = null; return a;
        // 还有一个无参方法 Object[] toArray();

        // **************  第一种方式(最优)
        // 执行泛型方法<T> T[] toArray(T[] a); 当a的长度小于ArrayList数组的长度时，返回一个新数组引用return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        // 这个匿名实参new String[0]就是起一个模板的作用，指定了返回数组的类型，0是为了节省空间，因为它只是为了说明返回的类型。
        Integer[] integer = arrayList.toArray(new Integer[0]);// 由于JVM优化，new String[0]作为Collection.toArray()方法的参数现在使用更好
        System.out.print("列表转换成数组第一种方式:");
        System.out.println(Arrays.toString(integer));//列表转换成数组第一种方式:[1, 3, 5, 7, 9]


        // **************  第二种方式(容易理解)
        // 执行泛型方法<T> T[] toArray(T[] a); 当a的长度等于ArrayList数组的长度时，直接复制到a中返回a，System.arraycopy(elementData, 0, a, 0, size);
        // 不使用返回值，传递的实参数组也引用转换后的数组，可以直接使用
        System.out.print("列表转换成数组第二种方式:");
        Integer[] integer1 = new Integer[arrayList.size()];
        arrayList.toArray(integer1);
        System.out.println(Arrays.toString(integer1));//列表转换成数组第二种方式:[1, 3, 5, 7, 9]

        System.out.println();


        // **************  错误的方法

        // 执行方法<T> T[] toArray(null); 在判断实参的长度时，就会报空指针的错
//        Integer[] integer2 = null;
//        arrayList.toArray(integer2);// NullPointerException

        // 在没有参数时，只能执行无参方法 Object[] toArray(); 返回Object[]数组，在强转到原类型数组时抛出异常，java不支持向下转型
//        Integer[] integer3 = (Integer[])arrayList.toArray();//Object[] can not be casted to Integer[]


        /*********************** 添加列表元素  *****************************/
        // **************  在指定位置插入元素，指定位置上原来的元素不会被替换，而是向后移动
        arrayList.add(2,2);
        System.out.println(arrayList);//[1, 3, 2, 5, 7, 9]

        /*********************** 删除列表元素  *****************************/

        // **************  删除指定位置上的元素，指定位置后面的元素整体向前移动
        arrayList.remove(2);
        System.out.println(arrayList);//[1, 3, 5, 7, 9]

        // **************  删除指定元素
        arrayList.remove((Object)3);
        System.out.println(arrayList);//[1, 5, 7, 9]

        /*********************** 判断是否包含指定元素  *****************************/

        // **************  判断arrayList是否包含5
        System.out.println("ArrayList contains 5 is: " + arrayList.contains(5));//ArrayList contains 5 is: true

        /*********************** 清空列表元素，元素没有了，size就没有了  *****************************/
        // ************** 清空ArrayList，size=0，size反映的是有效元素数，而不是ArrayList内置数组的实际容量
        arrayList.clear();

        // ************** 判断ArrayList是否为空
        System.out.println("ArrayList is empty: " + arrayList.isEmpty());//ArrayList is empty: true

        // size反映的是有效元素数，而不是ArrayList内置数组的实际容量
        System.out.printf("After clear: arrayList.size() = %d\n", arrayList.size());//After clear: arrayList.size() = 0


        /*********************** 列表长度：size反映的是有效元素数，而不是ArrayList内置数组的实际容量  *****************************/
        // 数组是length，反映实际容量，集合是size，反映有效元素数量
        System.out.printf("arrayList.size() = %d\n", arrayList.size());//arrayList.size() = 0
    }
}
