package GUI;

import DAO.FixedDepositDAO;
import Entity.FixedDeposit;

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


public class FixedDepositQuery {
    /**
     * 定期利率余额查询界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    JFrame frame = new JFrame("定期查询");
    JPanel  pan = new JPanel(),
            panDepositId = new JPanel(),
            panButton = new JPanel();
    JButton btnOk = new JButton("确认"),
            btnCancel = new JButton("返回");
    JLabel numLab = new JLabel("单号：");
    JTextField num = new JTextField("", 20);//单号
    int bankId;

    public FixedDepositQuery(int id) {
        this.bankId = id;
        btnOk.setFont(new Font("黑体", Font.PLAIN, 20));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 20));
        numLab.setFont(new Font("黑体", Font.PLAIN, 20));
        panDepositId.add(numLab);
        panDepositId.add(num);
        pan.add(panDepositId);

        btnOk.setPreferredSize(new Dimension(100, 45));
        btnCancel.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnOk);
        panButton.add(btnCancel);
        pan.add(panButton);
        pan.setLayout(new GridLayout(3, 2));
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //确认按钮监测
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query(e);
            }
        });

        //返回按钮监测
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
    }

    //查询响应
    void query(ActionEvent e) {
        try {
            int r = JOptionPane.showConfirmDialog(null, "确认单号？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                int depositId = Integer.parseInt(num.getText());
                FixedDeposit fd = FixedDepositDAO.getDepositById(depositId);
                if (fd == null) {
                    JOptionPane.showMessageDialog(null, "单号不存在");
                } else {
                    //查询到单号进入下一窗口
                    FixedDepositDetail detail = new FixedDepositDetail(bankId, depositId);
                    frame.setVisible(false);
                }
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "单号不合法，请输入正确单号");
        }
    }

    //返回响应
    void goBack(ActionEvent e) {
        FixedDepositOption d = new FixedDepositOption(bankId);//返回上一界面
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);
    }
}
