import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//��¼����
class LoginFrame extends JFrame implements ActionListener {
    private JLabel l_user, l_pwd;  //�û�����ǩ�������ǩ
    private JTextField t_user; //�û����ı���
    private JPasswordField t_pwd; //�����ı���
    private JButton b_ok, b_cancel; //��¼��ť���˳���ť

    public LoginFrame() {
        super("��ӭʹ�ù��ʹ���ϵͳ!");
        l_user = new JLabel("�û�����", JLabel.RIGHT);
        l_pwd = new JLabel(" ���룺", JLabel.RIGHT);
        t_user = new JTextField(31);
        t_pwd = new JPasswordField(31);
        b_ok = new JButton("��¼");
        b_cancel = new JButton("�˳�");
        //���ַ�ʽFlowLayout��һ����������һ��
        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());
        c.add(l_user);
        c.add(t_user);
        c.add(l_pwd);
        c.add(t_pwd);
        c.add(b_ok);
        c.add(b_cancel);
        //Ϊ��ť��Ӽ����¼�
        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);
        //�����С���ɵ���
        this.setResizable(false);
        this.setSize(455, 150);
        //������ʾ����
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (b_cancel == e.getSource()) {
            //����˳�����
            System.exit(1);
        } else if (b_ok == e.getSource()) {
            //��Ӵ��룬��֤��ݳɹ�����ʾ������
            try {
                new MainFrame(t_user.getText().trim());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
