package GUI;

import DAO.FixedDepositDAO;
import Entity.FixedDeposit;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ManageFixedDeposit {
    /**
     * 管理员定期用户查询界面
     */
    JFrame frame = new JFrame("管理员——定期用户查询");
    JLabel depositIdLab = new JLabel("定期单号：");
    JTextField depositId = new JTextField("", 20);
    JButton btnQuery = new JButton("查询"),
            btnBack = new JButton("返回"),
            btnUpdate = new JButton("修改");
    JPanel  panDeposit = new JPanel(),
            panUpdate = new JPanel();
    JTable table = new JTable();
    JPanel pan = new JPanel();
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    Font font1 = new Font("黑体", Font.PLAIN, 30);
    int bankId;

    ManageFixedDeposit(int id) {
        bankId = id;
        //组件
        depositIdLab.setFont(font1);
        depositId.setFont(font1);
        btnQuery.setFont(font1);
        btnUpdate.setFont(font1);
        btnBack.setFont(font1);
        panDeposit.add(depositIdLab);
        panDeposit.add(depositId);
        panDeposit.add(btnQuery);
        panUpdate.add(btnUpdate);
        panUpdate.add(btnBack);
        panDeposit.setLayout(new GridLayout(1, 3));
        tableModel.addColumn("姓名");
        tableModel.addColumn("单号");
        tableModel.addColumn("电话");
        tableModel.addColumn("到期日期");
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
        JTableHeader head = table.getTableHeader();                         // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));  // 设置表头大小
        head.setFont(font1);                                                // 设置表格字体
        JScrollPane sp = new JScrollPane(table);
        pan.add(panDeposit);
        pan.add(sp);
        pan.add(panUpdate);
        pan.setSize(1080, 600);

        //载入相应数据
        List<FixedDeposit> f = FixedDepositDAO.getAllDeposit();
        tableModel.setRowCount(0);
        for (int i = 0; i < f.size(); i++) {
            FixedDeposit f1 = f.get(i);
            tableModel.addRow(new Object[]{f1.getName(), f1.getId(), f1.getPhoneNumber(), f1.getDueDate(), f1.getIdNumber(), f1.getAmount()});
        }//查表
        //this.validate();
        frame.add(pan);
        frame.setSize(1080, 760);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //查询按钮
        btnQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String h_id = depositId.getText();
                    if (h_id.equals("")) {
                        List<FixedDeposit> f2 = FixedDepositDAO.getAllDeposit();
                        tableModel.setRowCount(0);
                        for (int i = 0; i < f.size(); i++) {
                            FixedDeposit f1 = f.get(i);

                            tableModel.addRow(new Object[]{f1.getName(), f1.getId(), f1.getPhoneNumber(), f1.getDueDate(), f1.getIdNumber(), f1.getAmount()});
                        }//查表
                    } else {
                        tableModel.setRowCount(0);
                        int id = Integer.parseInt(h_id);
                        FixedDeposit f2 = FixedDepositDAO.getDepositById(id);
                        //通过h_id活期用户账号查表

                        tableModel.addRow(new Object[]{f2.getName(), f2.getId(), f2.getPhoneNumber(), f2.getDueDate(), f2.getIdNumber(), f2.getAmount()});
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "账号不合法");
                }
            }
        });

        //修改按钮
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                int id = Integer.parseInt(table.getValueAt(selectedRow, 1).toString());

                ManagingFixedDepositLogin gg = new ManagingFixedDepositLogin(id);
            }
        });

        //返回按钮
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageOption F = new ManageOption(bankId);
                frame.setVisible(false);
            }
        });
    }
}


class ManagingFixedDepositLogin {
    /**
     * 修改-登录界面
     */
    private static final long serialVersionUID = -7899228102358391474L;

