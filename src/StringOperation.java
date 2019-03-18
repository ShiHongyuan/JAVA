public class StringOperation {
    public static void main(String[] args) {
        /**
         * java.lang.String�Ĵ����ͳ�ʼ��
         * String��11�����캯����40������������
         * ������ڲ���string����˽�е��ַ����������ʾ��
         */
        // ֱ����
        String s1 = "123";
        System.out.println(s1);      // 123

        // �ַ����� ���빹�캯�� ��������ʼ��String
        String s2 = new String(new char[] {65, 66, 67});
        System.out.println(s2);      // ABC
        String s3 = new String(new char[] {'a', 'b', 'c'});
        System.out.println(s3);      // abc

        /**
         * string�Ƚ�
         * ������ < >���ᱨ��
         */
        String s4 = "abc";
        String s5 = "abe";
        // == ����ֵ���
        System.out.println(s4 == s5);      // false

        // equals �������
        System.out.println(s4.equals(s5)); // false

        // equalsIgnoreCase ͬequals + ���Դ�Сд
        System.out.println("aBC".equalsIgnoreCase("ABc")); // true  �������

        // compareTo  ������ȣ����ش����ҵ�һ������ͬ�ַ��ľ��볤�ȣ���Ⱦͷ���0
        System.out.println(s4.compareTo(s5)); // -2  s1��һ������ͬ�ַ� - s2��һ������ͬ�ַ�  c��eС2������
        System.out.println(s5.compareTo(s4)); // 2  s1��һ������ͬ�ַ� - s2��һ������ͬ�ַ�   e��c��2������
        System.out.println(s4.compareTo("abc")); // 0  �������
        System.out.println("abe".compareTo(s5)); // 0  �������

//        System.out.println(s4 < s5); // ���뱨��

        // compareToIgnoreCase ͬcompareTo + ���Դ�Сд
        System.out.println("aBC".compareToIgnoreCase("ABc")); // 0  �������

        // startsWith(prefix) �Ƿ���prefix�ַ�����ͷ
        System.out.println("aBC".startsWith("BC")); // false
        System.out.println("aBC".startsWith("aB"));  // true
        // startsWith ��ƫ���� �� ����ƫ����
        System.out.println("aBC".startsWith("BC", 1));   // true k����ָ�������￪ʼƥ��ǰ׺
        System.out.println("".startsWith(""));                        // true
        System.out.println("ll".startsWith(""));                      // true
        System.out.println("ll".startsWith("", 0));      // true
        System.out.println("".startsWith("ll"));                      // false
        System.out.println("".startsWith("", -1));       // false
        System.out.println("hello".startsWith("ll"));                 // false
        System.out.println("hello".startsWith("ll", 2)); // true
        System.out.println("hello".startsWith("o", -1)); // false

        // endsWith(suffix) �Ƿ���suffix�ַ�������
        System.out.println("aBC".endsWith("B"));  // false
        System.out.println("aBC".endsWith("BC"));  // true

        // regionMatches([booleanҪ��Ҫ���Դ�Сд]��s1��ʼ�Ƚϵ�λ�ã�s2��s2��ʼ�Ƚϵ�λ�ã�Ҫ�Ƚϵĳ���) �����ַ����Ƿ���ͬ
        System.out.println("aBCdefg".regionMatches(1,"BCdefg",0,3));  // true
        System.out.println("aBCdefg".regionMatches(1,"BCdefg",0,6));  // true
        System.out.println("aBCdefg".regionMatches(1,"BCdefg",0,20)); // false
        System.out.println("aBCdefg".regionMatches(0,"12aBCdefg",2,7)); // true

        System.out.println("abc".regionMatches(false,0,"ABC",0,3)); // false
        System.out.println("abc".regionMatches(true,0,"ABC",0,3));  // true

        /**
         * string�����ӷ�����
         * 1�����ӷ� +
         * 2��concat
         */
        // ����֮һ���ַ������������ӷ�����һ����ת�����ַ��������ӣ����������ǣ����Ǽӷ������
        System.out.println("string=" + 1 + 2);   //string=12  û�����ţ������Ҽ��㣬���������ӷ�
        System.out.println("string=" + (1 + 2)); //string=3   �����ţ�����������ģ�������������ӣ��ٺ��������

        String s6 = "123";
        String s7 = "abc";
        System.out.println(s6.concat(s7));         // 123abc
        System.out.println("123".concat("abc"));   // 123abc


        /**
         * string��ȡ�ַ���
         */
        // ��ȡ�ַ���substring(��ʼλ�ã�����λ��)
        System.out.println("123".substring(0,0)); // ����"", ����Ҳ���ᱨ��
//        System.out.println("123".substring(2,1)); // ���б���
        System.out.println("123".substring(1,2)); // 2
        System.out.println("123".substring(1,3)); // 23

        // substring(��ʼλ��)
        System.out.println("123".substring(1)); // 23
        System.out.println("123".substring(0)); // 123

        /**
         * string��ȡָ��λ���ַ�
         */
        // charAt(int)
        char c = "abc".charAt(1);
        System.out.println(c); // b
        System.out.println("1 ".charAt(1));   //' ' �հ��ַ�

        /**
         * �ַ�����Сдת�� ���ı�ԭ����ֵ������һ���µ��ַ���
         */
        // תСд
        System.out.println("Hello".toLowerCase());   // hello

        // ת��д
        System.out.println("Hello".toUpperCase());   // HELLO

        /**
         * ȥͷβ�ո�
         */
        // תͷβ�ո�
        System.out.println("  Hel lo  ".trim());     // Hel lo

        /**
         * �ָ�string
         */
        String[] strings = "a#b#c#d#e#f".split("#");
        for (String s: strings) {
            System.out.print(s + " ");   // a b c d e f
        }
        System.out.print("\n");

        // limit ���ֻ�ܱ��ָ�ɵ���������������ĺ���Ͳ��ָ���
        String[] strings1 = "a#b#c#d#e#f".split("#", 3);
        for (String s: strings1) {
            System.out.print(s + " ");   // limit=3��a b c#d#e#f   limit=4��a b c d#e#f
        }
        System.out.print("\n");

        // ���������ʽ ���һ���ָ�������û�еĻ����Ͳ�����""Ԫ��
        String[] strings2 = ";java,c?;:c++#".split("[,:;?#]");
        for (String s: strings2) {
            System.out.println(s);   // "" java c "" "" c++
        }

        /**
         * string�滻
         * 1��replace        ȫ���滻����֧��������ʽ
         * 2��replaceAll     ȫ���滻��������ɲ�����
         * 3��replaceFirst   �滻��һ��ƥ����󣬿�����ɲ�����
         */
        // replace
        // ������char
        System.out.println("abc".replace('a','b'));     // bbc
        // ������string
        System.out.println("abc".replace("a","b"));    // bbc
        System.out.println("abc".replace("ab","ba"));  // bac
        System.out.println("abc".replace("aa","ba"));  // abc   û���滻�ɹ�

        // replaceAll
        System.out.println("abc".replaceAll("a", "c"));       // cbc
        System.out.println("abc".replaceAll("[a]", "abc"));   // abcbc

        // replaceFirst
        System.out.println("abcabc".replaceFirst("a", "b"));     // bbcabc
        System.out.println("abcabc".replaceFirst("(abc)", "a"));     // aabc

        /**
         * string��ȡָ���ַ���λ�� indexOf ָ������ʼλ�ó���string�����ˣ�Ҳ���ᱨ������Ҳ���
         */
        // indexOf(char)          ����string��char��һ��ƥ���λ��
//        System.out.println("".indexOf(''));           // ���뱨��char�����ǿ�
        System.out.println("".indexOf('a'));            // -1
        System.out.println("abc abc abc".indexOf('a')); // 0

        // indexOf(char, int)     int�󣨰���int����char��һ��ƥ���λ��
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

        // lastIndexOf(char)      ����string��char���һ��ƥ���λ��
        System.out.println("".lastIndexOf('a'));            // -1
        System.out.println("abc abc abc".lastIndexOf('a')); // 8

        // lastIndexOf(char, int) intǰ������int������char���һ��ƥ���λ��
        System.out.println("".lastIndexOf('a', 2));            // -1
        System.out.println("abc abc abc".lastIndexOf('a', 8)); // 8
        System.out.println("abc abc abc".lastIndexOf('a', 0)); // 0
        System.out.println("abc abc abc".lastIndexOf('a', 6)); // 4

        // lastIndexOf(string)
        System.out.println("".lastIndexOf(""));               // 0
        System.out.println("abc abc abc".lastIndexOf(""));    // 11  ���һλ����һλ
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
         * string �ַ����� ��ת
         */
        // string ת char[]
        char[] chars1 = "abc".toCharArray();
        System.out.println(chars1);  // abc

        char[] chars2 = {'a', 'b', 'c', 'd', 'e', 'f'};
        // getChars ֻ���滻ԭchar[]��һ�����ַ�������滻��λ�ó�����ԭchar[]�ĳ��ȣ����лᱨ��
        "123".getChars(0, 3, chars2, 1);
        System.out.println(chars2);  // a123ef

        // char[] ת string
        String str1 = new String(chars2);
        System.out.println(str1);       // a123ef

        String str2 = String.valueOf(chars2);
        System.out.println(str2);       // a123ef


        /**
         * ������������ ת string
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
         * ���� ת string  String.valueOf() == toString()
         */
        StringOperation x = new StringOperation();
        System.out.println(String.valueOf(x));     // StringOperation@60e53b93
        System.out.println(x.toString());          // StringOperation@60e53b93

        /**
         * string ת ��������
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
         * ������������ תΪ ��ʽ����string
         * ��ʽ������ ͬprintf��ֻ�Ǹ�ʽ������Զ�ת��Ϊһ��string����
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
         * toString������ϵͳ��������ö���ġ�������������Ϣ�����Ը�����������е�״̬��Ϣ
         * Object ���ṩ��toString�������Ƿ��ظö���ʵ��������� + @ +hashCodeֵ
         * ����ͨ����д�����toString()�������ı�����������������
         */

        System.out.println(chars1.toString());           // [C@60e53b93
        System.out.println(new int[] {1,2}.toString());  // [I@5e2de80c



    }
}
