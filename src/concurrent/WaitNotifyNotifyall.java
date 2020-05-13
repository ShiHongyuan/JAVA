package concurrent;

/**
 * 线程间协作的方法：
 *      wait() notify() notifyall() 都是java.lang.Object的方法
 *
 *      都是针对一个实例一个锁的方法：—— 与其说是针对线程的操作，不如说是针对实例的线程等待队列的操作。
 *      obj.wait();  obj.notify();  obj.notifyall();
 *      或者实例类方法内部：this.wait();  this.notify();  this.notifyall();
 */
public class WaitNotifyNotifyall {
}
