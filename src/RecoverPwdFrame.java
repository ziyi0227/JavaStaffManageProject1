import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverPwdFrame extends JFrame implements ActionListener {
    private JLabel l_user, l_email, l_Pwd, l_PwdAgain;
    private JTextField t_user, t_email;
    private JPasswordField t_Pwd, t_PwdAgain;
    private JButton b_ok, b_cancel;
    public RecoverPwdFrame (){
        super("找回密码");
        l_user = new JLabel("用户名：");
        l_email = new JLabel("邮箱：");
        l_Pwd = new JLabel("新密码：");
        l_PwdAgain = new JLabel("确认新密码：");
        t_user = new JTextField(15);
        t_email = new JTextField(15);
        t_Pwd = new JPasswordField(15);
        t_PwdAgain = new JPasswordField(15);
        b_ok = new JButton("确定");
        b_cancel = new JButton("取消");

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
            //添加代码

        } else if (b_ok == e.getSource())        //修改密码
        {
            //添加代码

        }
    }
}
