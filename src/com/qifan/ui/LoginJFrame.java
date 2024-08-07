package com.qifan.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {

    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }

    public LoginJFrame() {
        this.setSize(488, 430);
        this.setTitle("登录");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.setVisible(true);
    }


}
