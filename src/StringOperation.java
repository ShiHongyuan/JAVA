public class StringOperation {
    public static void main(String[] args) {
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
    }
}
