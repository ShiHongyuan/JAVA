package concurrent;

import java.util.*;
import java.util.concurrent.*;

/**
 * jdk提供的并发容器
 *
 * Java提供的线程安全的 Queue 可以分为阻塞队列和非阻塞队列：
 * 其中阻塞队列的典型例子是 BlockingQueue，非阻塞队列的典型例子是ConcurrentLinkedQueue。
 * 阻塞队列通过加锁来实现。
 * 非阻塞队列是无锁的，通过 CAS 非阻塞算法实现，因此算是高并发环境中性能最好的队列了。
 */
public class Concurrent {
    public static void main (String[] args) {

        // null可以作为map的key，但是多个null被认为是同一个
        // 除了PriorityBlockingQueue要排序不能插入null值，其他队列，链表都可以插入null值。
        Map<String, Integer> map = new HashMap<>();
        map.put(null,1);
        map.put(null,2);
        System.out.println(map.get(null));//2

        /**
         * java.util.ArrayList：
         *          是非线程安全的。
         *          在写的时候，同时有多线程在并发的读（iterator或者增强for循环（增强for循环的底层是iterator实现的）），
         *          读的过程中，比如说读了几个，后面几个被写线程给修改了，就会抛出 ConcurrentModificationException异常。
         *
         * java.util.Collections.synchronizedList:
         *          是线程安全的，是由java.util.ArrayList转换而来的，指向原对象的可变对象，但是支持同步。
         *          实现线程安全的方式是，给add/remove/iterator等方法添加了 synchronized多线程互斥处理。
         *          但是注意增强for循环 是隐式使用iterator，没有synchronized，并不是同步处理的。
         *          所以 synchronizedList直接调用其列表方法，就是线程安全的。
         *
         * java.util.concurrent.CopyOnWriteArrayList:
         *          是线程安全的，比读写锁还要厉害，读取是完全不用加锁的，写入也不会阻塞读取操作。只有写入和写入之间需要进行同步等待。
         *
         *          实现方式：
         *          不是靠synchronized实现安全性，
         *          而是当修改 List 的时候，对原有数据进行一次复制，将修改的内容写入副本。写完之后，再将原来的引用指向副本。
         *          当读 List 的时候，返回一个原引用的返回值作为读的对象，这样在写完即使修改了原引用指向副本，读的对象也是指向原引用的。
         *          只在add的时候，会加锁，防止多个写时创建多个副本。
         *
         *
         *          写不影响读（iterator或者增强for循环），就不会抛出 ConcurrentModificationException异常。
         *
         *          如果频繁执行写操作，就得频繁的复制，会比较花时间，所以CopyOnWriteArrayList适合读操作频繁，写操作少的。
         *
         * java.util.concurrent.CopyOnWriteSet: 实现写时复制的Set。
         */

        // java.util.Collections.synchronizedList
        List<Integer> list1 = Collections.synchronizedList(new ArrayList<Integer>());
        list1.add(1);
        list1.remove(0);

        list1 = new ArrayList<>();
        list1.add(2);

        // java.util.concurrent.CopyOnWriteArrayList
        List<Integer> list2 = new CopyOnWriteArrayList<>();
        list2.add(2);
        list2.remove(0);


        /**
         * java.util.concurrent.BlockingQueue接口以及它的实现类们：阻塞队列，适合于数据共享。
         * 都是生产者消费者模式，生产者没有put前，take会阻塞wait，直到有元素后被notify，装满后put，直到消费者take后，才能真的放进去。
         *
         * BlockingQueue extends Queue
         * LinkedList implements Queue
         *
         * java.util.LinkedList: offer/peek/remove等操作，是非线程安全的，在取数据时不会等待，可能抛出 NoSuchElementException
         *
         * java.util.concurrent.LinkedBlockingQueue<T>：take/put等操作，是线程安全的，基于单向链表元素个数没有限制，只在没有数据可take时会阻塞wait，永远不会抛出 NoSuchElementException
         *
         * java.util.concurrent.ArrayBlockingQueue<T>: 基于数组，元素个数固定。当数组满了仍要put数据，或者数组为空仍要take数据，队列都会阻塞 wait，不会抛出异常
         *
         * java.util.concurrent.PriorityBlockingQueue<T>: 基于链表元素个数没有限制，只在没有数据可take时会阻塞wait，永远不会抛出 NoSuchElementException，
         *      但是take数据有优先级，按照元素的Comparable接口的自然排序，或者按照构造函数传入的Comparator比较器接口指定的顺序。
         *
         *
         * java.util.concurrent.SynchronousQueue<T>: 直接传递的队列，也可以说只能有一个元素，如果生产者put，在消费者take之前，put会一直阻塞；如果消费者take，在生产者put之前，take会一直阻塞。
         *
         * java.util.concurrent.DelayQueue<T>：基于链表，但是take时，需要等到各元素指定的时间到期后才能take。到期时间最长的元素将先被take。
         *
         */

        // ********** java.util.concurrent.ArrayBlockingQueue<T> *********
        // 保证公平性的ArrayBlockingQueue构造函数
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10,true);
        blockingQueue.add(null);



