package collectionFramework;

import java.util.*;

/**
 * java集合框架：数据结构，容器对象
 * 数据结构，容器：能够存储其他对象的对象 + 访问和操作这些对象
 * 两种类型的容器：集合（存储元素集合） 图（存储键值对）
 * 集合类型：1、规则集set 不重复的集合  2、线性表 有序集合  3、队列 先进先出集合
 * 集合实现的接口，继承的抽象类，实现的具体类都在java.util.*中
 * 集合框架都实现了java.lang.Cloneable<T> 和 java.io.Serializable<T>接口，可复制可序列化
 *
 *
 * Collections工具类：
 * 基本的引用类型String，包装类型Integer，Charactor，Double，Float，Short，Long，Boolean，Byte都实现了java.lang.Comparable<T>接口，在集合对象里，可以使用Collections.sort(collection)排序集合对象
 * Collections的方法中所有依赖的自然顺序，都是被排序对象实现的Comparable<T>的compareTo方法定义的顺序
 * Collections的方法中和collection集合的方法中，所有的定制排序，都是通过传递一个自定义排序顺序的比较器java.util.Comparator<T>来实现的
 */
public class CollectionsFactory {
    public static void main(String[] args) {

        /****************************  collection（List，Set，Map）公共方法  ***************************/

        // 不像数组Array，所有collection对象都重写了toString方法转换成字符串形式，可以直接打印
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        System.out.println(names);//[peter, anna, mike, xenia]


        /****************************  java.util.Collections工具类  ***************************/

        // *****************  sort的自然排序 & 定制排序  *****************
        List<Person> list = new ArrayList<>();
        list.add(new Person("anna",10));
        list.add(new Person("tina",10));
        list.add(new Person("susan",10));
        list.add(new Person("balala",10));

        //Collections.sort的默认比较器（所谓的自然排序）用的就是实现Comparable接口时的compareTo方法
        //所以list的Person必须实现Comparable<Person>接口，否则Collections.sort(list)用不了，编译不通过
        Collections.sort(list);

        // *****************  Collections集合类关于List的方法  *****************

        // 自然排序 sort(List list)
        List<Integer> ages = Arrays.asList(15, 10, 27, 25, 20, 17);
        Collections.sort(ages);//自然排序就是根据String实现的Comparable接口的compareTo方法排序
        System.out.println(ages);//[10, 15, 17, 20, 25, 27]

        // 定制排序 sort(List list, Comparator c)
        Collections.sort(ages, new Comparator<Integer>() {//这里第二个参数需要一个实现List的比较方法compare的比较器，默认用默认的比较器，也可以创建匿名比较器自定义比较的方式
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);//自定义的比较器改变了compareTo方法排序的前后顺序，刚好是自然排序的倒序
            }
        });
        System.out.println(ages);//[27, 25, 20, 17, 15, 10]

        // 反转 reverse(List list)，单纯的反转
        Collections.reverse(ages);
        System.out.println(ages);//[10, 15, 17, 20, 25, 27]  刚排完序，反转成倒序

        // 随机排序 shuffle(List list)，也可以指定随机种子
        Collections.shuffle(ages);
        System.out.println(ages);//[27, 17, 20, 15, 10, 25]

        // 交换两个索引位置的元素 swap(List list, int i , int j)
        Collections.swap(ages, 0,5);
        System.out.println(ages);//[17, 25, 10, 20, 15, 27]

        // 指定范围前后平移元素 rotate(List list, int distance)，当distance为正数时，将list后distance个元素整体移到最前面。当distance为负数时，将 list的前distance个元素整体移到最后面
        Collections.rotate(ages,2);
        System.out.println(ages);//[15, 27, 17, 25, 10, 20]

        Collections.rotate(ages,-2);
        System.out.println(ages);//[17, 25, 10, 20, 15, 27]

        // 二分查找关键字 binarySearch(List list, Object key)，被查找的列表必须是有序的，否则找不到
        Collections.sort(ages);
        System.out.println(Collections.binarySearch(ages, 27));//5
        System.out.println(Collections.binarySearch(ages, 30));//-7

        // 指定List第一次出现的索引 indexOfSubList(List list, List target)
        System.out.println(ages);//[10, 15, 17, 20, 25, 27]

        List<Integer> list1 = Arrays.asList(17, 20, 25);
        System.out.println(Collections.indexOfSubList(ages, list1));//2

        // 指定List最后一次出现的索引 lastIndexOfSubList(List list, list target)
        List<Integer> list2 = Arrays.asList(27);
        System.out.println(Collections.indexOfSubList(ages, list2));//5

        // 用旧元素替换新元素 replaceAll(List list, Object oldVal, Object newVal)
        Collections.replaceAll(ages, new Integer(10), new Integer(13));
        System.out.println(ages);//[13, 15, 17, 20, 25, 27]

        // 用指定的元素替换所有元素 fill(List list, Object obj)
        Collections.fill(ages, 27);
        System.out.println(ages);//[27, 27, 27, 27, 27, 27]


        // *****************  Collections工具类关于集合类 List，Set，Queue，Stack 的方法 ！！注意没有Map，Map是图类 ！！*****************
        ages = new ArrayList<>();
        ages.add(10);
        ages.add(10);
        ages.add(27);
        ages.add(25);
        ages.add(20);
        ages.add(17);

        Set<Integer> incomes = new HashSet<>();
        incomes.add(100);
        incomes.add(250);
        incomes.add(100); // 因为Set是没有重复元素的集合，这个重复元素会被忽略，没有添加
        incomes.add(999);

        Queue<Integer> pays = new PriorityQueue<>();
        pays.add(50);
        pays.add(100);
        pays.add(500);

        Stack<Integer> rates = new Stack<>();
        rates.push(50);
        rates.push(150);
        rates.push(499);

        System.out.println(ages);//[10, 10, 27, 25, 20, 17]
        System.out.println(incomes);//[100, 999, 250]  没有添加重复的元素
        System.out.println(pays);//[50, 100, 500]
        System.out.println(rates);//[50, 150, 499]


        // 返回自然排序结果的最大元素 max(Collection coll)，自然排序后的最后一个元素
        System.out.println(Collections.max(ages));//27
        System.out.println(Collections.max(incomes));//999
        System.out.println(Collections.max(pays));//500
        System.out.println(Collections.max(rates));//499

        System.out.println(Collections.max(names));//"xenia"

        // 同理。返回自然排序结果的最小元素 min(Collection coll)，自然排序后的第一个元素
        System.out.println(Collections.min(ages));//10
        System.out.println(Collections.min(incomes));//100
        System.out.println(Collections.min(pays));//50
        System.out.println(Collections.min(rates));//50

        System.out.println(Collections.min(names));//"anna"

        // 返回定制排序结果的最大元素 max(Collection coll, Comparator c)，比较器定制排序后的最后一个元素

        //这里是在创建一个比较器的匿名实例对象，comparator也是指向这个对象的引用，不是在定义类或者方法的声明类型，所以不能用泛型也不能用通配符，在创建对象时需要指定具体类型，这里要兼容多个类型，所以只能指定为Object
        Comparator<Object> comparator = new Comparator<Object>() { //自定义的排序，刚好与自然排序相反
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Integer) {
                    return ((Integer) o2).compareTo((Integer) o1);
                }
                else if (o1 instanceof String) {
                    return ((String) o2).compareTo((String) o1);
                }
                else {
                    return 0;
                }
            }
        };

        System.out.println(Collections.max(ages, comparator));//10
        System.out.println(Collections.max(incomes, comparator));//100
        System.out.println(Collections.max(pays, comparator));//50
        System.out.println(Collections.max(rates, comparator));//50

        System.out.println(Collections.max(names, comparator));//anna


        // 同理。返回定制排序结果的最小元素 min(Collection coll, Comparator c)，比较器定制排序后的第一个元素
        System.out.println(Collections.min(ages, comparator));//27
        System.out.println(Collections.min(incomes, comparator));//999
        System.out.println(Collections.min(pays, comparator));//500
        System.out.println(Collections.min(rates, comparator));//499

        System.out.println(Collections.min(names, comparator));//xenia


        // 统计元素出现的次数 frequency(Collection c, Object o)
        System.out.println(Collections.frequency(ages,new Integer(10)));//2  List可以有重复元素
        System.out.println(Collections.frequency(incomes,100));//1  Set不能有重复元素
        System.out.println(Collections.frequency(pays,50));//1
        System.out.println(Collections.frequency(rates,499));//1


        // *****************  Collections工具类 转换线程不安全的collection（包括集合类和图类）为同步对象  *****************

        // HashSet，TreeSet，ArrayList,LinkedList,HashMap,TreeMap 都是线程不安全的

        // 保证线程安全 第一种方式：Collections提供多个synchronizedXxx()方法，转换成添加同步锁的线程安全的集合，但是这些方法效率很低，需要线程安全的集合类型时请考虑使用 JUC 包下的并发集合
        // synchronizedList(List<T> list)，返回一个支持同步（线程安全）的 指向原对象的List对象
        List<Integer> SYNages = Collections.synchronizedList(ages);
        SYNages.add(29);
        ages.add(30);
        System.out.println(ages);//[10, 10, 27, 25, 20, 17, 29, 30]
        System.out.println(SYNages);//[10, 10, 27, 25, 20, 17, 29, 30]

        // synchronizedSet(Set<T> s) ，返回一个支持同步（线程安全）的 指向原对象的Set对象
        Set<Integer> SYNincomes = Collections.synchronizedSet(incomes);
        SYNincomes.add(666);
        System.out.println(incomes);//[100, 999, 250, 666]
        System.out.println(SYNincomes);//[100, 999, 250, 666]

        // synchronizedMap(Map<K,V> m) ，返回一个支持同步（线程安全）的 指向原对象的Map对象
        Map<String, Integer> families = new HashMap<>();
        families.put("shy", 27);
        families.put("yzm", 26);
        families.put("child", -3);

        Map<String, Integer> SYNfamilies = Collections.synchronizedMap(families);
        SYNfamilies.put("child2", -8);
        System.out.println(families);//shy=27, child2=-8, yzm=26, child=-3}
        System.out.println(SYNfamilies);//shy=27, child2=-8, yzm=26, child=-3}

        // synchronizedCollection(Collection<T>  c)，返回一个支持同步（线程安全）的 指向原对象的Collection对象，返回不能是Collection的子类对象，因为父类不能转变成子类对象
        // Collection是collection集合对象的父类，是个抽象类，子类强转为父类对象，实际调用的方法还是子类自己实现的方法
        Collection<Integer> SYNpays = Collections.synchronizedCollection((Collection) pays);
        Collection<Integer> SYNrates = Collections.synchronizedCollection((Collection) rates);
        SYNpays.add(1000);
        SYNrates.add(2000);
        System.out.println(SYNpays);//[50, 100, 500, 1000]
        System.out.println(SYNrates);//[50, 150, 499, 2000]


        // 保证线程安全 第二种方式：转换成不可变集合，不可变的东西，不会被任何线程修改，所以就不会有线程安全的问题

        //Collections.emptyXXX()，创建一个空的、不可改变的List，Set，Map新对象
        List<Integer> EMPlist = Collections.emptyList();
        System.out.println(EMPlist);//[]

        Set<Integer> EMPset = Collections.emptySet();
        System.out.println(EMPset);//[]

        Map<Object, Integer> EMPmap = Collections.emptyMap();
        System.out.println(EMPmap);//{}

        //Collections.singletonXXX()，创建一个只有一个元素，不可变的List，Set，Map新对象

