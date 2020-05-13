package designPattern.mediator;

import designPattern.mediator.framework.Mediator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 仲裁者模式：仲裁者角色的具体实现类
 */
public class LoginFrame extends Frame implements Mediator, ActionListener {
    private CollegueCheckBox checkGuest;
    private CollegueCheckBox checkLogin;

    private CollegueTextField textUser;
    private CollegueTextField textPassword;

    private ColleagueButton buttonOk;
    private ColleagueButton buttonCancel;

    public LoginFrame(String title) {
        super(title);
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(4, 2));

        createColleagues();
        add(checkGuest);
        add(checkLogin);
        add(new Label("Username:"));
        add(textUser);
        add(new Label("Password:"));
        add(textPassword);
        add(buttonOk);
        add(buttonCancel);

        colleagueChanged();

        pack();
        show();
    }

    @Override
    public void createColleagues() {
        CheckboxGroup group = new CheckboxGroup();
        checkGuest = new CollegueCheckBox("Guest", group, true);
        checkLogin = new CollegueCheckBox("Login", group, true);

        textUser = new CollegueTextField("", 10);
        textPassword = new CollegueTextField("", 10);

        buttonOk = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");

        // 设置 Mediator
        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPassword.setMediator(this);
        buttonOk.setMediator(this);
        buttonCancel.setMediator(this);

        // 设置Listener
        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);
        textUser.addTextListener(textUser);
        textPassword.addTextListener(textPassword);
        buttonOk.addActionListener(buttonOk);
        buttonCancel.addActionListener(buttonCancel);
    }

    @Override
    public void colleagueChanged() {
        if (checkGuest.getState()) {
            textUser.setColleagueEnabled(false);
            textPassword.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(true);
        } else {
            textUser.setColleagueEnabled(true);
            userPassChanged();
        }
    }

    private void userPassChanged() {
        if (textUser.getText().length() > 0) {
            textPassword.setColleagueEnabled(true);
            if (textUser.getText().length() >= 4 && textPassword.getText().length() >= 4) {
                buttonOk.setColleagueEnabled(true);
            } else {
                buttonOk.setColleagueEnabled(false);
            }
        } else {
            textPassword.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        System.exit(0);
    }
}
