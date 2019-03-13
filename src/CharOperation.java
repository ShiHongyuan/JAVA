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
    }
}

