// 引入map
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class CharAndStringTransform {
    static void fun() {
        // MAP的KV类型一定要是Object，不能是int
        Map<String, Integer> map = new HashMap<String, Integer>();
    }
    static char stringToChar(String s, int n) {
        return s.charAt(n);
    }

    static char[] stringToChars(String s) {
        return s.toCharArray();
    }

    static String[] stringToStrings(String s) {
        return s.split("_");
    }

    static String charToString(char c) {
        return String.valueOf(c);
    }

    static String charsToString(char[] chars) {
        String s = "";
        for (char c :chars) {
            s += c;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(CharAndStringTransform.stringToChars("shy")[1]);
        System.out.println(CharAndStringTransform.charsToString(new char[]{'s','h','y'}));
        String s = "123456";
        Map<String, String> map = new HashMap<String, String>();
        s.chars().mapToObj(i -> String.valueOf((char)i)).map(i -> s.toLowerCase().indexOf(i)
                == s.toLowerCase().lastIndexOf(i) ? "(" : ")").collect(Collectors.joining());
    }
}
