import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

//修改密码界面
class ModifyPwdFrame extends JFrame implements ActionListener {
    private JLabel l_oldPWD, l_newPWD, l_newPWDAgain;
    private JPasswordField t_oldPWD, t_newPWD, t_newPWDAgain;
    private JButton b_ok, b_cancel;

    public ModifyPwdFrame() {
        super("修改密码");
        l_oldPWD = new JLabel("  旧密码:          ");
        l_newPWD = new JLabel("   新密码：      ");
        l_newPWDAgain = new JLabel("确认新密码：");
        t_oldPWD = new JPasswordField(15);
        t_newPWD = new JPasswordField(15);
        t_newPWDAgain = new JPasswordField(15);
        b_ok = new JButton("确定");
        b_cancel = new JButton("取消");
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
        } else if (b_ok == e.getSource())        //修改密码
        {
            String oldPwd = new String(t_oldPWD.getPassword());
            String newPwd = new String(t_newPWD.getPassword());
            String newPwdAgain = new String(t_newPWDAgain.getPassword());

            //检查输入的旧密码是否正确
            if (!oldPwd.equals(LoginFrame.password)) {
                JOptionPane.showMessageDialog(null, "旧密码输入错误，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
                t_oldPWD.setText("");
                t_oldPWD.requestFocus();
                return;
            }

            //检查两次输入的新密码是否一致
            if (!newPwd.equals(newPwdAgain)) {
                JOptionPane.showMessageDialog(null, "两次输入的新密码不一致，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
                t_newPWD.setText("");
                t_newPWDAgain.setText("");
                t_newPWD.requestFocus();
                return;
            }

            //更新用户密码
            String sql = "UPDATE loginInfo SET upw=? WHERE uid=?";
            try {
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sql);
                pstmt.setString(1, newPwd);
                pstmt.setString(2, LoginFrame.username);
                int result = pstmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "密码修改成功，请重新登录！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose(); //关闭修改密码窗口
                    LoginFrame.showLoginFrame(); //重新显示登录窗口
                } else {
                    JOptionPane.showMessageDialog(null, "密码修改失败，请稍后再试！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Error message: " + ex.getMessage());
            }
        }
    }
}
