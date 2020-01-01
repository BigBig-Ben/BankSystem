package GUI;

import DAO.BankDAO;
import Entity.Interest;

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


public class ManageInterestRate {
    /**
     * 管理员—利率查询界面
     */
    JFrame frame = new JFrame("管理员——利率查询");
    JLabel rateLab = new JLabel("利率号：");
    JTextField rateId = new JTextField("", 20);//活期用户查询用的id
    JButton btnQuery = new JButton("查询"),
            btnBack = new JButton("返回"),
            btnUpdate = new JButton("修改");
    JPanel panRate = new JPanel(),
            updatePan = new JPanel();
    JTable table = new JTable();
    JPanel pan = new JPanel();
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    Font font1 = new Font("黑体", Font.PLAIN, 30);
    int bankId;

    ManageInterestRate(int id) {
        bankId = id;
        //组件
        rateLab.setFont(font1);
        rateId.setFont(font1);
        btnQuery.setFont(font1);
        btnUpdate.setFont(font1);
        btnBack.setFont(font1);
        panRate.add(rateLab);
        panRate.add(rateId);
        panRate.add(btnQuery);
        updatePan.add(btnUpdate);
        updatePan.add(btnBack);
        panRate.setLayout(new GridLayout(1, 3));
        tableModel.addColumn("利率号");
        tableModel.addColumn("利率类型");
        tableModel.addColumn("利率");
        tableModel.addColumn("利率时长");
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
        JTableHeader head = table.getTableHeader(); // 创建表格标题对象
        head.setPreferredSize(new Dimension(head.getWidth(), 35));// 设置表头大小
        head.setFont(font1);// 设置表格字体
        JScrollPane sp = new JScrollPane(table);
        pan.add(panRate);
        pan.add(sp);
        pan.add(updatePan);
        pan.setSize(1080, 600);

        //载入表格数据
        List<Interest> in = BankDAO.getInterestRates();
        tableModel.setRowCount(0);
        for (int i = 0; i < in.size(); i++) {
            Interest in2 = in.get(i);
            tableModel.addRow(new Object[]{in2.getId(), in2.getType(), in2.getInterestRate(), in2.getMouths()});//通过所属银行查表
        }
        frame.add(pan);
        frame.setSize(1080, 760);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        //查询按钮监测
        btnQuery.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String h_id = rateId.getText();
                    if (h_id.equals("")) {
                        List<Interest> in = BankDAO.getInterestRates();
                        tableModel.setRowCount(0);
                        for (int i = 0; i < in.size(); i++) {
                            Interest in2 = in.get(i);
                            tableModel.addRow(new Object[]{in2.getId(), in2.getType(), in2.getInterestRate(), in2.getMouths()});//通过所属银行查表
                        }
                    } else {
                        tableModel.setRowCount(0);
                        int id = Integer.parseInt(h_id);
                        Interest in3 = BankDAO.getRateById(id);
                        tableModel.addRow(new Object[]{in3.getId(), in3.getType(), in3.getInterestRate(), in3.getMouths()});
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "账号不合法");
                }

            }
        });

		//修改按钮监测
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                String type = table.getValueAt(selectedRow, 1).toString();
                String id = table.getValueAt(selectedRow, 0).toString();
                InterestRateUpdate gg = new InterestRateUpdate(type, id);
            }
        });

        //返回按钮监测
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageOption F = new ManageOption(bankId);
                frame.setVisible(false);
            }
        });
    }
}


class InterestRateUpdate extends JFrame {
    private Font font = new Font("黑体", Font.PLAIN, 20);
    private Font font1 = new Font("黑体", Font.PLAIN, 24);
    String type;
    String rid;
    int ridInt;

    InterestRateUpdate(String _type, String id) {
        type = _type;
        rid = id;
        ridInt = Integer.parseInt(rid);
        this.setTitle("利率修改");
        //组件
        JLabel  ratetypeLab = new JLabel("利率类型"),
                rateLab = new JLabel("利率"),
                repeatLab = new JLabel("二次输入");
        JTextField  typeTF = new JTextField(type),
                	newRate = new JTextField("", 7);
        typeTF.setEditable(false);
        JButton btnOK = new JButton("保存"),
                btnCancel = new JButton("取消");
        ratetypeLab.setFont(font1);
        rateLab.setFont(font1);
        repeatLab.setFont(font1);
        typeTF.setFont(font1);
        newRate.setFont(font1);
        btnOK.setFont(font);
        btnCancel.setFont(font);
        JPanel  panRate = new JPanel(),
                panBtn = new JPanel(),
                pan = new JPanel();

        panRate.setLayout(new GridLayout(3, 2));
        panRate.add(ratetypeLab);
        panRate.add(typeTF);
        panRate.add(rateLab);
        panRate.add(newRate);
        panBtn.add(btnOK);
        panBtn.add(btnCancel);
        pan.add(panRate);
        pan.add(panBtn);
        this.add(pan);
        setSize(275, 200);
        centerWindow(this);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

        //修改按钮监测
        btnOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Interest in4 = BankDAO.getRateById(ridInt);
                String key = newRate.getText();
                System.out.println(Double.valueOf(key.toString()) + "   " + ridInt);
                in4.setInterestRate(Double.valueOf(key.toString()));//修改利率  利率类型为type 新利率为pw_2.getText()
                BankDAO.updateRate(ridInt, Double.valueOf(key.toString()));
                JOptionPane.showMessageDialog(null, "修改成功！");

                dispose();
            }
        });

        //取消按钮监测
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
