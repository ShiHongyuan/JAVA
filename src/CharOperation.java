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
//        char uniCode3 = '你';       // 你
        Character uniCode4 = new Character('\u6B22'); // 欢
        char uniCode5 = '\u0041';  // A 本质是英文字母，GBK的英文字符占字节数是1
        char uniCode6 = '\u00FF';
        char uniCode7 = '\u0101';

        System.out.println(String.valueOf(uniCode) + String.valueOf(uniCode2)); // 欢迎
        System.out.println(String.valueOf('\u0000') + String.valueOf('\u00ff') + String.valueOf('\u0100') + String.valueOf('\u0101') + String.valueOf('\uffff')); //  ??ā
        System.out.println(String.valueOf('\uffff')); // ''
        System.out.println(String.valueOf(uniCode).getBytes().length);   // 2 String的内部存储就是按字符的存储积累起来的，所以一个字符在String里面占用的字节数跟char相同
//        System.out.println(String.valueOf(uniCode3).getBytes().length);  // 2
        System.out.println(String.valueOf(uniCode4).getBytes().length);  // 2
        System.out.println(String.valueOf(uniCode5).getBytes().length);  // 1
        System.out.println(String.valueOf('\u00FF').getBytes().length);  // 1 ?
        System.out.println(String.valueOf('\u0100').getBytes().length);  // 2 ?
        System.out.println(String.valueOf('\u0101').getBytes().length);  // 2 ā
        System.out.println(String.valueOf('\uffff').getBytes().length);  // 2 ā


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
        byte b1 = (byte)'\uff41'; // short类型是2个字节，char大于1个字节，要强制转换
        byte b11 = '\u0041';
        // byte b = '\uff41';     // char大于1个字节，隐式转换会报错
        System.out.println(b1);  // 65 byte类型，说明强转时只取了ch1的低位的一个字节
        // 隐式转换
        byte b2 = 'A';           // short类型是2个字节，char小于1个字节，可以隐式转换
        System.out.println(b2);  // 65 byte类型，'a'的char字节数在一个字节内，所以可以隐式转换

        // 转换成比它大的整型
        int n1 = '\uff41';
        System.out.println(n1); // 65345 int类型，隐式转换

        char ch2 = (char) 0x11AB0041;
        char ch3 = (char) 0x11AB1141;

        System.out.println(ch2); // A  =65，一个比/uffff大的int，强转时只取了int的低位的两个字节
        System.out.println(ch3); // ?  一个比/uffff大的int，强转时只取了int的低位的两个字节

        long n2 = '\uff41';
        System.out.println(n2); // 65345 long类型，隐式转换

        char chn2 = (char) 0x11AB0041L;
        char chn3 = (char) 0x11AB1141L;
        System.out.println(chn2); // A  =65，一个比/uffff大的int，强转时只取了int的低位的两个字节
        System.out.println(chn3); // ?  一个比/uffff大的int，强转时只取了int的低位的两个字节

        short n3 = '\u0041'; // 65 short类型是2个字节，char小于2个字节，隐式转换
        short n4 = 'A';      // 65 short类型是2个字节，char小于2个字节，隐式转换
        short n5 = (short)'\u0141'; //65324 short类型是2个字节，char大于2个字节，要强制转换
        System.out.println(n3); // 65
        System.out.println(n4); // 65
        System.out.println(n5); // 321 ox0141 转换成int是321

        char chs2 = (char) 0x11AB0041;
        char chs3 = (char) 0x11AB1141;
        System.out.println(chs2); // A  =65，一个比/uffff大的int，强转时只取了int的低位的两个字节
        System.out.println(chs3); // ?  一个比/uffff大的int，强转时只取了int的低位的两个字节


        // 转换成比它大的浮点
        float f1 = '\uffff';
        System.out.println(f1); // 65535.0 float类型，隐式转换

        char ch4 = (char) 165535.777f;
        char ch5 = (char) 65.777f;
        System.out.println(ch4); // 蚟  =165535，一个比/uffff大的float，强转时只取了float的低位的两个字节
        System.out.println(ch5); // A  一个比/uffff小的，强转时只取了float的低位的两个字节
        System.out.println((int)ch4); // 34463
        System.out.println((int)ch5); // 65


        double d1 = '\uffff';
        System.out.println(d1); // 65535.0 double类型，隐式转换

        char ch6 = (char) 165535.777;
        char ch7 = (char) 65.777;
        System.out.println(ch6); // 蚟  =165535，一个比/uffff大的double，强转时只取了double的低位的两个字节
        System.out.println(ch7); // A  一个比/uffff小的，强转时只取了double的低位的两个字节
        System.out.println((int)ch6); // 34463
        System.out.println((int)ch7); // 65

        /** 在隐式转换里，如果已经声明了类型，大范围类型转小范围类型必须强制转，
         *  但是用直接量，根据所占字节数，如果在小范围内，就不用强制转
         */
        char char1 = 0x00000041;
        System.out.println(char1); // A

        // long再小也不能隐式转换，报错
        // char char2 = 1L;

        // 浮点数因为带小数点，整数再小也不能隐式转换，报错
        // char char3 = 111.56f;
        // char char3 = 111.56;

        /**
         * char函数
         */
        // 生成随机字符 随机整数+a 转char

        /**
         * java.lang.Character的 一个构造函数
         */
        /** 构造函数，Character的创建和初始化 */
        Character character = new Character('a');

        /** Character获取拆箱后的char值 */
        System.out.println( character.charValue() );   // a

        /** 静态方法 */
        // 字符是否数字字符
        System.out.println( java.lang.Character.isDigit('a') );  // false
        System.out.println( Character.isDigit('1') );            // true

        // 字符是否字母字符
        System.out.println( Character.isLetter('a') );           // true
        System.out.println( Character.isLetter('1') );           // false

        // 字符是否数字或者字母字符
        System.out.println( Character.isLetterOrDigit('a') );    // true
        System.out.println( Character.isLetterOrDigit('1') );    // true

        // 字符是否大写
        System.out.println( Character.isUpperCase('a') );        // false
        System.out.println( Character.isUpperCase('A') );        // true

        // 字符是否小写
        System.out.println( Character.isLowerCase('a') );        // true
        System.out.println( Character.isLowerCase('A') );        // false

        // 字符转换成大写
        System.out.println( Character.toUpperCase('a') );        // A

        // 字符转换成小写
        System.out.println( Character.toLowerCase('A') );        // a

        // 编码中数字代替字符 '1'=49  'A'=65  'a'=97
        System.out.println( Character.isDigit(97) );        // false
        System.out.println( Character.isDigit(49) );        // true

        System.out.println( Character.isLetter(97) );       // true
        System.out.println( Character.isLetter(49) );       // false

        System.out.println( Character.isLetterOrDigit(97) );    // true
        System.out.println( Character.isLetterOrDigit(49) );    // true

        System.out.println( Character.isUpperCase(97) );        // false
        System.out.println( Character.isUpperCase(65) );        // true

        System.out.println( Character.isLowerCase(97) );        // true
        System.out.println( Character.isLowerCase(65) );        // false

        System.out.println( Character.toUpperCase(97) );        // 65 参数是编码，输出也是数字

        System.out.println( Character.toLowerCase(65) );        // 97 参数是编码，输出也是数字

    }
}

