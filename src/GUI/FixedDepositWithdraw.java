package GUI;

import DAO.BankDAO;
import DAO.FixedDepositDAO;
import Entity.FixedDeposit;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class FixedDepositWithdraw {
    /**
     * 定期取款界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    JFrame frame = new JFrame("定期取款");
    JPanel pan = new JPanel(),
            panId = new JPanel(),
            panPwd = new JPanel(),
            panButton = new JPanel();
    JButton btnOk = new JButton("确认");
    JButton btnCancel = new JButton("返回");
    JLabel depositIdLab = new JLabel("单号：");
    JLabel pwdLab = new JLabel("密码：");
    JTextField depositId = new JTextField("", 30);//单号
    JTextField password = new JTextField("", 30);//密码
    int bankId;

    public FixedDepositWithdraw(int id) {
        bankId = id;
        //组件
        btnOk.setFont(new Font("黑体", Font.PLAIN, 20));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 20));
        depositIdLab.setFont(new Font("黑体", Font.PLAIN, 30));
        pwdLab.setFont(new Font("黑体", Font.PLAIN, 30));
        panId.add(depositIdLab);
        panId.add(depositId);
        panPwd.add(pwdLab);
        panPwd.add(password);
        pan.add(panId);
        pan.add(panPwd);

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


        btnOk.addActionListener(new ActionListener() {            //取款按钮监测
            public void actionPerformed(ActionEvent e) {
                withdraw(e);
            }
        });

        btnCancel.addActionListener(new ActionListener() {            //返回按钮监测
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
    }

    //取款响应
    void withdraw(ActionEvent e) {
        try {
            int depositId = Integer.parseInt(this.depositId.getText());
            FixedDeposit fd = FixedDepositDAO.getDepositById(depositId);
            int r = JOptionPane.showConfirmDialog(null, "确认取出？", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                if (fd == null) {
                    //单号错误
                    JOptionPane.showMessageDialog(null, "单号错误，为查询到相应定期存款！");
                } else if(password.getText().length()==0)
                {
                    //未输入密码情况
                    //验证存款时候是否输入密码
                    if(fd.getPassword().equals("111111"))
                    {
                        if (fd.getDepositBank() != bankId) {
                            //非本行存款
                            JOptionPane.showMessageDialog(null, "非本行定期存款！");
                        } else if (fd.getDueDate().after(new Date())) {
                            //取款时间未到
                            JOptionPane.showMessageDialog(null, "未到取款时间！");
                        } else {
                            //数据库减去款项
                            FixedDepositDAO.deleteDeposit(fd);
                            //打印利息清单
                            File interestFile = new File("利息清单.txt");
                            FileOutputStream interestOutput = new FileOutputStream(interestFile);
                            String interestStr = "存款存单： " + depositId + "\n";
                            interestStr += "存款人：   " + fd.getName() + "\n";
                            interestStr += "存款行：   " + BankDAO.getBankById(bankId).getName() + "\n";
                            interestStr += "存款日期： " + fd.getDepositDate() + "\n";
                            interestStr += "定期时长： " + BankDAO.getRateById(fd.getType()).getType() + "\n";
                            interestStr += "存款数额： " + fd.getAmount() + "\n";
                            interestStr += "利率：     " + fd.getInterestRate() * 100 + "%\n";
                            double interest = BankDAO.getRateById(fd.getType()).getMouths()*fd.getInterestRate()*fd.getAmount()/12d;
                            interestStr += "利息：     " + interest+"\n";
                            byte[] bytes = interestStr.getBytes();
                            interestOutput.write(bytes);
                            interestOutput.close();
                            System.out.println(interestStr);
                            JOptionPane.showMessageDialog(null, "取款成功！");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "您设置过密码，请输入正确密码！");
                    }

                }else{
                    //有输入密码
                    //密码正确
                    if(fd.getPassword().equals(password.getText()))
                    {
                        if (fd.getDepositBank() != bankId) {
                            //非本行存款
                            JOptionPane.showMessageDialog(null, "非本行定期存款！");
                        } else if (fd.getDueDate().after(new Date())) {
                            //取款时间未到
                            JOptionPane.showMessageDialog(null, "未到取款时间！");
                        } else {
                            //数据库减去款项
                            FixedDepositDAO.deleteDeposit(fd);
                            /*
                             * 打印利息清单
                             * */
                            JOptionPane.showMessageDialog(null, "取款成功！");
                        }
                    }else{
                        //密码错误
                        JOptionPane.showMessageDialog(null, "密码错误，请输入正确密码！");
                    }
                }

            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "请输入正确格式的单号和密码");
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
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
