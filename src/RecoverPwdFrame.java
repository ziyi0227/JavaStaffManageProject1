import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// 找回密码界面
public class RecoverPwdFrame extends JFrame implements ActionListener {
    private JLabel l_user, l_email, l_Pwd, l_PwdAgain;
    private JTextField t_user, t_email;
    private JPasswordField t_Pwd, t_PwdAgain;
    private JButton b_ok, b_cancel;
    public RecoverPwdFrame (){
        super("\u627e\u56de\u5bc6\u7801");
        l_user = new JLabel("\u7528\u6237\u540d\uff1a");
        l_email = new JLabel("\u90ae\u7bb1\uff1a");
        l_Pwd = new JLabel("\u65b0\u5bc6\u7801\uff1a");
        l_PwdAgain = new JLabel("\u786e\u8ba4\u65b0\u5bc6\u7801\uff1a");
        t_user = new JTextField(15);
        t_email = new JTextField(15);
        t_Pwd = new JPasswordField(15);
        t_PwdAgain = new JPasswordField(15);
        b_ok = new JButton("\u786e\u5b9a");
        b_cancel = new JButton("\u53d6\u6d88");

        Container c = this.getContentPane();
        c.setLayout(new FlowLayout());

        c.add(l_user);
        c.add(t_user);
        c.add(l_email);
        c.add(t_email);
        c.add(l_Pwd);
        c.add(t_Pwd);
        c.add(l_PwdAgain);
        c.add(t_PwdAgain);
        c.add(b_ok);
        c.add(b_cancel);

        b_ok.addActionListener(this);
        b_cancel.addActionListener(this);

        this.setResizable(false);
        this.setSize(280, 200);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

            // 调用修改密码方法
            boolean result = modifyPassword(username, email, password);
            if (result) {
                JOptionPane.showMessageDialog(this, "密码修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或邮箱不正确！", "错误", JOptionPane.ERROR_MESSAGE);
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
}
