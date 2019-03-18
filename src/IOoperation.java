import java.util.Scanner;

public class IOoperation {
    public static void main(String[] args) {
        // sanner 控制台输入 1 2 3 4 567890
        Scanner input = new Scanner(System.in);
        input.next(); // 以空白字符结束/t /f /r /n ' ' 但是不会以''结束
        System.out.println(input.next()); // 2
        System.out.println(input.next()); // 3
        System.out.println(input.next()); // 4

        System.out.println(input.nextLine()); // 读入一行才会结束  ' '567890

        /**
         * 格式化控制台输出  printf
         * char %c
         * int long short %d
         * float double %f
         * boolean %b
         * string %s
         * 科学计数法 %e 必须是浮点数
         */
        // 正常输出
        System.out.println();
        System.out.printf("int is %d, long is %d, short is %d", (int)1, (long)2l, (short)3); // int is 1, long is 2, short is 3"
        System.out.println();
        System.out.printf("float is %f, double is %f, float is %e, double is %e,", 1.1f, 1.1, 1.1f, 1.1); // float is 1.100000, double is 1.100000, float is 1.100000e+00, double is 1.100000e+00"
        System.out.println();
        System.out.printf("char is %c, boolean is %b, string is %s", 'A', true, "123"); // char is A, boolean is true, string is 123"
        System.out.println();

        // 隐式转换输出   基本数据类型可以隐式转换成string输出
        // System.out.printf("float is %d, double is %d", 1.1f, 1.1); // 报错
        // System.out.printf("char is %d, boolean is %d, float is %f, double is %f", 'A', false, 1, 2); // 报错
        System.out.println();
        System.out.printf("%s", 1.2);       // 1.2
        System.out.println();
        System.out.printf("%s", 1.2f);      // 1.2
        System.out.println();
        System.out.printf("%s", 1);         // 1
        System.out.println();
        System.out.printf("%s", '\u0041');  // A
        System.out.println();
        System.out.printf("%s", true);      // true
        System.out.println();

        // 隐式转换输出 string可以隐式转换成boolean输出，其他转换都会报错
        System.out.println();
//        System.out.printf("%d", "1.2");       // 1.2  d != java.lang.String
//        System.out.printf("%f", "1.2f");      // 1.2  f != java.lang.String
//        System.out.printf("%d", "1");         // 1    d != java.lang.String
//        System.out.printf("%c", "\u0041");    // A    c != java.lang.String
        System.out.printf("%b", "true");      // true

        // 隐式转换输出   基本数据类型可以隐式转换成boolean输出，都输出true
        System.out.printf("%b", 1);           // true
        System.out.printf("%b", 0);           // true
        System.out.printf("%b", 'c');         // true
        System.out.printf("%b", 2.2);         // true
        System.out.println();                 // true


        // 强制转换输出  boolean不能强转成int
        System.out.println();
        System.out.printf("float is %d, double is %d", (short)1.1f, (long)1.1);
        System.out.println();
        // 'A'不能由%d输出，但是65可以由%c输出
        System.out.printf("char is %d, char is %c, float is %f, double is %f", (int)'A', 65, (float)1, (double)2);
        System.out.println();
    }
}
