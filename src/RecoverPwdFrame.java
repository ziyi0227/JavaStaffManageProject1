import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 找回密码界面
public class RecoverPwdFrame extends JFrame implements ActionListener {
    private JLabel l_user, l_email, l_Pwd, l_PwdAgain;
    private JTextField t_user, t_email;
    private JPasswordField t_Pwd, t_PwdAgain;
    private JButton b_ok, b_cancel;
    JLabel l_verificationCode = new JLabel("\u9a8c\u8bc1\u7801\uff1a");
    JTextField t_verificationCode = new JTextField();
    JButton b_sendCode = new JButton("\u53d1\u9001");
    private int correctCode;
    private Timer timer;
    private int countdown;
    public RecoverPwdFrame (){
        super("\u627e\u56de\u5bc6\u7801");
        l_user = new JLabel("\u7528\u6237\u540d\uff1a\u0020\u0020\u0020\u0020\u0020\u0020\u0020");
        l_email = new JLabel("\u90ae\u7bb1\uff1a\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020\u0020");
        l_Pwd = new JLabel("\u65b0\u5bc6\u7801\uff1a\u0020\u0020\u0020\u0020\u0020\u0020\u0020");
        l_PwdAgain = new JLabel("\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
        t_user = new JTextField(15);
        t_email = new JTextField(15);
        t_Pwd = new JPasswordField(15);
        t_PwdAgain = new JPasswordField(15);
        b_ok = new JButton("\u786e\u5b9a");
        b_cancel = new JButton("\u53d6\u6d88");




        this.setSize(700, 440);
        this.setLocationRelativeTo((Component)null);
        this.setResizable(false);
        //this.setVisible(true);
        Container contentPane = this.getContentPane();
        contentPane.setLayout((LayoutManager)null);

        ImageIcon icon = new ImageIcon("..\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        JLabel jLabel = new JLabel(icon);
        jLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        this.getContentPane().add(jLabel);
        this.repaint();
        this.setVisible(true);

        //Container c = this.getContentPane();
        //c.setLayout(new FlowLayout(FlowLayout.CENTER,20,10));



        //c.add(l_user);
        //c.add(t_user);
        //c.add(l_email);
        //c.add(t_email);
        //c.add(l_Pwd);
        //c.add(t_Pwd);
        //c.add(l_PwdAgain);
        //c.add(t_PwdAgain);
        //c.add(b_ok);
        //c.add(b_cancel);

        contentPane.add(l_user);
        contentPane.add(t_user);
        contentPane.add(l_email);
        contentPane.add(t_email);
        contentPane.add(l_Pwd);
        contentPane.add(t_Pwd);
        contentPane.add(l_PwdAgain);
        contentPane.add(t_PwdAgain);
        contentPane.add(b_ok);
        contentPane.add(b_cancel);
        contentPane.add(this.l_verificationCode);
        contentPane.add(this.t_verificationCode);
        contentPane.add(this.b_sendCode);

        this.l_user.setBounds(380, 60, 80, 30);
        this.t_user.setBounds(470, 60, 180, 30);
        this.l_email.setBounds(380, 110, 80, 30);
        this.t_email.setBounds(470, 110, 180, 30);
        this.l_Pwd.setBounds(380, 160, 120, 30);
        this.t_Pwd.setBounds(470, 160, 180, 30);
        this.l_PwdAgain.setBounds(380, 210, 120, 30);
        this.t_PwdAgain.setBounds(470, 210, 180, 30);
        this.l_verificationCode.setBounds(380, 260, 80, 30);
        this.t_verificationCode.setBounds(470, 260, 110, 30);
        this.b_sendCode.setBounds(590, 260, 60, 30);
        this.b_ok.setBounds(560, 320, 60, 30);
        this.b_cancel.setBounds(460, 320, 60, 30);

        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);

        //this.setResizable(false);
        //this.setSize(280, 200);
        //Dimension screen = this.getToolkit().getScreenSize();
        //this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setVisible(true);

        //this.b_register.addActionListener(this);
        this.b_sendCode.addActionListener(this);
        this.timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                --RecoverPwdFrame.this.countdown;
                if (RecoverPwdFrame.this.countdown == 0) {
                    RecoverPwdFrame.this.resetVerificationCode();
                    RecoverPwdFrame.this.timer.stop();
                }

            }
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.b_sendCode) {
            this.sendVerificationCode();
        }
        if (b_cancel == e.getSource()) {

            this.dispose();
        } else if (b_ok == e.getSource())        //修改密码
        {

            String username = t_user.getText().trim();
            String email = t_email.getText().trim();
            String password = new String(t_Pwd.getPassword());
            String passwordAgain = new String(t_PwdAgain.getPassword());

            // 判断密码格式是否正确
            String regex = "^[a-zA-Z]\\w{5,15}$";
            if (!password.matches(regex)) {
                JOptionPane.showMessageDialog(this, "\u5bc6\u7801\u4e0d\u7b26\u5408\u8981\u6c42\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff086-16\u4e2a\u5b57\u7b26\uff0c\u4e0d\u80fd\u4ee5\u6570\u5b57\u548c\u4e0b\u5212\u7ebf\u5f00\u5934\uff09", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 判断两次密码是否一致
            if (!password.equals(passwordAgain)){
                JOptionPane.showMessageDialog(this,"\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165","\u63d0\u793a",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            //判断验证码是否正确
            String code = this.t_verificationCode.getText().trim();
            if (!this.judgeVerificationCode(code)) {
                JOptionPane.showMessageDialog(this, "验证码错误", "提示", 1);
                return;}
            // 调用修改密码方法
            boolean result = modifyPassword(username, email, password);
            if (result) {
                JOptionPane.showMessageDialog(this, "\u5bc6\u7801\u4fee\u6539\u6210\u529f\uff01", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "\u7528\u6237\u540d\u6216\u90ae\u7bb1\u4e0d\u6b63\u786e\uff01", "\u9519\u8bef", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public boolean modifyPassword(String username, String email, String password){
        try {
            // 查询信息，匹配信息
            String sql = "SELECT * FROM loginInfo WHERE uid=? AND email=?";
            PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,email);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()){
                return false;
            }

            // 更新密码
            sql = "UPDATE loginInfo SET upw=? WHERE uid=?";
            pstmt = SalaryManager.conn.prepareStatement(sql);
            pstmt.setString(1,password);
            pstmt.setString(2,username);
            int row = pstmt.executeUpdate();
            if (row == 1){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void sendVerificationCode() {
        String email = this.t_email.getText().trim();
        this.correctCode = EmailSender.sendMail(email);
        this.countdown = 60;
        this.timer.start();
    }

    private void resetVerificationCode() {
        this.t_verificationCode.setText("");
    }

    public boolean judgeVerificationCode(String code) {
        String correctCode = "" + this.correctCode;
        return correctCode.equals(code);
    }
}
