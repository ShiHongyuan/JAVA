package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：不解决任何问题的Support具体实现类
 */
public class NoSupport extends Support {
    public NoSupport (String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}
