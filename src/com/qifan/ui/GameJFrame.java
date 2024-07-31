package com.qifan.ui;

import javax.swing.*;
import java.util.Random;

public class GameJFrame extends JFrame {
    int[][] data = new int[4][4];

    public GameJFrame(){
        // 初始化界面
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化数据（打乱）
        initData();
        // 初始化图片
        initImage();


        this.setVisible(true);

    }

    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                data[i][j] = tempArr[i * 4 + j];
            }
        }
    }

    private void initImage() {

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                // 创建一个图片Image的对象
                ImageIcon icon = new ImageIcon("C:\\Users\\chinf\\IdeaProjects\\jigsawgame\\image\\animal\\animal3\\" + data[i][j] +".jpg");
                // 创建一个JLabel的对象（管理容器）
                JLabel label = new JLabel(icon);
                // 指定图片位置
                label.setBounds(105 * j, 105 * i, 105, 105);

                this.getContentPane().add(label);
            }
        }



        // 把管理容器添加到界面中
        //this.add(label);

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
        this.setLayout(null);
    }

}
