import java.util.Scanner;

public class IOoperation {
    public static void main(String[] args) {
//        // sanner 控制台输入 1 2 3 4 567890
//        Scanner input = new Scanner(System.in);
//        input.next(); // 以空白字符结束/t /f /r /n ' ' 但是不会以''结束
//        System.out.println(input.next()); // 2
//        System.out.println(input.next()); // 3
//        System.out.println(input.next()); // 4
//
//        System.out.println(input.nextLine()); // 读入一行才会结束  ' '567890

        /**
         * 格式化控制台输出  printf
         * char %c
         * int long short %d
         * float double %f
         * boolean %b
         * string %s
         * 科学计数法 %e 必须是浮点数
         */
        // 正常类型匹配输出
        System.out.printf("int is %d, long is %d, short is %d", (int)1, (long)2l, (short)3); // int is 1, long is 2, short is 3"
        System.out.println();
        System.out.printf("float is %f, double is %f, float is %e, double is %e,", 1.1f, 1.1, 1.1f, 1.1); // float is 1.100000, double is 1.100000, float is 1.100000e+00, double is 1.100000e+00"
        System.out.println();
        System.out.printf("char is %c, boolean is %b, string is %s", 'A', true, "123"); // char is A, boolean is true, string is 123"

        /** 隐式转换输出 会报错 */
        // System.out.printf("float is %d, double is %d", 1.1f, 1.1); // 报错
        // System.out.printf("char is %d, boolean is %d, float is %f, double is %f", 'A', false, 1, 2); // 报错


        /** 强制转换输出 */
        System.out.printf("float is %d, double is %d", (short)1.1f, (long)1.1);
        System.out.println();

        /** boolean不能强转成int,int不能强转成boolean，boolean只能由%b输出 */
//        System.out.printf("boolean is %d", (int)true);   // 编译报错
//        System.out.println("boolean is %b", (boolean)1); // 编译报错

        /** 'A'不能由%d输出，但是65可以由%c输出，这个是隐式转换的特例 */
        System.out.printf("char is %d, char is %c, float is %f, double is %f", (int)'A', 65, (float)1, (double)2);
        int n = 288;
        System.out.printf("char is %c", 65); // A
        System.out.printf("char is %c", n);  // ？
//        System.out.printf("char is %c", 22222288);  // 超出两个字节，报错
//        System.out.printf("char is %d", 'A');  // 运行报错 d != java.lang.Character
        System.out.println();

        /** int也不能隐式转换为浮点，由%f输出，这个也是隐式转换的特例 */
//        System.out.printf("float is %f", 1); // 报错 f != java.lang.Integer


        /** 指定长度输出
         * 1、默认右对齐，指定长度比实际长度大，前面填充空格
         * 2、在%后加-号，左对齐，指定长度比实际长度大，后面填充空格
         * */
        // 默认右对齐，前面填充空格
        System.out.printf("char is %5c", 'A');      // '    A'  长度是5，前面填充4个空格
        System.out.println();
        System.out.printf("boolean is %5b", true);  // ' true'  长度至少是5，true占了4个长度，前面填充1个空格
        System.out.println();
        System.out.printf("int is %5d", 22);        // '   22'  长度至少是5位数，前面填充3个空格
        System.out.println();
        System.out.printf("string is %5s", "123");  // '  123'  长度至少是5位数，前面填充2个空格
        System.out.println();
        System.out.printf("float is %5.1f", 1.23f); // '  1.2'  总长度至少是5位数（至少对整数而言，小数超了就截断了），小数占1位数，小数点占1位数，整数占3位数，前面填充2个空格
        System.out.println();

        System.out.printf("boolean is %3b", true);       // 'true'    长度至少是3，true占了4个长度，自动加宽，前面不填充空格
        System.out.println();
        System.out.printf("int is %3d", 22222);          // '22222'   长度至少是3位数，自动加宽，前面不填充空格
        System.out.println();
        System.out.printf("string is %3s", "123456");    // '123456'  长度至少是3位数，自动加宽，前面不填充空格
        System.out.println();
        System.out.printf("double is %5.1f", 11111.23456);  // '11111.2'  总长度至少是5位数（至少对整数而言，小数超了就截断了），整数不够自动增宽，前面不填充空格
        System.out.println();


        // 加-号左对齐，后面填充空格
        System.out.printf("char is %-5c", 'A');      // 'A    '  长度是5，后面填充4个空格
        System.out.println();
        System.out.printf("boolean is %-5b", true);  // 'true '  长度至少是5，true占了4个长度，后面填充1个空格
        System.out.println();
        System.out.printf("int is %-5d", 22);        // '22   '  长度至少是5位数，后面填充3个空格
        System.out.println();
        System.out.printf("string is %-5s", "123");  // '123  '  长度至少是5位数，后面填充2个空格
        System.out.println();
        System.out.printf("float is %-5.1f", 1.23f); // '1.2  '  总长度至少是5位数（至少对整数而言，小数超了就截断了），小数占1位数，小数点占1位数，整数占3位数，后面填充2个空格
        System.out.println();
    }
}
