import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            //��Ӵ���


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
