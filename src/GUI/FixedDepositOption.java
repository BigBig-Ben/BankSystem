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


public class FixedDepositOption {
    /**
     * 定期功能选择界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    JFrame frame = new JFrame("定期储蓄系统—功能选择");
    JPanel pan = new JPanel(),
            panDeposit = new JPanel(),
            panWithdraw = new JPanel(),
            panQuery = new JPanel(),
            panCancel = new JPanel();
    JButton btnDeposit = new JButton("存款"),
            btnWithdraw = new JButton("取款"),
            btnQuery = new JButton("定期查询"),
            btnCancel = new JButton("返回");

    JComboBox cbRoles = new JComboBox();
    int bankId;

    public FixedDepositOption(int id) {
        bankId = id;
        //组件
        cbRoles.setFont(new Font("黑体", Font.PLAIN, 20));
        btnDeposit.setFont(new Font("黑体", Font.PLAIN, 14));
        btnWithdraw.setFont(new Font("黑体", Font.PLAIN, 14));
        btnQuery.setFont(new Font("黑体", Font.PLAIN, 14));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 14));
        btnDeposit.setPreferredSize(new Dimension(160, 45));
        btnWithdraw.setPreferredSize(new Dimension(160, 45));
        btnQuery.setPreferredSize(new Dimension(160, 45));
        btnCancel.setPreferredSize(new Dimension(160, 45));

        panDeposit.add(btnDeposit);
        panWithdraw.add(btnWithdraw);
        panQuery.add(btnQuery);
        panCancel.add(btnCancel);
        pan.add(panDeposit);
        pan.add(panWithdraw);
        pan.add(panQuery);
        pan.add(panCancel);
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
                goback(e);
            }
        });
    }

    //存款响应
    void deposit(ActionEvent e) {
        FixedDepositDeposit d = new FixedDepositDeposit(bankId);
        frame.setVisible(false);
    }

    //取款响应
    void withdraw(ActionEvent e) {
        FixedDepositWithdraw d = new FixedDepositWithdraw(bankId);
        frame.setVisible(false);
    }

    //余额利率查询响应
    void query(ActionEvent e) {
        FixedDepositQuery d = new FixedDepositQuery(bankId);
        frame.setVisible(false);
    }

    //返回上级界面
    void goback(ActionEvent e) {
        FunctionOption d = new FunctionOption(bankId);
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
