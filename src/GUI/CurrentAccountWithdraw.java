package GUI;

import DAO.CurrentAccountDAO;
import Entity.CurrentAccount;

import java.awt.Dimension;
import java.awt.Font;
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


public class CurrentAccountWithdraw {
    /**
     * 取款界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    JFrame frame = new JFrame("活期取款");
    JPanel pan = new JPanel(),
            panCount = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();
    JButton btnOk = new JButton("确认"),
            btnCancel = new JButton("返回");
    JLabel countLab = new JLabel("取款额：");
    JTextField count = new JTextField("", 20);//取款额
    int accountId, bankId;

    public CurrentAccountWithdraw(int username, int id) {
        bankId = id;
        accountId = username;
        //组件
        btnOk.setFont(new Font("黑体", Font.PLAIN, 20));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 20));
        countLab.setFont(new Font("黑体", Font.PLAIN, 20));
        panCount.add(countLab);
        panCount.add(count);
        pan.add(panCount);
        pan.add(panPwd);

        btnOk.setPreferredSize(new Dimension(100, 45));
        btnCancel.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnOk);
        panButton.add(btnCancel);
        pan.add(panButton);
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //取款按钮监测
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                withdraw(e);
            }
        });

        //返回按钮监测
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancel(e);
            }
        });
    }

    //取款响应
    void withdraw(ActionEvent e) {
        try {
            int amount = Integer.parseInt(count.getText());
            int r = JOptionPane.showConfirmDialog(null, "确认取出：" + count.getText() + "!", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                //数据库减去取款额
                CurrentAccount ca = CurrentAccountDAO.getAccountById(accountId);
                //先判断余额是否充足
                if (ca.getAmount() < amount) {
                    JOptionPane.showMessageDialog(null, "账户余额不足！");
                } else {
                    if (CurrentAccountDAO.withdraw(accountId, amount))
                        JOptionPane.showMessageDialog(null, "取款成功！");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "请输入正确的取款额！");
        }
    }

    //返回响应
    void cancel(ActionEvent e) {
        CurrentAccountOption d = new CurrentAccountOption(accountId, bankId);//返回上一界面
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
