package com.qifan.ui;

import com.qifan.domain.User;
import com.qifan.util.CodeUtils;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static com.qifan.util.CodeUtils.getcode;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> list = new ArrayList<>();
    static {
        list.add(new User("zhangsan","123"));
        list.add(new User("lisi","1234"));
    }

    JButton login = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    JLabel rightCode = new JLabel();







    public LoginJFrame() {
        // 初始化界面
        initJFrame();
        // 添加内容
        initView();

        this.setVisible(true);
    }

    private void initView() {
        // 添加用户名
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116,135,47,17);
        this.add(usernameText);
        username.setBounds(195, 134, 200, 30);
        this.add(username);
        // 添加密码
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.add(passwordText);
        password.setBounds(195, 195, 200, 30);
        this.add(password);
        // 验证码
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.add(codeText);
        code.setBounds(195, 256, 100, 30);
        this.add(code);

        // 显示正确验证码
        String codestr = getcode();
        rightCode.setText(codestr);
        rightCode.addMouseListener(this);
        rightCode.setBounds(300, 256, 50, 30);
        this.add(rightCode);

        // 登录和注册按键
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮的边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.add(login);


        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.add(register);


        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);





    }


    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("登录");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);//取消内部默认布局
    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == login) {
            System.out.println("登录");

            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String codeInput = code.getText();

            //创建一个User对象
            User userInfo = new User(usernameInput, passwordInput);
            System.out.println("用户输入的用户名为" + usernameInput);
            System.out.println("用户输入的密码为" + passwordInput);

            if (codeInput.length() == 0) {
                showJDialog("验证码不能为空");
            } else if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                //校验用户名和密码是否为空
                System.out.println("用户名或者密码为空");

                //调用showJDialog方法并展示弹框
                showJDialog("用户名或者密码为空");


            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("验证码输入错误");
            } else if (contains(userInfo)) {
                System.out.println("用户名和密码正确可以开始玩游戏了");
                //关闭当前登录界面
                this.setVisible(false);
                //打开游戏的主界面
                //需要把当前登录的用户名传递给游戏界面
                new GameJFrame();
            } else {
                System.out.println("用户名或密码错误");
                showJDialog("用户名或密码错误");
            }

        }else if(e.getSource() == register) {
            System.out.println("注册");
        }else if(e.getSource() == rightCode) {
            System.out.println("更换验证码");
            String codestr = getcode();
            rightCode.setText(codestr);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean contains(User userInput){
        for (int i = 0; i < list.size(); i++) {
            User rightUser = list.get(i);
            if(userInput.getUsername().equals(rightUser.getUsername()) && userInput.getPassword().equals(rightUser.getPassword())){
                //有相同的代表存在，返回true，后面的不需要再比了
                return true;
            }
        }
        //循环结束之后还没有找到就表示不存在
        return false;
    }

}
