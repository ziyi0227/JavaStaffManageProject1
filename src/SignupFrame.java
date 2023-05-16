import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

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
    JDialog d_successRegister = new JDialog(this,"员工工资管理系统");
    public SignupFrame(){
        super("\u5458\u5de5\u5de5\u8d44\u7ba1\u7406\u7cfb\u7edf \u6ce8\u518c");
        InitFrame();
        InitImage();

        b_register.addActionListener(this);
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

        l_newUser.setBounds(380, 80, 80, 30);
        t_newUser.setBounds(470, 80, 180, 30);
        l_newPassword.setBounds(380, 130, 80, 30);
        t_newPassword.setBounds(470, 130, 180, 30);
        l_checkNewPassword.setBounds(380, 180, 120, 30);
        t_checkNewPassword.setBounds(470, 180, 180, 30);
        l_email.setBounds(380, 230, 120, 30);
        t_email.setBounds(470, 230, 180, 30);
        b_register.setBounds(480, 300, 100, 30);

        contentPane.add(l_newUser);
        contentPane.add(l_newPassword);
        contentPane.add(l_checkNewPassword);
        contentPane.add(t_newUser);
        contentPane.add(t_newPassword);
        contentPane.add(t_checkNewPassword);
        contentPane.add(l_email);
        contentPane.add(t_email);
        contentPane.add(b_register);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b_register){
            String newUser = t_newUser.getText().trim();
            String newPassword = new String(t_newPassword.getPassword());
            String checkNewPAssword = new String(t_checkNewPassword.getPassword());
            String email = t_email.getText().trim();

            // 验证用户名与密码格式
            String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
            if (!newUser.matches(regex)) {
                JOptionPane.showMessageDialog(this, "用户名不符合要求，请重新输入（5-16个字符，不能以数字和下划线开头）", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            regex = "^[a-zA-Z]\\w{5,15}$";
            if (!newPassword.matches(regex)) {
                JOptionPane.showMessageDialog(this, "密码不符合要求，请重新输入（6-16个字符，不能以数字和下划线开头）", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 判断两次密码是否一致
            if (!newPassword.equals(checkNewPAssword)){
                JOptionPane.showMessageDialog(this,"两次输入的密码不一致，请重新输入","提示",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 连接数据库，查询用户名是否已经存在
            String sql = "SELECT * FROM loginInfo WHERE uid = ?";
            try {
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
                pstmt.setString(1,newUser);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    JOptionPane.showMessageDialog(this, "该用户名已经被注册", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "注册失败，请稍后重试", "提示", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            sql = "INSERT INTO loginInfo(uid,upw,email) VALUES (?,?,?)";
            try {
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
                pstmt.setString(1, newUser);
                pstmt.setString(2, newPassword);
                pstmt.setString(3, email);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "注册失败，请稍后重试", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}