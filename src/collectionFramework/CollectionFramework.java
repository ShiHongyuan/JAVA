package collectionFramework;

import java.util.*;
import java.util.function.Predicate;

/**
 * java集合框架：是一种数据结构，也是一种容器对象
 *
 *      数据结构，容器：能够存储其他对象的对象 + 访问和操作这些对象
 *      两种类型的容器：
 *          1. 集合（存储元素集合）
 *          2. 图（存储键值对）
 *
 *      Java集合框架包括：
 *          1. 规则集set（不重复的集合，元素没有索引，不能指定index访问元素）
 *          2. 线性表List（有序集合，元素有索引，可以指定index访问元素）Stack算是列表的一种实现
 *          3. 队列Queue（先进先出集合，元素没有索引，不能指定index访问元素）
 *          4. 图（键值对，元素没有索引，不能指定index访问元素）
 *
 *      Java集合框架的接口，抽象类，实现的具体类都是泛型，都在java.util.*中：
 *          1. 所有集合对象的根接口是 Collection<E>
 *          2. 所有图对象的根接口是 Map<E>
 *
 *      Java集合框架的具体类都实现了java.lang.Cloneable 和 java.io.Serializable接口，可复制可序列化
 *
 *      Java集合框架的具体类都重写了toString()，可以直接打印出元素列表
 *
 *
 * 迭代器：
 *      是一个定义了遍历集合或者图的抽象方法的接口，Iterator<E>接口
 *      每个集合或者图的具体实现类会创建一个匿名类，并按照自己的特点实现Iterator<E>接口的方法，作为iterator()方法的返回
 *
 *      ListIterator<E>接口：是Iterator<E>接口的扩展接口，增加了两边方向都可以遍历的抽象方法，使用方式同Iterator<E>接口，只不过只有列表类的listIterator()方法会使用
 *
 *  hashCode和equals：
 *      元素的hashCode和equals：
 *              hashCode原始是地址值，equals原始也是判断引用是否相等==。
 *              但是集合需要判断元素的值相等就不能添加，所以需要重写元素的hashCode和equals。
 *              元素的hashCode根据元素的具体类型有不同的算法，元素的equals先判断元素的hashCode是否相等，如果不相等，则元素的值一定不相等，如果相等，则元素的值也不一定相等，有可能只是hashCode刚好相等，所以还需要挨个判断值是否相等的方法。
 *              所以判断元素是否相等，只需要调用元素的equals方法，元素的equals方法里会用到元素的hashCode方法。
 *
 *      集合的hashCode和equals（也是HashSet检查元素，HashMap检查键值重复的方法）：
 *              集合的hashCode一般是根据元素的hashCode累积相加的。
 *              集合的equals首先会判断集合的hashCode是否相等，如果不相等，则集合的值一定不相等，如果相等，则集合的值也不一定相等，有可能只是hashCode刚好相等，所以还需要挨个调用元素的equals判断元素值是否相等的方法。
 *              所以判断集合是否相等，只需要调用集合的equals方法，集合的equals方法里会用到集合的hashCode方法，也会用到元素的equals方法。
 *
 *      所以，hashCode和equals必须同时重写，如果只重写任意一个，另一个是按照引用是否相等判断，会造成原本值相等的变得不等。
 *      用在集合里就可能造成添加重复元素了。
 *
 *
 * 具体类：
 *      作用：
 *          1. HashSet：普通的规则集
 *          2. LinkedHashSet：按照插入顺序排序存储的规则集
 *          3. TreeSet：按照指定顺序排序存储的规则集
 *          4. ArrayList：内置对象是数组，读方便，任意位置写麻烦的简单列表
 *          5. LinkedList：内置对象是双向链表，既是列表，也是队列，还是栈，是任意位置写快，读没ArrayList快的列表，也是继承了双端操作方法的双端队列，还是正向栈和反向栈
 *          6. PriorityQueue：按照指定顺序排序，越排在前面越优先出队，的队列
 *          7. HashMap：普通的图，map的键值是final类型的，所以map的键是唯一的，从而条目也是唯一的
 *          8. LinkedHashMap：按照插入顺序或者最近访问顺序排序存储的图
 *          9. TreeMap：键值按照指定顺序排列存储的图
 *
 *       内置对象数据结构：
 *          1. HashSet：内置对象是HashMap
 *          2. LinkedHashSet：内置对象是LinkedHashMap
 *          3. TreeSet：内置对象是红黑树（自平衡的排序二叉树）
 *          4. ArrayList：内置对象是数组
 *          5. LinkedList：内置对象是双向链表(JDK1.6之前为循环链表），没有了循环，迭代器的hasPrevios和hasNext才有了false
 *          6. PriorityQueue：内置对象是链表，
 *          7. HashMap：数组+链表/红黑二叉树：
 *                      JDK1.8之前HashMap由数组+链表组成的，也就是链表数组（拉链法）。数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）。
 *                      JDK1.8以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。
 *          8. LinkedHashMap：内置对象是仍然是基于拉链式散列结构即由数组和链表或红黑树组成。
 *                            另外，LinkedHashMap 在上面结构的基础上，增加了一条双向链表，使得上面的结构可以保持键值对的插入顺序。
 *                            同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。
 *          9. TreeMap：内置对象是红黑树（自平衡的排序二叉树）
 *          10.Hashtable：数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的
 *
 *
 *          TreeMap、TreeSet、JDK1.8之后的HashMap内置对象都是红黑树，所以才会实现快速自排序，红黑树就是为了解决二叉查找树的缺陷，因为二叉查找树在某些情况下会退化成一个线性结构。
 *
 *
 * 其他公用方法说明：
 *      集合对象：
 *          size反映的是有效元素数，而不是规则集，列表，队列内置对象的实际容量（数组是length，反映实际容量，集合是size，反映有效元素数量）
 *          迭代器和for-each是按照存储的顺序读的
 *          规则集，列表，队列对象和数组都实现了for-each循环，按照存储顺序读
 *          规则集，列表，队列都有toArray方法，可以转换成数组
 *          只有ArrayList和LinkedList有 sort(Comparator c); 排序方法
 *          只有ArrayList和LinkedList有三种遍历方式（包括for循环遍历），规则集和队列的元素没有顺序，所以只有两种遍历方式（不包括for循环遍历）
 *          规则集，列表，队列的containAll与元素的位置无关，只要都有，就是true。
 *          规则集，列表，队列的retainAll与元素的位置无关，只要有就保留。
 *          规则集的hashCode和equals都与元素的位置无关；但是列表的hashCode与元素的位置无关，equals与位置有关；队列的hashCode和equals都与元素的位置有关。
 *          只有规则集和列表实现了java.lang.Cloneable接口，有 Object clone(); 方法，可以得到一个集合转换而来的Object，但是优先队列PriorityQueue不能执行clone方法。
 *          规则集，列表，队列对象都实现了java.io.Serializable接口，都可对象序列化
 *
 *      图对象：
 *          size反映的是有效元素数，而不是HashMap内置对象的实际容量
 *          map的hashCode使用键值来计算图对象的hashCode，图对象的hashCode和equals都与条目的位置无关
 *          map实现了java.lang.Cloneable接口和java.io.Serializable接口，可克隆可对象序列化，有 Object clone(); 方法，可以得到一个由图转换而来的Object
 *          map没有toArray和sort方法
 *          map自身没有遍历方法，只能通过keySet方法、values方法、entrySet方法分别获取键值，值，条目的集合，然后遍历它们
 *
 *
 *
 * Arraylist 与 LinkedList 区别：
 *      1. 是否保证线程安全： ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；
 *      2. 底层数据结构： Arraylist 底层使用的是 Object 数组；LinkedList 底层使用的是 双向链表 数据结构
 *                    （JDK1.6之前为循环链表，JDK1.7取消了循环。注意双向链表和双向循环链表的区别，没有了循环，迭代器的hasPrevios和hasNext才有了false）
 *      3. 插入和删除是否受元素位置的影响：① ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响，近似 O（n）
 *                                  ② LinkedList 采用链表存储，所以插入，删除元素时间复杂度不受元素位置的影响，都是近似 O（1）
 *      4. 是否支持快速随机访问：LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。快速随机访问就是通过元素的序号快速获取元素对象(对应于get(int index)方法)。
 *      5. 内存空间占用： ArrayList的空 间浪费主要体现在在list列表的结尾会预留一定的容量空间，而LinkedList的空间花费则体现在它的每一个元素都需要消耗比ArrayList更多的空间（因为要存放直接后继和直接前驱以及数据）。
 *
 *
 *
 * RandomAccess标识性接口：标识实现这个接口的类具有随机访问功能。
 *      扩展RandomAccess接口的底层内置对象一般是数组，数组天然支持随机访问，时间复杂度为 O（1），所以称为快速随机访问。
 *
 *      ArrayList 实现了 RandomAccess 接口， 而 LinkedList 没有实现，其他除了Vector和Stack之外都是不会数组，都没有实现RandomAccess标识性接口。
 *
 * 遍历方式的耗时比较：
 *      1. 实现了 RandomAccess 接口的list（即ArrayList），优先选择普通for循环。遍历时间排序：
 *         遍历时间排序：for循环 < for-each（foreach底层也是iterator实现的） < 迭代器iterator
 *
 *      2. 未实现 RandomAccess接口的list（如LinkedList），大size的数据，千万不要使用普通for循环。
 *         遍历时间排序：for-each（foreach底层也是iterator实现的） < 迭代器iterator < for循环
 *
 *      另外，用如LinkedList或者PriorityQueue的queue操作来遍历：for-each < pollFirst()或pollLast() < 迭代器iterator < for循环
 *
 * ArrayList的扩容机制：
 *      if 有效元素数量 > 内置数组的容量*容座率 then 按照一定规则扩容
 *
 *
 * ArrayList 与 Vector 区别呢?为什么要用Arraylist取代Vector呢？
 *      Vector类的所有方法都是同步的。可以由两个线程安全地访问一个Vector对象、但是一个线程访问Vector的话代码要在同步操作上耗费大量的时间。
 *      Arraylist不是同步的，所以在不需要保证线程安全时建议使用Arraylist。
 *
 * HashMap 和 Hashtable 的区别：
 *      1. 线程是否安全： HashMap 是非线程安全的，HashTable 是线程安全的；HashTable 内部的方法基本都经过synchronized 修饰。（如果你要保证线程安全的话就使用 ConcurrentHashMap 吧！）
 *      2. 效率： 因为线程安全的问题，HashMap 要比 HashTable 效率高一点。另外，HashTable 基本被淘汰，不要在代码中使用它；
 *      3. 对Null key 和Null value的支持： HashMap 中，null 可以作为键，这样的键只有一个，可以有一个或多个键所对应的值为 null。。但是在 HashTable 中 put 进的键值只要有一个 null，直接抛出 NullPointerException。
 *      4. 底层数据结构： JDK1.8 以后的 HashMap 在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。Hashtable 没有这样的机制。
 *      5. 初始容量大小和每次扩充容量大小的不同 ： ①创建时如果不指定容量初始值，Hashtable 默认的初始大小为11，之后每次扩充，容量变为原来的2n+1。HashMap 默认的初始化大小为16。之后每次扩充，容量变为原来的2倍。②创建时如果给定了容量初始值，那么 Hashtable 会直接使用你给定的大小，而 HashMap 会将其扩充为2的幂次方大小（也就是说 HashMap 总是使用2的幂作为哈希表的大小）。
 *
 *
 * HashMap 和 HashSet区别：
 *      HashSet 底层就是基于 HashMap 实现的。
 *      除了 clone()、writeObject()、readObject()是 HashSet 自己不得不实现之外，其他方法都是直接调用 HashMap 中的方法。
 *
 *
 * HashMap的底层实现：
 *      JDK1.8 之前：
 *          HashMap 底层是 数组和链表 结合在一起使用也就是 链表散列。
 *          HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash 值，然后通过 (n - 1) & hash 判断当前元素存放的位置（这里的 n 指的是数组的长度），
 *          如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。
 *
 *          扰动函数：就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止一些实现比较差的 hashCode() 方法 换句话说使用扰动函数之后可以减少碰撞。
 *
 *          “拉链法” ：将链表和数组相结合。也就是说创建一个链表数组，数组中每一格就是一个链表。若遇到哈希冲突，则将冲突的值加到链表中即可。
 *       JDK1.8之后：
 *          在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。
 *
 * HashMap 的长度为什么是2的幂次方：
 *      JDK1.8之前，还用的是数组链表的时候，为了让 Hash值 能映射到数组的长度，需要取模运算。
 *      但是如果采用二进制位操作 &，相对于%能够提高运算效率，
 *      也就是说 hash%length==hash&(length-1)，只是前提是length是2的n次方，
 *      所以HashMap的初始容量和扩展容量的长度都是2的幂次方
 *
 * HashMap 多线程操作导致死循环问题：
 *      主要原因在于 并发下的Rehash 会造成元素之间会形成一个循环链表。
 *      不过，jdk 1.8 后解决了这个问题，但是还是不建议在多线程下使用 HashMap,因为多线程下使用 HashMap 还是会存在其他问题比如数据丢失。
 *      并发环境下推荐使用 ConcurrentHashMap 。
 *
 */

