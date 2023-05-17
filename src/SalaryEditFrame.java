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

//���ʱ༭����
class SalaryEditFrame extends JFrame implements ActionListener {
    private JLabel l_ps1, l_ps2, l_date, l_empID, l_empName, l_gongzi, l_jintie;
    private JTextField t_date, t_empID, t_empName, t_gongzi, t_jintie;
    private JButton b_update, b_delete, b_select, b_new, b_clear;
    private JPanel p1, p2, p3;
    private JScrollPane scrollpane;
    private JTable table;

    public SalaryEditFrame() {
        super("���ʹ���");
        Container c = this.getContentPane();
        l_date = new JLabel("������Ϣ", JLabel.CENTER);
        l_empID = new JLabel("Ա����", JLabel.CENTER);
        l_empName = new JLabel("Ա������", JLabel.CENTER);
        l_gongzi = new JLabel("��������", JLabel.CENTER);
        l_jintie = new JLabel("����", JLabel.CENTER);
        b_new = new JButton("¼��");
        b_update = new JButton("�޸�");
        b_delete = new JButton("ɾ��");
        b_clear = new JButton("���");
        b_select = new JButton("��ѯ");
        t_date = new JTextField(8);
        t_empID = new JTextField(8);
        t_empName = new JTextField(8);
        t_gongzi = new JTextField(8);
        t_jintie = new JTextField(8);

        c.setLayout(new BorderLayout());
        p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2, 10, 10));
        p1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("�༭������Ϣ"),
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
                BorderFactory.createTitledBorder("��ʾ������Ϣ"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        String[] cloum = {"������Ϣ", "Ա����", "����", "�»�������", "����", "��н"};
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

        //��Ӵ��룬Ϊtable���������¼�����addMouseListener
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
//		this.setResizable(true);// ���Ե��������С
//		this.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (b_select == e.getSource())            //��ѯ���еĹ�����Ϣ
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
                        // �������Ļ������ʲ��ǺϷ������֣�����Ӹ�����
                    }
                }
                if (!t_jintie.getText().isEmpty()) {
                    try {
                        Double jintie = Double.parseDouble(t_jintie.getText());
                        sqlBuilder.append(" AND jintie=?");
                        params.add(jintie);
                    } catch (NumberFormatException ex) {
                        // �������Ľ������ǺϷ������֣�����Ӹ�����
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


        } else if (b_update == e.getSource())        //�޸�ĳ�����ʼ�¼
        {
            //��Ӵ���


        } else if (b_delete == e.getSource())        //ɾ��ĳ�����ʼ�¼
        {
            //��Ӵ���


        } else if (b_new == e.getSource())            //����µĹ��ʼ�¼
        {
            //��Ӵ���

        } else if (b_clear == e.getSource())            //��������
        {
            //��Ӵ���

        }
    }
}
