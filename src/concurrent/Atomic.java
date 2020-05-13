package concurrent;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class Atomic {
    /**
     * long和double的赋值操作不是原子的，其他的赋值操作是原子的。
     *
     *
     * java.util,concurrent.atomic包：
     *      多线程下使用原子类型，不需要加锁，也可以实现线程安全。
     *      原子类的方法都是线程安全的方法，主要是利用CAS (compare and swap) + volatile 和 native 方法来保证原子操作）：
     *
     * 以原子方式操作int、boolean、long、引用对象的类：
     *      AtomicInteger、AtomicBoolean、AtomicLong、AtomicReference<V>
     *
     * 以原子方式操作int、long、引用对象的数组的类：
     *       AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray<V>
     *
     * 以原子更新某个类里的某个字段时，需要用到对象的属性修改类型原子类：
     *      AtomicIntegerFieldUpdater:原子更新整型字段的更新器
     *      AtomicLongFieldUpdater：原子更新长整型字段的更新器
     */
    public static void main (String[] args) {
        /**
         * 基本类型原子类 AtomicInteger、AtomicBoolean、AtomicLong
         *
         *
         *     提供的方法几乎相同，AtomicInteger 类常用方法：
         *     public final int get() //获取当前的值
         *     public final int getAndSet(int newValue)//获取当前的值，并设置新的值，返回以前的值
         *     public final int getAndIncrement()//获取当前的值，并自增，返回以前的值
         *     public final int getAndDecrement() //获取当前的值，并自减，返回以前的值
         *     public final int getAndAdd(int delta) //获取当前的值，并加上预期的值，返回以前的值
         *     boolean compareAndSet(int expect, int update) //如果输入的数值等于预期值，则以原子方式将该值设置为输入值（update）
         *     public final void lazySet(int newValue)//最终设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
         */
        // AtomicInteger 常见方法使用
        int temvalue = 0;
        AtomicInteger i = new AtomicInteger(0);
        temvalue = i.getAndSet(3);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:0;  i:3
        temvalue = i.getAndIncrement();
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:3;  i:4
        temvalue = i.getAndAdd(5);
        System.out.println("temvalue:" + temvalue + ";  i:" + i);//temvalue:4;  i:9


        /**
         *  基本类型数组原子类：AtomicIntegerArray、AtomicLongArray
         *
         *     提供的方法几乎相同，AtomicIntegerArray 类常用方法：
         *     public final int get(int i) //获取 index=i 位置元素的值
         *     public final int getAndSet(int i, int newValue)//返回 index=i 位置的当前的值，并将其设置为新值：newValue，返回以前的值
         *     public final int getAndIncrement(int i)//获取 index=i 位置元素的值，并让该位置的元素自增，返回以前的值
         *     public final int getAndDecrement(int i) //获取 index=i 位置元素的值，并让该位置的元素自减，返回以前的值
         *     public final int getAndAdd(int delta) //获取 index=i 位置元素的值，并加上预期的值，返回以前的值
         *     boolean compareAndSet(int i, int expect, int update) //如果输入的数值等于index=i 位置的预期值，则以原子方式将 index=i 位置的元素值设置为输入值（update）
         *     public final void lazySet(int i, int newValue)//最终 将index=i 位置的元素设置为newValue,使用 lazySet 设置之后可能导致其他线程在之后的一小段时间内还是可以读到旧的值。
         */
        // AtomicIntegerArray 常见方法使用
        temvalue = 0;
        int[] nums = { 1, 2, 3, 4, 5, 6 };
        AtomicIntegerArray array = new AtomicIntegerArray(nums);
        for (int j = 0; j < nums.length; j++) {
            System.out.print(array.get(j) + " ");//1 2 3 4 5 6
        }
        temvalue = array.getAndSet(0, 2);
        System.out.println("temvalue:" + temvalue + ";  i:" + array);//temvalue:1;  i:[2, 2, 3, 4, 5, 6]
        temvalue = array.getAndIncrement(0);
        System.out.println("temvalue:" + temvalue + ";  i:" + array);//temvalue:2;  i:[3, 2, 3, 4, 5, 6]
        temvalue = array.getAndAdd(0, 5);
        System.out.println("temvalue:" + temvalue + ";  i:" + array);//temvalue:3;  i:[8, 2, 3, 4, 5, 6]


        /**
         * 引用类型原子类：AtomicReference<V>，AtomicReferenceArray<V>
         *     因为引用类型包含多个变量，可以一次更新多个变量。
         */
        // AtomicReference<V>
        AtomicReference<Person> ar = new AtomicReference<Person>();
        Person person = new Person("SnailClimb", 22);
        ar.set(person);
        Person updatePerson = new Person("Daisy", 20);
        ar.compareAndSet(person, updatePerson);

        System.out.println(ar.get().getName());//Daisy
        System.out.println(ar.get().getAge());//20


        /**
         * 以原子更新某个类里的某个字段时，需要用到对象的属性修改类型原子类：
         *      AtomicIntegerFieldUpdater:原子更新整型字段的更新器
         *      AtomicLongFieldUpdater：原子更新长整型字段的更新器
         *
         * 第一步，因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须使用静态方法 newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。
         * 第二步，更新的对象属性必须使用 public volatile 修饰符。
         */
        // AtomicIntegerFieldUpdater
        // User的age字段是 public volatile 修饰的字段。
        AtomicIntegerFieldUpdater<User> a = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");

        User user = new User("Java", 22);
        System.out.println(a.getAndIncrement(user));// 22
        System.out.println(a.get(user));// 23

    }
}

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class User {
    private String name;
    // 想要原子更新某个类里的某个字段时，更新的对象属性必须使用 public volatile 修饰符。
    public volatile int age;

    public User(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}