public class CollectionFramework {

    public static void main(String... args) {
        /********************  迭代器接口 Iterator<E> 抽象方法 ************************/
        // ******************  从头开始，判断下一个元素的指针，是否有元素
        // boolean hasNext();

        // ******************  指针移动到下一个元素，获取元素
        // E next();

        // ******************  删除当前指针的元素
        // void remove();


        /********************  所有集合对象的根接口 Collection<E> 抽象方法  ************************/

        // ******************  添加元素
        // boolean add(E o); 添加单个元素
        // boolean addAll(Collection<? extends E>); 添加一个任意集合对象的所有元素，但是其元素必须是当前对象具体类的子类

        // ******************  删除元素
        // boolean remove(Object o); 删除跟指定元素匹配的元素
        // boolean removeAll(Collection<?> c); 删除跟任意集合对象的所有元素匹配的元素
        // clear(); 清空所有元素，size=0

        // ******************  元素个数
        // int size(); 返回有效元素个数，跟内置对象的容量无关，不像数组，length返回数组容量，size返回内置对象的有效元素的个数
        // boolean isEmpty(); 判断判断size=0?

        // ******************  判断是否包含元素
        // boolean contains(Object o); 是否包含跟指定的元素匹配的元素
        // boolean containsAll(Collection<?> c);  是否包含传入集合的全部元素


        // ******************  获取迭代器对象，遍历元素
        // Iterator<E> iterator();

        // ******************  hashCode和equals
        // int hashCode();  返回集合的hashCode值，根据元素的hashCode累加的，元素的hashCode根据不同的类型计算方式不一样
        // boolean equals(Object o);  判断集合和传参是否每个元素都相等，集合的equals方法里会用到集合的hashCode方法和元素的hashCode方法

        // ******************  求集合的交集
        // boolean retainAll(Collection<?> c); 保留集合和传入集合都有的相同元素，会改变原集合


        // ******************  集合转换成数组
        // Object[] toArray();  返回Object[]的数组，子类不能转父类
        // <E> E[] toArray(E[] e); 泛型方法，需要传入确切类型的数组名字，返回一个相同具体类型的数组引用


        /********************  规则集具体类 HashSet<E> 具体方法  ************************/


        // ******************  创建HashSet
        // 第一种方式
        HashSet<Integer> hashSet = new HashSet<>();
        // 第二种方式，另一个集合对象赋值
        HashSet<Integer> hashSet1 = new HashSet<>();
        HashSet<Integer> hashSet2 = new HashSet<>(hashSet1);
        // 第三种方式，指定初始容量和客座率
        HashSet<Integer> hashSet3 = new HashSet<>(20);// 指定内置对象HashMap的初始容量是16
        HashSet<Integer> hashSet4 = new HashSet<>(20, 0.6f);//客座率是个float，当超过20*0.6=12的客座时，内置对象就要扩充了

        // ******************  获取反射类
        System.out.println( "HashSet的反射类：" + hashSet.getClass() );//HashSet的反射类：class java.util.HashSet


        // ******************  添加元素
        // 添加单个元素
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(1);//不能添加重复元素，不会报错，但是会忽略

        // 添加一个任意集合对象的所有元素，但是其元素必须是当前对象具体类的子类
        hashSet1.addAll(hashSet);
        hashSet2.addAll(hashSet);

        // ******************  打印整个元素
        // toString()被默认调用，Java集合框架的具体类都重写了toString()，可以直接打印出元素列表
        System.out.println(hashSet.toString());//[1, 2, 3, 5, 6]
        System.out.println(hashSet1);//[1, 2, 3, 5, 6]

        // ******************  两种遍历方法，规则集元素没有索引，不能指定index访问元素，所以没有for循环的遍历方法
        // 第一种方式，迭代器
        Iterator<Integer> hashSetIterator = hashSet.iterator();
        while(hashSetIterator.hasNext()) {
            System.out.print(hashSetIterator.next() + " ");//1 2 3 5 6
        }
        System.out.println();

        // 第二种方式，for-each
        for(Integer i : hashSet) {
            System.out.print(i + " ");//1 2 3 5 6
        }
        System.out.println();

        // ******************  删除元素 & 元素个数
        // 删除跟指定元素匹配的元素
        hashSet1.remove(2);
        System.out.println(hashSet1.toString());//[1, 3, 5, 6] 少了2
        System.out.println( hashSet1.size() );//4
        System.out.println( hashSet1.isEmpty() );//false

        // 删除跟任意集合对象的所有元素匹配的元素
        // size()返回有效元素个数，跟内置对象的容量无关，不像数组，length返回数组容量，size返回内置对象的有效元素的个数
        // isEmpty() 判断size=0?
        hashSet1.removeAll(hashSet);
        System.out.println( hashSet1.size() );//0
        System.out.println( hashSet1.isEmpty() );//true

        // 清空所有元素，size=0
        hashSet1.clear();
        System.out.println( hashSet1.size() );//0
        System.out.println( hashSet1.isEmpty() );//true

        // ******************  判断是否包含元素
        // 是否包含跟指定的元素匹配的元素
        System.out.println( hashSet.contains(1) );//true
        // 是否包含传入集合的全部元素，与元素的位置无关
        System.out.println( hashSet.containsAll(hashSet2) );//true


        // ******************  hashCode和equals
        System.out.println( hashSet.hashCode() );//17，1+2+3+5+6=17
        System.out.println( hashSet2.hashCode() );//17

        // 判断与传入集合的所有元素的值都相同，与元素的位置无关
        System.out.println( hashSet.equals(hashSet2) );//true

        // ******************  求集合的交集
        // 与一个空的规则集的交集也是空的，原集合变为交集，元素交集与元素的位置无关
        hashSet2.retainAll(hashSet1);
        System.out.println(hashSet2.toString());//[]


        // ******************  集合转换成数组
        // 返回Object[]的数组，子类不能转父类
        Object[] o = hashSet.toArray();
//        Integer[] integers = (Integer[])hashSet.toArray(); // 运行时出错，Object[] can not casted to Integer[]
        Integer[] h = hashSet.toArray(new Integer[0]);
        Integer[] h1 = new Integer[hashSet.size()];
        hashSet.toArray(h1);
        System.out.println(Arrays.toString(o));//[1, 2, 3, 5, 6]
        System.out.println(Arrays.toString(h));//[1, 2, 3, 5, 6]
        System.out.println(Arrays.toString(h1));//[1, 2, 3, 5, 6]

        // ******************  集合框架的具体类（除了priorityQueue）都实现了java.lang.Cloneable 和 java.io.Serializable接口，可复制可序列化
        // 克隆成一个新的Object对象，不能还原成一个相同的HashSet对象
        Object obj = hashSet.clone();
        System.out.println(obj.toString());//[1, 2, 3, 5, 6]
        hashSet.add(9);
        System.out.println(obj.toString());//[1, 2, 3, 5, 6]
        obj = null;
        System.out.println(hashSet.toString());//[1, 2, 3, 5, 6, 9]

        // ******************  1.8新增方法  ********************
        // Spliterator<E> spliterator(); spliterator是 split iterator的意思。也就是返回一个迭代器，但是这个迭代器是分割的，将元素分割成若干组，并不像原有的迭代器只能通过单一线程顺序的访问。它可以通过多线程并行的形式，访问容器中的元素，加快元素的访问效率。
        Spliterator<Integer> spliterator1 = hashSet.spliterator();

        // Stream<E> stream(); 函数式编程，Stream是Java8中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，但是将执行操作的时间交给具体实现来决定。
        hashSet.stream();

        // Stream<E> parallelStream(); stream的并发版本
        hashSet.parallelStream();

        // boolean removeIf(Predicate<? super E>); 过滤条件删除，函数式表达式，通过lambda表达式减少代码量
        // 也可以不使用lambda表达式，删除小于3的元素
        System.out.println("removeIf 删除小于3的元素前：" + hashSet.toString());//removeIf 删除小于3的元素前：[1, 2, 3, 5, 6, 9]
        hashSet.removeIf(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer < 3;//删除小于3的元素
            }
        });
        System.out.println("removeIf 删除小于3的元素后：" + hashSet.toString());//removeIf 删除小于3的元素后：[3, 5, 6, 9]

        // void forEach(Comsumer<? super E>); 函数式编程，遍历集合对象
        // 使用lambda表达式，只遍历大于3的元素
        hashSet.forEach(item -> {
            if(item > 3) {
                System.out.print(item + " ");//5 6 9
            }
        });
        System.out.println();


        /********************  规则集具体类 LinkedHashSet<E> 具体方法  ************************/
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        // ******************  跟HashSet不一样的是：按插入顺序存储的  ********************
        linkedHashSet.add(2);
        linkedHashSet.add(1);
        linkedHashSet.add(6);
        linkedHashSet.add(3);
        linkedHashSet.add(9);
        linkedHashSet.add(5);


        System.out.println(hashSet.toString());//[1, 2, 3, 5, 6, 9]
        System.out.println(linkedHashSet.toString());//[2, 1, 6, 3, 9, 5]


        // ******************  其他方法跟 HashSet 一样  ********************


        /********************  规则集具体类 TreeSet<E> 具体方法  ************************/

        // ******************  跟HashSet不一样的是：按照元素的比较顺序存储，或者创建时指定元素的比较器  ********************
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(2);
        treeSet.add(1);
        treeSet.add(6);
        treeSet.add(3);
        treeSet.add(5);


        TreeSet<Integer> treeSet1 = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); // 指定顺序为降序
            }
        });
        treeSet1.addAll(treeSet);

        System.out.println("按照元素的比较顺序存储： " + treeSet.toString());//按照元素的比较顺序存储： [1, 2, 3, 5, 6]
        System.out.println("创建时指定元素的比较器： " + treeSet1.toString());//创建时指定元素的比较器： [6, 5, 3, 2, 1]


        // ******************  TreeSet特有的方法
        // 获取TreeSet的比较器
        System.out.println( treeSet.comparator() );//null，按照元素实现的Comparable接口排序的，没有用到比较器
        System.out.println( treeSet1.comparator() );//collectionFramework.CollectionFramework$1@1d44bcfa，指定的比较器


        // ******************  SortedSet<E>接口 扩展的方法，都被TreeSet继承实现
        // E first(); 返回TreeSet顺序存储的第一个元素
        Integer i = treeSet.first();
        System.out.println("TreeSet顺序存储的第一个元素：" + i);//TreeSet顺序存储的第一个元素：1

        // E last();  返回TreeSet顺序存储的最后一个元素
        i = treeSet.last();
        System.out.println("TreeSet顺序存储的最后一个元素：" + i);//TreeSet顺序存储的最后一个元素：6

        // SortedSet<E> headSet(E e);  返回元素小于e的前面部分Set
        SortedSet<Integer> sortedSet = treeSet.headSet(4);
        System.out.println("TreeSet小于指定元素的前部分规则集：" + sortedSet.toString());//TreeSet小于指定元素的前部分规则集：[1, 2, 3]


        // SortedSet<E> tailSet(E e);  返回元素大于等于e的后面部分Set
        sortedSet = treeSet.tailSet(4);
        System.out.println("TreeSet大于等于指定元素的后部分规则集：" + sortedSet.toString());//TreeSet大于等于指定元素的后部分规则集：[5, 6]

        // SortedSet<E> subSet(E e1, E e2);  返回元素大于等于e1，小于e2的中间部分Set
        sortedSet = treeSet.subSet(2,5);
        System.out.println("TreeSet在指定元素区间的中间部分规则集：" + sortedSet.toString());//TreeSet在指定元素区间的中间部分规则集：[2, 3]

        // ******************  NavigableSet<E>接口 扩展的方法，都被TreeSet继承实现
        // E lower(E e); 返回TreeSet中小于指定元素的最近一个元素，没有就返回null
        i = treeSet.lower(4);
        System.out.println("TreeSet中小于指定元素的最近一个元素：" + i);//TreeSet中小于指定元素的最近一个元素：3

        // E higher(E e); 返回TreeSet中大于指定元素的最近一个元素，没有就返回null
        i = treeSet.higher(3);
        System.out.println("TreeSet中大于指定元素的最近一个元素：" + i);//TreeSet中大于指定元素的最近一个元素：5

        // E floor(E e); 返回TreeSet中小于等于指定元素的最近一个元素，没有就返回null
        i = treeSet.floor(4);
        System.out.println("TreeSet中小于等于指定元素的最近一个元素：" + i);//TreeSet中小于等于指定元素的最近一个元素：3

        // E celling(E e);  返回TreeSet中大于等于指定元素的最近一个元素，没有就返回null
        i = treeSet.ceiling(3);
        System.out.println("TreeSet中大于等于指定元素的最近一个元素：" + i);//TreeSet中大于等于指定元素的最近一个元素：3

        System.out.println("删除前后的TreeSet：" + treeSet);//删除后的TreeSet：[1, 2, 3, 5, 6]

        // E pollFirst(); 删除并返回TreeSet顺序存储的第一个元素
        i = treeSet.pollFirst();
        System.out.println("TreeSet删除并返回顺序存储的第一个元素：" + i);//TreeSet删除并返回顺序存储的第一个元素：1

        // E pollLast(); 删除并返回TreeSet顺序存储的最后一个元素
        i = treeSet.pollLast();
        System.out.println("TreeSet删除并返回顺序存储的最后一个元素：" + i);//TreeSet删除并返回顺序存储的最后一个元素：6

        System.out.println("删除后的TreeSet：" + treeSet);//删除后的TreeSet：[2, 3, 5]


        // NavigableSet<E> headSet(E e, boolean inclusive);  返回元素小于e的前面部分Set，inclusive为true，包含e，否则不包含e
        NavigableSet<Integer> navigableSet = treeSet.headSet(3, true);
        System.out.println("TreeSet小于指定元素的前部分规则集，包含指定元素：" + navigableSet.toString());//TreeSet小于指定元素的前部分规则集，包含指定元素：[2, 3]


        // NavigableSet<E> tailSet(E e, boolean inclusive);  返回元素大于等于e的后面部分Set，inclusive为true，包含e，否则不包含e
        navigableSet = treeSet.tailSet(3, true);
        System.out.println("TreeSet大于等于指定元素的后部分规则集，包含指定元素：" + navigableSet.toString());//TreeSet大于等于指定元素的后部分规则集，包含指定元素：[3, 5]

        // NavigableSet<E> subSet(E e1, boolean inclusive, E e2, boolean inclusive);  返回元素大于等于e1，小于e2的中间部分Set，inclusive为true，包含e，否则不包含e
        navigableSet = treeSet.subSet(2, true, 5, true);
        System.out.println("TreeSet在指定元素区间的中间部分规则集，包含两边的指定元素：" + navigableSet.toString());//TreeSet在指定元素区间的中间部分规则集，包含两边的指定元素：[2, 3, 5]




        // ******************  1.8新增方法  ********************
        // Iterator<E> treeSet.descendingIterator();  返回一个降序排列元素的迭代器
        Iterator<Integer> iterator2 = treeSet.descendingIterator();
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next() + " ");//5 3 2
        }
        System.out.println();

        // NavigableSet<E> descendingSet(); 返回一个降序排列元素的迭代器
        navigableSet = treeSet.descendingSet();
        System.out.println("TreeSet的descendingSet返回降序排列元素的集合：" + navigableSet.toString());//TreeSet的descendingSet返回降序排列元素的集合：[5, 3, 2]


        // ******************  其他方法跟 HashSet 一样  ********************

        /******************** ListIterator<E>接口 定义了列表特有的迭代器，扩展了Iterator<E>接口   ************************/
        // ******************  从指定的位置开始，移动前一个元素的指针，是否有元素，如果创建ListIterator时没有指定位置，则从头开始，hasPrevious为空
        // boolean hasPrevious();

        // ******************  指针移动到前下一个元素，获取元素
        // E previous();

        // ******************  从头开始，判断下一个元素的指针，是否有元素
        // boolean hasNext();

        // ******************  指针移动到下一个元素，获取元素
        // E next();

        // ******************  删除当前指针的元素
        // void remove();

        // ******************  在列表的当前指针前插入元素
        // void add(E e);

        // ******************  修改previous()方法或者next()方法最近一次返回的元素为指定的元素
        // void set(E e);


        /********************  列表具体类 ArrayList<E> 具体方法  ************************/

        // ******************  创建列表
        // 内置对象的容量为默认初始容量
        ArrayList<Integer> arrayList = new ArrayList<>();

        // 指定内置对象的初始容量
        ArrayList<Integer> arrayList1 = new ArrayList<>(20);
        // 指定ArrayList
        ArrayList<Integer> arrayList2 = new ArrayList<>(arrayList);

        // ******************  获取反射类
        System.out.println( "ArrayList的反射类：" + arrayList.getClass() );//ArrayList的反射类：class java.util.ArrayList

        // ******************  ArrayList特有的方法  ********************

        // 内置对象的容量缩小为有效元素的长度
        arrayList.trimToSize();

        // ******************  添加元素
        // 添加单个元素
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(5);
        arrayList.add(6);
        System.out.println(arrayList.toString());//[1, 2, 3, 5, 6]

        // 添加一个任意集合对象的所有元素，但是其元素必须是当前对象具体类的子类
        arrayList2.addAll(arrayList);

        // ******************  打印整个元素
        // toString()被默认调用，Java集合框架的具体类都重写了toString()，可以直接打印出元素列表
        System.out.println(arrayList.toString());//[1, 2, 3, 5, 6]

        // ******************  列表遍历的方法

        // 第一种：迭代器Iterator & 列表迭代器ListIterator
        Iterator<Integer> iterator = arrayList.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");//1 2 3 5 6
        }
        System.out.println();

        // ListIterator<E> listIterator(); 返回可以两端遍历的列表迭代器
        ListIterator<Integer> listIterator = arrayList.listIterator();
        while(listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");//空
        }
        System.out.println();

        // ListIterator<E> listIterator(int index); 返回的列表迭代器从指定索引处开始
        listIterator = arrayList.listIterator(3);
        while(listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");//3 2 1
            // 列表迭代器替换最近访问的数据
            listIterator.set(0);
        }
        System.out.println();

        System.out.println(arrayList.toString());//[0, 0, 0, 5, 6]

        // 如果最近一次访问的是previous，就add到前一个指针；如果最近一次访问的是next，就add到后一个指针
        listIterator.add(6);
        System.out.println(arrayList.toString());//[6, 0, 0, 0, 5, 6]


        // 第二种：for-each
        for (int j: arrayList) {
            System.out.print(j + " ");//6 0 0 0 5 6
        }
        System.out.println();

        // 第三种：for循环
        for (int j = 0; j < arrayList.size(); j++) {
            System.out.print(arrayList.get(j) + " ");//6 0 0 0 5 6
        }
        System.out.println();

        // ******************  集合具体类共同的方法  ********************

        // ******************  删除元素 & 元素个数
        // 删除跟指定元素匹配的元素
        arrayList.remove(2);
        System.out.println(arrayList.toString());//[6, 0, 0, 5, 6]
        System.out.println( arrayList.size() );//5
        System.out.println( arrayList.isEmpty() );//false

        // 删除跟任意集合对象的所有元素匹配的元素
        // size()返回有效元素个数，跟内置对象的容量无关，不像数组，length返回数组容量，size返回内置对象的有效元素的个数
        // isEmpty() 判断size=0?
        arrayList1.removeAll(arrayList);
        System.out.println( arrayList1.size() );//0
        System.out.println( arrayList1.isEmpty() );//true

        // 清空所有元素，size=0
        arrayList1.clear();
        System.out.println( arrayList1.size() );//0
        System.out.println( arrayList1.isEmpty() );//true

        // ******************  判断是否包含元素

        // 是否包含跟指定的元素匹配的元素
        System.out.println( arrayList.contains(1) );//false

        // 是否包含传入集合的全部元素，与元素的位置无关
        arrayList1.addAll(arrayList2);
        arrayList1.remove(new Integer(2));
        arrayList1.add(2);
        System.out.println( arrayList1.toString());//[1, 3, 5, 6, 2]
        System.out.println( arrayList2.toString());//[1, 2, 3, 5, 6]
        System.out.println( arrayList2.containsAll(arrayList1) );//true


        // ******************  hashCode和equals
        System.out.println( arrayList1.hashCode() );//29647038
        System.out.println( arrayList1.hashCode() );//29647038
        // 判断与传入集合的所有元素的值都相同，与元素的位置有关
        System.out.println( arrayList1.equals(arrayList2) );//false

        // ******************  求集合的交集
        // 在arrayList2中保留arrayList1的元素，与元素位置无关
        arrayList2.retainAll(arrayList1);
        System.out.println(arrayList2.toString());//[1, 2, 3, 5, 6]


        // ******************  集合转换成数组
        // 返回Object[]的数组，子类不能转父类
        Object[] oo = arrayList.toArray();
