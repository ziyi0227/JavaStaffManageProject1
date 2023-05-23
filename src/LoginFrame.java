import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

//��¼����
class LoginFrame extends JFrame implements ActionListener {

    public static String password = "";
    public static String username = "";

    // ������������
    JLabel l_user = new JLabel("�û�����");
    JTextField t_user = new JTextField();
    JLabel l_pass = new JLabel("���룺");
    JPasswordField t_pass = new JPasswordField();
    JButton b_signup = new JButton("ע��");
    JButton b_login = new JButton("��¼");
    UnderlineButton b_find = new UnderlineButton("�һ�����");
    //JButton b_modify = new JButton("�޸�����");


    public LoginFrame() {
        super("Ա�����ʹ���ϵͳ ��¼");

        //����Ļ�������
        InitFrame();

        //��ʼ��ͼƬ
        InitImage();

        //Ϊ��ť��Ӽ����¼�
        b_signup.addActionListener(this);
        b_login.addActionListener(this);
        //b_modify.addActionListener(this);
        b_find.addActionListener(this);

        this.setVisible(true);
    }

    private void InitFrame() {
        // ���ô����С��λ��
        this.setSize(700, 440);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // ��ȡ������壬��ʹ�� null ���ֹ�����
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        l_user.setBounds(380, 100, 80, 30);
        t_user.setBounds(470, 100, 180, 30);
        l_pass.setBounds(380, 170, 80, 30);
        t_pass.setBounds(470, 170, 180, 30);
        b_signup.setBounds(410, 260, 80, 30);
        b_login.setBounds(540, 260, 80, 30);
        //b_modify.setBounds(500, 380, 90, 20);
        b_find.setBounds(610, 380, 50, 20);

        b_find.setBorder(null);
        b_find.setOpaque(false);
        b_find.setContentAreaFilled(false);
        Color color = new Color(0, 60, 255); // ��ɫ
        b_find.setForeground(color);
        b_find.setUnderline(true);

        contentPane.add(l_user);
        contentPane.add(t_user);
        contentPane.add(l_pass);
        contentPane.add(t_pass);
        contentPane.add(b_signup);
        contentPane.add(b_login);
        contentPane.add(b_find);
        //contentPane.add(b_modify);

        // ���ô���ɼ���
        this.setVisible(true);
    }

    private void InitImage() {
        //����һ��ͼƬ����
        //ImageIcon icon = new ImageIcon("G:\\.ChenHuangWei\\Develop\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        ImageIcon icon = new ImageIcon("..\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        //����һ��JLable����
        JLabel jLabel = new JLabel(icon);
        //ָ��ͼƬλ��
        jLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        //��ӵ�������
        this.getContentPane().add(jLabel);
        // ����repaint()����ˢ�´���
        this.repaint();
        //ʹͼƬ�ɼ�
        this.setVisible(true);
    }

    //��ť��������ʵ��
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (b_signup == source) {
            //ע�����
            new SignupFrame();
            dispose();
        } else if (b_login == source) {
            //��Ӵ��룬��֤��ݳɹ�����ʾ������
            username = t_user.getText().trim();
            password = new String(t_pass.getPassword());
            try {
                // ʹ�� JDBC ��֤�û����
                String sql = "SELECT * FROM loginInfo WHERE uid=? AND upw=?";
                PreparedStatement stmt = SalaryManager.conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // �����֤�ɹ����رյ�¼���ڣ���������
                    dispose(); // �رյ�¼����
                    new MainFrame(username); // ��������
                } else {
                    // �����֤ʧ�ܣ�������ʾ
                    JOptionPane.showMessageDialog(this, "�û�����������������ԣ�", "�����֤ʧ��", JOptionPane.ERROR_MESSAGE);
                    t_pass.setText(""); // ��������
                    t_pass.requestFocus(); // ��������ȡ����
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//        } else if (b_modify == source) {
//            new ModifyPwdFrame();
        } else if (b_find == source) {
            //�һ��������
            RecoverPwdFrame rpf = new RecoverPwdFrame();
        }
    }

    // ��ʾ��¼����
    public static void showLoginFrame() {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}

class UnderlineButton extends JButton {
    private boolean underline;

    public UnderlineButton(String text) {
        super(text);
        underline = false;
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        super.paintComponent(g2d);
        if (underline) {
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(getText());
            int textHeight = fm.getHeight();
            int textBaseline = fm.getAscent();

            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.setColor(getForeground());
            g2d.drawLine(0, textBaseline + 1, textWidth, textBaseline + 1);
        }
        g2d.dispose();
    }
}
