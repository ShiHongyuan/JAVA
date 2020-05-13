package designPattern.mediator;

import designPattern.mediator.framework.Colleague;
import designPattern.mediator.framework.Mediator;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 仲裁者模式：很多的处理者角色中的其中一个具体实现处理者（组员）
 */
public class CollegueCheckBox extends Checkbox implements Colleague, ItemListener {
    private Mediator mediator;

    public CollegueCheckBox(String caption, CheckboxGroup group, boolean state) {
        // 调用父类 Checkbox 的构造方法
        super(caption, group, state);
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    // 仲裁者与组员之间的通信接口
    @Override
    public void setColleagueEnabled(boolean enabled) {
        // 继承自父类 Checkbox 的方法
        setEnabled(enabled);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        mediator.colleagueChanged();
    }
}
