import com.mysql.cj.Session;

// import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

public class SignupFrame extends JFrame implements ActionListener{
    JLabel l_newUser = new JLabel("\u7528\u6237\u540d\uff1a");//用户名
    JTextField t_newUser = new JTextField();
    JLabel l_newPassword = new JLabel("\u5bc6\u7801\uff1a");//密码
    JPasswordField t_newPassword = new JPasswordField();
    JLabel l_checkNewPassword = new JLabel("\u518d\u6b21\u8f93\u5165\u5bc6\u7801\uff1a");//再次输入密码
    JPasswordField t_checkNewPassword = new JPasswordField();
    JLabel l_email = new JLabel("\u90ae\u7bb1\uff1a");//再次输入密码
    JTextField t_email = new JTextField();
    JButton b_register = new JButton("\u786e\u8ba4\u6ce8\u518c");//确认注册
    JDialog d_successRegister = new JDialog(this,"\u5458\u5de5\u5de5\u8d44\u7ba1\u7406\u7cfb\u7edf");
    JLabel l_verificationCode = new JLabel("\u9a8c\u8bc1\u7801\uff1a");//验证码
    JTextField t_verificationCode = new JTextField(); // 验证码输入框
    JButton b_sendCode = new JButton("\u53d1\u9001"); // 发送验证码按钮
    private Timer timer; // 计时器
    private int countdown; // 倒计时

    private int correctCode;// 验证码

    public SignupFrame(){
        super("\u5458\u5de5\u5de5\u8d44\u7ba1\u7406\u7cfb\u7edf \u6ce8\u518c");
        InitFrame();
        InitImage();

        b_register.addActionListener(this);
        b_sendCode.addActionListener(this);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countdown--;
                if (countdown == 0) {
                    resetVerificationCode();
                    timer.stop();
                }
            }
        });

        // 添加窗口关闭事件监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                LoginFrame.showLoginFrame(); // 打开登录页面
            }
        });
    }

    private void InitFrame() {
        // 设置窗体大小和位置
        this.setSize(700, 440);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        // 获取内容面板，并使用 null 布局管理器
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        //设置组件大小和位置
        l_newUser.setBounds(380, 60, 80, 30);
        t_newUser.setBounds(470, 60, 180, 30);
        l_newPassword.setBounds(380, 110, 80, 30);
        t_newPassword.setBounds(470, 110, 180, 30);
        l_checkNewPassword.setBounds(380, 160, 120, 30);
        t_checkNewPassword.setBounds(470, 160, 180, 30);
        l_email.setBounds(380, 210, 120, 30);
        t_email.setBounds(470, 210, 180, 30);
        l_verificationCode.setBounds(380,260,80,30);
        t_verificationCode.setBounds(470, 260, 110, 30);
        b_sendCode.setBounds(590, 260, 60, 30);
        b_register.setBounds(490, 320, 100, 30);

        //添加组件
        contentPane.add(l_newUser);
        contentPane.add(l_newPassword);
        contentPane.add(l_checkNewPassword);
        contentPane.add(t_newUser);
        contentPane.add(t_newPassword);
        contentPane.add(t_checkNewPassword);
        contentPane.add(l_email);
        contentPane.add(t_email);
        contentPane.add(b_register);
        contentPane.add(l_verificationCode);
        contentPane.add(t_verificationCode);
        contentPane.add(b_sendCode);

        this.setVisible(true);
    }

    private void InitImage() {
        //创建一个图片对象
        //ImageIcon icon = new ImageIcon("G:\\.ChenHuangWei\\Develop\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        ImageIcon icon = new ImageIcon("..\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        //创建一个JLable对象
        JLabel jLabel = new JLabel(icon);
        //指定图片位置
        jLabel.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
        //添加到界面中
        this.getContentPane().add(jLabel);
        // 调用repaint()方法刷新窗口
        this.repaint();
        //使图片可见
        this.setVisible(true);
    }

    private void showSuccessDialog() {
        disableComponents();
        JOptionPane.showMessageDialog(this, "\u6ce8\u518c\u6210\u529f\uff01", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // 关闭注册页面
        enableComponents();
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }

    private void disableComponents() {
        // 禁用注册界面的所有组件
        t_newUser.setEnabled(false);
        t_newPassword.setEnabled(false);
        t_checkNewPassword.setEnabled(false);
        t_email.setEnabled(false);
        b_register.setEnabled(false);
    }

    private void enableComponents() {
        // 启用注册界面的所有组件
        t_newUser.setEnabled(true);
        t_newPassword.setEnabled(true);
        t_checkNewPassword.setEnabled(true);
        t_email.setEnabled(true);
        b_register.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (e.getSource() == b_sendCode) {
            sendVerificationCode();
        }
        if (source == b_register){
            String newUser = t_newUser.getText().trim();
            String newPassword = new String(t_newPassword.getPassword());
            String checkNewPAssword = new String(t_checkNewPassword.getPassword());
            String email = t_email.getText().trim();

            // 验证用户名与密码格式
            String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
            if (!newUser.matches(regex)) {
                JOptionPane.showMessageDialog(this, "\u7528\u6237\u540d\u4e0d\u7b26\u5408\u8981\u6c42\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff085-16\u4e2a\u5b57\u7b26\uff0c\u4e0d\u80fd\u4ee5\u6570\u5b57\u548c\u4e0b\u5212\u7ebf\u5f00\u5934\uff09", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            regex = "^[a-zA-Z]\\w{5,15}$";
            if (!newPassword.matches(regex)) {
                JOptionPane.showMessageDialog(this, "\u5bc6\u7801\u4e0d\u7b26\u5408\u8981\u6c42\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165\uff086-16\u4e2a\u5b57\u7b26\uff0c\u4e0d\u80fd\u4ee5\u6570\u5b57\u548c\u4e0b\u5212\u7ebf\u5f00\u5934\uff09", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 判断两次密码是否一致
            if (!newPassword.equals(checkNewPAssword)){
                JOptionPane.showMessageDialog(this,"\u4e24\u6b21\u8f93\u5165\u7684\u5bc6\u7801\u4e0d\u4e00\u81f4\uff0c\u8bf7\u91cd\u65b0\u8f93\u5165","\u63d0\u793a",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //判断验证码是否正确
            String code = t_verificationCode.getText().trim();
            if (!judgeVerificationCode(code)) {
                JOptionPane.showMessageDialog(this, "\u9a8c\u8bc1\u7801\u9519\u8bef", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            // 连接数据库，查询用户名是否已经存在
            String sql = "SELECT * FROM loginInfo WHERE uid = ?";
            try {
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
                pstmt.setString(1,newUser);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "\u8be5\u7528\u6237\u540d\u5df2\u7ecf\u88ab\u6ce8\u518c", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "\u6ce8\u518c\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            sql = "INSERT INTO loginInfo(uid,upw,email) VALUES (?,?,?)";
            try {
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
                pstmt.setString(1, newUser);
                pstmt.setString(2, newPassword);
                pstmt.setString(3, email);
                pstmt.executeUpdate();
                showSuccessDialog(); // 显示注册成功提示框
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "\u6ce8\u518c\u5931\u8d25\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5", "\u63d0\u793a", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void sendVerificationCode() {
        String email = t_email.getText().trim();
        correctCode = EmailSender.sendMail(email);

        // 重置计时器和倒计时
        countdown = 60;
        timer.start();
    }

    private void resetVerificationCode() {
        t_verificationCode.setText("");
    }

    public boolean judgeVerificationCode(String code){
        String correctCode = "" + this.correctCode;
        return correctCode.equals(code);
    }
}


