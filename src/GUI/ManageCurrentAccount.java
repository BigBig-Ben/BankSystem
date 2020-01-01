package GUI;

import DAO.CurrentAccountDAO;
import Entity.CurrentAccount;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class ManageCurrentAccount {
    /**
     * 管理员——活期用户查询界面
     */
    JFrame frame = new JFrame("管理员—活期用户查询");
    JLabel accountLab = new JLabel("储户账号：");
    JTextField accountId = new JTextField("", 20);//活期用户查询用的id
    JButton btnQuery = new JButton("查询"),
            btnNew = new JButton("开户"),
            btnUpdate = new JButton("修改"),
            btnBack = new JButton("返回");
    JPanel panAccount = new JPanel(),
            panBtn = new JPanel();
    JTable table = new JTable();
    JPanel pan = new JPanel();
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    Font font1 = new Font("黑体", Font.PLAIN, 30);
    int bankId;

    ManageCurrentAccount(int bid) {
        bankId = bid;
        //组件
        accountLab.setFont(font1);
        accountId.setFont(font1);
        btnQuery.setFont(font1);
        btnNew.setFont(font1);
        btnUpdate.setFont(font1);
        btnBack.setFont(font1);
        panAccount.add(accountLab);
        panAccount.add(accountId);
        panAccount.add(btnQuery);
        panBtn.add(btnNew);
        panBtn.add(btnUpdate);
        panBtn.add(btnBack);
        panAccount.setLayout(new GridLayout(1, 3));
        tableModel.addColumn("姓名");
        tableModel.addColumn("账号");
        tableModel.addColumn("电话");
        tableModel.addColumn("身份证号");
        tableModel.addColumn("款数");
        table.setRowHeight(50);
        table.setRowMargin(5);
        table.setRowSelectionAllowed(true);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.doLayout();
        table.setBackground(Color.WHITE);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 530));
        table.setFont(font1);
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(font1);// 设置表格字体
        JScrollPane sp = new JScrollPane(table);
        pan.add(panAccount);
        pan.add(sp);
        pan.add(panBtn);
        pan.setSize(1080, 600);
        List<CurrentAccount> current = CurrentAccountDAO.getAllAccount();
        tableModel.setRowCount(0);
        for (int i = 0; i < current.size(); i++) {
            CurrentAccount g = current.get(i);

            tableModel.addRow(new Object[]{g.getName(), g.getId(), g.getPhoneNumber(), g.getIdNumber(), g.getAmount()});
        }//查表

        //this.validate();
        frame.add(pan);
        frame.setSize(1080, 760);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //查询按钮监测
        btnQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String h_id = accountId.getText();
                    if (h_id.equals("")) {
                        List<CurrentAccount> current = CurrentAccountDAO.getAllAccount();
                        tableModel.setRowCount(0);
                        for (int i = 0; i < current.size(); i++) {
                            CurrentAccount g = current.get(i);
                            tableModel.addRow(new Object[]{g.getName(), g.getId(), g.getPhoneNumber(), g.getIdNumber(), g.getAmount()});
                        }//查表
                    } else {
                        tableModel.setRowCount(0);
                        int id = Integer.parseInt(h_id);
                        CurrentAccount c = CurrentAccountDAO.getAccountById(id);
                        //通过h_id活期用户账号查表
                        tableModel.addRow(new Object[]{c.getName(), c.getId(), c.getPhoneNumber(), c.getIdNumber(), c.getAmount()});
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "账号不合法");
                }
            }
        });

        //开户按钮监测
        btnNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrentAccountCreate c = new CurrentAccountCreate();
            }
        });

        //修改按钮监测
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                String id = table.getValueAt(selectedRow, 1).toString();
                ManagingCurrentAccountLogin gg = new ManagingCurrentAccountLogin(id);
            }
        });

        //返回按钮监测
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageOption F = new ManageOption(bankId);
                frame.setVisible(false);
            }
        });
    }
}


