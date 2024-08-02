package com.qifan.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    // 记录空白块位置
    int x;
    int y;
    // 记录路径
    String path = "image\\animal\\animal3\\";
    // 记录正确
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    // 统计步数
    int step = 0;

    // 创建条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reloginItem = new JMenuItem("重新登陆");
    JMenuItem exitItem = new JMenuItem("关闭游戏");

    JMenuItem accoutItem = new JMenuItem("公众号");

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
                if (tempArr[i * 4 + j] == 0){
                    x = j;
                    y = i;
                }
                    data[i][j] = tempArr[i * 4 + j];
            }
        }
    }

    private void initImage() {
        // 清空
        this.getContentPane().removeAll();

        if(victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepJLabel = new JLabel("步数：" + step);
        stepJLabel.setBounds(50,30,100,20);
        this.getContentPane().add(stepJLabel);

        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                // 创建一个图片Image的对象
                ImageIcon icon = new ImageIcon( path+ data[i][j] + ".jpg\\");
                // 创建一个JLabel的对象（管理容器）
                JLabel label = new JLabel(icon);
                // 指定图片位置
                label.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));

                this.getContentPane().add(label);
            }
        }

        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40,508, 560);
        this.getContentPane().add(background);

        // 刷新界面
        this.getContentPane().repaint();

        // 把管理容器添加到界面中
        //this.add(label);

    }

    private void initJMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");



        functionJMenu.add(replayItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(exitItem);

        aboutJMenu.add(accoutItem);

        // 给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        exitItem.addActionListener(this);
        accoutItem.addActionListener(this);

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

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            // 全部
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);

            //背景
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40,508, 560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(victory()){
            return;
        }
        // 左：37；上：38；右：39；下：40
        int code = e.getKeyCode();
        if(code == 37){
            if(x == 3){
                System.out.println("出错了移不动");
                return;
            }
            System.out.println("向左移动");
            data[y][x] = data[y][x + 1];
            data[y][x + 1] = 0;
            x++;
            step++;
        }else if(code == 38){
            if(y == 3){
                System.out.println("出错了移不动");
                return;
            }
            System.out.println("向上移动");
            data[y][x] = data[y+1][x];
            data[y+1][x] = 0;
            y++;
            step++;
        }else if(code == 39){
            if(x == 0){
                System.out.println("出错了移不动");
                return;
            }
            System.out.println("向右移动");
            data[y][x] = data[y][x-1];
            data[y][x-1] = 0;
            x--;
            step++;
        }else if(code == 40){
            if(y == 0){
                System.out.println("出错了移不动");
                return;
            }
            System.out.println("向下移动");
            data[y][x] = data[y - 1][x];
            data[y - 1][x] = 0;
            y--;
            step++;
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            x = 3;
            y = 3;
        }
        initImage();

    }

    public boolean victory(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
}


    @Override
    public void actionPerformed(ActionEvent e) {

        Object obj = e.getSource();
        if(obj == replayItem){
            System.out.println("重新游戏");
            initData();
            step = 0;
            initImage();

        }else if(obj == reloginItem){
            System.out.println("重新登陆");
        }else if(obj == exitItem){
            System.out.println("关闭游戏");
            System.exit(0);
        }else if(obj == accoutItem){
            System.out.println("公众号");
        }
    }
}
