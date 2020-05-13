package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：所有Support具体实现类的抽象父类
 */
public abstract class Support {
    private String name;
    private Support next;

    public Support(String name) {
        this.name = name;
    }

    public Support setNextSupport(Support support) {
        this.next = support;
        return next;
    }

    public Support getNextSupport() {
        return next;
    }

    // 递归调用support方法
//    public final void support(Trouble trouble) {
//        if (resolve(trouble)) {
//            done(trouble);
//        } else if (next != null) {
//            next.support(trouble);
//        } else {
//            fail(trouble);
//        }
//    }

    // 循环调用方式
    public final void support(Trouble trouble) {
        Support support = this;
        do {
            if (support.resolve(trouble)) {
                support.done(trouble);
                break;
            } else if (support.getNextSupport() != null){
                support = support.getNextSupport();
            } else {
                support.fail(trouble);
                break;
            }
        } while (true);
    }

    protected abstract boolean resolve(Trouble trouble);

    protected void done (Trouble trouble) {
        System.out.println(trouble + "is resolved by" + this + ".");
    }

    protected void fail (Trouble trouble) {
        System.out.println(trouble + "can not be resolved.");
    }

    @Override
    public String toString() {
        return "[Support " + name + "]";
    }

}
