import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//�޸��������
class ModifyPwdFrame extends JFrame implements ActionListener {
    private JLabel l_oldPWD, l_newPWD, l_newPWDAgain;
    private JPasswordField t_oldPWD, t_newPWD, t_newPWDAgain;
    private JButton b_ok, b_cancel;

    public ModifyPwdFrame() {
        super("�޸�����");
        l_oldPWD = new JLabel("  ������:          ");
        l_newPWD = new JLabel("   �����룺      ");
        l_newPWDAgain = new JLabel("ȷ�������룺");
        t_oldPWD = new JPasswordField(15);
        t_newPWD = new JPasswordField(15);
        t_newPWDAgain = new JPasswordField(15);
        b_ok = new JButton("ȷ��");
        b_cancel = new JButton("ȡ��");
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(l_oldPWD);
        c.add(t_oldPWD);
        c.add(l_newPWD);
        c.add(t_newPWD);
        c.add(l_newPWDAgain);
        c.add(t_newPWDAgain);
        c.add(b_ok);
        c.add(b_cancel);

        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);

        this.setResizable(false);
        this.setSize(280, 160);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (b_cancel == e.getSource()) {
            //��Ӵ���

        } else if (b_ok == e.getSource())        //�޸�����
        {
            //��Ӵ���

        }
    }
}
