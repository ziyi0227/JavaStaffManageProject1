import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SignupFrame extends JFrame {
    public SignupFrame(){
        super("员工工资管理系统 注册");
        InitFrame();
    }

    private void InitFrame() {
        // 设置窗体大小和位置
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}