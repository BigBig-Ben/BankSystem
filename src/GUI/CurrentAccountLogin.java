package GUI;

import DAO.CurrentAccountDAO;
import Entity.CurrentAccount;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class CurrentAccountLogin {
    /**
     * 登录界面
     */
    private static final long serialVersionUID = -7899228102358391474L;


    JFrame frame = new JFrame("储户登录");
    JPanel pan = new JPanel(),
            panCount = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();
    JButton btnEnter = new JButton("登录"),
            btnCancel = new JButton("返回");
    JLabel  countLab = new JLabel("账号"),
            pwdLab = new JLabel("密码");

    JTextField count = new JTextField("", 20);//账户号
    JTextField password = new JTextField("", 20);//密码
    int bankId;

    public CurrentAccountLogin(int id) {
        bankId = id;

        btnEnter.setFont(new Font("黑体", Font.PLAIN, 20));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 20));
        countLab.setFont(new Font("黑体", Font.PLAIN, 20));
        pwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        count.setFont(new Font("黑体", Font.PLAIN, 20));
        password.setFont(new Font("黑体", Font.PLAIN, 20));

        panCount.add(countLab);
        panCount.add(count);
        panPwd.add(pwdLab);
        panPwd.add(password);

        pan.add(panCount);
        pan.add(panPwd);

        btnEnter.setPreferredSize(new Dimension(100, 45));
        btnCancel.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnEnter);
        panButton.add(btnCancel);
        pan.add(panButton);
        pan.setLayout(new GridLayout(3, 1));
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //登陆按钮监测
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login(e);
            }
        });

        //返回按钮监测
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back(e);
            }
        });
    }

    //登陆响应
    void login(ActionEvent e) {

        try {
            int username = Integer.parseInt(count.getText());
            String key = password.getText();

            //对比活期用户的账号密码
            CurrentAccount ca = CurrentAccountDAO.getAccountById(username);
            if (ca.getPassword().equals(key)) {
                CurrentAccountOption df = new CurrentAccountOption(username, bankId);  //传给下一界面账号
                frame.setVisible(false);
            } else JOptionPane.showMessageDialog(null, "密码或用户名错误");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "账号不合法");
        }
    }

    //返回响应
    void back(ActionEvent e)
    {
        FunctionOption func = new FunctionOption(bankId);
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
