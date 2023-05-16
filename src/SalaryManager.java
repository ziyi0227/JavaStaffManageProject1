import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SalaryManager {
    public static Connection conn;

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://123.60.142.8:3306/hov";
        String username = "hov";
        String password = "NX6f8baShtXnFEKj";
        conn = DriverManager.getConnection(url, username, password);

        LoginFrame lf = new LoginFrame();

        lf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        lf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}







