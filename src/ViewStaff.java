import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewStaff extends MainFrame{
    Statement stmt;
    public ViewStaff(String username, Statement stmt) throws Exception {
        super(username);
        this.stmt = stmt;
    }

    public void getViewStaff() throws SQLException {
        ResultSet resultSet = stmt.executeQuery("select paydate, sid, sname, gongzi, jintie from salary");
        while (resultSet.next()){
            System.out.println(resultSet.getString("paydate"));

        }
    }
}
