package designPattern.state;

/**
 * 状态用类来表示的 state模式：状态类共同的接口
 */
public interface State {
    public abstract void doClock(Context context, int hour);
    public abstract void doUse(Context context);
    public abstract void doAlam(Context context);
    public abstract void doPhone(Context context);
}
