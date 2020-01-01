package GUI;

import DAO.BankDAO;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ManageOption {
    /**
     * 登录界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    private Font font = new Font("黑体", Font.PLAIN, 14);
    JMenuBar mb = new JMenuBar();
    JFrame frame = new JFrame("管理员——功能选择");
    FgMenu info = new FgMenu("管理员信息(I)", KeyEvent.VK_I);
    JMenuItem pwdUpdate = new JMenuItem("密码修改(M)", KeyEvent.VK_M);
    JPanel pan = new JPanel(),
            panCA = new JPanel(),
            panFD = new JPanel(),
            panRate = new JPanel(),
            panBack = new JPanel();
    JButton btnCA = new JButton("维护活期储户"),
            btnFD = new JButton("维护定期储户"),
            btnRate = new JButton("维护利率"),
            btnBack = new JButton("返回");
    JComboBox cbRoles = new JComboBox();
    int bankId;

    public ManageOption(int id) {
        bankId = id;
        //组件
        info.setFont(font);
        pwdUpdate.setFont(font);
        info.add(pwdUpdate);
        mb.add(info);
        cbRoles.setFont(new Font("黑体", Font.PLAIN, 14));
        btnCA.setFont(new Font("黑体", Font.PLAIN, 14));
        btnFD.setFont(new Font("黑体", Font.PLAIN, 14));
        btnRate.setFont(new Font("黑体", Font.PLAIN, 14));
        btnBack.setFont(new Font("黑体", Font.PLAIN, 14));
        btnCA.setPreferredSize(new Dimension(150, 55));
        btnFD.setPreferredSize(new Dimension(150, 55));
        btnRate.setPreferredSize(new Dimension(150, 55));
        btnBack.setPreferredSize(new Dimension(150, 55));
        panCA.add(btnCA);
        panFD.add(btnFD);
        panRate.add(btnRate);
        panBack.add(btnBack);
        pan.add(panCA);
        pan.add(panFD);
        pan.add(panRate);
        pan.add(panBack);
        pan.setLayout(new GridLayout(4, 1));
        frame.setJMenuBar(mb);//?
        frame.add(pan);
        frame.setSize(400, 320);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //活期维护按钮监测
        btnCA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentAccountManage(e);
            }
        });

        //定期维护按钮监测
        btnFD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FixedFepositManage(e);
            }
        });

        //利率维护按钮监测
        btnRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RateManage(e);
            }
        });

        //返回按钮监测
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });

        //管理员信息更改按钮监测
        pwdUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManagerUpdate(e);
            }
        });

    }

    //活期响应
    void CurrentAccountManage(ActionEvent e) {
        ManageCurrentAccount d = new ManageCurrentAccount(bankId);
        frame.setVisible(false);
    }

    //定期响应
    void FixedFepositManage(ActionEvent e) {
        ManageFixedDeposit D = new ManageFixedDeposit(bankId);
        frame.setVisible(false);
    }

    //利率响应
    void RateManage(ActionEvent e) {
        ManageInterestRate L = new ManageInterestRate(bankId);
        frame.setVisible(false);
    }

    //维护管理员密码响应
    void ManagerUpdate(ActionEvent e) {
        ManagerPasswordUpdate l = new ManagerPasswordUpdate(bankId);
    }

    //返回响应
    void goBack(ActionEvent e) {
        FunctionOption f = new FunctionOption(bankId);
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}

class ManagerPasswordUpdate {
    /**
     * 管理员密码修改界面
     */
    private static final long serialVersionUID = -7899228102358391474L;


    JFrame frame = new JFrame("管理员密码修改");
    JPanel pan = new JPanel(),
            panAccount = new JPanel(),
            panNewpwd = new JPanel(),
            panRepeat = new JPanel(),
            panButton = new JPanel();
    JButton btnEnter = new JButton("确认"),
            btnBack = new JButton("返回");
    JLabel accountLab = new JLabel("账号:"),
            newpwdLab = new JLabel("新的密码:"),
            repeatLab = new JLabel("再次输入:");
    JComboBox cbRoles = new JComboBox();
    JTextField password = new JTextField("", 20),//旧密码
            newpassword = new JTextField("", 20),//新密码第一次
            pwdrepeat = new JTextField("", 20);//新密码第二次
    int bankId;

    public ManagerPasswordUpdate(int id) {

        bankId = id;
        //组件
        JLabel account = new JLabel(String.valueOf(bankId));
        cbRoles.setFont(new Font("黑体", Font.PLAIN, 20));
        btnEnter.setFont(new Font("黑体", Font.PLAIN, 20));
        btnBack.setFont(new Font("黑体", Font.PLAIN, 20));
        accountLab.setFont(new Font("黑体", Font.PLAIN, 20));
        newpwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        repeatLab.setFont(new Font("黑体", Font.PLAIN, 20));
        account.setFont(new Font("黑体", Font.PLAIN, 20));
        password.setPreferredSize(new Dimension(200, 30));
        newpassword.setPreferredSize(new Dimension(200, 30));
        pwdrepeat.setPreferredSize(new Dimension(200, 30));
        panAccount.add(accountLab);
        panAccount.add(account);
        panNewpwd.add(newpwdLab);
        panNewpwd.add(newpassword);
        panRepeat.add(repeatLab);
        panRepeat.add(pwdrepeat);
        pan.add(panAccount);
        pan.add(panNewpwd);
        pan.add(panRepeat);
        btnEnter.setPreferredSize(new Dimension(100, 45));
        btnBack.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnEnter);
        panButton.add(btnBack);
        pan.add(panButton);
        pan.setLayout(new GridLayout(4, 1));
        frame.add(pan);
        frame.setSize(540, 360);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //确认按钮监测
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePassword(e);
            }
        });

        //确认按钮监测
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    //确认修改密码响应
    void changePassword(ActionEvent e) {
        try {
            int username = bankId;//账户
            String n1 = newpassword.getText();//密码
            String n2 = pwdrepeat.getText();

            if (n1.equals(n2)) {
                if (BankDAO.updateBank(bankId, n1))  //对比管理员的账号密码
                {
                    JOptionPane.showMessageDialog(null, "修改成功"); //修改成功
                } else JOptionPane.showMessageDialog(null, "密码或用户名错误");
            } else {
                JOptionPane.showMessageDialog(null, "两次新密码不一致");
            }
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "密码不合法");
        }
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }

}

//菜单栏
class FgMenu extends JMenu {
    public FgMenu(String label) {
        super(label);
    }

    public FgMenu(String label, int nAccelerator) {
        super(label);
        setMnemonic(nAccelerator);
    }
}
