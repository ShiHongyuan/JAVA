import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleFloatIntLongShortOperation {
    public static void main(String[] args) {

        float f = (float)1.3; // 大的不能隐式转换成小的，1.3默认是double，不能隐式转换成float
        float f1 = 1.3f;      // 1.3f默认是float,不用转换

        // 整型就没有默认的类型，只有浮点数默认是double
        long l = 1;
        short s = 2;
        byte b = 3;

        int i = 0;
        double d = i; // 隐式转换
        System.out.println(d == i);  // true  i隐式转换成double
        System.out.println(d == 0);  // true  0隐式转换成double

        int i1 =1;
        double d1 = i1; // 隐式转换
        System.out.println(d1 == i1);  // true  i1隐式转换成double
        System.out.println(d1 == 1);  // true   1隐式转换成double

        double dd = 1.9;
        float ff = 1.9f;
        System.out.println((int)dd); // 1  浮点转int，直接去掉小数部分
        System.out.println((int)ff); // 1  浮点转int，直接去掉小数部分


        System.out.println(2.00-1.10); // 0.8999999999999999   double - double，由于0.1存储不精确，所以结果不精确，循环后面的直接舍弃掉了
        System.out.println(1 - 0.9);   // 0.09999999999999998  double - double，由于0.9存储不精确，所以结果不精确
        System.out.println(1.00 - 0.00); // 1.0  double - double，但是整数部分存储是精确的，0.0无限接近于0，所以结果看起来比较精确，但是实际上存储的也不是精确的，因为0不精确

        System.out.println(1f - 0.9f);     // 0.100000024  float-float，得到的也是float，存储0.9也不精确，舍弃得更多
        System.out.println(2.00f - 1.10f); // 0.9          float-float，得到的也是float，存储0.1也不精确


        System.out.println(2.00-1.10);   // 0.8999999999999999
        System.out.println(2.0 - 1.10f); // 0.8999999761581421  double-double，1.10f隐式转换成double，由于1.10f本身就是不精确的存储，
                                         // 转换成double时，与1.10本身double的存储又不一样了，所以结果也不一样了

        double d2 = 0.9;
        float f2 = 0.9f;
        System.out.println(d2 == f2);  // false float会隐式转换成double后比较，但是由于float存储的0.9不精确，转换成double时，
                                       // 与0.9本身double的存储也不一样，比较是二进制的比较，所以不等
        System.out.println(d2 < f2);   // false  因为f2隐式转换成double后二进制值是0.8999999761581421，double本身0.9的二进制值是0.8999999999999999
        System.out.println(d2 > f2);   // true   同理，所以f2=0.8999999761581421，d2=0.8999999999999999 f2 < d2

        double d3 = 1;
        float f3 = 1f;
        System.out.println(d3 == f3);  // true  float会隐式转换成double后比较，但是float和double对整数的存储都是一样的，精确的，所以相等

        System.out.println(0.9f);         // 0.9
        System.out.println(0.9d);         // 0.9
        System.out.println((double)0.9f); // 0.8999999761581421  同样的float转换成double后值就不一样了


        // 浮点数不能有余数运算
        System.out.println(5.5%10); // 5.5


        /** 由上可知，float和double不适合用于浮点数的计算和比较，一般用BigDecimal
         * float和double类型是为了在广域数值范围上提供较为精确的快速近似计算而精心设计的。然而，它们没有提供完全精确的结果，所以不应该被用于要求精确结果的场合
         * 在大多数的商业计算中，float和double不适合用于计算和比较，一般用BigDecimal来进行精确计算
         *
         * java.math.BigDecima
         */

        /**
         * BigDecimal构造方法
         */
        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal bString = new BigDecimal("2.3");
        BigDecimal bDouble = new BigDecimal(2.3);

        System.out.println("bigDecimal=" + bigDecimal); // 2 将int表示形式转换成BigDecimal
        System.out.println("bDouble=" + bDouble);       // 2.3 将String表示形式转换成BigDecimal 强烈建议使用
        System.out.println("bString=" + bString);       // 2.2999999999 将double表示形式转换为BigDecimal *不建议使用
                                                        // 因为double不精确，传进去的不一样是2.3，bDouble就不一定是2.3

        // 必须用double作为构造源时的办法
        BigDecimal bDouble1 = BigDecimal.valueOf(2.3);
        BigDecimal bDouble2 = new BigDecimal(Double.toString(2.3));

        System.out.println("bDouble1=" + bDouble1);     // 2.3
        System.out.println("bDouble2=" + bDouble2);     // 2.3


        double test = 2.3;
        BigDecimal bDouble3 = BigDecimal.valueOf(test);
        BigDecimal bDouble4 = new BigDecimal(Double.toString(test));

        System.out.println("bDouble3=" + bDouble3);     // 2.3
        System.out.println("bDouble4=" + bDouble4);     // 2.3

        /**
         * BigDecimal加减乘除运算
         */
        BigDecimal bigDecimal1 = new BigDecimal("4.5");
        BigDecimal bigDecimal2 = new BigDecimal("1.5");
        System.out.println("a + b =" + bigDecimal1.add(bigDecimal2));      // 6.0
        System.out.println("a - b =" + bigDecimal1.subtract(bigDecimal2)); // 3.0
        System.out.println("a * b =" + bigDecimal1.multiply(bigDecimal2)); // 6.75
        System.out.println("a / b =" + bigDecimal1.divide(bigDecimal2));   // 3

        // 减乘除其实最终都返回的是一个新的BigDecimal对象，因为BigInteger与BigDecimal都是不可变的（immutable）的，在进行每一步运算时，都会产生一个新的对象
        System.out.println(bigDecimal1.add(bigDecimal2)); // 6.0
        System.out.println(bigDecimal1); // 4.5 bigDecimal1、bigDecimal2都是没有变的
        System.out.println(bigDecimal2); // 1.5 bigDecimal1、bigDecimal2都是没有变的

        /** 注意 除法运算divide，是三个参数public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode)
         *  第二个参数表示小数点后保留位数，第三个参数表示舍入模式：
         *
         *  java.math.RoundingMode
         *
         *  RoundingMode.CEILING  //向正无穷方向舍入
         *  RoundingMode.FLOOR    //向负无穷方向舍入
         *
         *  RoundingMode.DOWN     //向0方向舍入
         *  RoundingMode.UP       //向远离0的方向舍入
         *
         *  RoundingMode.HALF_DOWN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向下舍入, 例如1.55 保留一位小数结果为1.5
         *
         *  RoundingMode.HALF_EVEN    //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP，如果是偶数，使用ROUND_HALF_DOWN
         *
         *  RoundingMode.HALF_UP      //向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，向上舍入, 1.55保留一位小数结果为1.6，真正的四舍五入
         *
         *  RoundingMode.UNNECESSARY    //计算结果是精确的，不需要舍入模式，默认模式
         */

        // divide的RoundingMode默认是ROUND_UNNECESSARY，scale默认是1位小数，所以能除尽，就除尽，除尽成整数、小数都可以，但也是一个BigDecimal
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.5")));   // 3
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("3")));     // 1.5
        // System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.3")));   // 除不尽，又没指定RoundingMode，就会报错
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.3"), RoundingMode.HALF_UP)); // 3.5 除不尽，指定了RoundingMode，四舍五入，scale默认保留一位小数
        System.out.println(new BigDecimal("4.5").divide(new BigDecimal("1.3"), 2, RoundingMode.HALF_UP)); // 3.46，指定scale保留2位小数


        /**
         * BigDecimal截断、四舍五入，setScale，保留小数位是不能缺省的，RoundingMode缺省是UNNECESSARY
         */
        BigDecimal bigDecimal3 = new BigDecimal("3.265");
        // 截断成4位小数
        System.out.println(bigDecimal3.setScale(4)); // 3.2650  RoundingMode缺省默认是UNNECESSARY
        // 截断成2位小数，RoundingMode缺省UNNECESSARY，会报错
        // System.out.println(bigDecimal3.setScale(2)); // 报错，保留2位不能舍入，会报错
        // 截断成2位小数，并且四舍五入
        System.out.println(bigDecimal3.setScale(2, RoundingMode.HALF_UP)); // 3.27 指定了RoundingMode，保留2位四舍五入

    }
}
