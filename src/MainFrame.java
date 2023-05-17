import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//������
class MainFrame extends JFrame implements ActionListener {
    private JMenuBar mb = new JMenuBar();
    private JMenu m_system = new JMenu("ϵͳ����");
    private JMenu m_gongzi = new JMenu("���ʹ���");
    private JMenuItem mI[] = {new JMenuItem("��������"), new JMenuItem("�˳�ϵͳ")};
    private JMenuItem m_FMEdit = new JMenuItem("���ʱ༭");
    private JLabel l_ps, l_empid, l_fromdate, l_todate;
    private JTextField t_empid, t_fromdate, t_todate;
    private JButton b_byempID, b_bydate, b_all;
    private JPanel p_condition, p_detail;
    private JTable table;

    public MainFrame(String username) throws Exception {
        super(username + " ��ӭʹ����ҵԱ�����ʹ���ϵͳ!");
//        //�������ݿ�
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
        m_system.add(mI[0]);
        m_system.add(mI[1]);
        m_gongzi.add(m_FMEdit);
        m_FMEdit.addActionListener(this);
        mI[0].addActionListener(this);
        mI[1].addActionListener(this);

        l_ps = new JLabel("ע�⣺ʱ���ʽ��Ϊ(����)�����磺201502");
        l_empid = new JLabel("Ա������");
        t_empid = new JTextField(8);
        l_fromdate = new JLabel("��ʼʱ��");
        l_todate = new JLabel("��ֹʱ��");
        t_fromdate = new JTextField(8);
        t_todate = new JTextField(8);
        b_byempID = new JButton("��ѯ");
        b_all = new JButton("��ѯ����");
        b_bydate = new JButton("��ѯ");

        p_condition = new JPanel();
        p_condition.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("�����ѯ����"),
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

        //���3����ť�ļ����¼�
        t_empid.addActionListener(this);
        t_fromdate.addActionListener(this);
        t_todate.addActionListener(this);
        b_byempID.addActionListener(this);
        b_all.addActionListener(this);
        b_bydate.addActionListener(this);

        p_detail = new JPanel();
        p_detail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("������ϸ��Ϣ"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] cloum = {"������Ϣ", "Ա����", "����", "����", "�»�������", "��н"};
        Object[][] row = new Object[50][6];
        table = new JTable(row, cloum);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(580, 350));
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setViewportView(table);
        p_detail.add(scrollpane);

        c.add(p_detail, BorderLayout.SOUTH);


        //��Ӵ��룬������Ա��������Ϣ��ʾ�ڽ�������
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
            //����˳�ϵͳ�Ĵ���
            System.exit(0);

        } else if (temp == m_FMEdit) {
            SalaryEditFrame bef = new SalaryEditFrame();
        } else if (temp == b_byempID)  //����Ա�����Ų�ѯ
        {
            String empID = t_empid.getText().trim();
            if (empID.equals("")){
                JOptionPane.showMessageDialog(this,"������Ա������",
                        "��ʾ",JOptionPane.INFORMATION_MESSAGE);
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
                    row++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (temp == b_all)  //��ѯ����Ա������
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
                    row1++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (temp == b_bydate)     //������ʼʱ������ѯ
        {
            String date1 = t_fromdate.getText().trim();
            String date2 = t_todate.getText().trim();
            if (date1.equals("") || date2.equals("")){
                JOptionPane.showMessageDialog(this,"�������ѯʱ��",
                        "��ʾ",JOptionPane.INFORMATION_MESSAGE);
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
                    row++;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
