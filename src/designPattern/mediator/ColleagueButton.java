package designPattern.mediator;

import designPattern.mediator.framework.Colleague;
import designPattern.mediator.framework.Mediator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 仲裁者模式：很多的处理者角色中的其中一个具体实现处理者（组员）
 */
public class ColleagueButton extends Button implements Colleague, ActionListener {
    private Mediator mediator;

    public ColleagueButton(String caption) {
        // 调用父类 Button 的构造方法
        super(caption);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    // 仲裁者与组员之间的通信接口
    @Override
    public void setColleagueEnabled(boolean enabled) {
        // 继承自父类 Button 的方法
        setEnabled(enabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("点击了button");
        System.exit(0);
    }

}
