package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：只解决问题number是奇数的Support具体实现类
 */
public class OddSupport extends Support {
    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNumber()  % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
}
