package designPattern.state;

/**
 * 状态用类来表示的 state模式：使用状态类的类的接口
 */
public interface Context {
    public abstract void setClock(int hour);
    public abstract void changeState(State state);
    public abstract void callSecurityCenter(String msg);
    public abstract void recording(String msg);
}
