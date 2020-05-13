package designPattern.flyweight;

/**
 * 为了轻量级共享对象模式：通过工厂类来第一次产生被共享的对象，然后保存下来，使用者从工厂类获取共享对象使用，
 *                   这样就避免了每次使用者使用都创建新的对象，浪费内存空间，而是让相同的对象请求就返回已经生成的对象
 *
 *                   但是共享对象是唯一的，大家修改的都是同一个对象，所以要注意区分哪些属性可以在共享对象里暴露出来被修改被共享，
 *                   哪些属性是跟使用场景关联的，不应该在共享对象里，而应该在使用者的处理流程中确定。
 */
public class Main {

    private static BigString[] bsarray = new BigString[1000];

    public static void main (String[] args) {
//        // 共享对象
//        BigString bigString = new BigString("627", true);
//        bigString.print();
//
//        // 不共享对象
//        bigString = new BigString("627", false);
//        bigString.print();
//
//        bigString = new BigString("62709", true);
//        bigString.print();

        // 测试共享对象和不共享对象的内存使用对比：使用共享不仅内存消耗少，不用每次都去读文件，获取对象的时间也少了
        testAllocation(false);//false: 耗时 388    使用内存 = 3658416
        testAllocation(true);//true: 耗时 5     使用内存 = 508960


    }

    public static void testAllocation(boolean shared) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < bsarray.length; i++) {
            bsarray[i] = new BigString("1212123", shared);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println( shared + ": 耗时 " + (stopTime - startTime) );
        showMemory();
    }

    public static void showMemory () {
        // 先回收其余不必要的垃圾
        Runtime.getRuntime().gc();
        long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("使用内存 = " + used);
    }

}
