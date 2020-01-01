package GUI;

import DAO.BankDAO;
import DAO.FixedDepositDAO;
import Entity.Bank;
import Entity.FixedDeposit;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FixedDepositDetail {
    /**
     * 查询存款细节界面
     */
    private static final long serialVersionUID = -7899228102358391474L;

    JFrame frame = new JFrame("余额查询");
    JPanel  pan = new JPanel(),
            panAmount = new JPanel(),
            panDueDate = new JPanel(),
            panRate = new JPanel(),
            panBank = new JPanel(),
            panButton = new JPanel();
    JButton btnBack = new JButton("返回");
    JLabel amountLab = new JLabel("起存金额：" );
    JLabel dueLab = new JLabel("到期日期：" );
    JLabel rateLab = new JLabel("利率：" );
    JLabel bankLab = new JLabel("存款行");

    int bankid, depositid;//id是接收的账号，op是定期单号

    public FixedDepositDetail(int bankId, int depositId)
    {
        bankid = bankId;
        depositid = depositId;
        //从后台获取存款信息
		FixedDeposit fd = FixedDepositDAO.getDepositById(depositid);
		int amount = fd.getAmount();
		double rate = fd.getInterestRate();
		Date dueDate = fd.getDueDate();
		amountLab.setText("起存金额："+String.valueOf(amount));
		rateLab.setText("到期日期："+String.valueOf(rate));
		dueLab.setText("利率："+dueDate.toString());
		//存款行信息
		Bank bank = BankDAO.getBankById(fd.getDepositBank());
        bankLab.setText("存款行"+bank.getName());
        //组件
        btnBack.setFont(new Font("黑体", Font.PLAIN, 20));
        amountLab.setFont(new Font("黑体", Font.PLAIN, 20));
        dueLab.setFont(new Font("黑体", Font.PLAIN, 20));
        rateLab.setFont(new Font("黑体", Font.PLAIN, 20));
        bankLab.setFont(new Font("黑体", Font.PLAIN, 20));
        panAmount.add(amountLab);
        panDueDate.add(dueLab);
        panRate.add(rateLab);
        panBank.add(bankLab);
        pan.add(panAmount);
        pan.add(panDueDate);
        pan.add(panRate);
        pan.add(panBank);
        btnBack.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnBack);
        pan.add(panButton);
        pan.setLayout(new GridLayout(4, 1));
        frame.add(pan);

        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        btnBack.addActionListener(new ActionListener() {            //返回按钮监测
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
    }

    //返回响应
    void goBack(ActionEvent e) {
        FixedDepositQuery d = new FixedDepositQuery(bankid);
        frame.setVisible(false);
    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);
    }
}
