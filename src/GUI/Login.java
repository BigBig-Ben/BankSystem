package GUI;

import DAO.BankDAO;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Login {
    /**
     * 登录界面
     */
    private static final long serialVersionUID = -7899228102358391474L;


    JFrame frame = new JFrame("银行储蓄系统");
    JPanel pan = new JPanel(),
            panRole = new JPanel(),
            panCount = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();
    JButton enter = new JButton("登录");
    JLabel roleLab = new JLabel("登录银行"),
            countLab = new JLabel("账号"),
            pwdLab = new JLabel("密码");

    JTextField count = new JTextField("", 20);
    JTextField password = new JTextField("", 20);

    public Login() {

        enter.setFont(new Font("黑体", Font.PLAIN, 20));
        roleLab.setFont(new Font("黑体", Font.PLAIN, 20));
        countLab.setFont(new Font("黑体", Font.PLAIN, 20));
        pwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        count.setFont(new Font("黑体", Font.PLAIN, 20));
        password.setFont(new Font("黑体", Font.PLAIN, 20));
        panRole.add(roleLab);

        panCount.add(countLab);
        panCount.add(count);
        panPwd.add(pwdLab);
        panPwd.add(password);
        pan.add(panRole);
        pan.add(panCount);
        pan.add(panPwd);

        enter.setPreferredSize(new Dimension(100, 45));
        panButton.add(enter);
        pan.add(panButton);
        pan.setLayout(new GridLayout(4, 1));
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);


        enter.addActionListener(new ActionListener() {            //登陆按钮监测
            public void actionPerformed(ActionEvent e) {
                login(e);
            }
        });
    }

    //登陆响应
    void login(ActionEvent e) {
        try {
            int username = Integer.parseInt(count.getText());//账号
            String key = password.getText();//密码
            if (BankDAO.login(username, key)) {
                //正确进入选择活期、定期、维护界面
                FunctionOption f = new FunctionOption(username);//传给下一界面银行名称
                frame.setVisible(false);
            } else JOptionPane.showMessageDialog(null, "密码或用户名错误");
        }
        catch(Exception e1)
        {
            JOptionPane.showMessageDialog(null, "账号不合法");
        }
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
