package designPattern.state;

/**
 * 状态用类来表示的 state模式：一个状态就是一个类，使用状态类的类通过切换表示状态的类的实例来实现状态迁移
 *                        具体的状态类有一个共同的状态接口
 *
 *                        不用在使用类里每次都去判断当前状态是什么，再采用对应状态的处理
 *                        而是状态改变时只需要改变使用类持有的状态类，不同状态的处理在不同状态类里自己实现，
 *                        在使用类里每次可以直接调用持有状态的处理方法，不同的状态类有一个共同的接口，所以它们的处理方法签名都一样，
 *                        也就是使用类里调用的方法都一样。
 *
 *                        处理改变了，只需要修改相应的状态类，或者需要新增状态，就新增一个状态类，实现它的处理方法即可，
 *                        增加了状态处理类的组件可复用性。
 *
 *                        而且使用状态的类不需要知道有哪些状态，每次变更都是交给当前的状态类自己去判断迁移状态，
 *                        这就把使用状态的类和状态类们完全的分离开了，它们是独立的两部分，新增删除状态都不需要修改使用状态的类
 */
public class Main {
    public static void main (String[] args) {
        SafeFrame frame = new SafeFrame("State Sample");
        while (true) {
            for (int hour = 0; hour < 24; hour++) {
                frame.setClock(hour);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
