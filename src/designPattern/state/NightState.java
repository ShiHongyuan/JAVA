package designPattern.state;

/**
 * 状态用类来表示的 state模式：状态类的其中一个具体实现类
 */
public class NightState implements State{
    private static NightState singleton = new NightState();
    private NightState(){}

    public static State getInstance() {
        return singleton;
    }

    @Override
    public void doClock(Context context, int hour) {
        if (hour >= 9 && hour <17) {
            context.changeState(DayState.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.recording("使用金库（晚上）");
    }

    @Override
    public void doAlam(Context context) {
        context.callSecurityCenter("按下警铃（晚上）");
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("正常通话（晚上）");
    }

    @Override
    public String toString() {
        return "[晚上]";
    }
}
