import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

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
            this.dispose();
        } else if (b_ok == e.getSource())        //�޸�����
        {
            String oldPwd = new String(t_oldPWD.getPassword());
            String newPwd = new String(t_newPWD.getPassword());
            String newPwdAgain = new String(t_newPWDAgain.getPassword());

            //�������ľ������Ƿ���ȷ
            if (!oldPwd.equals(LoginFrame.password)) {
                JOptionPane.showMessageDialog(null, "����������������������룡", "����", JOptionPane.ERROR_MESSAGE);
                t_oldPWD.setText("");
                t_oldPWD.requestFocus();
                return;
            }

            //�������������������Ƿ�һ��
            if (!newPwd.equals(newPwdAgain)) {
                JOptionPane.showMessageDialog(null, "��������������벻һ�£����������룡", "����", JOptionPane.ERROR_MESSAGE);
                t_newPWD.setText("");
                t_newPWDAgain.setText("");
                t_newPWD.requestFocus();
                return;
            }

            //�����û�����
            String sql = "UPDATE loginInfo SET upw=? WHERE uid=?";
            try {
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
                pstmt.setString(1, newPwd);
                pstmt.setString(2, LoginFrame.username);
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "�����޸ĳɹ��������µ�¼��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); //�ر��޸����봰��
                    LoginFrame.showLoginFrame(); //������ʾ��¼����
                } else {
                    JOptionPane.showMessageDialog(null, "�����޸�ʧ�ܣ����Ժ����ԣ�", "����", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error message: " + ex.getMessage());
            }
        }
    }
}
