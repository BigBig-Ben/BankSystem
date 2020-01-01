package GUI;

import DAO.CurrentAccountDAO;

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


public class CurrentAccountDeposit {
    /**
     * 活期存款界面
     */
    private static final long serialVersionUID = -7899228102358391474L;


    JFrame frame = new JFrame("活期存款");
    JPanel pan = new JPanel(),
            panCount = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();

    JButton btnOk = new JButton("确认");
    JButton btnCancel = new JButton("返回");
    JLabel countLab = new JLabel("存款额：");


    JTextField count = new JTextField("", 20);//存款额
    int accountId, bankId;

    public CurrentAccountDeposit(int username, int id) {
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
        //pan.setLayout(new GridLayout(2, 1));
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //存款按钮监测
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositConfirm(e);
            }
        });

        //返回按钮监测
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                depositCancel(e);
            }
        });
    }

    //存款响应
    void depositConfirm(ActionEvent e) {
        //存款数额
        try {
            int amount = Integer.parseInt(count.getText());
            int r = JOptionPane.showConfirmDialog(null, "确认存入：" + count.getText() + "!", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                //数据库加上存款额
                if (CurrentAccountDAO.deposit(accountId, amount)) ;
                JOptionPane.showMessageDialog(null, "存款成功！");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "请输入正确的数额");
        }
    }

    void depositCancel(ActionEvent e) {

        CurrentAccountOption d = new CurrentAccountOption(accountId, bankId);//返回上一界面
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
