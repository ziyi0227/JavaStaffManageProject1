import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.*;

//主界面
class MainFrame extends JFrame implements ActionListener {
    private JMenuBar mb = new JMenuBar();
    private JMenu m_system = new JMenu("系统管理");
    private JMenu m_gongzi = new JMenu("工资管理");
    private JMenu m_bangzhu=new JMenu("帮助");
    private JMenuItem helpMenuItem=new JMenuItem("查看帮助");
    private JMenuItem mI[] = {new JMenuItem("密码重置"), new JMenuItem("退出系统")};
    private JMenuItem m_FMEdit = new JMenuItem("工资编辑");
    private JLabel l_ps, l_empid, l_fromdate, l_todate;
    private JTextField t_empid, t_fromdate, t_todate;
    private JButton b_byempID, b_bydate, b_all;
    private JPanel p_condition, p_detail;
    private JTable table;

    public MainFrame(String username) throws Exception {
        super(username + " 欢迎使用企业员工工资管理系统!");
//        //连接数据库
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        String url = "jdbc:mysql://123.60.142.8:3306/hov";
//        String sqlUsername = "hov";
//        String password = "NX6f8baShtXnFEKj";
//        Connection conn = DriverManager.getConnection(url,sqlUsername,password);

        Container c = this.getContentPane();

        c.setLayout(new BorderLayout());
        c.add(mb, BorderLayout.NORTH);
        mb.add(m_system);
        mb.add(m_gongzi);
        mb.add(m_bangzhu);
        m_system.add(mI[0]);
        m_system.add(mI[1]);
        m_gongzi.add(m_FMEdit);
        m_bangzhu.add(helpMenuItem);
        helpMenuItem.addActionListener(e -> {
            JLabel label = new JLabel("<html>" +
                    "                      Editer Version 1.0  TIME:2023/5/21<br>" +
                    "                              作者：郑景元 陈黄未 徐程<br>" +
                    "GitHub开源 ：请访问我们的网站：<a href=\"https://github.com/ziyi0227/JavaStaffManageProject1\" style=\"color: blue; text-decoration: underline;\">https://github.com/ziyi0227/JavaStaffManageProject1</a><br>" +
                    "实现功能：<br>" +
                    "1.注册登录<br>" +
                    "2.找回密码<br>" +
                    "3.修改密码<br>" +
                    "4.显示所有员工工资<br>" +
                    "5.根据时间查询<br>" +
                    "6.发送邮箱验证码<br>" +
                    "功能使用介绍：<br>" +
                    "本程序含系统管理与工资管理两部分<br>" +
                    "1.在工资管理中：使用者在工资编辑中可使用录入、修改、删除、查询、清空等功能，方便得将公司员工的基本信息（姓名、工号、工资、津贴）以表格呈现，功能强大格式优美。<br>" +
                    "2.在系统管理中：使用者可使用密码重置与退出系统功能，方便使用者操作<br>" +
                    "3.在主界面：使用者可根据提示信息查询<br>" +
                    "</html>");

            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() > 0) {
                        try {
                            Desktop.getDesktop().browse(new URI("https://github.com/ziyi0227/JavaStaffManageProject1"));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            JOptionPane.showMessageDialog(this, label, "帮助", JOptionPane.INFORMATION_MESSAGE);
        });
        m_FMEdit.addActionListener(this);
        mI[0].addActionListener(this);
        mI[1].addActionListener(this);

        l_ps = new JLabel("注意：时间格式均为(年月)，例如：201502");
        l_empid = new JLabel("员工工号");
        t_empid = new JTextField(8);
        l_fromdate = new JLabel("起始时间");
        l_todate = new JLabel("终止时间");
        t_fromdate = new JTextField(8);
        t_todate = new JTextField(8);
        b_byempID = new JButton("查询");
        b_all = new JButton("查询所有");
        b_bydate = new JButton("查询");

        p_condition = new JPanel();
        p_condition.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("输入查询条件"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        p_condition.setLayout(new GridLayout(3, 1));
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        p1.add(l_empid);
        p1.add(t_empid);
        p1.add(b_byempID);
        p1.add(b_all);
        p2.add(l_fromdate);
        p2.add(t_fromdate);
        p2.add(l_todate);
        p2.add(t_todate);
        p2.add(b_bydate);
        p3.add(l_ps);
        p_condition.add(p1);
        p_condition.add(p2);
        p_condition.add(p3);
        c.add(p_condition, BorderLayout.CENTER);

        //添加3个按钮的监听事件
        t_empid.addActionListener(this);
        t_fromdate.addActionListener(this);
        t_todate.addActionListener(this);
        b_byempID.addActionListener(this);
        b_all.addActionListener(this);
        b_bydate.addActionListener(this);

        p_detail = new JPanel();
        p_detail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("工资明细信息"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] cloum = {"年月信息", "员工号", "姓名", "津贴", "月基本工资", "月薪"};
        Object[][] row = new Object[50][6];
        table = new JTable(row, cloum);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(580, 350));
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setViewportView(table);
        p_detail.add(scrollpane);

        c.add(p_detail, BorderLayout.SOUTH);


        //添加代码，将所有员工工资信息显示在界面表格中
        String sql = "SELECT * FROM salary";
        Statement stmt = SalaryManager.conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        int row1 = 0;
        while (rs.next()) {
            table.setValueAt(rs.getString("paydate"), row1, 0);
            table.setValueAt(rs.getString("sid"), row1, 1);
            table.setValueAt(rs.getString("sname"), row1, 2);
            table.setValueAt(rs.getDouble("gongzi"), row1, 3);
            table.setValueAt(rs.getDouble("jintie"), row1, 4);
            table.setValueAt(rs.getDouble("gongzi")+rs.getDouble("jintie"),row1,5);
            row1++;
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(true);
        this.setSize(600, 620);
        Dimension screen = this.getToolkit().getScreenSize();
        this.setLocation((screen.width - this.getSize().width) / 2, (screen.height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Object temp = e.getSource();
        if (temp == mI[0]) {
            ModifyPwdFrame mf = new ModifyPwdFrame();
        } else if (temp == mI[1]) {
            //添加退出系统的代码
            System.exit(0);

        } else if (temp == m_FMEdit) {
            SalaryEditFrame bef = new SalaryEditFrame();
        } else if (temp == b_byempID)  //根据员工工号查询
        {
            String empID = t_empid.getText().trim();
            if (empID.equals("")){
                JOptionPane.showMessageDialog(this,"请输入员工工号",
                        "提示",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for(int i = 0; i < table.getRowCount(); i++){
                for(int j = 0; j < table.getColumnCount(); j++){
                    table.setValueAt("", i, j);
                }
            }
            String sql = "SELECT * FROM salary WHERE sid = '" + empID + "'";
            try {
                Statement stmt = SalaryManager.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int row = 0;
                while (rs.next()){
                    table.setValueAt(rs.getString("paydate"), row, 0);
                    table.setValueAt(rs.getString("sid"), row, 1);
                    table.setValueAt(rs.getString("sname"), row, 2);
                    table.setValueAt(rs.getDouble("gongzi"), row, 3);
                    table.setValueAt(rs.getDouble("jintie"), row, 4);
                    table.setValueAt(rs.getDouble("gongzi")+rs.getDouble("jintie"),row,5);
                    row++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (temp == b_all)  //查询所有员工工资
        {
            String sql = "SELECT * FROM salary";
            Statement stmt = null;
            try {
                stmt = SalaryManager.conn.createStatement();
                ResultSet rs = null;
                rs = stmt.executeQuery(sql);
                int row1 = 0;
                while (rs.next()) {
                    table.setValueAt(rs.getString("paydate"), row1, 0);
                    table.setValueAt(rs.getString("sid"), row1, 1);
                    table.setValueAt(rs.getString("sname"), row1, 2);
                    table.setValueAt(rs.getDouble("gongzi"), row1, 3);
                    table.setValueAt(rs.getDouble("jintie"), row1, 4);
                    table.setValueAt(rs.getDouble("gongzi")+rs.getDouble("jintie"),row1,5);
                    row1++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (temp == b_bydate)     //根据起始时间来查询
        {
            String date1 = t_fromdate.getText().trim();
            String date2 = t_todate.getText().trim();
            if (date1.equals("") || date2.equals("")){
                JOptionPane.showMessageDialog(this,"请输入查询时间",
                        "提示",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for(int i = 0; i < table.getRowCount(); i++){
                for(int j = 0; j < table.getColumnCount(); j++){
                    table.setValueAt("", i, j);
                }
            }
            String sql = "SELECT * FROM salary WHERE paydate BETWEEN '" + date1 +
                    "'and'" +date2 + "'";
            try {
                Statement stmt = SalaryManager.conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int row = 0;
                while (rs.next()){
                    table.setValueAt(rs.getString("paydate"), row, 0);
                    table.setValueAt(rs.getString("sid"), row, 1);
                    table.setValueAt(rs.getString("sname"), row, 2);
                    table.setValueAt(rs.getDouble("gongzi"), row, 3);
                    table.setValueAt(rs.getDouble("jintie"), row, 4);
                    table.setValueAt(rs.getDouble("gongzi")+rs.getDouble("jintie"),row,5);
                    row++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