//        Integer[] integers = (Integer[])hashSet.toArray(); // 运行时出错，Object[] can not casted to Integer[]
        Integer[] hh = arrayList.toArray(new Integer[0]);
        Integer[] h1h1 = new Integer[arrayList.size()];
        arrayList.toArray(h1h1);
        System.out.println(Arrays.toString(oo));//[6, 0, 0, 5, 6]
        System.out.println(Arrays.toString(hh));//[6, 0, 0, 5, 6]
        System.out.println(Arrays.toString(h1h1));//[6, 0, 0, 5, 6]

        // ******************  集合框架的具体类（除了PriorityQueue）都实现了java.lang.Cloneable 和 java.io.Serializable接口，可复制可序列化
        // 克隆成一个新的Object对象，不能还原成一个相同的HashSet对象
        Object obj2 = arrayList.clone();
        System.out.println(obj2.toString());//[6, 0, 0, 5, 6]
        arrayList.add(9);
        System.out.println(obj2.toString());//[6, 0, 0, 5, 6]
        obj2 = null;
        System.out.println(arrayList.toString());//[6, 0, 0, 5, 6, 9]



        // ******************  List<E>接口 扩展的特有方法 实现  ********************
        // 添加元素
        // boolean add(int index, E e); 在指定索引处插入元素，原位置元素以及其后元素往后移动，只能插入到有效元素的位置，不能超过有效元素的长度
        arrayList.add(0, 1);//列表可以添加重复元素
        arrayList.add(1, 5);//只能插入到有效元素的位置，不能超过有效元素的长度

        System.out.println(arrayList.toString());//[1, 5, 6, 0, 0, 5, 6, 9]

        // boolean addAll(int index, Collection<? extends E>); 在指定索引处插入传入的集合对象的全部元素
        arrayList.addAll(1, arrayList);
        System.out.println(arrayList.toString());//[1, 1, 5, 6, 0, 0, 5, 6, 9, 5, 6, 0, 0, 5, 6, 9]

        // E set(int index, E e); 修改指定索引处的元素
        arrayList.set(0, 6);
        System.out.println(arrayList.toString());//[6, 1, 5, 6, 0, 0, 5, 6, 9, 5, 6, 0, 0, 5, 6, 9]

        // E get(int index); 获取指定索引处的元素
        System.out.println( arrayList.get(2) );//5

        // E remove(int index); 删除指定索引处的元素，其后元素往前移动
        arrayList.remove(0);
        System.out.println(arrayList.toString());//[1, 5, 6, 0, 0, 5, 6, 9, 5, 6, 0, 0, 5, 6, 9]

        // int indexOf(Object o); 返回第一个匹配元素的下标
        System.out.println( arrayList.indexOf(5) );//1

        // int lastIndexOf(Object o); 返回最后一个匹配元素的下标
        System.out.println( arrayList.lastIndexOf(5) );//12

        // List<E> subList(int fromIndex, int lastIndex); 返回索引从fromIndex到lastIndex的子列表
        List<Integer> list = arrayList.subList(2, 5);
        System.out.println(list.toString());//[6, 0, 0]



        // ******************  列表排序
        // void sort(Comparator<? super E> c); 将类表排序，原列表顺序被改变
        System.out.println("列表调用sort前：" + arrayList.toString());//列表调用sort前：[1, 5, 6, 0, 0, 5, 6, 9, 5, 6, 0, 0, 5, 6, 9]

        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);// 顺序为降序
            }
        });
        System.out.println("列表调用sort后：" + arrayList.toString());//列表调用sort后：[9, 9, 6, 6, 6, 6, 5, 5, 5, 5, 1, 0, 0, 0, 0]


        // ******************  设置列表的最小容量
        // void ensureCapacity(int i);  最小容量是内置对象的容量，包括有效元素和没有填充的容量，容量变了，但是size不会改变，size只是有效元素的个数
        System.out.println("列表ensureCapacity前：" + arrayList.size());//列表ensureCapacity前：15，
        arrayList.ensureCapacity(20);
        System.out.println("列表ensureCapacity后：" + arrayList.size());//列表ensureCapacity前：15，容量变了，但是size不会改变，size只是有效元素的个数


        // ******************  1.8 新增的方法  ********************
