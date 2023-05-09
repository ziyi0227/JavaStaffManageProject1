import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

//登录界面
class LoginFrame extends JFrame implements ActionListener {

    // 创建并添加组件
    JLabel l_user = new JLabel("用户名：");
    JTextField t_user = new JTextField();
    JLabel l_pass = new JLabel("密码：");
    JPasswordField t_pass = new JPasswordField();
    JButton b_signup = new JButton("注册");
    JButton b_login = new JButton("登录");


    public LoginFrame() {
        super("员工工资管理系统 登录");

        //窗体的基本设置
        InitFrame();

        //初始化图片
        InitImage();

        //为按钮添加监听事件
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

        l_user = new JLabel("用户名：", JLabel.RIGHT);
        l_pwd = new JLabel(" 密码：", JLabel.RIGHT);
        t_user = new JTextField(35);
        t_pwd = new JPasswordField(36);

        Container c = this.getContentPane();
        c.setLayout(null); // 设置内容面板的布局管理器为 null
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
        // 设置窗体大小和位置
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);

        // 获取内容面板，并使用 null 布局管理器
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

        // 设置窗体可见性
        this.setVisible(true);
    }
    private void InitImage() {
        //创建一个图片对象
        ImageIcon icon = new ImageIcon("..\\JavaStaffManageProject1\\Image\\LoginFrameCover.png");
        //创建一个JLable对象
        JLabel jLabel = new JLabel(icon);
        //指定图片位置
        jLabel.setBounds(0, 0, 350, 400);
        //添加到界面中
        this.getContentPane().add(jLabel);
        //使图片可见
        this.setVisible(true);
    }

    //按钮监听功能实现
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (b_signup == source) {
            //注册界面
            new SignupFrame();
        } else if (b_login == source) {
            //添加代码，验证身份成功后显示主界面
            try {
                new MainFrame(t_user.getText().trim());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
