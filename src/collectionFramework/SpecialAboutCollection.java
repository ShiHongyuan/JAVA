package collectionFramework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SpecialAboutCollection {
    public static void main(String... args) {

        /**
         * Arrays.asList()使用方法
         *
         */


        /*******************
         Arrays.asList() 方法返回的并不是 java.util.ArrayList ，
         而是 java.util.Arrays 的一个内部类,
         这个内部类并没有实现集合的 add()/remove()/clear() 方法，或者说并没有重写这些方法
         ********************/
//        // 返回由指定数组支持的固定大小的列表。此方法作为基于数组和基于集合的API之间的桥梁，与Collection.toArray()结合使用。
//        // 返回的Arrays&List是可序列化并实现RandomAccess标识性接口。因为Arrays&List的内置对象还是原数组，数组支持快速随机访问，所以正常实现RandomAccess标识性接口
//        public static <T> List<T> asList(T... a) {
//            return new ArrayList<>(a);
//        }

        /*******************  Arrays.asList() 源码  *****************/
//        private static class ArrayList<E> extends AbstractList<E>
//                implements RandomAccess, java.io.Serializable
//        {
//        ...
//
//            @Override
//            public E get(int index) {
//          ...
//            }
//
//            @Override
//            public E set(int index, E element) {
//          ...
//            }
//
//            @Override
//            public int indexOf(Object o) {
//          ...
//            }
//
//            @Override
//            public boolean contains(Object o) {
//           ...
//            }
//
//            @Override
//            public void forEach(Consumer<? super E> action) {
//          ...
//            }
//
//            @Override
//            public void replaceAll(UnaryOperator<E> operator) {
//          ...
//            }
//
//            @Override
//            public void sort(Comparator<? super E> c) {
//          ...
//            }
//        }

        /*******************  Arrays.asList转换成的List是java.util.Arrays的内部类  ***********/
        List<Integer> asList = Arrays.asList(1, 2, 3);
        System.out.println( asList.getClass() );//class java.util.Arrays$ArrayList

        /*******************
         Arrays.asList()转换成的List实际上还是指向的原数组
         任何一方有修改，另一方也会更改，因为它们的引用内容是一个
         ********************/
        Integer[] integers = {1, 2, 3};
        List<Integer> asList1 = Arrays.asList(integers);
        System.out.println( Arrays.toString(integers) );//[1, 2, 3]
        System.out.println( asList1 );//[1, 2, 3]

        integers[2] = 10;
        asList1.set(0, 6);

        System.out.println( Arrays.toString(integers) );//[6, 2, 10]
        System.out.println( asList1 );//[6, 2, 10]

        /*******************
         java.util.Arrays的内部类List没有实现AbstractList的add/remove/clear
         AbstractList的抽象方法抛出UnsupportedOperationException，
         所以Arrays的内部类List在add/remove/clear时会抛出UnsupportedOperationException
         ********************/

//        public E remove(int index) {
//            throw new UnsupportedOperationException();
//        }

//        asList.remove(1);//java.lang.UnsupportedOperationException
//        asList.add(6);//java.lang.UnsupportedOperationException
//        asList.clear();//java.lang.UnsupportedOperationException

        /************ 如何正确的将数组转换为ArrayList? ***********/
        List asList2 = new ArrayList<>(Arrays.asList("a", "b", "c"));


        /*******************
            Arrays.asList()是泛型方法，传入的对象必须是对象数组
            如果是基本数据类型的数组，整个数组对象本身会被当成Object[]的一个元素
            转换成的List的唯一元素就是这个基本数据类型数组
         ********************/
        int[] myArray = { 1, 2, 3 };
        List asList3 = Arrays.asList(myArray);
        System.out.println(asList3.size());//1
        System.out.println(asList3.get(0));//数组地址值
//        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int [] array=(int[]) asList3.get(0);
        System.out.println(array[0]);//1


        /**
         *  在遍历的过程中 add/remove操作：
         *  必须用迭代器iterator的方法，是安全失败；如果是并发操作，需要对Iterator对象加锁
         *  for-each在遍历过程中列表结构发生改变，是快速失败，会抛出异常ConcurrentModificationException
         *  ***/

        // ***************** 在for-each遍历过程中，删除元素
        // 删除1
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        for (String s: list) {
            if ("1".equals(s)){ //换成1没有异常
                list.remove(s);
            }
        }
        System.out.println(list);//[2]

        // 删除2
//        list.clear();
//        list.add("1");
//        list.add("2");
//        for (String s: list) {
//            if ("2".equals(s)){ //换成2没有异常，会抛出异常java.util.ConcurrentModificationException
//                list.remove(s);
//            }
//        }

        // 我也不知道为什么1和2不一样

        // ***************** 在iterator遍历过程中，删除元素
        // 删除1
        list.clear();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if ("1".equals(iterator.next())){ //换成1没有异常
                iterator.remove();
            }
        }
        System.out.println(list);//[2]

        // 删除2
        list.clear();
        list.add("1");
        list.add("2");
        iterator = list.iterator();
        while (iterator.hasNext()) {
            if ("2".equals(iterator.next())){ //换成2也没有异常
                iterator.remove();
            }
        }
        System.out.println(list);//[1]

    }
}
