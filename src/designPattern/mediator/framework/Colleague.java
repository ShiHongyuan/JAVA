package designPattern.mediator.framework;

/**
 * 仲裁者模式：很多的处理者角色的统一接口
 */
public interface Colleague {
    // 捆绑仲裁者和组员
    public abstract void setMediator(Mediator mediator);

    // 仲裁者与组员之间的通信接口
    public abstract void setColleagueEnabled(boolean enabled);
}
