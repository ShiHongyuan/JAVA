public class Test {
    public static void main(String[] args) {
        String s = "(1,(2,3),(4,(5,6)7))";
        String s1 = s.replaceAll("[\\(\\)\\d,]","");
        System.out.println(s1);
        System.out.println(s1.length());

        int n = Integer.parseInt("-1");
        n = Integer.valueOf("-1");
        Integer n1 = Integer.parseInt("+1");
        n1 = Integer.valueOf("+1");
        "123".split(",");
    }
}
