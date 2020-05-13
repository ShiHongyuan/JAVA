package designPattern.mediator.framework;

/**
 * 仲裁者模式：仲裁者角色的类的接口
 */
public interface Mediator {
    // 捆绑仲裁者和组员
    public abstract void createColleagues();

    // 仲裁者与组员之间的通信接口
    public abstract void colleagueChanged();
}
