import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//工资编辑界面
class SalaryEditFrame extends JFrame implements ActionListener {
    private JLabel l_ps1, l_ps2, l_date, l_empID, l_empName, l_gongzi, l_jintie;
    private JTextField t_date, t_empID, t_empName, t_gongzi, t_jintie;
    private JButton b_update, b_delete, b_select, b_new, b_clear;
    private JPanel p1, p2, p3;
    private JScrollPane scrollpane;
    private JTable table;

    public SalaryEditFrame() {
        super("工资管理");
        Container c = this.getContentPane();
        l_date = new JLabel("年月信息", JLabel.CENTER);
        l_empID = new JLabel("员工号", JLabel.CENTER);
        l_empName = new JLabel("员工姓名", JLabel.CENTER);
        l_gongzi = new JLabel("基本工资", JLabel.CENTER);
        l_jintie = new JLabel("津贴", JLabel.CENTER);
        b_new = new JButton("录入");
        b_update = new JButton("修改");
        b_delete = new JButton("删除");
        b_clear = new JButton("清空");
        b_select = new JButton("查询");
        t_date = new JTextField(8);
        t_empID = new JTextField(8);
        t_empName = new JTextField(8);
        t_gongzi = new JTextField(8);
        t_jintie = new JTextField(8);

        c.setLayout(new BorderLayout());
        p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2, 10, 10));
        p1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("编辑工资信息"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        p1.add(l_date);
        p1.add(t_date);
        p1.add(l_empID);
        p1.add(t_empID);
        p1.add(l_empName);
        p1.add(t_empName);
        p1.add(l_gongzi);
        p1.add(t_gongzi);
        p1.add(l_jintie);
        p1.add(t_jintie);

        c.add(p1, BorderLayout.WEST);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(5, 1, 10, 10));
        p2.add(b_new);
        p2.add(b_update);
        p2.add(b_delete);
        p2.add(b_select);
        p2.add(b_clear);
        c.add(p2, BorderLayout.CENTER);


        p3 = new JPanel();
        p3.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("显示工资信息"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] cloum = {"年月信息", "员工号", "姓名", "月基本工资", "津贴", "月薪"};
        Object[][] row = new Object[50][6];
        table = new JTable(row, cloum);
        scrollpane = new JScrollPane(table);
        scrollpane.setViewportView(table);
        p3.add(scrollpane);
        c.add(p3, BorderLayout.EAST);

        b_new.addActionListener(this);
        b_update.addActionListener(this);
        b_delete.addActionListener(this);
        b_select.addActionListener(this);
        b_clear.addActionListener(this);

        //添加代码，为table添加鼠标点击事件监听addMouseListener
        t_date.addActionListener(this);
        t_empID.addActionListener(this);
        t_gongzi.addActionListener(this);
        t_jintie.addActionListener(this);
        t_empName.addActionListener(this);

        this.setResizable(false);
        this.setSize(800, 300);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setVisible(true);

//		this.setBounds(200, 100, 500, 600);
//		this.setResizable(true);// 可以调整界面大小
//		this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (b_select == e.getSource())            //查询所有的工资信息
        {
            for(int i = 0; i < table.getRowCount(); i++){
                for(int j = 0; j < table.getColumnCount(); j++){
                    table.setValueAt("", i, j);
                }
            }
            try {
                StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM salary WHERE 1=1");
                List<Object> params = new ArrayList<>();
                if (!t_date.getText().isEmpty()) {
                    sqlBuilder.append(" AND paydate=?");
                    params.add(t_date.getText());
                }
                if (!t_empID.getText().isEmpty()) {
                    sqlBuilder.append(" AND sid=?");
                    params.add(t_empID.getText());
                }
                if (!t_empName.getText().isEmpty()) {
                    sqlBuilder.append(" AND sname=?");
                    params.add(t_empName.getText());
                }
                if (!t_gongzi.getText().isEmpty()) {
                    try {
                        Double gongzi = Double.parseDouble(t_gongzi.getText());
                        sqlBuilder.append(" AND gongzi=?");
                        params.add(gongzi);
                    } catch (NumberFormatException ex) {
                        // 如果输入的基本工资不是合法的数字，则不添加该条件
                    }
                }
                if (!t_jintie.getText().isEmpty()) {
                    try {
                        Double jintie = Double.parseDouble(t_jintie.getText());
                        sqlBuilder.append(" AND jintie=?");
                        params.add(jintie);
                    } catch (NumberFormatException ex) {
                        // 如果输入的津贴不是合法的数字，则不添加该条件
                    }
                }
                PreparedStatement pstmt = SalaryManager.conn.prepareStatement(sqlBuilder.toString());
                for (int i = 0; i < params.size(); i++){
                    Object param = params.get(i);
                    if (param instanceof String){
                        pstmt.setString(i+1,(String) param);
                    } else if (param instanceof Double) {
                        pstmt.setDouble(i+1,(Double) param);
                    }
                }
                ResultSet rs = pstmt.executeQuery();
                int i = 0;
                while (rs.next()){
                    String date = rs.getString("paydate");
                    String empID = rs.getString("sid");
                    String empName = rs.getString("sname");
                    double gongzi = rs.getDouble("gongzi");
                    double jintie = rs.getDouble("jintie");
                    table.setValueAt(date, i, 0);
                    table.setValueAt(empID, i, 1);
                    table.setValueAt(empName, i, 2);
                    table.setValueAt(gongzi, i, 3);
                    table.setValueAt(jintie, i, 4);
                    table.setValueAt(gongzi+jintie,i,5);
                    i++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        } else if (b_update == e.getSource())        //修改某条工资记录
        {
            //添加代码


        } else if (b_delete == e.getSource())        //删除某条工资记录
        {
            //添加代码


        } else if (b_new == e.getSource())            //添加新的工资记录
        {
            //添加代码

        } else if (b_clear == e.getSource())            //清空输入框
        {
            //添加代码

        }
    }
}