        // ********** java.util.concurrent.LinkedBlockingQueue<T> *********
        // 为了防止 LinkedBlockingQueue 容量迅速增，损耗大量内存。通常在创建LinkedBlockingQueue 对象时，会指定最大容量，如果未指定，最大容量等于Integer.MAX_VALUE
        // 指定了最大容量的LinkedBlockingQueue，就是有界队列，会出现put阻塞，否则不会出现put阻塞。
        LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<>(10);
        list.add(null);

        // java.util.LinkedList 实现LinkedBlockingQueue的效果，改装方法
        LinkedList<Integer> linkedList = new LinkedList<>();
        LinkedBlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();


        // ********** 取元素
        class SynchronizedLinkedQueue {
            synchronized Integer getElement() throws InterruptedException {
                while (linkedList.peek() == null) {
                    wait();
                }
                return linkedList.remove();
            } // 等同于 linkedBlockingQueue.take();



            // ********** 放元素
            synchronized void putElement(Integer i) {
                linkedList.offer(i);
                notifyAll();
            } //等同于 linkedBlockingQueue.put(i);


        }


        // LinkedBlockingQueue 也可以设置方法内部的wait超时
        // poll超时，30s未获取到元素就超时返回null
        try {
            // TimeUnit 是一个枚举类型的类，表示的时间单位都是long类型
            Integer req = linkedBlockingQueue.poll(30L, TimeUnit.SECONDS);
            System.out.println(req);//null
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // offer超时，30s未放入元素就超时返回false
        try {
            // TimeUnit 是一个枚举类型的类，表示的时间单位都是long类型
            boolean offer = linkedBlockingQueue.offer(1, 30L, TimeUnit.SECONDS);
            System.out.println(offer);//true
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // ********** java.util.concurrent.PriorityBlockingQueue<T> *********
        // 就是 PriorityQueue 的线程安全版本。
        // 不能指定最大的容量，是个无界队列
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue();

        // PriorityBlockingQueue 因为要排序，不能插入null值。并且插入对象必须是可比较大小的（comparable）
        priorityBlockingQueue.add(null);// 运行时报 ClassCastException 异常

        /**
         * java.util.concurrent.ConcurrentLinkedQueue类 高效的并发队列
         *      元素个数没有限制的链表队列，不是生产者消费者模式，所以是非阻塞的队列。
         *      但是操作是线程安全的，内部结构是分开的，线程之间互不影响，所以也就无需执行互斥处理。
         *
         *      相当于一个线程安全的LinkedList
         */

        /**
         * java.util.concurrent.ConcurrentMap接口
         *
         * java.util.concurrent.ConcurrentHashMap类 继承自 ConcurrentMap接口
         *      可以并发，操作是线程安全的map。
         *      内部结构是分开的，线程之间互不影响，所以也就无需执行互斥处理。
         *
         *
         *      HashMap通过使用一个全局的锁来同步不同线程间的并发访问，因此会带来不可忽视的性能问题。
         *      ConcurrentHashMap在写操作时通过锁分段技术只对所操作的段加锁而不影响客户端对其它段的访问。
         *
         */

        /**
         * java.util.concurrent.ConcurrentSkipListMap类: 跳表的实现。
         *      这是一个Map，使用跳表的数据结构进行快速查找。
         *
         *      跳表是一种可以用来快速查找的数据结构，有点类似于平衡树。
         *      它们都可以对元素进行快速的查找。但一个重要的区别是：对平衡树的插入和删除往往很可能导致平衡树进行一次全局的调整。而对跳表的插入和删除只需要对整个数据结构的局部进行操作即可。
         *      这样带来的好处是：在高并发的情况下，你会需要一个全局锁来保证整个平衡树的线程安全。而对于跳表，你只需要部分锁即可。
         *      这样，在高并发环境下，你就可以拥有更好的性能。而就查询的性能而言，跳表的时间复杂度也是 O(nlogn) 所以在并发数据结构中，JDK 使用跳表来实现一个 Map。
         *
         *      使用跳表实现Map 和使用哈希算法实现Map的另外一个不同之处是：
         *      哈希并不会保存元素的顺序，
         *      而跳表内所有的元素都是排序的，因此在对跳表进行遍历时，你会得到一个有序的结果。
         *      所以，如果你的应用需要有序性，那么跳表就是你不二的选择。
         */



    }
}
