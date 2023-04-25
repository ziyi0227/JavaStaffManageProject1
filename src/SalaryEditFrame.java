import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            //添加代码


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
