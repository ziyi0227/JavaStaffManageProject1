import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SignupFrame extends JFrame {
    public SignupFrame(){
        super("\u5458\u5de5\u5de5\u8d44\u7ba1\u7406\u7cfb\u7edf \u6ce8\u518c");
        InitFrame();
    }

    private void InitFrame() {
        // 设置窗体大小和位置
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}