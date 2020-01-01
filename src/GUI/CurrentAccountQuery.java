package GUI;

import DAO.CurrentAccountDAO;

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
import javax.swing.JPanel;


public class CurrentAccountQuery {
    /**
     * 活期查询界面
     */
    private static final long serialVersionUID = -7899228102358391474L;

    JFrame frame = new JFrame("活期账户余额查询");
    JPanel pan = new JPanel(),
            panCount = new JPanel(),
            panButton = new JPanel();
    JButton back = new JButton("返回");
    JLabel countLab = new JLabel();
    int accountId, bankId;

    public CurrentAccountQuery(int username, int id) {
        bankId = id;
        accountId = username;
        //组件
        back.setFont(new Font("黑体", Font.PLAIN, 20));
        countLab.setFont(new Font("黑体", Font.PLAIN, 20));
        panCount.add(countLab);
        pan.add(panCount);
        back.setPreferredSize(new Dimension(100, 45));
        panButton.add(back);
        pan.add(panButton);
        pan.setLayout(new GridLayout(2, 1));
        frame.add(pan);

        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        //查询数据库活期账户的余额
        int amount = CurrentAccountDAO.getAccountById(username).getAmount();
        countLab.setText("您的余额：" + amount + " 元");
        back.addActionListener(new ActionListener() {            //返回按钮监测
            public void actionPerformed(ActionEvent e) {
                r(e);
            }
        });
    }

    //返回响应
    void r(ActionEvent e) {
        CurrentAccountOption d = new CurrentAccountOption(accountId, bankId);//返回上一界面
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
