public class StringOperation {
    public static void main(String[] args) {
        /**
         * java.lang.String的创建和初始化
         * String有11个构造函数，40几个操作方法
         * 计算机内部，string是由私有的字符数组变量表示的
         */
        // 直接量
        String s1 = "123";
        System.out.println(s1);      // 123

        // 字符数组 传入构造函数 来创建初始化String
        String s2 = new String(new char[] {65, 66, 67});
        System.out.println(s2);      // ABC
        String s3 = new String(new char[] {'a', 'b', 'c'});
        System.out.println(s3);      // abc

        /**
         * string比较
         * 不能用 < >，会报错
         */
        String s4 = "abc";
        String s5 = "abe";
        // == 引用值相等
        System.out.println(s4 == s5);      // false

        // equals 内容相等
        System.out.println(s4.equals(s5)); // false

        // equalsIgnoreCase 同equals + 忽略大小写
        System.out.println("aBC".equalsIgnoreCase("ABc")); // true  内容相等

        // compareTo  内容相等，返回从左到右第一个不相同字符的距离长度，相等就返回0
        System.out.println(s4.compareTo(s5)); // -2  s1第一个不相同字符 - s2第一个不相同字符  c比e小2个长度
        System.out.println(s5.compareTo(s4)); // 2  s1第一个不相同字符 - s2第一个不相同字符   e比c大2个长度
        System.out.println(s4.compareTo("abc")); // 0  内容相等
        System.out.println("abe".compareTo(s5)); // 0  内容相等

//        System.out.println(s4 < s5); // 编译报错

        // compareToIgnoreCase 同compareTo + 忽略大小写
        System.out.println("aBC".compareToIgnoreCase("ABc")); // 0  内容相等

        // startsWith(prefix) 是否以prefix字符串开头
        System.out.println("aBC".startsWith("BC")); // false
        System.out.println("aBC".startsWith("aB"));  // true
        // startsWith 加偏移量 和 不加偏移量
        System.out.println("aBC".startsWith("BC", 1));   // true k可以指定从哪里开始匹配前缀
        System.out.println("".startsWith(""));                        // true
        System.out.println("ll".startsWith(""));                      // true
        System.out.println("ll".startsWith("", 0));      // true
        System.out.println("".startsWith("ll"));                      // false
        System.out.println("".startsWith("", -1));       // false
        System.out.println("hello".startsWith("ll"));                 // false
        System.out.println("hello".startsWith("ll", 2)); // true
        System.out.println("hello".startsWith("o", -1)); // false

        // endsWith(suffix) 是否以suffix字符串结束
        System.out.println("aBC".endsWith("B"));  // false
        System.out.println("aBC".endsWith("BC"));  // true

        // regionMatches([boolean要不要忽略大小写]，s1开始比较的位置，s2，s2开始比较的位置，要比较的长度) 部分字符串是否相同
        System.out.println("aBCdefg".regionMatches(1,"BCdefg",0,3));  // true
        System.out.println("aBCdefg".regionMatches(1,"BCdefg",0,6));  // true
        System.out.println("aBCdefg".regionMatches(1,"BCdefg",0,20)); // false
        System.out.println("aBCdefg".regionMatches(0,"12aBCdefg",2,7)); // true

        System.out.println("abc".regionMatches(false,0,"ABC",0,3)); // false
        System.out.println("abc".regionMatches(true,0,"ABC",0,3));  // true

        /**
         * string的连接符操作
         * 1、连接符 +
         * 2、concat
         */
        // 其中之一是字符串，就是连接符，另一个先转换成字符串后连接，两个都不是，就是加法运算符
        System.out.println("string=" + 1 + 2);   //string=12  没有括号，从左到右计算，所以是连接符
        System.out.println("string=" + (1 + 2)); //string=3   有括号，先算括号里的，都是整数，相加，再和左边连接

        String s6 = "123";
        String s7 = "abc";
        System.out.println(s6.concat(s7));         // 123abc
        System.out.println("123".concat("abc"));   // 123abc


        /**
         * string截取字符串
         */
        // 截取字符串substring(开始位置，结束位置)
        System.out.println("123".substring(0,0)); // 返回"", 运行也不会报错
//        System.out.println("123".substring(2,1)); // 运行报错
        System.out.println("123".substring(1,2)); // 2
        System.out.println("123".substring(1,3)); // 23

        // substring(开始位置)
        System.out.println("123".substring(1)); // 23
        System.out.println("123".substring(0)); // 123

        /**
         * string获取指定位置字符
         */
        // charAt(int)
        char c = "abc".charAt(1);
        System.out.println(c); // b
        System.out.println("1 ".charAt(1));   //' ' 空白字符

        /**
         * 字符串大小写转换 不改变原来的值，生成一个新的字符串
         */
        // 转小写
        System.out.println("Hello".toLowerCase());   // hello

        // 转大写
        System.out.println("Hello".toUpperCase());   // HELLO

        /**
         * 去头尾空格
         */
        // 转头尾空格
        System.out.println("  Hel lo  ".trim());     // Hel lo

        /**
         * 分割string
         */
        String[] strings = "a#b#c#d#e#f".split("#");
        for (String s: strings) {
            System.out.print(s + " ");   // a b c d e f
        }
        System.out.print("\n");

        // limit 最多只能被分割成的数组个数，不够的后面就不分割了
        String[] strings1 = "a#b#c#d#e#f".split("#", 3);
        for (String s: strings1) {
            System.out.print(s + " ");   // limit=3：a b c#d#e#f   limit=4：a b c d#e#f
        }
        System.out.print("\n");

        // 结合正则表达式 最后一个分隔符后面没有的话，就不会有""元素
        String[] strings2 = ";java,c?;:c++#".split("[,:;?#]");
        for (String s: strings2) {
            System.out.println(s);   // "" java c "" "" c++
        }

        /**
         * string替换
         * 1、replace        全部替换，不支持正则表达式
         * 2、replaceAll     全部替换，可正则可不正则
         * 3、replaceFirst   替换第一个匹配对象，可正则可不正则
         */
        // replace
        // 参数是char
        System.out.println("abc".replace('a','b'));     // bbc
        // 参数是string
        System.out.println("abc".replace("a","b"));    // bbc
        System.out.println("abc".replace("ab","ba"));  // bac
        System.out.println("abc".replace("aa","ba"));  // abc   没有替换成功

        // replaceAll
        System.out.println("abc".replaceAll("a", "c"));       // cbc
        System.out.println("abc".replaceAll("[a]", "abc"));   // abcbc

        // replaceFirst
        System.out.println("abcabc".replaceFirst("a", "b"));     // bbcabc
        System.out.println("abcabc".replaceFirst("(abc)", "a"));     // aabc

        /**
         * string获取指定字符的位置 indexOf 指定的起始位置超过string长度了，也不会报错，最多找不到
         */
        // indexOf(char)          整个string与char第一次匹配的位置
//        System.out.println("".indexOf(''));           // 编译报错，char不能是空
        System.out.println("".indexOf('a'));            // -1
        System.out.println("abc abc abc".indexOf('a')); // 0

        // indexOf(char, int)     int后（包括int）与char第一次匹配的位置
        System.out.println("".indexOf('a', 2));            // -1
        System.out.println("abc abc abc".indexOf('a', 0)); // 0
        System.out.println("abc abc abc".indexOf('a', 2)); // 4

        // indexOf(string)
        System.out.println("".indexOf(""));               // 0
        System.out.println("abc abc abc".indexOf(""));    // 0
        System.out.println("".indexOf("a"));              // -1
        System.out.println("abc abc abc".indexOf("a"));   // 0

        // indexOf(string, int)
        System.out.println("".indexOf("", 1));               // 0
        System.out.println("abc abc abc".indexOf("", 1));    // 1
        System.out.println("abc abc abc".indexOf("", 2));    // 2
        System.out.println("".indexOf("a", 1));              // -1
        System.out.println("abc abc abc".indexOf("a", 0));   // 0
        System.out.println("abc abc abc".indexOf("a", 1));   // 4
        System.out.println("abc abc abc".indexOf("a", 20));  // -1

        // lastIndexOf(char)      整个string与char最后一次匹配的位置
        System.out.println("".lastIndexOf('a'));            // -1
        System.out.println("abc abc abc".lastIndexOf('a')); // 8

        // lastIndexOf(char, int) int前（包括int）与与char最后一次匹配的位置
        System.out.println("".lastIndexOf('a', 2));            // -1
        System.out.println("abc abc abc".lastIndexOf('a', 8)); // 8
        System.out.println("abc abc abc".lastIndexOf('a', 0)); // 0
        System.out.println("abc abc abc".lastIndexOf('a', 6)); // 4

        // lastIndexOf(string)
        System.out.println("".lastIndexOf(""));               // 0
        System.out.println("abc abc abc".lastIndexOf(""));    // 11  最后一位的下一位
        System.out.println("".lastIndexOf("a"));              // -1
        System.out.println("abc abc abc".lastIndexOf("a"));   // 8

        // lastIndexOf(string, int)
        System.out.println("".lastIndexOf("", 1));               // 0
        System.out.println("abc abc abc".lastIndexOf("", 1));    // 1
        System.out.println("abc abc abc".lastIndexOf("", 2));    // 2
        System.out.println("".lastIndexOf("a", 1));              // -1
        System.out.println("abc abc abc".lastIndexOf("a", 0));   // 0
        System.out.println("abc abc abc".lastIndexOf("a", 8));   // 8
        System.out.println("abc abc abc".lastIndexOf("a", 20));  // 8
        System.out.println("abc abc abc".lastIndexOf("a", -1));  // -1

        /**
         * string 字符数组 互转
         */
        // string 转 char[]
        char[] chars1 = "abc".toCharArray();
        System.out.println(chars1);  // abc

        char[] chars2 = {'a', 'b', 'c', 'd', 'e', 'f'};
        // getChars 只能替换原char[]的一部分字符，如果替换的位置超过了原char[]的长度，运行会报错
        "123".getChars(0, 3, chars2, 1);
        System.out.println(chars2);  // a123ef

        // char[] 转 string
        String str1 = new String(chars2);
        System.out.println(str1);       // a123ef

        String str2 = String.valueOf(chars2);
        System.out.println(str2);       // a123ef


        /**
         * 基本数据类型 转 string
         */
        System.out.println(String.valueOf('a'));      // a char -> string
        System.out.println(String.valueOf(1));        // 1 int -> string
        System.out.println(String.valueOf(2.1));      // 2.1 double -> string
        System.out.println(String.valueOf(3.0f));     // 3.0 float -> string
        System.out.println(String.valueOf(6l));       // 6 long -> string
        System.out.println(String.valueOf((short)9)); // 9 short -> string
        System.out.println(String.valueOf((byte)10)); // 10 byte -> string
        System.out.println(String.valueOf(true));     // true boolean -> string

        /**
         * 对象 转 string  String.valueOf() == toString()
         */
        StringOperation x = new StringOperation();
        System.out.println(String.valueOf(x));     // StringOperation@60e53b93
        System.out.println(x.toString());          // StringOperation@60e53b93

        /**
         * string 转 数字类型
         */
        int n1 = Integer.parseInt("123");
        double n2 = Double.parseDouble("123");
        double n3 = Integer.valueOf("123");
        double n4 = Double.valueOf("123");

        System.out.println(n1); // 123
        System.out.println(n2); // 123.0
        System.out.println(n3); // 123.0
        System.out.println(n4); // 123.0

        /**
         * 基本数据类型 转为 格式化的string
         * 格式化部分 同printf，只是格式化后会自动转换为一个string返回
         */
        String str3 = String.format("%5.2f", 266.256);
        System.out.println(str3);   // 266.26

        String str4 = String.format("%b", 1);
        System.out.println(str4);   // true
        String str5 = String.format("%b", 0);
        System.out.println(str5);   // true

        String str6 = String.format("%c", 'c');
        System.out.println(str6);   // c
        String str7 = String.format("%2c", 67);
        System.out.println(str7);   // " C"

        String str8 = String.format("%3d", 10);
        System.out.println(str8);   // " 10"
        String str9 = String.format("%d", 11L);
        System.out.println(str9);   // 11


        /**
         * toString()
         * toString方法是系统将会输出该对象的“自我描述”信息，用以告诉外界对象具有的状态信息
         * Object 类提供的toString方法总是返回该对象实现类的类名 + @ +hashCode值
         * 可以通过重写对象的toString()方法，改变对象输出的自我描述
         */

        System.out.println(chars1.toString());           // [C@60e53b93
        System.out.println(new int[] {1,2}.toString());  // [I@5e2de80c


        /**
         * 字符串生成器：java.lang.StringBuilder、 java.lang.StringBuffer
         * StringBuilder修改缓冲区的方法是同步的，所以是线程安全的（同步方法，异步方法，加锁？）
         * StringBuilder和StringBuffer的构造方法相同，其他方法StringBuffer包含了StringBuilder所有的，甚至更多
         * 所有方法：1、改变原生成器的内容 2、返回新生成器的引用（很少用到）
         */
        /** StringBuilder---构造方法 **/
        // 创建一个默认容量（16），实际长度为0的空字符数组的字符串生成器
        StringBuilder builder1 = new StringBuilder();
        System.out.println(builder1.toString());                // ""
        // 创建一个指定容量（10），实际长度为0的空字符数组的字符串生成器
        StringBuilder builder2 = new StringBuilder(10);
        System.out.println(builder2.toString());                // ""
        // 创建一个默认容量（16），实际长度为"abc"（3）的字符数组的字符串生成器
        StringBuilder builder3 = new StringBuilder("abc");
        System.out.println(builder3.toString());                // abc

        /** StringBuilder---追加元素 **/
        // 追加一个string
        builder3.append("def");
        System.out.println(builder3.toString());                // abcdef
        // 追加一个char[]数组
        builder3.append(new char[] {'s','h','y'});
        System.out.println(builder3.toString());                // abcdefshy
        // 指定char[]数组的部分元素追加，从char[]数组的哪里开始，多少个元素去追加
        builder3.append(new char[] {'s','h','y'}, 1, 2);
        System.out.println(builder3.toString());                // abcdefshyhy

        // 运行报错，ArrayIndexOutOfBoundsException，指定的长度超过了指定起始位置后的元素个数
//        builder3.append(new char[] {'s','h','y'}, 1, 3);

        // 基本数据类型转换成string，去追加
        builder3.append(1);
        System.out.println(builder3.toString());                // abcdefshyhy1
        builder3.append(2L);
        System.out.println(builder3.toString());                // abcdefshyhy12
        builder3.append(6.0);
        System.out.println(builder3.toString());                // abcdefshyhy126.0
        builder3.append(6.6f);
        System.out.println(builder3.toString());                // abcdefshyhy126.06.6
        builder3.append('c');
        System.out.println(builder3.toString());                // abcdefshyhy126.06.6c
        builder3.append(false);
        System.out.println(builder3.toString());                // abcdefshyhy126.06.6cfalse

        /** StringBuilder---插入元素   就比append多一个想插入的位置，在指定位置前插入 **/
        builder3 = new StringBuilder("abc");
        // 插入一个string  最前面插入
        builder3.insert(0, "def");
        System.out.println(builder3.toString());                // defabc
        // 插入一个char[]数组  最后面插入
        builder3.insert(6, new char[] {'s','h','y'});
        System.out.println(builder3.toString());                // defabcshy
        // 指定char[]数组的部分元素，从char[]数组的哪里开始，多少个元素去插入
        builder3.insert(8, new char[] {'s','h','y'}, 1, 2);
        System.out.println(builder3.toString());                // defabcshhyy

        // 运行报错，ArrayIndexOutOfBoundsException，指定的长度超过了指定起始位置后的元素个数
//        builder3.insert(1, new char[] {'s','h','y'}, 1, 3);

        // 基本数据类型转换成string，去插入
        builder3.insert(1, 1);
        System.out.println(builder3.toString());                // d1efabcshhyy
        builder3.insert(1, 2L);
        System.out.println(builder3.toString());                // d21efabcshhyy
        builder3.insert(1, 6.0);
        System.out.println(builder3.toString());                // d6.021efabcshhyy
        builder3.insert(1, 6.6f);
        System.out.println(builder3.toString());                // d6.66.021efabcshhyy
        builder3.insert(1, 'c');
        System.out.println(builder3.toString());                // dc6.66.021efabcshhyy
        builder3.insert(1, true);
        System.out.println(builder3.toString());                // dtruec6.66.021efabcshhyy

        /** StringBuilder--- 删除元素 **/
        builder3 = new StringBuilder("abcdef");
        // 删除指定下标位置的字符
        builder3.deleteCharAt(1);
        System.out.println(builder3.toString());                // acdef
        // 删除指定下标区域的字符 startindex ~ endindex-1
        builder3.delete(1, 2);
        System.out.println(builder3.toString());                // adef

        /** StringBuilder--- 修改元素 **/
        builder3 = new StringBuilder("abc");
        // 修改指定位置的字符
        builder3.setCharAt(1, 'c');
        System.out.println(builder3.toString());                // acc

        /** StringBuilder--- 替换元素 **/
        builder3 = new StringBuilder("abc");
        // 修改指定位置区域的字符  startindex ~ endindex-1
        builder3.replace(1, 3, "sh");
        System.out.println(builder3.toString());                // ash

        /** StringBuilder--- 翻转生成器的字符 **/
        builder3 = new StringBuilder("abc");
        builder3.reverse();
        System.out.println(builder3.toString());                // cba

        /** StringBuilder--- 以上所有方法都可以返回新生成器的引用 **/
        builder3 = new StringBuilder("abc");
        StringBuilder builder = builder3.reverse();
        System.out.println(builder.toString());                 // cba

        /** StringBuilder---获取生成器当前可用的容量大小 **/
        builder1 = new StringBuilder();
        builder2 = new StringBuilder(10);
        builder3 = new StringBuilder("abc");
        System.out.println(builder1.capacity());                 // 16
        System.out.println(builder2.capacity());                 // 10
        System.out.println(builder3.capacity());                 // 19

        /** StringBuilder---获取生成器的实际大小 **/
        builder1 = new StringBuilder();
        builder2 = new StringBuilder(10);
        builder3 = new StringBuilder("abc");
        System.out.println(builder1.length());                 // 0 当length是0时，没有生成数组，只是分配了这么多的空间
        System.out.println(builder2.length());                 // 0
        System.out.println(builder3.length());                 // 3

        /** StringBuilder---设置生成器的实际大小 **/
        builder3 = new StringBuilder("abc");
        builder3.setLength(2);
        System.out.println(builder3.length());                 // 2
        System.out.println(builder3.toString());               // ab
        System.out.println(builder3.capacity());               // 19  改变实际大小，原来的容量大小不变

        builder3 = new StringBuilder("abc");
        builder3.setLength(6);
        System.out.println(builder3.length());                 // 6
        System.out.println(builder3.toString());               // abc     length只反映实际大小，当length设置大时，剩余数组的字符填充null字符，'\u0000'
        System.out.println(builder3.capacity());               // 19  改变实际大小，原来的容量大小不变

        /** StringBuilder---设置的容量为实际大小 **/
        System.out.println(builder3.capacity());               // 19
        builder3.trimToSize();
        System.out.println(builder3.length());                 // 6
        System.out.println(builder3.toString());               // abc   
        System.out.println(builder3.capacity());               // 6  容量大小变成实际大小，null字符也算字符，也在实际大小里

        builder3 = new StringBuilder("abc");
        System.out.println(builder3.length());                 // 3
        System.out.println(builder3.capacity());               // 19
        builder3.trimToSize();
        System.out.println(builder3.capacity());               // 3

        /** StringBuilder---获取指定位置的字符 **/
        char builder_char = builder3.charAt(1);
        System.out.println(builder_char);                      // b

<<<<<<< Updated upstream
        /** StringBuilder---截取字串 **/
        builder3 = new StringBuilder("abc");
        // 截取从指定定位置开始到结尾
        System.out.println(builder3.substring(1));        // bc
        // 截取从指定区域，startindex ~ endindex-1
        System.out.println(builder3.substring(1,2));           // b
=======


>>>>>>> Stashed changes

    }
}
