package designPattern.flyweight;

/**
 * 为了轻量级共享对象模式：使用共享对象，从工厂类那里获取被共享对象的使用者
 */
public class BigString {
    private BigChar[] bigChars;

    public BigString(String string, boolean shared) {

        bigChars = new BigChar[string.length()];

        // shared=true共享对象，shared=false不共享对象
        if (shared) {
            BigCharFactory factory = BigCharFactory.getInstance();
            for (int i = 0; i < bigChars.length; i++) {
                bigChars[i] = factory.getChar(string.charAt(i));
            }
        } else {
            for (int i = 0; i < bigChars.length; i++) {
                bigChars[i] = new BigChar(string.charAt(i));
            }
        }

    }

    public void print() {
        for (int i = 0; i < bigChars.length; i++) {
            bigChars[i].print();
        }
    }
}
