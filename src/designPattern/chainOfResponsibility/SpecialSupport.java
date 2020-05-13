package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：只解决问题number是指定数值的Support具体实现类
 */
public class SpecialSupport extends Support {
    private int number;
    public SpecialSupport(String name, int number) {
        super(name);
        this.number = number;
    }
    
    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNumber() == number) {
            return true;
        } else {
            return false;
        }
    }
}
