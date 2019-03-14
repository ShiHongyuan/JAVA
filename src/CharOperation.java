import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class CharOperation {
    public static void main(String[] args) {
        /**
         * char的字节数
         * 1、内码：是实现char或String类型时在内存内部使用的编码，在系统没有指定编码集的时候，java默认是将utf-16（统一编码）作为内存的字符存储格式的，
         *    utf-16是两字节定长编码，几乎可以翻译世界上所有语言，所以存储char和string时，汉字和英文字母都是2个字节。
         *
         * 2、外码：在Java中读取、写入字符文件时可以指定字符编码集，作为系统默认编码集，指定的这些编码集叫作外码，不同编码会导致字符所占字节数是不同，常见的有：
         * (1) utf-8: 英文字符所占字节数:1  中文字符所占字节数:3
         * (2) GBK: 英文字符所占字节数:1  中文字符所占字节数:2
         * (3) ASCII码: 只能编码英文字符，所占字节数:1
         */

        // 获取现在系统设置的默认的编码集
        String defaultCharsetName = Charset.defaultCharset().displayName();
        System.out.println("defaultCharsetName:" + defaultCharsetName);    // GBK

        // 1、Unicode 统一编码
        char uniCode = '\u6b22';   // 欢
        char uniCode2 = '\u8FCE';  // 迎
        char uniCode3 = '你';      // 你
        Character uniCode4 = new Character('\u6B22'); // 欢
        char uniCode5 = '\u0041';  // A 本质是英文字母，GBK的英文字符占字节数是1
        char uniCode6 = '\u00FF';
        char uniCode7 = '\u0101';

        System.out.println(String.valueOf(uniCode) + String.valueOf(uniCode2)); // 欢迎
        System.out.println(String.valueOf('\u0000') + String.valueOf('\u00ff') + String.valueOf('\u0100') + String.valueOf('\u0101')); // ??ā
//        System.out.println(Character.SIZE);
        System.out.println(String.valueOf(uniCode).getBytes().length);   // 2 String的内部存储就是按字符的存储积累起来的，所以一个字符在String里面占用的字节数跟char相同
        System.out.println(String.valueOf(uniCode3).getBytes().length);  // 2
        System.out.println(String.valueOf(uniCode4).getBytes().length);  // 2
        System.out.println(String.valueOf(uniCode5).getBytes().length);  // 1
        System.out.println(String.valueOf('\u00FF').getBytes().length);  // 1 ?
        System.out.println(String.valueOf('\u0100').getBytes().length);  // 1 ?
        System.out.println(String.valueOf('\u0101').getBytes().length);  // 2 ā


        char uniCode8 = 'A';
        System.out.println(String.valueOf(uniCode8).getBytes().length);  // 1 英文字母，GBK的英文字符占字节数是1
        try {
            System.out.println(String.valueOf(uniCode).getBytes("UTF-8").length);  // 3 汉字 utf-8: 英文字符所占字节数:1  中文字符所占字节数:3
            System.out.println(String.valueOf(uniCode5).getBytes("UTF-8").length); // 1 英文

            System.out.println(String.valueOf(uniCode2).getBytes("utf-16").length); // 4 汉字
            System.out.println(String.valueOf(uniCode8).getBytes("utf-16").length);// 4 英文

            System.out.println(String.valueOf(uniCode).getBytes("ASCII").length);  // 1 汉字 ASCII 取了低位的一个字节
            System.out.println(String.valueOf(uniCode6).getBytes("ASCII").length); // 1 英文
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * char的运算操作，可以像整型一样进行运算和操作
         */
        System.out.println("A" + '1');  // A1 其中之一及以上操作数是string，结果是直接连接的string
        System.out.println("A" + 1);    // A1 其中之一及以上操作数是string，结果是直接连接的string
        System.out.println('A' + 'B');  // 131 A==65 B==66 两个都是char,按照数值运算，A+B=131
        System.out.println('A' + 1);    // 66  A==65 其中一个是数字，按照数值运算，A+1=66
        System.out.println('A' + 1L);   // 66  A==65 其中一个是数字，按照数值运算，A+1L=66L
        System.out.println('A' + 1.5);  // A==65 其中一个是数字，按照数值运算，A+1.5=66.5
        System.out.println('A' + 1.5f); // A==65 其中一个是数字，按照数值运算，A+1.5f=66.5f
        char ch = 'A';
        System.out.println(ch++);   // A
        System.out.println(++ch);   // C
        System.out.println(ch + 1); // 68 所以 char++ != char + 1，char++和++char会保持原来的char类型，改变的只是字符的增减变化，但是+1是转换为int类型后相加的

        /**
         * char的类型装换
         */
        // 装换成比它小的byte
        // 强制转换
        char ch1 = '\uff41';
        byte b1 = (byte)ch1;
        // byte b = '\uff41';   // 会报错
        System.out.println(b1);  // 65 byte类型，说明强转时只取了ch1的低位的一个字节
        // 隐式转换
        byte b2 = 'A';
        System.out.println(b2);  // 65 byte类型，'a'的char字节数在一个字节内，所以可以隐式转换

        // 转换成比它大的int
        int n1 = '\uff41';
        System.out.println(n1); // 65324 int类型，隐式转换

        char ch2 = (char) 0x11AB0041;
        char ch3 = (char) 0x11AB1141;
        System.out.println(ch2); // A  =65，一个比/uffff大的int，强转时只取了int的低位的两个字节
        System.out.println(ch3); // ?  一个比/uffff大的int，强转时只取了int的低位的两个字节


        // 转换成比它大的浮点
        float f1 = '\uffff';
        System.out.println(f1); // 65535.0 float类型，隐式转换

        char ch4 = (char) 165535.777;
        char ch5 = (char) 65.777f;
        System.out.println(ch4); // 蚟  =165535，一个比/uffff大的int，强转时只取了int的低位的两个字节
        System.out.println((int)ch4); // 34463
        System.out.println((int)'\uc000');
        System.out.println(ch5); // A  一个比/uffff大的int，强转时只取了int的低位的两个字节








    }
}

