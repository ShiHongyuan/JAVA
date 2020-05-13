package designPattern.mediator;

import designPattern.mediator.framework.Colleague;
import designPattern.mediator.framework.Mediator;

import java.awt.*;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;

/**
 * 仲裁者模式：很多的处理者角色中的其中一个具体实现处理者（组员）
 */
public class CollegueTextField extends TextField implements Colleague, TextListener {
    private Mediator mediator;

    public CollegueTextField(String text, int columns) {
        // 调用父类 TextField 的构造方法
        super(text, columns);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    // 仲裁者与组员之间的通信接口
    @Override
    public void setColleagueEnabled(boolean enabled) {
        // 继承自父类 TextField 的方法
        setEnabled(enabled);
        setBackground(enabled ? Color.WHITE : Color.LIGHT_GRAY);
    }

    @Override
    public void textValueChanged(TextEvent e) {
        mediator.colleagueChanged();
    }
}