//        List<ArrayList<Integer>> SIGlist = Collections.singletonList(ages);不能是ArrayList是因为ages声明的类型是List，而不是ArrayList，虽然根据多态性，ages调用的成员方法是ArrayList的，但是在对外曝露类型时还是得是List

        List<List<Integer>> SIGlist = Collections.singletonList(ages);
        System.out.println(SIGlist);//[[10, 10, 27, 25, 20, 17, 29, 30]]

        Set<List<Integer>> SIGset = Collections.singleton(ages);
        System.out.println(SIGset);//[[10, 10, 27, 25, 20, 17, 29, 30]]

        Map<String, Map<String, Integer>> SIGmap = Collections.singletonMap("1", families);
        System.out.println(SIGmap);//{1={shy=27, child2=-8, yzm=26, child=-3}}

        //unmodifiableXXX()，把普通集合对象转换成一个不可变的List，Set，Map新对象
        List<Integer> UMDlist  = Collections.unmodifiableList(ages);
        System.out.println(UMDlist);//[10, 10, 27, 25, 20, 17, 29, 30]

        Set<Integer> UMDset = Collections.unmodifiableSet(incomes);
        System.out.println(UMDset);//[100, 999, 250, 666]

        Map<Object, Object> UMDmap = Collections.unmodifiableMap(families);
        System.out.println(UMDmap);//{shy=27, child2=-8, yzm=26, child=-3}

        //添加出现异常：java.lang.UnsupportedOperationException，不支持操作
//        EMPlist.add(1);
//        SIGlist.add(ages);
//        UMDset.add(1);


        /****************************  List<T>泛型接口、ArrayList<T>泛型类  ***************************/




    }

}

/****************************  java.util.Collections工具类  ***************************/

// *****************  sort的自然排序 & 制定排序  *****************

class Person implements Comparable<Person>{
    private String name;
    private int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person o){
        if(this.age > o.age) {
            return 1;
        }
        else if(this.age < o.age) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
