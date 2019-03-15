public class StringOperation {
    public static void main(String[] args) {
        /**
         * string的字符串函数操作
         */
        // 返回"", 运行也不会报错
        System.out.println("123".substring(0,0));

        // indexOf()
        System.out.println("".indexOf(""));   // 0
        System.out.println("ll".indexOf("")); // 0
        System.out.println("".indexOf("ll")); // -1
        System.out.println("hello".indexOf("ll"));   // 2

        // startsWith 加偏移量 和 不加偏移量
        System.out.println("".startsWith(""));                    // true
        System.out.println("ll".startsWith(""));                  // true
        System.out.println("ll".startsWith("", 0));   // true
        System.out.println("".startsWith("ll"));                  // false
        System.out.println("".startsWith("ll", -1));  // false
        System.out.println("hello".startsWith("ll"));             // false
        System.out.println("hello".startsWith("ll", 2));  // true

        System.out.println("1 ".charAt(1));   //' ' 空白字符

        /**
         * string的连接符操作
         */
        // 其中之一是字符串，就是连接符，另一个先转换成字符串后连接，两个都不是，就是加法运算符
        System.out.println("string=" + 1 + 2);   //string=12  没有括号，从左到右计算，所以是连接符
        System.out.println("string=" + (1 + 2)); //string=3   有括号，先算括号里的，都是整数，相加，再和左边连接

        /**
         * string的转数字函数操作
         */
        int n1 = Integer.parseInt("123");
        double n2 = Double.parseDouble("123");
        double n3 = Integer.valueOf("123");
        double n4 = Double.valueOf("123");

        System.out.println(n1); // 123
        System.out.println(n2); // 123.0
        System.out.println(n3); // 123.0
        System.out.println(n4); // 123.0



    }
}