//        arrayList.spliterator();
//        arrayList.stream();
//        arrayList.parallelStream();
//        arrayList.removeIf();
//        arrayList.forEach();

        // ******************  列表替换
        // void replaceAll(UnaryOperator<Integer> op); UnaryOperator是一元运算符，接受类似 x -> x 这样类型的参数
        System.out.println("列表replaceAll前：" + arrayList.toString());//列表replaceAll前：[9, 9, 6, 6, 6, 6, 5, 5, 5, 5, 1, 0, 0, 0, 0]
        arrayList.replaceAll(t -> t+1);
        System.out.println("列表replaceAll后：" + arrayList.toString());//列表replaceAll后：[10, 10, 7, 7, 7, 7, 6, 6, 6, 6, 2, 1, 1, 1, 1]



        /********************  列表具体类 LinkedList<E> 具体方法  ************************/
        // ******************  创建LinkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        LinkedList<Integer> linkedList1 = new LinkedList<>(arrayList);


        // *******************  AbstractSequenceList<E>抽象类 扩展操作两端元素的方法，被LinkedList继承实现  ********************
        // void addFirst(E e); 添加元素到列表头
        linkedList.addFirst(1);
        System.out.println(linkedList.toString());//[1]

        // void addLast(E e); 添加元素到列表尾
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(6);
        System.out.println(linkedList.toString());//[1, 2, 3, 6]

        // E getFirst(); 获取列表的第一个元素
        System.out.println(linkedList.getFirst());//1

        // E getLast(); 获取列表的最后一个元素
        System.out.println(linkedList.getLast());//6

        // E removeFirst(); 删除并返回列表的第一个元素
        System.out.println("removeFirst前：" + linkedList.toString());//removeFirst前：[1, 2, 3, 6]
        linkedList.removeFirst();
        System.out.println("removeFirst后：" + linkedList.toString());//removeFirst后：[2, 3, 6]

        // E removeLast(); 删除并返回列表的最后一个元素
        System.out.println("removeLast前：" + linkedList.toString());//removeLast前：[2, 3, 6]
        linkedList.removeLast();
        System.out.println("removeLast后：" + linkedList.toString());//removeLast后：[2, 3]


        // *******************  Deque<E>接口 扩展作为队列和栈的方法  ********************

        // 从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表）
        linkedList.add(2);
        System.out.println("removeFirstOccurrence(2)前:" + linkedList);//removeFirstOccurrence(3)前:[2, 3, 2]
        linkedList.removeFirstOccurrence(2);
        System.out.println("removeFirstOccurrence(2)后:" + linkedList);//removeFirstOccurrence(2)后:[3, 2]

        // 从此列表中移除最后一次出现的指定元素（从尾部到头部遍历列表）
        linkedList.add(3);
        System.out.println("removeLastOccurrence(3)前:" + linkedList);//removeLastOccurrence(3)前:[3, 2, 3]
        linkedList.removeLastOccurrence(3);
        System.out.println("removeLastOccurrence(3)后:" + linkedList);//removeLastOccurrence(3)后:[3, 2]

        // ***************  正向队列，删除头部，插入尾部
        linkedList.clear();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);

        // E peek(); 获取但不删除此列表的头，如果列表为空，返回null
        System.out.println( linkedList.peek() );//1

        // E element(); 获取但不删除此列表的头，如果列表为空，抛出异常
        System.out.println( linkedList.element() );//1

        // E poll(); 删除并返回此列表的头，如果列表为空，返回null
        System.out.println("poll前：" + linkedList.toString());//poll前：[1, 2, 3, 4, 5, 6]
        linkedList.poll();
        System.out.println("poll后：" + linkedList.toString());//poll后：[2, 3, 4, 5, 6]

        // E remove(); 删除并返回此列表的头，如果列表为空，抛出异常
        System.out.println("remove前：" + linkedList.toString());//remove前：[2, 3, 4, 5, 6]
        linkedList.remove();
        System.out.println("remove后：" + linkedList.toString());//remove后：[3, 4, 5, 6]

        // 添加元素到尾部
        System.out.println("offer前：" + linkedList.toString());//offer前：[3, 4, 5, 6]
        linkedList.offer(9);
        System.out.println("offer后：" + linkedList.toString());//offer后：[3, 4, 5, 6, 9]


        // ***************  双向队列，删除尾部插入头部或者，删除头部插入尾部  ********************

        // 在此列表的开头插入元素
        System.out.println("offerFirst(2)前:" + linkedList);//offerFirst(2)前:[3, 4, 5, 6, 9]
        linkedList.offerFirst(2);
        System.out.println("offerFirst(2)后:" + linkedList);//offerFirst(2)后:[2, 3, 4, 5, 6, 9]

        // 在此列表末尾插入元素
        System.out.println("offerLast(5)前:" + linkedList);//offerLast(5)前:[2, 3, 4, 5, 6, 9]
        linkedList.offerLast(5);
        System.out.println("offerLast(5)后:" + linkedList);//offerLast(5)后:[2, 3, 4, 5, 6, 9, 5]

        // 获取但不移除此列表的第一个元素
        System.out.println("peekFirst(): " + linkedList.peekFirst());//peekFirst(): 2

        // 获取但不移除此列表的最后一个元素
        System.out.println("peekLast(): " + linkedList.peekLast());//peekLast(): 5

        // 获取并移除此列表的第一个元素
        System.out.println("pollFirst()前:" + linkedList);//pollFirst()前:[2, 3, 4, 5, 6, 9, 5]
        linkedList.pollFirst();
        System.out.println("pollFirst()后:" + linkedList);//pollFirst()后:[3, 4, 5, 6, 9, 5]

        // 获取并移除此列表的最后一个元素
        System.out.println("pollLast()前:" + linkedList);//pollLast()前:[3, 4, 5, 6, 9, 5]
        linkedList.pollLast();
        System.out.println("pollLast()后:" + linkedList);//pollLast()后:[3, 4, 5, 6, 9]


        // *************** 有点像stack操作，插入头部删除头部  ********************

        // 将元素推入到列表的头
        linkedList.push(2);
        System.out.println("push(2)后:" + linkedList);//push(2)后:[2, 3, 4, 5, 6, 9]

        // 获取并移除列表第一个元素
        System.out.println("pop()前:" + linkedList);//pop()前:[2, 3, 4, 5, 6, 9]
        linkedList.pop();
        System.out.println("pop()后:" + linkedList);//pop()后:[3, 4, 5, 6, 9]

        // ***************  其他方法同ArrayList  ********************



        /********************  列表具体类 Vector<E> 自己的方法（除了重新设计继承List的方法）  ************************/


        // *******************  创建vector
        Vector<Integer> vector = new Vector<>();
        Vector<Integer> vector1 = new Vector<>(vector);
        Vector<Integer> vector2 = new Vector<>(5);//指定内置对象的初始容量
        Vector<Integer> vector3 = new Vector<>(5,2);//指定内置对象的初始容量和每次扩充的容量大小

        // *******************  添加元素
        //尾部添加
        vector.addElement(1);

        //void insertElementAt(E e, int index); 指定索引处添加
        vector.insertElementAt(2,0);
        System.out.println( vector.toString() );//[2, 1]

        //尾部添加任意集合对象的全部元素
        vector.addAll(vector1);
        System.out.println( vector.toString() );//[2, 1]

        //在指定索引处添加任意集合对象的全部元素
        vector.addAll(1, vector1);
        System.out.println( vector.toString() );//[2, 1]

        // *******************  内置对象容量
        // 返回当前内置对象容量
        System.out.println( vector.capacity() );//10

        // 缩小内置对象容量到有效元素尺寸
        vector.trimToSize();
        System.out.println( vector.capacity() );//2

        // 扩充当前容量，扩充到5
        vector.ensureCapacity(5);
        System.out.println( vector.capacity() );//5


        // 设置内置对象的新容量
        vector.setSize(10);
        System.out.println( vector.capacity() );//10

        // *******************  大小：vector很不一样的是，vector的有效长度就是内置对象数组的容量，没有有效元素的位置用null占位
        System.out.println( vector.size() );//10
        System.out.println( vector.toString() );//[2, 1, null, null, null, null, null, null, null, null]


        // *******************  获取元素
        System.out.println( vector.elementAt(1) );//1
        System.out.println( vector.firstElement() );//2
        System.out.println( vector.lastElement() );//null
        // Enumeration<E> elements(); 返回向量元素的枚举
        Enumeration<Integer> enumVector  =  vector.elements();
        System.out.println( enumVector.toString() );//java.util.Vector$1@266474c2


        // *******************  修改元素
        // void setElementAt(E e, int index);
        vector.setElementAt(6, 0);
        System.out.println( vector.toString() );//[6, 1, null, null, null, null, null, null, null, null]

        // *******************  删除元素：不一样的是，会删除内置对象数组的容量，删除全部元素，会让内置对象数组容量为0
        System.out.println("removeElement(1)前：" + vector.toString());//removeElement(2)前：[6, 1, null, null, null, null, null, null, null, null]
        vector.removeElement(1);
        System.out.println("removeElement(1)后：" + vector.toString());//removeElement(1)后：[6, null, null, null, null, null, null, null, null]

        System.out.println("removeElementAt(0)前：" + vector.toString());//removeElementAt(0)前：[6, null, null, null, null, null, null, null, null]
        vector.removeElementAt(0);
        System.out.println("removeElementAt(0)后：" + vector.toString());//removeElementAt(0)后：[null, null, null, null, null, null, null, null]

        vector.removeAllElements();
        System.out.println( vector.size() );//0
        System.out.println( vector.toString() );//[]


        // *******************  遍历元素，没有迭代器
        vector.addElement(1);
        vector.addElement(2);
        vector.addElement(3);
        for (Integer j : vector) {
            System.out.print(j + " ");//1 2 3
        }
        System.out.println();

        for (int j = 0; j < vector.size(); j++) {
            System.out.print(vector.get(j) + " ");//1 2 3
        }
        System.out.println();

        // *******************  vector转换成数组
        // 复制成一个新数组，互不影响
        Integer[] integerVector = new Integer[vector.size()];
        vector.copyInto(integerVector);
        System.out.println(Arrays.toString(integerVector));//[1, 2, 3]


        /********************  列表具体类 Stack<E> 自己的方法（除了重新设计继承List的方法）  ************************/
        // *******************  创建stack
        Stack<Integer> stack = new Stack<>();

        // *******************  向栈顶增加元素
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // *******************  判断栈是否为空
        System.out.println( stack.empty() );//false

        // *******************  获取但不删除栈顶元素
        System.out.println( stack.peek() );//3

        // *******************  获取并删除栈顶元素
        System.out.println("pop前：" + stack.toString());//pop前：[1, 2, 3]
        System.out.println( stack.pop() );//3
        System.out.println("pop后：" + stack.toString());//pop后：[1, 2]

        // *******************  返回指定元素在栈中的索引
        System.out.println( stack.search(2) );//1


        /********************  队列具体类 PriorityQueue<E> 具体方法   ************************/

        // *******************  创建PriorityQueue队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>(priorityQueue);
        // 指定链表的初始容量
        PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>(1);
        // 指定队列元素的比较器，队列按序存储
        PriorityQueue<Integer> priorityQueue3 = new PriorityQueue<>(3, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1); //倒序
            }
        });

        // ******************  获取反射类
        System.out.println( "PriorityQueue的反射类：" + priorityQueue.getClass() );//PriorityQueue的反射类：class java.util.PriorityQueue

        // ******************  添加元素
        // 添加单个元素
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(2);
        priorityQueue.add(5);
        priorityQueue.add(4);


        // 添加一个任意集合对象的所有元素，但是其元素必须是当前对象具体类的子类
        priorityQueue3.addAll(priorityQueue);

        // ******************  打印整个元素
        // toString()被默认调用，Java集合框架的具体类都重写了toString()，可以直接打印出元素列表
        System.out.println(priorityQueue.toString());//[1, 3, 2, 5, 4]


        // *******************  返回创建时指定的比较器
        System.out.println( priorityQueue.comparator() );//null
        System.out.println( priorityQueue3.comparator() );//collectionFramework.CollectionFramework$3@6f94fa3e
        System.out.println("传入比较器的优先队列：" + priorityQueue3.toString());//传入比较器的优先队列：[5, 4, 2, 1, 3]

        // *******************  两种方式遍历队列元素，因为队列是操作队首队尾的，所以不能for循环遍历
        // 第一种方式：迭代器
        Iterator<Integer> iterator1 = priorityQueue.iterator();
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");//1 3 2 5 4
        }
        System.out.println();

        // 第二种方式：for-each
        for(Integer j : priorityQueue) {
            System.out.print(j + " ");//1 3 2 5 4
        }
        System.out.println();


        // ******************  集合具体类共同的方法  ********************

        // ******************  删除元素 & 元素个数
        // 删除跟指定元素匹配的元素
        priorityQueue.remove(2);
        System.out.println( priorityQueue.toString() );//[1, 3, 4, 5]
        System.out.println( priorityQueue.size() );//4
        System.out.println( priorityQueue.isEmpty() );//false

        // 删除跟任意集合对象的所有元素匹配的元素
        // size()返回有效元素个数，跟内置对象的容量无关，不像数组，length返回数组容量，size返回内置对象的有效元素的个数
        // isEmpty() 判断size=0?
        priorityQueue1.removeAll(priorityQueue);
        System.out.println( priorityQueue1.size() );//0
        System.out.println( priorityQueue1.isEmpty() );//true

        // 清空所有元素，size=0
        priorityQueue2.clear();
        System.out.println( priorityQueue2.size() );//0
        System.out.println( priorityQueue2.isEmpty() );//true

        // ******************  判断是否包含元素

        // 是否包含跟指定的元素匹配的元素
        System.out.println( priorityQueue.contains(1) );//true

        // 是否包含传入集合的全部元素，与元素的顺序无关
        priorityQueue3.clear();
        priorityQueue3.addAll(priorityQueue);
        System.out.println( priorityQueue.toString() );//[1, 3, 4, 5]
        System.out.println( priorityQueue3.toString() );//[5, 4, 3, 1]
        System.out.println( priorityQueue.containsAll(priorityQueue3) );//true


        // ******************  hashCode和equals
        System.out.println( priorityQueue.hashCode() );//1581781576
        System.out.println( priorityQueue3.hashCode() );//1725154839
        // 判断与传入集合的所有元素的值和位置都相同，与元素位置有关
        System.out.println( priorityQueue.equals(priorityQueue3) );//false

        // ******************  求集合的交集
        // 保留相同的元素，与元素的顺序无关
        priorityQueue.retainAll(priorityQueue3);
        System.out.println(priorityQueue.toString());//[1, 3, 4, 5]


        // ******************  集合转换成数组
        // 返回Object[]的数组，子类不能转父类
        Object[] ooo = priorityQueue.toArray();
