import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//��¼����
class LoginFrame extends JFrame implements ActionListener {

    // ������������
    JLabel l_user = new JLabel("�û�����");
    JTextField t_user = new JTextField();
    JLabel l_pass = new JLabel("���룺");
    JPasswordField t_pass = new JPasswordField();
    JButton b_signup = new JButton("ע��");
    JButton b_login = new JButton("��¼");


    public LoginFrame() {
        super("Ա�����ʹ���ϵͳ ��¼");

        //����Ļ�������
        InitFrame();

        //��ʼ��ͼƬ
        InitImage();

        //Ϊ��ť��Ӽ����¼�
        b_signup.addActionListener(this);
        b_login.addActionListener(this);
    }

 /*   private void InitFrame() {
        this.setResizable(false);
        this.setSize(700, 500);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        l_user = new JLabel("�û�����", JLabel.RIGHT);
        l_pwd = new JLabel(" ���룺", JLabel.RIGHT);
        t_user = new JTextField(35);
        t_pwd = new JPasswordField(36);

        Container c = this.getContentPane();
        c.setLayout(null); // �����������Ĳ��ֹ�����Ϊ null
        c.add(l_user);
        c.add(t_user);
        c.add(l_pwd);
        c.add(t_pwd);
        c.add(b_ok);
        c.add(b_register);
        c.add(b_cancel);

        this.setVisible(true);
    }*/

    private void InitFrame() {
        // ���ô����С��λ��
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);

        // ��ȡ������壬��ʹ�� null ���ֹ�����
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        l_user.setBounds(380, 100, 80, 30);
        t_user.setBounds(470, 100, 180, 30);
        l_pass.setBounds(380, 170, 80, 30);
        t_pass.setBounds(470, 170, 180, 30);
        b_signup.setBounds(420, 260, 80, 30);
        b_login.setBounds(550, 260, 80, 30);

        contentPane.add(l_user);
        contentPane.add(t_user);
        contentPane.add(l_pass);
        contentPane.add(t_pass);
        contentPane.add(b_signup);
        contentPane.add(b_login);

        // ���ô���ɼ���
        this.setVisible(true);
    }
    private void InitImage() {
        //����һ��ͼƬ����
        ImageIcon icon = new ImageIcon("..\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        //����һ��JLable����
        JLabel jLabel = new JLabel(icon);
        //ָ��ͼƬλ��
        jLabel.setBounds(0, 0, 350, 400);
        //��ӵ�������
        this.getContentPane().add(jLabel);
        //ʹͼƬ�ɼ�
        this.setVisible(true);
    }

    //��ť��������ʵ��
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (b_signup == source) {
            //ע�����
            new SignupFrame();
        } else if (b_login == source) {
            //��Ӵ��룬��֤��ݳɹ�����ʾ������
            try {
                new MainFrame(t_user.getText().trim());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
