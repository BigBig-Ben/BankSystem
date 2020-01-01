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


public class FunctionOption {
    /**
     * 功能选择界面
     */
    private static final long serialVersionUID = -7899228102358391474L;


    JFrame frame = new JFrame("银行储蓄系统——功能选择");
    JPanel pan = new JPanel(),

    panButton = new JPanel(),
            panButton2 = new JPanel(),
            panButton3 = new JPanel();
    JButton enter = new JButton("活期操作"), enter2 = new JButton("定期操作"), enter3 = new JButton("维护");

    JComboBox cbRoles = new JComboBox();
    int id;

    public FunctionOption(int bankId) {

        id = bankId;
        cbRoles.setFont(new Font("黑体", Font.PLAIN, 20));
        enter.setFont(new Font("黑体", Font.PLAIN, 14));
        enter2.setFont(new Font("黑体", Font.PLAIN, 14));
        enter3.setFont(new Font("黑体", Font.PLAIN, 14));

        enter.setPreferredSize(new Dimension(100, 45));
        enter2.setPreferredSize(new Dimension(100, 45));
        enter3.setPreferredSize(new Dimension(100, 45));
        panButton.add(enter);
        panButton2.add(enter2);
        panButton3.add(enter3);
        pan.add(panButton);
        pan.add(panButton2);
        pan.add(panButton3);
        pan.setLayout(new GridLayout(3, 1));
        frame.add(pan);
        frame.setSize(400, 260);
        //frame.setSize(1080, 760);
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);


        enter.addActionListener(new ActionListener() {            //活期按钮监测
            public void actionPerformed(ActionEvent e) {
                f(e);
            }
        });

        enter2.addActionListener(new ActionListener() {            //定期按钮监测
            public void actionPerformed(ActionEvent e) {
                f2(e);
            }
        });

        enter3.addActionListener(new ActionListener() {            //维护按钮监测
            public void actionPerformed(ActionEvent e) {
                f3(e);
            }
        });
    }

    //活期响应
    void f(ActionEvent e) {
        CurrentAccountLogin login = new CurrentAccountLogin(id);
        frame.setVisible(false);
    }

    //定期响应
    void f2(ActionEvent e) {
        FixedDepositOption fixed = new FixedDepositOption(id);
        frame.setVisible(false);
    }

    //维护响应
    void f3(ActionEvent e) {
        ManageOption manage = new ManageOption(id);
        frame.setVisible(false);

    }

    void centerWindow(Window f) {
        Toolkit tk = f.getToolkit();
        Dimension dm = tk.getScreenSize();
        f.setLocation((int) (dm.getWidth() - f.getWidth()) / 2, (int) (dm.getHeight() - f.getHeight()) / 2);    //�ô��ھ�����ʾ
    }
}
