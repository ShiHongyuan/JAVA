import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class NumberOperation {
    public static void main (String[] args) {
        /**自动装箱和自动拆箱**/
        int x = new Integer(3); System.out.println(x);     // 3
        // 运算比较等包装类型要转换成基本数据类型后再计算
        System.out.println(new Integer(3) + new Integer(3));  // 6       int 3 + int 3 = 6
        System.out.println(new Integer(2) < new Integer(3));  // true    int 2 < int 3 = true

        /**包装类型 java.lang.*
         * java.lang.Character Boolean implements java.lang.Comparable extends java.lang.Object
         * java.lang.Integer Short Long Double Float Byte implements java.lang.Comparable extends java.lang.Number
         * 所有包装类型都重写了Object的toString() 和 equals() 方法
         * 所有包装类型都继承了 Comparable接口的compareTo()方法
         * String和包装类型 对象是不可变的，没有set方法
         */


        /** 抽象类 java.lang.Number 都不是静态的方法，都没实现 **/
        // byte byteValue() 转换Number的包装类型为基本数据类型 byte
        // int intValue() 转换Number的包装类型为基本数据类型 int
        // short shortValue() 转换Number的包装类型为基本数据类型 ?short
        // long longValue() 转换Number的包装类型为基本数据类型 long
        // float floatValue() 转换Number的包装类型为基本数据类型 float
        // double doubleValue() 转换Number的包装类型为基本数据类型 double

        /** 所有包装类型都没有无参构造方法 **/
//        Integer integer = new Integer();   // new对象时必须传value，否则编译报错


        /** 给函数传参传包装类型，要改变传进来的对象的值，要么是new Integer()会新创建对象，要么是=6，会指向另一个常量，所以函数里的变量的值都会变，跟复制传参一样了 **/
        Integer integer1 = 5;
        Integer integer2 = 5;
        System.out.println(integer1 == integer2);   // true  指向的是同一个常量，所以变量引用值相等

        Integer integer11 = new Integer(5);
        Integer integer22 = new Integer(5);
        System.out.println(integer11 == integer22); // false


        /** 所有包装类型都重写了Object的toString() 和 equals() 方法
         *  toString()输出包装对象的value值(字符，布尔值)对应的字符串：输出对象System.out.println(object) 直接就是调用的System.out.println(object.toString())
         *  equals(Object)源码：
         *  public boolean equals(Object obj) {
         *         if (obj instanceof Integer) {
         *             return value == ((Integer)obj).intValue();
         *         }
         *         return false;
         *     }
         *
         */
        Integer o1 = new Integer(5);
        Integer o2 = new Integer(5);
        Double o3 = 5.2;
        Boolean o4 = true;
        Byte o5 = 3;
        Character o6 = 'a';
        System.out.println(o1);                   // 5   System.out.println(object) 就是调用的System.out.println(object.toString())
        System.out.println(o3);                   // 5.2
        System.out.println(o4);                   // true   布尔值输出布尔值
        System.out.println(o5);                   // 3      byte输出数字
        System.out.println(o6);                   // a      字符输出字符
        System.out.println(o1.toString());        // 5
        System.out.println(o3.toString());        // 5.2
        System.out.println(o4.toString());        // true   布尔值输出布尔值
        System.out.println(o5.toString());        // 3      byte输出数字
        System.out.println(o6.toString());        // a      字符输出字符
        System.out.println( o1.equals(o2) );      // true
        System.out.println( o1.equals(o3) );      // false 因为继承的equals(Object),所以可以传不同对象的参数，但是类型相同比较大小，类型不同直接false



        /** 所有包装类型都实现了 比较接口 java.lang.Comparable **/
        // 泛型接口 Comparable<T> public int compareTo(T) Integer实现接口是Comparable<Integer> 所以实现方法是compareTo(Integer)
        Integer c1 = 5;
        Integer c2 = 7;
        Double c3 = 6.6;
        System.out.println( c1.compareTo(c2) );                     // -1  c1 < c2
        System.out.println( c1.compareTo(1) );                      //  1  c1 > 1
        System.out.println( new Integer(5).compareTo(c1) );    // 0
//        c1.compareTo(c3); // 类型不匹配，编译报错 Integer实现接口是Comparable<Integer> 所以实现方法是compareTo(Integer)




        /** java.lang.Integer **/
        // 构造方法
        Integer integerValue1 = new Integer(2);  // 2
        Integer integerValue2 = new Integer("2");   // 2

        // 继承Number的方法
        System.out.println( integerValue1.intValue() );      // 2
        System.out.println( integerValue1.shortValue() );    // 2
        System.out.println( integerValue1.longValue() );     // 2
        System.out.println( integerValue1.doubleValue() );   // 2.0
        System.out.println( integerValue1.floatValue() );    // 2.0
        System.out.println( integerValue1.byteValue() );     // 2


        // 扩展的静态变量
        System.out.println(Integer.MAX_VALUE);             // 2147483647
        System.out.println(integerValue1.MIN_VALUE);       // -2147483648

        // 扩展的方法
        // 其他值转换成Integer
        Integer integerTemp = Integer.valueOf(1);
        System.out.println(integerTemp);                  // 1

        integerTemp =  Integer.valueOf("1");
        System.out.println(integerTemp);                  // 1

         /** valueOf只能转换对应类型的字符串，否则运行报错 **/
//        integerTemp = Integer.valueOf("1.23");
//        System.out.println(integerTemp);


        // 其他值转换成int
        int intTemp =  Integer.parseInt("1");           // 1
        System.out.println(intTemp);


        /** java.lang.Double **/
        // 构造方法
        Double doubleValue1 = new Double(2.6);   // 2.6
        Double doubleValue2 = new Double(2.6f);  // 2.6
        Double doubleValue3 = new Double("2.6");    // 2.6

        // 继承Number的方法
        System.out.println( doubleValue1.intValue() );      // 2
        System.out.println( doubleValue1.shortValue() );    // 2
        System.out.println( doubleValue1.longValue() );     // 2
        System.out.println( doubleValue1.doubleValue() );   // 2.6
        System.out.println( doubleValue1.floatValue() );    // 2.6
        System.out.println( doubleValue1.byteValue() );     // 2


        // 扩展的静态变量
        System.out.println(Double.MAX_VALUE);             // 1.7976931348623157E308
        System.out.println(doubleValue1.MIN_VALUE);       // 4.9E-324

        // 扩展的方法
        // 其他值转换成Double
        Double doubleTemp = Double.valueOf(1.23);
        System.out.println(doubleTemp);                   // 1.23

        doubleTemp =  Double.valueOf("1.23");
        System.out.println(doubleTemp);                   // 1.23

        doubleTemp = Double.valueOf("1.23f");
        System.out.println(doubleTemp);                   // 1.23

        doubleTemp = Double.valueOf("1");
        System.out.println(doubleTemp);                   // 1.0


        /** double没有进制的转换 **/

        // 其他值转换成int
        double doubelTemp =  Double.parseDouble("1.23");           // 1.23
        System.out.println(doubelTemp);


        /** 把整数型字符串按照指定基数转换成十进制的int **/
        Integer NUM1 = Integer.valueOf("11", 2);   // 3
        System.out.println(NUM1);
        int num1 = Integer.parseInt("11", 2);      // 3
        System.out.println(num1);

        short NUM2 = Short.valueOf("11", 2);       // 3
        System.out.println(NUM2);
        short num2 = Short.parseShort("11", 2);    // 3
        System.out.println(num2);

        long NUM3 = Long.valueOf("11", 2);         // 3
        System.out.println(NUM3);
        long num3 = Long.parseLong("11", 2);       // 3
        System.out.println(num3);

        byte NUM4 = Byte.valueOf("11", 2);         // 3
        System.out.println(NUM4);
        byte num4 = Byte.parseByte("11", 2);       // 3
        System.out.println(num4);

        /**
         * instanceof 前面是个实例，后面一定是个类名
         * instanceof == true表示前面的实例可以转换（隐式转换或强制转换）成后面的类型
         */

        System.out.println(NUM1 instanceof Comparable);               // true

        System.out.println(new Integer[5] instanceof Comparable[]);   // true
        System.out.println(new Integer[5] instanceof Object[]);       // true
        System.out.println(new Integer[5] instanceof Number[]);       // true
        System.out.println(new Number[5] instanceof Object[]);        // true

        /**
         * 数组本身是一个对象，是Object的一个实例，所以int[] Integer[] Number[] Double[] Object[]都是Object的不同实例，是不同子类型，他们之间都不能强制转换
         */

//        System.out.println(num1 instanceof Object);                   // 编译报错，基本数据类型不是对象，不是Object的子类
//        Object[] a = new int[2] {1, 2};                               // 编译报错
        System.out.println(new int[5] instanceof Object);             // true
        System.out.println(new Integer[5] instanceof Object);         // true
        System.out.println(new Number[5] instanceof Object);          // true
        System.out.println(new Object[5] instanceof Object);          // true
        System.out.println(new Comparable[5] instanceof Object);      // true
        System.out.println(new Comparable[5] instanceof Object);      // true


        /**
         * int可以赋值给double，但是int[] 和 double[]类型不同，不能赋值
         */
        int i = 1;
        double d = i;
        System.out.println(d);              // 1.0
//        double[] b = new int[2] {1, 2};     // 编译报错


        /**
         * 接口与抽象类不能创建实例对象，但是可以在数组实例对象里存在
         */
        Object[] a = new Integer[]{1,2};
        System.out.println(a instanceof Integer[]);      // true

        Comparable[] b = new Integer[]{1,2};
        System.out.println(b instanceof Integer[]);      // true

        Comparable<Integer>[] c = new Integer[]{1, 2};
        System.out.println(c instanceof Integer[]);      // true

        Comparable[] e = new Comparable[]{1,2};
        System.out.println(e instanceof Integer[]);      // false
        System.out.println(e instanceof Comparable[]);   // true
        System.out.println(e[0]);                        // 1

        Number[] g = new Number[]{1,2};
        System.out.println(g instanceof Integer[]);      // false
        System.out.println(g instanceof Number[]);       // true
        System.out.println(g[1]);                        // 2





//        Comparable<Integer>[] f = new Comparable<Integer>[] {1,2};   // 编译报错，因为接口不能生成实例

        /** 由上可知，float和double不适合用于浮点数的计算和比较，一般用BigDecimal
         * float和double类型是为了在广域数值范围上提供较为精确的快速近似计算而精心设计的。然而，它们没有提供完全精确的结果，所以不应该被用于要求精确结果的场合
         * 在大多数的商业计算中，float和double不适合用于计算和比较，一般用BigDecimal来进行精确计算
         *
         * java.math.BigDecimal
         *
         * 扩展java.lang.Number类，实现Comparable接口
         */

        /** BigDecimal构造方法 **/
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

        /** BigDecimal加减乘除运算 **/
        BigDecimal bigDecimal1 = new BigDecimal("4.5");
        BigDecimal bigDecimal2 = new BigDecimal("1.5");
        System.out.println("a + b =" + bigDecimal1.add(bigDecimal2));      // 6.0
        System.out.println("a - b =" + bigDecimal1.subtract(bigDecimal2)); // 3.0
        System.out.println("a * b =" + bigDecimal1.multiply(bigDecimal2)); // 6.75
        System.out.println("a / b =" + bigDecimal1.divide(bigDecimal2));   // 3

        // 加减乘除其实最终都返回的是一个新的BigDecimal对象，因为BigInteger与BigDecimal都是不可变的（immutable）的，在进行每一步运算时，都会产生一个新的对象
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


        /** BigDecimal截断、四舍五入，setScale，保留小数位是不能缺省的，RoundingMode缺省是UNNECESSARY，不改变原值 **/
        BigDecimal bigDecimal3 = new BigDecimal("3.265");
        // 截断成4位小数
        System.out.println(bigDecimal3.setScale(4)); // 3.2650  RoundingMode缺省默认是UNNECESSARY
        // 截断成2位小数，RoundingMode缺省UNNECESSARY，会报错
        // System.out.println(bigDecimal3.setScale(2)); // 报错，保留2位不能舍入，会报错
        // 截断成2位小数，并且四舍五入
        System.out.println(bigDecimal3.setScale(2, RoundingMode.HALF_UP)); // 3.27 指定了RoundingMode，保留2位四舍五入

        System.out.println(bigDecimal3);                // 3.265


        /** 扩展java.lang.Number类，实现Comparable接口 **/
        System.out.println(bigDecimal3.doubleValue());  // 3.265
        System.out.println(bigDecimal3.intValue());     // 3
        System.out.println(bigDecimal3.compareTo(new BigDecimal("5")));  // -1  3.265 < 5

        /** 继承实现equals , toString方法 **/
        System.out.println(bigDecimal3 == new BigDecimal("3.265"));      // false
        System.out.println(bigDecimal3.equals(new BigDecimal("3.265"))); // true
        System.out.println(bigDecimal3);                                     // 3.265
        System.out.println(bigDecimal3.toString());                          // 3.265


        /**
         * java.math.BigInteger 可以表示任意大小的整数
         *
         * 扩展java.lang.Number类，实现Comparable接口
         */
        /** BigInteger构造方法 **/
//        java.math.BigInteger bigInteger = new BigInteger("2.8");   // 字符串数值类型不匹配，运行报错
        java.math.BigInteger bigInteger1 = new BigInteger("2");
        java.math.BigInteger bigInteger2 = new BigInteger("6");
        java.math.BigInteger bigInteger3 = BigInteger.valueOf(1l);

        System.out.println(bigInteger1);        // 2
        System.out.println(bigInteger2);        // 6
        System.out.println(bigInteger3);        // 1

        /**  BigInteger 常量 **/
        System.out.println(BigInteger.ONE);        // 1
        System.out.println(BigInteger.ZERO);       // 0
        System.out.println(BigInteger.TEN);        // 10
        System.out.println(BigInteger.ONE == new BigInteger("1"));        // false
        System.out.println(BigInteger.ONE.equals(new BigInteger("1")));   // true

        /**  BigInteger 加减乘除运算 **/
        System.out.println("a + b =" + bigInteger1.add(bigInteger2));      // 8   2+6
        System.out.println("a - b =" + bigInteger1.subtract(bigInteger2)); // -4  2-6
        System.out.println("a * b =" + bigInteger1.multiply(bigInteger2)); // 12  2*6
        System.out.println("a / b =" + bigInteger1.divide(bigInteger2));   // 0   2/6

        // 加减乘除其实最终都返回的是一个新的BigInteger对象，因为BigInteger与BigDecimal都是不可变的（immutable）的，在进行每一步运算时，都会产生一个新的对象
        System.out.println(bigInteger1.add(bigInteger2)); // 8
        System.out.println(bigInteger1);                  // 2 bigInteger1、bigInteger2都是没有变的
        System.out.println(bigInteger2);                  // 6 bigInteger1、bigInteger2都是没有变的


        /** 扩展java.lang.Number类，实现Comparable接口 **/
        System.out.println(bigInteger1.doubleValue());  // 2.0
        System.out.println(bigInteger1.intValue());     // 2
        System.out.println(bigInteger1.compareTo(new BigInteger("-2")));  // 1   2 > -2

        /** 继承实现equals , toString方法 **/
        System.out.println(bigInteger1 == new BigInteger("2"));          // false
        System.out.println(bigInteger1.equals(new BigInteger("2")));     // true
        System.out.println(bigInteger1);                                     // 2
        System.out.println(bigInteger1.toString());                          // 2

    }
}
