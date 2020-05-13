package collectionFramework;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * java.util.LinkedList
 */

public class LinkedCollection {
    public static void main(String... args) {
        //创建存放int类型的linkedList
        LinkedList<Integer> linkedList = new LinkedList<>();
        /************************** linkedList的基本操作 ************************/
        linkedList.addFirst(0); // 添加元素到列表开头
        linkedList.add(1); // 在列表结尾添加元素
        linkedList.add(2, 2); // 在指定位置添加元素
        linkedList.addLast(3); // 添加元素到列表结尾

        System.out.println("LinkedList（直接输出的）: " + linkedList);//LinkedList（直接输出的）: [0, 1, 2, 3]

        System.out.println("getFirst()获得第一个元素: " + linkedList.getFirst()); // getFirst()获得第一个元素: 0
        System.out.println("getLast()获得第最后一个元素: " + linkedList.getLast()); // getLast()获得第最后一个元素: 3
        System.out.println("removeFirst()删除第一个元素并返回: " + linkedList.removeFirst()); // removeFirst()删除第一个元素并返回: 0
        System.out.println("removeLast()删除最后一个元素并返回: " + linkedList.removeLast()); // removeLast()删除最后一个元素并返回: 3
        System.out.println("After remove:" + linkedList);//After remove:[1, 2]
        System.out.println("contains()方法判断列表是否包含1这个元素:" + linkedList.contains(1)); // contains()方法判断列表是否包含1这个元素:true
        System.out.println("该linkedList的大小 : " + linkedList.size()); // 该linkedList的大小 : 2

        /************************** 位置访问操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.set(1, 3); // 将此列表中指定位置的元素替换为指定的元素
        System.out.println("After set(1, 3):" + linkedList);//After set(1, 3):[1, 3]
        System.out.println("get(1)获得指定位置（这里为1）的元素: " + linkedList.get(1)); // get(1)获得指定位置（这里为1）的元素: 3

        /************************** Search操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.add(3);
        System.out.println("indexOf(3): " + linkedList.indexOf(3)); // indexOf(3): 1
        System.out.println("lastIndexOf(3): " + linkedList.lastIndexOf(3));// lastIndexOf(3): 2

        /************************** Queue操作 ************************/
        System.out.println("-----------------------------------------");
        System.out.println("peek(): " + linkedList.peek()); // peek(): 1
        System.out.println("element(): " + linkedList.element()); // element(): 1
        linkedList.poll(); // 获取并移除此列表的头
        System.out.println("After poll():" + linkedList);//After poll():[3, 3]
        linkedList.remove();
        System.out.println("After remove():" + linkedList); // After remove():[3]
        linkedList.offer(4);
        System.out.println("After offer(4):" + linkedList); // After offer(4):[3, 4]

        /************************** Deque操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.offerFirst(2); // After offerFirst(2):[2, 3, 4]
        System.out.println("After offerFirst(2):" + linkedList);
        linkedList.offerLast(5); // After offerLast(5):[2, 3, 4, 5]
        System.out.println("After offerLast(5):" + linkedList);
        System.out.println("peekFirst(): " + linkedList.peekFirst()); // peekFirst(): 2
        System.out.println("peekLast(): " + linkedList.peekLast()); // peekLast(): 5
        linkedList.pollFirst(); // 获取并移除此列表的第一个元素
        System.out.println("After pollFirst():" + linkedList);//After pollFirst():[3, 4, 5]
        linkedList.pollLast(); // 获取并移除此列表的最后一个元素
        System.out.println("After pollLast():" + linkedList);//After pollLast():[3, 4]
        linkedList.push(2); // 将元素推入此列表所表示的堆栈（插入到列表的头）
        System.out.println("After push(2):" + linkedList);//After push(2):[2, 3, 4]
        linkedList.pop(); // 从此列表所表示的堆栈处弹出一个元素（获取并移除列表第一个元素）
        System.out.println("After pop():" + linkedList);//After pop():[3, 4]
        linkedList.add(3);
        linkedList.removeFirstOccurrence(3); // 从此列表中移除第一次出现的指定元素（从头部到尾部遍历列表）
        System.out.println("After removeFirstOccurrence(3):" + linkedList);//After removeFirstOccurrence(3):[4, 3]
        linkedList.removeLastOccurrence(3); // 从此列表中移除最后一次出现的指定元素（从尾部到头部遍历列表）
        System.out.println("After removeFirstOccurrence(3):" + linkedList);//After removeFirstOccurrence(3):[4]

        /************************** 遍历操作 ************************/
        System.out.println("-----------------------------------------");
        linkedList.clear();
        for (int i = 0; i < 100000; i++) {
            linkedList.add(i);
        }
        // 迭代器遍历
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        long end = System.currentTimeMillis();
        System.out.println("Iterator：" + (end - start) + " ms");//Iterator：33 ms

        // 顺序遍历(随机遍历)
        start = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        end = System.currentTimeMillis();
        System.out.println("for：" + (end - start) + " ms");//for：5668 ms

        // 另一种for循环遍历
        start = System.currentTimeMillis();
        for (Integer i : linkedList)
            ;
        end = System.currentTimeMillis();
        System.out.println("for2：" + (end - start) + " ms");//for2：2 ms

        // 通过pollFirst()或pollLast()来遍历LinkedList
        LinkedList<Integer> temp1 = new LinkedList<>();
        temp1.addAll(linkedList);
        start = System.currentTimeMillis();
        while (temp1.size() != 0) {
            temp1.pollFirst();
        }
        end = System.currentTimeMillis();
        System.out.println("pollFirst()或pollLast()：" + (end - start) + " ms");//pollFirst()或pollLast()：5 ms

        // 通过removeFirst()或removeLast()来遍历LinkedList
        LinkedList<Integer> temp2 = new LinkedList<>();
        temp2.addAll(linkedList);
        start = System.currentTimeMillis();
        while (temp2.size() != 0) {
            temp2.removeFirst();
        }
        end = System.currentTimeMillis();
        System.out.println("removeFirst()或removeLast()：" + (end - start) + " ms");//removeFirst()或removeLast()：4 ms
    }
}
