import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

//登录界面
class LoginFrame extends JFrame implements ActionListener {

    public static String password = "";
    public static String username = "";

    // 创建并添加组件
    JLabel l_user = new JLabel("用户名：");
    JTextField t_user = new JTextField();
    JLabel l_pass = new JLabel("密码：");
    JPasswordField t_pass = new JPasswordField();
    JButton b_signup = new JButton("注册");
    JButton b_login = new JButton("登录");
    UnderlineButton b_find = new UnderlineButton("找回密码");
    //JButton b_modify = new JButton("修改密码");


    public LoginFrame() {
        super("员工工资管理系统 登录");

        //窗体的基本设置
        InitFrame();

        //初始化图片
        InitImage();

        //为按钮添加监听事件
        b_signup.addActionListener(this);
        b_login.addActionListener(this);
        //b_modify.addActionListener(this);
        b_find.addActionListener(this);

        this.setVisible(true);
    }

    private void InitFrame() {
        // 设置窗体大小和位置
        this.setSize(700, 440);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // 获取内容面板，并使用 null 布局管理器
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
        Color color = new Color(0, 60, 255); // 蓝色
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

        // 设置窗体可见性
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

    //按钮监听功能实现
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (b_signup == source) {
            //注册界面
            new SignupFrame();
            dispose();
        } else if (b_login == source) {
            //添加代码，验证身份成功后显示主界面
            username = t_user.getText().trim();
            password = new String(t_pass.getPassword());
            try {
                // 使用 JDBC 验证用户身份
                String sql = "SELECT * FROM loginInfo WHERE uid=? AND upw=?";
                PreparedStatement stmt = SalaryManager.conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // 身份验证成功，关闭登录窗口，打开主窗口
                    dispose(); // 关闭登录窗口
                    new MainFrame(username); // 打开主窗口
                } else {
                    // 身份验证失败，弹出提示
                    JOptionPane.showMessageDialog(this, "用户名或密码错误，请重试！", "身份验证失败", JOptionPane.ERROR_MESSAGE);
                    t_pass.setText(""); // 清空密码框
                    t_pass.requestFocus(); // 让密码框获取焦点
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//        } else if (b_modify == source) {
//            new ModifyPwdFrame();
        } else if (b_find == source) {
            //找回密码界面
            RecoverPwdFrame rpf = new RecoverPwdFrame();
        }
    }

    // 显示登录窗口
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