    JFrame frame = new JFrame("修改——储户登录");
    JPanel  pan = new JPanel(),
            panCount = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();
    JButton btnEnter = new JButton("登录"),
            btnBack = new JButton("返回");
    JLabel  accountLab = new JLabel("账号："),
            pwdLab = new JLabel("密码：");

    JTextField password = new JTextField("", 20);//密码
    int id;

    ManagingFixedDepositLogin(int uid) {
        id = uid;
        //组件
        JLabel count = new JLabel(String.valueOf(id));//账户号
        btnEnter.setFont(new Font("黑体", Font.PLAIN, 20));
        btnBack.setFont(new Font("黑体", Font.PLAIN, 20));
        accountLab.setFont(new Font("黑体", Font.PLAIN, 20));
        pwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        count.setFont(new Font("黑体", Font.PLAIN, 20));
        password.setFont(new Font("黑体", Font.PLAIN, 20));
        panCount.add(accountLab);
        panCount.add(count);
        panPwd.add(pwdLab);
        panPwd.add(password);
        pan.add(panCount);
        pan.add(panPwd);
        btnEnter.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnEnter);
        btnBack.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnBack);
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
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
    }

    //登陆响应
    void login(ActionEvent e) {
        int username = id;
        String key = password.getText();
        //System.out.println(role+","+username+","+key);
        FixedDeposit f2 = FixedDepositDAO.getDepositById(username);
        //通过h_id活期用户账号查表
        if (f2.getPassword().equals(""))//定期密码为空
        {
            FixedDepositPasswordUpdate gg = new FixedDepositPasswordUpdate(id, key);//传给下一界面账号
            frame.setVisible(false);
        } else {
            if (f2.getPassword().equals(key))  //对比定期用户的账号密码
            {
                FixedDepositPasswordUpdate gg = new FixedDepositPasswordUpdate(id, key);//传给下一界面账号
                //System.out.println("login success");
                frame.setVisible(false);

            } else JOptionPane.showMessageDialog(null, "密码或用户名错误");
        }
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}


class FixedDepositPasswordUpdate extends JFrame {
    private Font font = new Font("黑体", Font.PLAIN, 20);
    private Font font1 = new Font("黑体", Font.PLAIN, 24);
    int id;
    String password;

    FixedDepositPasswordUpdate(int uid, String key) {
        id = uid;
        password = key;
        //组件
        this.setTitle("密码修改");
        JLabel accountLab = new JLabel("账号"),
                newPwdLab = new JLabel("新密码"),
                repeatLab = new JLabel("二次输入");
        JTextField id_1 = new JTextField(String.valueOf(id)),
                pw_1 = new JTextField("", 20),
                pw_2 = new JTextField("", 20);
        id_1.setEditable(false);
        JButton btnOK = new JButton("保存"),
                btnCancel = new JButton("取消");
        accountLab.setFont(font1);
        newPwdLab.setFont(font1);
        repeatLab.setFont(font1);
        id_1.setFont(font1);
        pw_1.setFont(font1);
        pw_2.setFont(font1);
        btnOK.setFont(font);
        btnCancel.setFont(font);
        JPanel panI = new JPanel(),
                panBtn = new JPanel(),
                pan = new JPanel();

        panI.setLayout(new GridLayout(4, 2));
        panI.add(accountLab);
        panI.add(id_1);
        panI.add(newPwdLab);
        panI.add(pw_1);
        panI.add(repeatLab);
        panI.add(pw_2);
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
                if (pw_1.getText().equals(pw_2.getText())) {
                    FixedDeposit f4 = FixedDepositDAO.getDepositById(id);

                    f4.setPassword(pw_1.getText());//修改密码 储户账号为id 新密码为pw_2.getText()
                    if (FixedDepositDAO.updateDeposit(f4)) {
                        JOptionPane.showMessageDialog(null, "修改成功！");
                    } else JOptionPane.showMessageDialog(null, "修改失败！");
                } else JOptionPane.showMessageDialog(null, "两次密码不一致");
                dispose();
            }
        });

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
