package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class CurrentAccountOption {
    /**
     * 登录界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    JFrame frame = new JFrame("储户系统——功能选择");
    JPanel  pan = new JPanel(),
    		panButton = new JPanel(),
            panButton2 = new JPanel(),
            panButton3 = new JPanel(),
            panButton4 = new JPanel();
    JButton btnDeposit = new JButton("存款"),
            btnWithdraw = new JButton("取款"),
            btnQuery = new JButton("余额查询"),
            btnCancel = new JButton("返回");

    JComboBox cbRoles = new JComboBox();
    int accountId, bankId;

    public CurrentAccountOption(int userid, int id) {
        accountId = userid;
        bankId = id;
        //功能组件
        cbRoles.setFont(new Font("黑体", Font.PLAIN, 20));
        btnDeposit.setFont(new Font("黑体", Font.PLAIN, 14));
        btnWithdraw.setFont(new Font("黑体", Font.PLAIN, 14));
        btnQuery.setFont(new Font("黑体", Font.PLAIN, 14));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 14));
        btnDeposit.setPreferredSize(new Dimension(100, 45));
        btnWithdraw.setPreferredSize(new Dimension(100, 45));
        btnQuery.setPreferredSize(new Dimension(100, 45));
        btnCancel.setPreferredSize(new Dimension(100, 45));

        panButton.add(btnDeposit);
        panButton2.add(btnWithdraw);
        panButton3.add(btnQuery);
        panButton4.add(btnCancel);
        pan.add(panButton);
        pan.add(panButton2);
        pan.add(panButton3);
        pan.add(panButton4);
        pan.setLayout(new GridLayout(4, 1));
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);


        btnDeposit.addActionListener(new ActionListener() {            //存款按钮监测
            public void actionPerformed(ActionEvent e) {
                deposit(e);
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {            //取款按钮监测
            public void actionPerformed(ActionEvent e) {
                withdraw(e);
            }
        });

        btnQuery.addActionListener(new ActionListener() {            //余额查询按钮监测
            public void actionPerformed(ActionEvent e) {
                query(e);
            }
        });

        btnCancel.addActionListener(new ActionListener() {            //返回按钮监测
            public void actionPerformed(ActionEvent e) {
                cancel(e);
            }
        });
    }

    //存款响应
    void deposit(ActionEvent e) {
        CurrentAccountDeposit d = new CurrentAccountDeposit(accountId, bankId);
        frame.setVisible(false);
    }

    //取款响应
    void withdraw(ActionEvent e) {
        CurrentAccountWithdraw d = new CurrentAccountWithdraw(accountId, bankId);
        frame.setVisible(false);
    }

    //余额查询响应
    void query(ActionEvent e) {
        CurrentAccountQuery d = new CurrentAccountQuery(accountId, bankId);
        frame.setVisible(false);
    }

    //返回上一界面
    void cancel(ActionEvent e) {
        FunctionOption d = new FunctionOption(bankId);
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