class ManagingCurrentAccountLogin {
    /**
     * 修改—储户登录界面
     */
    private static final long serialVersionUID = -7899228102358391474L;

    JFrame frame = new JFrame("修改—储户登录");
    JPanel pan = new JPanel(),
            panAccount = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();
    JButton btnEnter = new JButton("登录"),
            btnBack = new JButton("返回");
    JLabel accountLab = new JLabel("账号："),
            pwdLab = new JLabel("密码：");
    JTextField password = new JTextField("", 20);//密码
    String bankId;

    ManagingCurrentAccountLogin(String uid) {
        bankId = uid;
        //组件
        JLabel count = new JLabel(bankId);//账户号
        btnEnter.setFont(new Font("黑体", Font.PLAIN, 20));
        btnBack.setFont(new Font("黑体", Font.PLAIN, 20));
        accountLab.setFont(new Font("黑体", Font.PLAIN, 20));
        pwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        count.setFont(new Font("黑体", Font.PLAIN, 20));
        password.setFont(new Font("黑体", Font.PLAIN, 20));
        panAccount.add(accountLab);
        panAccount.add(count);
        panPwd.add(pwdLab);
        panPwd.add(password);
        pan.add(panAccount);
        pan.add(panPwd);
        btnEnter.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnEnter);
        btnBack.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnBack);
        pan.add(panButton);
        pan.setLayout(new GridLayout(3, 1));
        frame.add(pan);
        frame.setSize(400, 260);
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
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    //登陆响应
    void login(ActionEvent e) {
        try {
            int username = Integer.parseInt(bankId);
            String key = password.getText();
            CurrentAccount c2 = CurrentAccountDAO.getAccountById(username);
            if (c2.getPassword().equals(key))  //对比活期用户的账号密码
            {
                CurrentAccountPasswordUpdate gg = new CurrentAccountPasswordUpdate(username, key);//传给下一界面账号
            } else JOptionPane.showMessageDialog(null, "密码或用户名错误");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "用户名不合法");
        }
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}


class CurrentAccountPasswordUpdate extends JFrame {
    private Font font = new Font("黑体", Font.PLAIN, 20);
    private Font font1 = new Font("黑体", Font.PLAIN, 24);
    String pwd;
    int id;

    CurrentAccountPasswordUpdate(int uid, String key) {
        id = uid;
        pwd = key;
        this.setTitle("密码修改");
        //组件
        JLabel snoLab = new JLabel("账号"),
                cnoLab = new JLabel("新密码"),
                gradeLab = new JLabel("二次输入");
        JTextField accountId = new JTextField(String.valueOf(id)),
                newpwd = new JTextField("", 20),
                repeat = new JTextField("", 20);
        accountId.setEditable(false);
        JButton btnOK = new JButton("保存"),
                btnCancel = new JButton("取消");
        snoLab.setFont(font1);
        cnoLab.setFont(font1);
        gradeLab.setFont(font1);
        accountId.setFont(font1);
        newpwd.setFont(font1);
        repeat.setFont(font1);
        btnOK.setFont(font);
        btnCancel.setFont(font);
        JPanel panI = new JPanel(),
                panBtn = new JPanel(),
                pan = new JPanel();
        panI.setLayout(new GridLayout(4, 2));
        panI.add(snoLab);
        panI.add(accountId);
        panI.add(cnoLab);
        panI.add(newpwd);
        panI.add(gradeLab);
        panI.add(repeat);
        panBtn.add(btnOK);
        panBtn.add(btnCancel);
        pan.add(panI);
        pan.add(panBtn);
        this.add(pan);
        setSize(540, 300);
        centerWindow(this);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

        //修改按钮监测
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (newpwd.getText().equals(repeat.getText())) {
                    CurrentAccount c3 = CurrentAccountDAO.getAccountById(id);
                    c3.setPassword(newpwd.getText());
                    CurrentAccountDAO.updateAccount(c3);
                    //修改密码 储户账号为id 新密码为pw_2.getText()
                    JOptionPane.showMessageDialog(null, "修改成功！");
                }
                dispose();
            }
        });

        //取消按钮监测
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);
    }
}

