package concurrent;

public class ThreadPriority {
    /**
     * 线程优先级：
     *      优先级的有效性与jvm的实现和版本，操作系统有关
     *      可能在某个jvm，优先级高的占用更长的CPU时间，有可能先占用全部的CPU时间，有可能对线程的执行不会产生任何影响。
     *
     *
     *      优先级静态字段：
     *          Thread.MIN_PRIORITY
     *          Thread.NORM_PRIORITY
     *          Thread.MAX_PRIORITY
     *
     *      设置优先级:
     *          t.setPriority(Thread.MIN_PRIORITY);  线程实例的方法
     *      获取优先级：
     *          t.getPriority();  线程实例的方法
     */
}
