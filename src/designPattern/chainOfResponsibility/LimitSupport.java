package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：只解决问题number小于指定的数值的Support具体实现类
 */
public class LimitSupport extends Support {
    private int limit;
    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }
    
    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNumber() < limit) {
            return true;
        } else {
            return false;
        }
    }
}
