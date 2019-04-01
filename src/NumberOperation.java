public class NumberOperation {
    public static void main (String[] args) {

        /**自动装箱和自动拆箱**/
        int x = new Integer(3); System.out.println(x);     // 3
        // 整数比较和运算的时候是int，Integer会自动拆箱
        System.out.println(new Integer(3) + new Integer(3));  // int 3 + int 3 = 6
        System.out.println(new Integer(2) < new Integer(3));  // int 2 < int 3 = true

        /**包装类 java.lang.*
         * java.lang.Character Boolean implements java.lang.Comparable extends java.lang.Object
         * java.lang.Integer Short Long Double Float Byte implements java.lang.Comparable extends java.lang.Number
         * 都重写了Object的 toString() 和 equals() 方法
         * 都实现了Comparable接口的compareTo()方法
         */


        /** 接口 java.lang.Comparable **/
        // public int compareTo(Object)

        /** 抽象类 java.lang.Number **/
        // byte byteValue() 把继承 Number 的包装数字类转换成 基本数据类型byte
        // int intValue() 把继承 Number 的包装数字类转换成 基本数据类型int
        // short shortValue() 把继承 Number 的包装数字类转换成 基本数据类型short
        // long longValue() 把继承 Number 的包装数字类转换成 基本数据类型long
        // float floatValue() 把继承 Number 的包装数字类转换成 基本数据类型float
        // double doubleValue() 把继承 Number 的包装数字类转换成 基本数据类型double

        /** 包装类没有无参构造方法，对象的内部值不可变，即包装类的实例不可变 **/
//        Integer integer = new Integer();   // 编译报错，没有无参构造方法


        /** 包装类实参传递的也是引用值，但是在函数内部如果new Integer()会改变引用值，或者=6也会改变引用值，与基本数据类型相同，所以都会变成一个复制传参的结果 **/
        Integer integer1 = 5;
        Integer integer2 = 5;
        System.out.println(integer1 == integer2);   // true  指向同一个常量，所以变量的引用值相同

        Integer integer11 = new Integer(5);
        Integer integer22 = new Integer(5);
        System.out.println(integer11 == integer22); // false


        /** 包装类都重写了Object的 toString() 和 equals() 方法 **/

        /** 包装类都实现了Comparable接口的compareTo()方法 **/




        /** java.lang.Integer **/
        // 构造方法
        Integer integerValue1 = new Integer(2);  // 2
        Integer integerValue2 = new Integer("2");   // 2

        // 继承Number的方法



        // 扩展的两个静态变量
        System.out.println(Integer.MAX_VALUE);
        System.out.println(integerValue1.MIN_VALUE);

        // 扩展的四个静态方法
        // 转换成Integer的方法
        Integer integerTemp = Integer.valueOf(1);
        System.out.println(integerTemp);

        integerTemp =  Integer.valueOf("1");
        System.out.println(integerTemp);

        integerTemp = Integer.valueOf("1.23");
        System.out.println(integerTemp);

        integerTemp = Integer.valueOf("3", 2);
        System.out.println(integerTemp);

        // 转换成int的方法
        int intTemp = Integer.valueOf(1);
        System.out.println(integerTemp);

        intTemp =  Integer.valueOf("1");
        System.out.println(integerTemp);

        intTemp = Integer.valueOf("1.23");
        System.out.println(integerTemp);

        intTemp = Integer.valueOf("3", 2);
        System.out.println(integerTemp);














    }
}
