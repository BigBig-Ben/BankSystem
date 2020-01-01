package GUI;

import DAO.BankDAO;
import DAO.FixedDepositDAO;
import Entity.FixedDeposit;
import Entity.Interest;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.*;


public class FixedDepositDeposit {
    /**
     * 定期存款界面
     */
    private static final long serialVersionUID = -7899228102358391474L;
    JFrame frame = new JFrame("定期存款");
    JPanel pan = new JPanel(),
            panName = new JPanel(),
            panPhone = new JPanel(),
            panUserId = new JPanel(),
            panAmount = new JPanel(),
            panDepositData = new JPanel(),
            panPwd = new JPanel(),
            panDueDate = new JPanel(),
            panButton = new JPanel();

    JButton btnOk = new JButton("确认");
    JButton btnCancel = new JButton("返回");
    JLabel nameLab = new JLabel("姓名：      ");
    JLabel phoneLab = new JLabel("电话：      ");
    JLabel idNumLab = new JLabel("身份证号：   ");
    JLabel amountLab = new JLabel("存款额：     ");
    JLabel depositDateLab = new JLabel("存款日期：(yyyyMMdd)");
    JLabel pwdLab = new JLabel("密码(选填)：   ");
    JLabel typeLab = new JLabel("到期日期：");

    JTextField name = new JTextField("", 18);//姓名
    JTextField phone = new JTextField("", 18);//电话
    JTextField idNum = new JTextField("", 18);//身份证号
    JTextField amount = new JTextField("", 18);//存款额
    JTextField depositDate = new JTextField("", 8);//yyyy-mm-dd 日期格式 存入数据库前做字符拆分  //存款日期
    JTextField password = new JTextField("", 15);
    //存款类型
    JComboBox<String> types = new JComboBox<String>();
    List<Interest> interests = BankDAO.getInterestRates();
    int bankId;

    public FixedDepositDeposit(int id) {
        this.bankId = id;
        //获取利率表
        for (Interest interest : interests) {
            types.addItem(interest.getType());
        }
        //组件
        btnOk.setFont(new Font("黑体", Font.PLAIN, 20));
        btnCancel.setFont(new Font("黑体", Font.PLAIN, 20));
        nameLab.setFont(new Font("黑体", Font.PLAIN, 20));
        phoneLab.setFont(new Font("黑体", Font.PLAIN, 20));
        idNumLab.setFont(new Font("黑体", Font.PLAIN, 20));
        amountLab.setFont(new Font("黑体", Font.PLAIN, 20));
        depositDateLab.setFont(new Font("黑体", Font.PLAIN, 20));
        pwdLab.setFont(new Font("黑体", Font.PLAIN, 20));
        typeLab.setFont(new Font("黑体", Font.PLAIN, 20));
        types.setFont(new Font("黑体", Font.PLAIN, 20));

        panName.add(nameLab);
        panName.add(name);
        panPhone.add(phoneLab);
        panPhone.add(phone);
        panUserId.add(idNumLab);
        panUserId.add(idNum);
        panAmount.add(amountLab);
        panAmount.add(amount);
        panDepositData.add(depositDateLab);
        panDepositData.add(depositDate);
        panPwd.add(pwdLab);
        panPwd.add(password);
        panDueDate.add(typeLab);
        panDueDate.add(types);
        pan.add(panName);
        pan.add(panPhone);
        pan.add(panUserId);
        pan.add(panAmount);
        pan.add(panDepositData);
        pan.add(panPwd);
        pan.add(panDueDate);

        btnOk.setPreferredSize(new Dimension(100, 45));
        btnCancel.setPreferredSize(new Dimension(100, 45));
        panButton.add(btnOk);
        panButton.add(btnCancel);
        pan.add(panButton);
        pan.setLayout(new GridLayout(7, 1));
        frame.add(pan);
        frame.setSize(700, 480);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        btnOk.addActionListener(new ActionListener() {            //存款按钮监测
            public void actionPerformed(ActionEvent e) {
                deposit(e);
            }
        });

        btnCancel.addActionListener(new ActionListener() {            //取消按钮监测
            public void actionPerformed(ActionEvent e) {
                goBack(e);
            }
        });
    }

    //存款响应
    void deposit(ActionEvent e) {
        try {
            FixedDeposit fd = new FixedDeposit();
            fd.setName(name.getText());
            fd.setPhoneNumber(Long.valueOf(phone.getText()));
            fd.setIdNumber(idNum.getText());
            //从选框types中得到相应存款类型、时长和利率信息
            int typeId = 0, mouths = 0;
            double rate = 0;
            for (Interest interest : interests) {
                if (types.getSelectedItem().toString().equals(interest.getType())) {
                    mouths = interest.getMouths();
                    typeId = interest.getId();
                    rate = interest.getInterestRate();
                    break;
                }
            }
            fd.setType(typeId);
            //格式转换出存款时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(depositDate.getText());
            fd.setDepositDate(new java.sql.Date(date.getTime()));
            //格式转换+存款类型选择 得出到期时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, mouths);
            fd.setDueDate(new java.sql.Date(calendar.getTime().getTime()));
            //利率
            fd.setInterestRate(rate);
            //密码
            if (password.getText().length() == 0)
                fd.setPassword("111111");
            else fd.setPassword(password.getText());
            //数额和存款行
            fd.setAmount(Integer.parseInt(amount.getText()));
            fd.setDepositBank(bankId);
            //生成单号
            long timestamp = System.currentTimeMillis();
            int depositId = (int) (timestamp % 100000000) * 10 + (int) Math.random() * 10;
            fd.setId(depositId);
            int r = JOptionPane.showConfirmDialog(null, "确认存入：" + amount.getText() + "!", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                FixedDepositDAO.addDeposit(fd, bankId);
                //提示存款成功，提供单号
                JOptionPane.showMessageDialog(null, "存款成功！\n存单号：" + depositId);
                //打印存款存单
                File file = new File("存款存单.txt");
                FileOutputStream output = new FileOutputStream(file);
                String str = "存款存单： " + depositId + "\n";
                str += "存款人：   " + fd.getName() + "\n";
                str += "存款行：   " + BankDAO.getBankById(bankId).getName() + "\n";
                str += "存款日期： " + fd.getDepositDate() + "\n";
                str += "到期日期： " + fd.getDueDate() + "\n";
                str += "存款数额： " + fd.getAmount() + "\n";
                str += "利率：     " + fd.getInterestRate() * 100 + "%\n";
                byte[] bytes = str.getBytes();
                output.write(bytes);
                output.close();

            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "请输入正确存款信息");
        }
    }

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