//        Integer[] integers = (Integer[])hashSet.toArray(); // 运行时出错，Object[] can not casted to Integer[]
        Integer[] hhh = priorityQueue.toArray(new Integer[0]);
        Integer[] h1h1h1 = new Integer[priorityQueue.size()];
        priorityQueue.toArray(h1h1h1);
        System.out.println(Arrays.toString(ooo));//[1, 3, 4, 5]
        System.out.println(Arrays.toString(hhh));//[1, 3, 4, 5]
        System.out.println(Arrays.toString(h1h1h1));//[1, 3, 4, 5]


        // *******************  实现 Queue<E>接口的方法，操作正向队列的方法   ********************

        // 添加元素到尾部
        System.out.println("offer前：" + priorityQueue.toString());//offer前：[1, 3, 4, 5]
        priorityQueue.offer(9);
        System.out.println("offer后：" + priorityQueue.toString());//offer后：[1, 3, 4, 5, 9]

        // E peek(); 获取但不删除此列表的头，如果列表为空，返回null
        System.out.println( priorityQueue.peek() );//1

        // E element(); 获取但不删除此列表的头，如果列表为空，抛出异常
        System.out.println( priorityQueue.element() );//1

        // E poll(); 删除并返回此列表的头，如果列表为空，返回null
        System.out.println("poll前：" + priorityQueue.toString());//poll前：[1, 3, 4, 5, 9]
        priorityQueue.poll();
        System.out.println("poll后：" + priorityQueue.toString());//poll后：[3, 5, 4, 9]

        // E remove(); 删除并返回此列表的头，如果列表为空，抛出异常
        System.out.println("remove前：" + priorityQueue.toString());//remove前：[3, 5, 4, 9]
        priorityQueue.remove();
        System.out.println("remove后：" + priorityQueue.toString());//remove后：[4, 5, 9]

        // ******************  优先队列只实现了java.io.Serializable接口，可序列化，没有clone()方法

        // ******************  1.8新增方法  ********************
