public class ClassLibrary {
    public static void main(String[] args) {
        /**
         * java.util.Date 日期、时间
         * */
        java.util.Date date = new java.util.Date();
        System.out.println(date.getTime());     // 1552789287767  从1970.1.1至今的毫秒数
        System.out.println(date.toString());    // Sun Mar 17 10:21:27 CST 2019 当前年月日星期时间

        /**
         * java.util.Random 随机数，种子相同，产生的随机序列相同
         * */
        // 无参构造方法:Random()   默认用过去的时间作为种子
        java.util.Random random = new java.util.Random();
        System.out.println(random.nextInt());     // 1999005345  返回一个随机int值

        // 带参构造方法:Random(long)   用传进去的long对象作为种子，种子相同，产生的随机数就相同（伪随机）
        java.util.Random random1 = new java.util.Random(3l);
        System.out.println(random1.nextInt());            // -1155099828 返回一个 随机int值
        System.out.println(random1.nextInt(1000));  // 660 返回一个 [0, 1000) 的随机int值
        System.out.println(random1.nextLong());           // 1309571695633557482 返回一个 随机long值
        System.out.println(random1.nextDouble());         // 0.06712000939049956 返回一个 (0.0, 1.0) 随机double值
        System.out.println(random1.nextFloat());          // 0.76815695 返回一个 (0.0f, 1.0f) 随机float值
        System.out.println(random1.nextBoolean());        // true 返回一个 随机boolean值

        java.util.Random random2 = new java.util.Random(3l);
        System.out.println(random2.nextInt());            // -1155099828 返回一个 随机int值
        System.out.println(random2.nextInt(1000));  // 660 返回一个 [0, 1000) 的随机int值
        System.out.println(random2.nextLong());           // 1309571695633557482 返回一个 随机long值
        System.out.println(random2.nextDouble());         // 0.06712000939049956  返回一个 (0.0, 1.0) 随机double值
        System.out.println(random2.nextFloat());          // 0.76815695   返回一个 (0.0f, 1.0f) 随机float值
        System.out.println(random2.nextBoolean());        // true  返回一个 随机boolean值



    }
}
