package com.qifan.ui;

import javax.swing.*;

public class GameJFrame extends JFrame {
    public GameJFrame(){
        // 初始化界面
        initJFrame();
        // 初始化菜单
        initJMenuBar();


        this.setVisible(true);

    }

    private void initJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reloginItem = new JMenuItem("重新登陆");
        JMenuItem exitItem = new JMenuItem("关闭游戏");

        JMenuItem accoutItem = new JMenuItem("公众号");

        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(exitItem);

        aboutJMenu.add(accoutItem);

        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);

        this.setJMenuBar(menuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机 v1");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
