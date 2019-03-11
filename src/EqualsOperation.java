import java.util.List;

public class EqualsOperation {
    public static void main(String[] args) {
        String a = "";
        String b = null;
        String c = new String("");
        System.out.println(a.equals(null)); // false
        System.out.println(a.equals(""));   // true
        // System.out.println(b.equals(null)); // 报错，null不是对象，没有equals方法
        System.out.println(a == "");        // true
        System.out.println(c.equals(""));   // true
        System.out.println(c == "");        // false
        System.out.println(b == "");        // false
        System.out.println(b == null);      // true
    }
}