class CurrentAccountCreate {
    /**
     * 活期用户注册
     */
    private static final long serialVersionUID = -7899228102358391474L;

    JFrame frame = new JFrame("活期用户注册");
    JPanel pan = new JPanel(),
            panNewpwd = new JPanel(),
            panRepeat = new JPanel(),
            panName = new JPanel(),
            panPhone = new JPanel(),
            panIdenti = new JPanel(),
            panButton = new JPanel();
    JButton btnEnter = new JButton("确认"),
            btnBack = new JButton("返回");
    JLabel newpwdLab = new JLabel("您的密码:"),
            repeatLab = new JLabel("再次输入:"),
            nameLab = new JLabel("您的姓名:"),
            phoneLab = new JLabel("您的电话:"),
            identiLab = new JLabel("身份证号:");
    JComboBox cbRoles = new JComboBox();
    JTextField newpassword = new JTextField("", 20),//新密码第一次
            pwdrepeat = new JTextField("", 20),//新密码第二次
            name = new JTextField("", 20),
            phone = new JTextField("", 20),
            identi = new JTextField("", 20);


    public CurrentAccountCreate() {
        //组件
        cbRoles.setFont(new Font("黑体", Font.PLAIN, 20));
        btnEnter.setFont(new Font("黑体", Font.PLAIN, 20));
        btnBack.setFont(new Font("黑体", Font.PLAIN, 20));
        newpwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        repeatLab.setFont(new Font("黑体", Font.PLAIN, 20));
        nameLab.setFont(new Font("黑体", Font.PLAIN, 20));
        phoneLab.setFont(new Font("黑体", Font.PLAIN, 20));
        identiLab.setFont(new Font("黑体", Font.PLAIN, 20));
        newpassword.setPreferredSize(new Dimension(200, 30));
        pwdrepeat.setPreferredSize(new Dimension(200, 30));
        name.setPreferredSize(new Dimension(200, 30));
        phone.setPreferredSize(new Dimension(200, 30));
        identi.setPreferredSize(new Dimension(200, 30));
        panNewpwd.add(newpwdLab);
        panNewpwd.add(newpassword);
        panRepeat.add(repeatLab);
        panRepeat.add(pwdrepeat);
        panName.add(nameLab);
        panName.add(name);
        panPhone.add(phoneLab);
        panPhone.add(phone);
        panIdenti.add(identiLab);
        panIdenti.add(identi);
        pan.add(panNewpwd);
        pan.add(panRepeat);
        pan.add(panName);
        pan.add(panPhone);
        pan.add(panIdenti);
        btnEnter.setPreferredSize(new Dimension(100, 45));
        btnBack.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnEnter);
        panButton.add(btnBack);
        pan.add(panButton);
        pan.setLayout(new GridLayout(7, 1));
        frame.add(pan);
        frame.setSize(540, 680);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //确认按钮监测
        btnEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Create(e);
            }
        });

        //返回按钮监测
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    //注册响应
    void Create(ActionEvent e) {
        try {
            String n1 = newpassword.getText();//密码
            String n2 = pwdrepeat.getText();
            String Name = name.getText();//姓名
            String Phone = phone.getText();//电话
            String ide = identi.getText();//身份证号

            if (n1.equals(n2)) {
                //生成账号
                long timestamp = System.currentTimeMillis();
                int accountId = (int) (timestamp % 100000000) * 10 + (int) Math.random() * 10;
                CurrentAccount newAcccount = new CurrentAccount();
                newAcccount.setId(accountId);
                newAcccount.setPassword(n1);
                newAcccount.setName(Name);
                newAcccount.setPhoneNumber(Long.parseLong(Phone));
                newAcccount.setIdNumber(ide);
                newAcccount.setAmount(0);
                CurrentAccountDAO.addAccount(newAcccount);
                JOptionPane.showMessageDialog(null, "注册成功，账户为：" + accountId);
                frame.setVisible(false);

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
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);
    }

}