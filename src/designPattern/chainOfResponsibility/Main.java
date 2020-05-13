package designPattern.chainOfResponsibility;

/**
 * 推卸责任模式：有一系列的责任可以设置下一个责任人是谁，每个人只关心自己能不能处理问题，但是不关心下一个责任人能不能处理问题，
 *              弱化了发出请求者和处理请求者之间的关系，每个责任人只需要知道自己能处理不能处理哪些问题就行，
 *              不用每个责任人都保存一份问题与能解决问题的责任人的对照表（每个人都保存一份就弱化了组件可复用的独立性，修改一个对应关系就得该所有责任人的对照表），
 *
 *              或者用仲裁者模式，仲裁者保存一份对照表，责任人不能处理都给仲裁者，由仲裁者发送到问题对应的责任人处理。
 */
public class Main {
    public static void main (String[] args) {
        Support noSupport = new NoSupport("noSupport");
        Support limitSupport100 = new LimitSupport("limitSupport100", 100);
        Support limitSupport200 = new LimitSupport("limitSupport200", 200);
        Support limitSupport300 = new LimitSupport("limitSupport300", 300);
        Support oddSupport = new OddSupport("oddSupport");
        Support specialSupport = new SpecialSupport("specialSupport",429);

        // 通过手动设置形成责任链
        noSupport.setNextSupport(limitSupport100).setNextSupport(specialSupport).
                setNextSupport(limitSupport200).setNextSupport(oddSupport).
                setNextSupport(limitSupport300);


        // 将问题抛给责任链
        for (int i = 0; i < 500; i += 33) {
            noSupport.support(new Trouble(i));
        }
    }
}