//        priorityQueue.spliterator();
//        priorityQueue.stream();
//        priorityQueue.parallelStream();
//        priorityQueue.removeIf();
//        priorityQueue.forEach();

        /********************  图对象的条目接口 java.util.Map.Entry<K, V> 抽象方法  ************************/
        // *******************  返回条目的键值
        // K getKey();

        // *******************  返回条目的值
        // V getValue();

        // *******************  修改条目的值
        // void setValue(V value);


        /********************  所有图对象的根接口 java.util.Map<K, V> 抽象方法  ************************/
        // ******************* 添加条目
        // V put(K key, V value);

        // ******************* 添加传入的map的所有条目
        // void putAll(Map<? extends K, ? extends V>);


        // ******************* 判断是否包含条目的值
        // 是否包含键值
        // boolean containsKey(Object key);

        // 是否包含值
        // boolean containsValue(Object value);


        // ******************* 获取条目，键值，值
        // 根据键值返回值
        // V get(K key);

        // 获取所有条目的一个规则集
        // Set<Map.Entry<K, V>> entrySet();

        // 获取所有键值的一个规则集
        // Set<K> keySet();

        // 获取所有值的一个集合
        // Collection<V> values();


        // ******************* map的长度
        // 返回条目个数
        // int size();

        // 判断条目个数为0
        // boolean isEmpty();


        // ******************* 删除条目
        // 删除键值是指定对象的条目
        // V remove(Object key);

        // 删除所有的条目
        // void clear();


        /********************  图具体类 java.util.HashMap<K, V> 具体方法  ************************/

        // ******************* 创建HashMap
        // 创建一个默认容量长度的容器
        HashMap<String, Integer> hashMap = new HashMap<>();
        // 创建时执行初始容量长度和容座率
        HashMap<String, Integer> hashMap1 = new HashMap<>(10, 0.6f);
        // 创建时赋值另一个map
        HashMap<String, Integer> hashMap2 = new HashMap<>(hashMap);

        // ******************  获取反射类
        System.out.println( "HashMap的反射类：" + hashMap.getClass() );//HashMap的反射类：class java.util.HashMap


        // *******************  实现Map的方法  *******************

        // ******************* 添加条目
        // V put(K key, V value);
        hashMap.put("a", 1);
        hashMap.put("b", 2);
        hashMap.put("c", 3);
        hashMap.put("d", 4);
        hashMap.put("e", 5);
        hashMap.put("f", 6);

        // 添加传入的map的所有条目
        // void putAll(Map<? extends K, ? extends V>);
        hashMap1.putAll(hashMap);
        hashMap2.putAll(hashMap);


        // ******************* 图的具体类重写了toString方法，可以直接打印
        System.out.println( hashMap.toString() );//{a=1, b=2, c=3, d=4, e=5, f=6}


        // ******************* hashCode & equals
        hashMap1.remove("a");
        hashMap1.put("a", 1);
        System.out.println( hashMap.toString() );//{a=1, b=2, c=3, d=4, e=5, f=6}
        System.out.println( hashMap1.toString() );//{a=1, b=2, c=3, d=4, e=5, f=6}

        // hashCode，使用键值计算Hashcode，跟条目的位置无关
        System.out.println( hashMap.hashCode() );//576
        System.out.println( hashMap1.hashCode() );//576

        // equals，跟条目的位置无关
        System.out.println( hashMap.equals(hashMap1) );//true


        // ******************* 遍历方法：map自身没有遍历方法，只能通过keySet方法、values方法、entrySet方法分别获取键值，值，条目的集合，然后遍历它们
        // 第一种：遍历键值
        Set<String> keys = hashMap.keySet();
        for (String key: keys) {
            System.out.print(key + " ");//a b c d e f
        }
        System.out.println();

        // 第二种：通过遍历键值获取值
        for (String key: keys) {
            System.out.print(hashMap.get(key) + " ");//1 2 3 4 5 6
        }
        System.out.println();

        // 第三种：遍历值
        Collection<Integer> values = hashMap.values();
        for (Integer value: values) {
            System.out.print(value + " ");//1 2 3 4 5 6
        }
        System.out.println();

        // 第四种：遍历条目的键和值
        Set<Map.Entry<String, Integer>>  entries = hashMap.entrySet();
        for (Map.Entry<String, Integer> entry: entries) {
            System.out.print("键值对：" + entry.getKey() + " --- " + entry.getKey() + "  ");
            //键值对：a --- a  键值对：b --- b  键值对：c --- c  键值对：d --- d  键值对：e --- e  键值对：f --- f
        }
        System.out.println();


        // ******************* 判断是否包含条目的值
        // 是否包含键值
        // boolean containsKey(Object key);
        System.out.println( hashMap.containsKey("a") );//true

        // 是否包含值
        // boolean containsValue(Object value);
        System.out.println( hashMap.containsValue(1) );//true


        // ******************* 获取条目，键值，值
        // 根据键值返回值
        // V get(K key);
        System.out.println( hashMap.get("a") );//1

        // 获取所有条目的一个规则集
        // Set<Map.Entry<K, V>> entrySet();
        Set<Map.Entry<String, Integer>>  setMapEntry = hashMap.entrySet();
        for (Map.Entry<String, Integer> entry: setMapEntry) {
            //Key=a;value=1  Key=b;value=2  Key=c;value=3  Key=d;value=4  Key=e;value=5  Key=f;value=6
            System.out.print("Key=" + entry.getKey() + ";value=" + entry.getValue() + "  ");
            entry.setValue(1);//值全部设置为1
        }
        System.out.println();

        // 获取所有键值的一个规则集
        // Set<K> keySet();
        Set<String> setMapKey = hashMap.keySet();
        System.out.println( setMapKey.toString() );//[a, b, c, d, e, f]

        // 获取所有值的一个集合
        // Collection<V> values();
        Collection<Integer> collectionMapValue = hashMap.values();
        System.out.println( collectionMapValue.toString() );//[1, 1, 1, 1, 1, 1]


        // ******************* map的长度
        // 返回条目个数
        // int size();
        System.out.println( hashMap.size() );//6

        // 判断条目个数为0
        // boolean isEmpty();
        System.out.println( hashMap.isEmpty() );//false

        // ******************* 替换map的值
        // V replace(K key, V value);  替换键值为key的条目的值为指定的value
        hashMap.replace("a", 10);
        System.out.println( hashMap.toString() );//{a=10, b=1, c=1, d=1, e=1, f=1}

        // boolean replace(K key, V oldValue, V newValue);  替换键值为key且值为oldValue，的条目的值为newValue，如果有任一条件不匹配，就不做替换
        hashMap.replace("b", 2, 12);// 没有匹配的键值对，不做替换，直接忽略
        hashMap.replace("a", 10, 12);
        System.out.println( hashMap.toString() );//{a=12, b=1, c=1, d=1, e=1, f=1}


        // ******************* 删除条目
        // 删除键值匹配的条目
        // V remove(Object key);
        hashMap.remove("a");
        System.out.println( hashMap.toString() );//{b=1, c=1, d=1, e=1, f=1}

        // 删除键值和值都匹配的条目
        // V remove(Object key, V value);
        hashMap.remove("b", 1);
        System.out.println( hashMap.toString() );//{c=1, d=1, e=1, f=1}

        // 清空所有条目
        // void clear();
        hashMap.clear();
        System.out.println( hashMap.toString() );//{}
        System.out.println( hashMap.size() );//0
        System.out.println( hashMap.isEmpty() );//true

        // ******************* 克隆map
        Object obj4 = hashMap2.clone();
        System.out.println( obj4.toString() );//{a=10, b=2, c=3, d=4, e=5, f=6}

        // *******************  HashMap中，null可以作为键，这样的键只有一个，但是可以有一个或多个键所对应的值为null；HashTable中不能有null的键值  *******************
        hashMap.put(null, 1);
        hashMap.put(null, 2);
        System.out.println( hashMap.get(null) );//2


        // *******************  1.8 新增方法  *******************
        // void replaceAll(BiFunction<? super K, ? super V, ? extends V>); 过滤匹配键值对<K,V>中的条目，并用新的V值替换旧的V值
        // 让key值大于c的条目的值都变成6
        System.out.println("HashMap在replaceAll前：" + hashMap2.toString() );//ashMap在replaceAll前：{a=1, b=2, c=3, d=4, e=5, f=6}
        hashMap2.replaceAll((k,v) -> {
            if (k.compareTo("c") > 0) {
                v=6;
            }
            return v;
        });
        System.out.println("HashMap在replaceAll后：" + hashMap2.toString() );//HashMap在replaceAll后：{a=1, b=2, c=3, d=6, e=6, f=6}

        // void forEach(BiComsumer<? super K, ? super V>); 函数式编程，遍历图对象
        // 使用lambda表达式，只遍历大于3的元素
        hashMap2.forEach((k,v) -> {
            if(v > 3) {
                System.out.print(k + ":" + v + " ");//d:6 e:6 f:6
            }
        });
        System.out.println();


        // V putIfAbsent(K key, V value);
        // 当value为null的时候，putIfAbsent()方法会覆盖null值，直到value不为null为止
        // 当value初始值不为null的时候，putIfAbsent()就不会执行了，值就不会改变了，并且是多线程安全的
        hashMap2.putIfAbsent("a", null);
        hashMap2.putIfAbsent("a", 1);
        hashMap2.putIfAbsent("a", 10);

        hashMap2.putIfAbsent("b", 2);
        hashMap2.putIfAbsent("b", 10);

        hashMap2.put("c", 3);
        hashMap2.put("d", 4);
        hashMap2.put("e", 5);

        System.out.println(hashMap2.toString());//{a=1, b=2, c=3, d=4, e=5, f=6}


        // V getOrDefault(Object key, V defaultValue); 当Map中有键值key时，就获取它对应的值，如果没有就返回默认值defaultValue
        System.out.println("键值存在，返回对应的值：" + hashMap2.getOrDefault("a", 10));//键值存在，返回对应的值：1
        System.out.println("键值不存在，返回默认值："  + hashMap2.getOrDefault("shy", 10));//键值不存在，返回默认值：10

        // V compute(K key, BiFunction<? super K, ? super V, ? extends V>); //对键值匹配key的条目做操作，入参为oldValue、value，根据两个value进行逻辑处理并返回新的value，返回值如果为null则删除该节点，否则即为要存储的新value。
        hashMap2.compute("b", (v1,v2) ->(v2 == 2)? 1:2);
        System.out.println(hashMap2.toString());//{a=1, b=1, c=3, d=4, e=5, f=6}

        // V computeIfAbsent(K key, Function<? super K, ? extends V>); 若key对应的value为空，会将第二个参数的返回值存入并返回
        hashMap2.put("test", null);
        hashMap2.computeIfAbsent("test", v -> new Integer(12));
        System.out.println(hashMap2.toString());//{a=1, b=1, c=3, d=4, test=12, e=5, f=6}

        // V computeIfPresent(K, BiFunction<? super K, ? super V, ? extends V>); 根据key做匹配，如果匹配不上则返回null,匹配上根据入参为key、oldValue，根据key和oldValue进行逻辑处理并返回新的value，返回值如果为null则删除该节点，否则即为要存储的新value。
        hashMap2.computeIfPresent("test", (k, v) -> {
            v = v * 3;
            return v;
        });
        System.out.println(hashMap2.toString());//{a=1, b=1, c=3, d=4, test=36, e=5, f=6}

        // V merge(K key, V value, BiFunction<? super K, ? super V, ? extends V>); 功能大部分与compute相同，不同之处在于同时匹配key和value，并且入参为oldValue、value，根据两个value进行逻辑处理并返回新value，返回值如果为null则删除该节点，否则即为要存储的新value。
        hashMap2.merge("test", 36, (v1, v2) -> {
            v2 = v1 + 20;
            return v2;
        });
        System.out.println(hashMap2.toString());//{a=1, b=1, c=3, d=4, test=56, e=5, f=6}



        /********************  图具体类 java.util.LinkedHashMap<K, V> 具体方法  ************************/

        // ******************* 创建LinkedHashMap
        // 默认按照插入顺序排序存储，accessOrder = false
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> linkedHashMap1 = new LinkedHashMap<>(linkedHashMap);
        // 指定内置链表初始容量，容座率
        LinkedHashMap<String, Integer> linkedHashMap2 = new LinkedHashMap<>(10, 0.6f);
        // 指定按照访问顺序排序，设置accessOrder = true，按照最近一次访问时间从远到近排除存储
        LinkedHashMap<String, Integer> linkedHashMap3 = new LinkedHashMap<>(10, 0.6f, true);

        // ******************  跟HashMap不一样的是：顺序
        // 按照插入顺序排序存储
        linkedHashMap.put("b", 2);
        linkedHashMap.put("a", 1);
        linkedHashMap.put("d", 4);
        linkedHashMap.put("c", 3);
        linkedHashMap.put("f", 6);
        linkedHashMap.put("e", 5);
        System.out.println("按照插入顺序排序存储：" + linkedHashMap.toString());//按照插入顺序排序存储：{b=2, a=1, d=4, c=3, f=6, e=5}

        // 按照条目的访问顺序排序存储，最近一次访问从远到近排序
        linkedHashMap3.putAll(linkedHashMap);
        linkedHashMap3.get("e");
        linkedHashMap3.get("f");
        linkedHashMap3.get("d");
        linkedHashMap3.get("c");
        linkedHashMap3.get("b");
        linkedHashMap3.get("a");
        System.out.println("按照条目的访问顺序排序存储：" + linkedHashMap3.toString());//按照条目的访问顺序排序存储：{e=5, f=6, d=4, c=3, b=2, a=1}


        // ******************  其他方法跟 HashMap 一样  ********************

        /********************  图具体类 java.util.TreeMap<K, V> 具体方法  ************************/

        // ******************  跟HashMap不一样的是：按照键值的比较顺序存储，或者创建时指定元素的比较器  ********************
        // 默认按照键值实现的Comparable接口排序的，一般是键值的自然比较顺序
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("b", 2);
        treeMap.put("a", 1);
        treeMap.put("d", 4);
        treeMap.put("c", 3);
        treeMap.put("f", 6);
        treeMap.put("e", 5);

        // 创建时赋值一个map的所有键值对
        TreeMap<String, Integer> treeMap1 = new TreeMap<>(treeMap);

        // 创建对象时指定键值的比较器
        TreeMap<String, Integer> treeMap2 = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeMap2.putAll(treeMap);

        System.out.println("按照键值的比较顺序存储： " + treeMap.toString());//按照键值的比较顺序存储： {a=1, b=2, c=3, d=4, e=5, f=6}
        System.out.println("创建时指定键值的比较器： " + treeMap2.toString());//创建时指定键值的比较器： {f=6, e=5, d=4, c=3, b=2, a=1}

        // ******************  TreeMap特有的方法
        // 获取TreeMap的比较器
        System.out.println( treeMap.comparator() );//null，按照键值实现的Comparable接口排序的，没有用到比较器
        System.out.println( treeMap2.comparator() );//collectionFramework.CollectionFramework$4@63947c6b，指定的比较器


        // ******************  SortedMap<K, V>接口 扩展的方法，都被TreeMap继承实现

        // Map.Entry<String, Integer> firstEntry(); 返回TreeMap顺序存储的第一个条目
        Map.Entry<String, Integer> entry = treeMap.firstEntry();
        System.out.println("TreeMap顺序存储的第一个条目：" + entry);//TreeMap顺序存储的第一个条目：a=1

        // Map.Entry<String, Integer> lastEntry();  返回TreeMap顺序存储的最后一个条目
        entry = treeMap.lastEntry();
        System.out.println("TreeMap顺序存储的最后一个条目：" + entry);//TreeMap顺序存储的最后一个条目：f=6

        // K firstKey(); 返回TreeMap顺序存储的第一个条目的键值
        String s = treeMap.firstKey();
        System.out.println("TreeMap顺序存储的第一个条目的键值：" + s);//TreeMap顺序存储的第一个条目的键值：a

        // K lastKey();  返回TreeMap顺序存储的最后一个条目的键值
        s = treeMap.lastKey();
        System.out.println("TreeMap顺序存储的最后一个条目的键值：" + s);//TreeMap顺序存储的最后一个条目的键值：f

        // SortedMap<K, V> headMap(K key);  返回键值小于key的前面部分Map
        SortedMap<String, Integer> sortedMap = treeMap.headMap("c");
        System.out.println("TreeMap小于key的前面部分Map：" + sortedMap.toString());//TreeMap小于key的前面部分Map：{a=1, b=2}

        // SortedMap<K, V> tailMap(K key);  返回键值大于等于key的后面部分Map
        sortedMap = treeMap.tailMap("c");
        System.out.println("TreeMap键值大于等于key的后面部分Map：" + sortedMap.toString());//TreeMap键值大于等于key的后面部分Map：{c=3, d=4, e=5, f=6}

        // SortedMap<K, V> subMap(K key1, K key2);  返回键值大于等于key1，大于key2的中间部分Map
        sortedMap = treeMap.subMap("b", "e");
        System.out.println("TreeMap在指定键值区间的中间部分Map：" + sortedMap.toString());//TreeMap在指定键值区间的中间部分Map：{b=2, c=3, d=4}


        // ******************  NavigableMap<K, V>接口 扩展的方法，都被TreeMap继承实现

        // NavigableMap<K, V> headMap(K key, boolean inclusive);  返回键值小于key的前面部分Map，包括key
        NavigableMap<String, Integer> navigableMap = treeMap.headMap("c",true);
        System.out.println("TreeMap小于key的前面部分Map，包括key：" + navigableMap.toString());//TreeMap小于key的前面部分Map，包括key：{a=1, b=2, c=3}

        // NavigableMap<K, V> tailMap(K key, boolean inclusive);  返回键值大于等于key的后面部分Map，包括key
        navigableMap = treeMap.tailMap("c", true);
        System.out.println("TreeMap键值大于等于key的后面部分Map，包括key：" + navigableMap.toString());//TreeMap键值大于等于key的后面部分Map，包括key：{c=3, d=4, e=5, f=6}

        // NavigableMap<K, V> subMap(K key1, boolean inclusive, K key2, boolean inclusive);  返回键值大于等于key1，大于key2的中间部分Map，包括key1、key2
        navigableMap = treeMap.subMap("b", true, "e", true);
        System.out.println("TreeMap在指定键值区间的中间部分Map，包括key1、key2：" + navigableMap.toString());//TreeMap在指定键值区间的中间部分Map，包括key1、key2：{b=2, c=3, d=4, e=5}

        // Map.Entry<String, Integer> lowerEntry(K key); 返回TreeMap中键值小于指定元素的最近一个条目，没有就返回null
        // K lowerKey(K key);  返回TreeMap中键值小于指定元素的最近一个键值，没有就返回null
        entry = treeMap.lowerEntry("c");
        s = treeMap.lowerKey("c");
        System.out.println("TreeMap中键值小于指定元素的最近一个条目：" + entry);//TreeMap中键值小于指定元素的最近一个条目：b=2
        System.out.println("TreeMap中键值小于指定元素的最近一个键值：" + s);//TreeMap中键值小于指定元素的最近一个键值：b

        // Map.Entry<String, Integer> higherEntry(K key); 返回TreeMap中键值大于指定元素的最近一个条目，没有就返回null
        // K higherKey(K key);  返回TreeMap中键值大于指定元素的最近一个键值，没有就返回null
        entry = treeMap.higherEntry("c");
        s = treeMap.higherKey("c");
        System.out.println("TreeMap中键值大于指定元素的最近一个条目：" + entry);//TreeMap中键值大于指定元素的最近一个条目：d=4
        System.out.println("TreeMap中键值大于指定元素的最近一个键值：" + s);//TreeMap中键值大于指定元素的最近一个键值：d

        // Map.Entry<String, Integer> floorEntry(K key); 返回TreeMap中键值小于等于指定元素的最近一个条目，没有就返回null
        // K floorKey(K key);  返回TreeMap中键值小于等于指定元素的最近一个键值，没有就返回null
        entry = treeMap.floorEntry("c");
        s = treeMap.floorKey("c");
        System.out.println("TreeMap中键值小于等于指定元素的最近一个条目：" + entry);//TreeMap中键值小于等于指定元素的最近一个条目：c=3
        System.out.println("TreeMap中键值小于等于指定元素的最近一个键值：" + s);//TreeMap中键值小于等于指定元素的最近一个键值：c

        // Map.Entry<String, Integer> ceilingEntry(K key); 返回TreeMap中键值大于等于指定元素的最近一个条目，没有就返回null
        // K ceilingKey(K key);  返回TreeMap中键值大于等于指定元素的最近一个键值，没有就返回null
        entry = treeMap.ceilingEntry("c");
        s = treeMap.ceilingKey("c");
        System.out.println("TreeMap中键值大于等于指定元素的最近一个条目：" + entry);//TreeMap中键值大于等于指定元素的最近一个条目：c=3
        System.out.println("TreeMap中键值大于等于指定元素的最近一个键值：" + s);//TreeMap中键值大于等于指定元素的最近一个键值：c


        System.out.println("删除前的TreeMap：" + treeMap);//删除前的TreeMap：{a=1, b=2, c=3, d=4, e=5, f=6}

        // Map.Entry<String, Integer> pollFirstEntry(); 删除并返回TreeMap顺序存储的第一个条目
        entry = treeMap.pollFirstEntry();
        System.out.println("TreeMap删除并返回顺序存储的第一个条目：" + entry);//TreeMap删除并返回顺序存储的第一个条目：a=1

        // Map.Entry<String, Integer> pollLastEntry(); 删除并返回TreeMap顺序存储的最后一个条目
        entry = treeMap.pollLastEntry();
        System.out.println("TreeMap删除并返回顺序存储的最后一个条目：" + entry);//TreeMap删除并返回顺序存储的最后一个条目：f=6

        System.out.println("删除后的TreeMap：" + treeMap);//删除后的TreeMap：{b=2, c=3, d=4, e=5}

        // ******************  1.8新增方法  ********************
        // NavigableSet<V> descendingKeySet();  返回所有键值，并且按照键值降序的集合
        NavigableSet<String> navigableSet1 = treeMap.descendingKeySet();
        for (String s1: navigableSet1) {
            System.out.print(s1 + " ");//e d c b
        }
        System.out.println();

        // NavigableMap<K, V> descendingMap();  返回所有条目，并且按照键值降序的集合
        navigableMap = treeMap.descendingMap();
        for (NavigableMap.Entry<String, Integer> s1: navigableMap.entrySet()) {
            System.out.print(s1.getKey() + ":" + s1.getValue() + "  ");//e:5  d:4  c:3  b:2
        }
        System.out.println();

        // NavigableSet<V> navigableKeySet(); 返回所有顺序的键值的NavigableSet集合
        navigableSet1 = treeMap.navigableKeySet();
        for (String s1: navigableSet1) {
            System.out.print(s1 + " ");//b c d e
        }
        System.out.println();

        // ******************  其他方法跟 HashSet 一样  ********************

    }
}
