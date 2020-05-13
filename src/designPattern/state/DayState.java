package designPattern.state;

/**
 * 状态用类来表示的 state模式：状态类的其中一个具体实现类
 */
public class DayState implements State {
    private static DayState singleton = new DayState();
    private DayState(){}
    public static State getInstance() {
        return singleton;
    }

    @Override
    public void doClock(Context context, int hour) {
        if (hour < 9 || 17 <= hour) {
            context.changeState(NightState.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.recording("使用金库（白天）");
    }

    @Override
    public void doAlam(Context context) {
        context.callSecurityCenter("按下警铃（白天）");
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("正常通话（白天）");
    }

    @Override
    public String toString() {
        return "[白天]";
    }
}
