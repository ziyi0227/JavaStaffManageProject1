import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//主界面
class MainFrame extends JFrame implements ActionListener {
    private JMenuBar mb = new JMenuBar();
    private JMenu m_system = new JMenu("系统管理");
    private JMenu m_gongzi = new JMenu("工资管理");
    private JMenuItem mI[] = {new JMenuItem("密码重置"), new JMenuItem("退出系统")};
    private JMenuItem m_FMEdit = new JMenuItem("工资编辑");
    private JLabel l_ps, l_empid, l_fromdate, l_todate;
    private JTextField t_empid, t_fromdate, t_todate;
    private JButton b_byempID, b_bydate, b_all;
    private JPanel p_condition, p_detail;
    private JTable table;

    public MainFrame(String username) throws Exception {
        super(username + "欢迎使用企业员工工资管理系统!");
        //连接数据库
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://123.60.142.8:3306/hov";
        String sqlUsername = "hov";
        String password = "NX6f8baShtXnFEKj";
        Connection conn = DriverManager.getConnection(url,sqlUsername,password);
        Container c = this.getContentPane();

        c.setLayout(new BorderLayout());
        c.add(mb, BorderLayout.NORTH);
        mb.add(m_system);
        mb.add(m_gongzi);
        m_system.add(mI[0]);
        m_system.add(mI[1]);
        m_gongzi.add(m_FMEdit);
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
        Statement stmt = conn.createStatement();
        ViewStaff viewStaff = new ViewStaff(username,stmt);



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


        } else if (temp == m_FMEdit) {
            SalaryEditFrame bef = new SalaryEditFrame();
        } else if (temp == b_byempID)  //根据员工工号查询
        {
            //添加代码


        } else if (temp == b_all)  //查询所有员工工资
        {
            //添加代码


        } else if (temp == b_bydate)     //根据起始时间来查询
        {
            //添加代码

        }
    }
}
