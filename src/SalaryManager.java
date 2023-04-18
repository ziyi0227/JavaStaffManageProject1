import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalaryManager {
        public static void main(String[] args) {
       	    LoginFrame lf=new LoginFrame();
		    lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	    		    
    }
}

//��¼����
class LoginFrame extends JFrame implements ActionListener{
	private JLabel l_user,l_pwd;  //�û�����ǩ�������ǩ
	private JTextField t_user; //�û����ı���
	private JPasswordField t_pwd; //�����ı���
	private JButton b_ok,b_cancel; //��¼��ť���˳���ť
	 
	public LoginFrame(){
		super("��ӭʹ�ù��ʹ���ϵͳ!");
		l_user=new JLabel("�û�����",JLabel.RIGHT);
		l_pwd=new JLabel(" ���룺",JLabel.RIGHT);
		t_user=new JTextField(31);
		t_pwd=new JPasswordField(31);
		b_ok=new JButton("��¼");
		b_cancel=new JButton("�˳�");
		//���ַ�ʽFlowLayout��һ����������һ��
		Container c=this.getContentPane();
		c.setLayout(new FlowLayout()); 
		c.add(l_user);
		c.add(t_user);
		c.add(l_pwd);
		c.add(t_pwd);
		c.add(b_ok);
		c.add(b_cancel);
		//Ϊ��ť��Ӽ����¼�
		b_ok.addActionListener(this);
		b_cancel.addActionListener(this);
        //�����С���ɵ��� 
		this.setResizable(false);
		this.setSize(455,150);
		//������ʾ����
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(b_cancel==e.getSource()){
			//����˳�����

		}else if(b_ok==e.getSource()){
			//��Ӵ��룬��֤��ݳɹ�����ʾ������
			  new MainFrame(t_user.getText().trim());
		}
	}
}

//������
class MainFrame extends JFrame implements ActionListener{
	private JMenuBar mb=new JMenuBar();
	private JMenu m_system=new JMenu("ϵͳ����");
	private JMenu m_gongzi=new JMenu("���ʹ���");
	private JMenuItem mI[]={new JMenuItem("��������"),new JMenuItem("�˳�ϵͳ")};
	private JMenuItem m_FMEdit=new JMenuItem("���ʱ༭");
	private JLabel l_ps,l_empid,l_fromdate,l_todate;  
	private JTextField t_empid,t_fromdate,t_todate; 
	private JButton b_byempID,b_bydate,b_all;
	private JPanel p_condition,p_detail;
	private JTable table;
	
	public MainFrame(String username)
	{
		super(username+"��ӭʹ����ҵԱ�����ʹ���ϵͳ!");
		Container c=this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(mb,BorderLayout.NORTH);
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
		l_fromdate=new JLabel("��ʼʱ��");
		l_todate=new JLabel("��ֹʱ��");
        t_fromdate=new JTextField(8);
       	t_todate=new JTextField(8);
        b_byempID=new JButton("��ѯ");
        b_all=new JButton("��ѯ����");
		b_bydate=new JButton("��ѯ");
				
		p_condition=new JPanel();		
		p_condition.setBorder(BorderFactory.createCompoundBorder(
	    	BorderFactory.createTitledBorder("�����ѯ����"), 
	    	BorderFactory.createEmptyBorder(5,5,5,5)));
	    p_condition.setLayout(new GridLayout(3,1));
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
        c.add(p_condition,BorderLayout.CENTER);
       	
		//���3����ť�ļ����¼�

        p_detail=new JPanel();
        p_detail.setBorder(BorderFactory.createCompoundBorder(
	    BorderFactory.createTitledBorder("������ϸ��Ϣ"), 
	    BorderFactory.createEmptyBorder(5,5,5,5)));
	    
		String[] cloum = { "������Ϣ","Ա����", "����", "����", "�»�������","��н"};
		Object[][] row = new Object[50][6];
		table = new JTable(row, cloum);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(580,350));
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setViewportView(table);
		p_detail.add(scrollpane);

		c.add(p_detail,BorderLayout.SOUTH);
		  
				
		//��Ӵ��룬������Ա��������Ϣ��ʾ�ڽ�������

		

		this.setResizable(true);
		this.setSize(600,620);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
	}
	
	
   public void actionPerformed(ActionEvent e) {
	     Object temp=e.getSource();
	     if(temp==mI[0]){
	    	 ModifyPwdFrame mf=new ModifyPwdFrame();
	     }else if(temp==mI[1]){
	    	 //����˳�ϵͳ�Ĵ���


	     }else if(temp==m_FMEdit){
	    	 SalaryEditFrame bef=new SalaryEditFrame();
	     }else if(temp==b_byempID)  //����Ա�����Ų�ѯ
	     {
	    	 //��Ӵ���

 
	     }
	     else if(temp==b_all)  //��ѯ����Ա������
	     {
	    	  //��Ӵ���


	     }
	     else if(temp==b_bydate)     //������ʼʱ������ѯ
	    {
	    	  //��Ӵ���

	     }
   }
}

//�޸��������
class ModifyPwdFrame extends JFrame implements ActionListener{
	private JLabel l_oldPWD,l_newPWD,l_newPWDAgain;
	private JPasswordField t_oldPWD,t_newPWD,t_newPWDAgain;
	private JButton b_ok,b_cancel;
	
	public ModifyPwdFrame()
	{
		super("�޸�����");
		l_oldPWD=new JLabel("  ������:          ");
		l_newPWD=new JLabel("   �����룺      ");
		l_newPWDAgain=new JLabel("ȷ�������룺");
		t_oldPWD=new JPasswordField(15);
		t_newPWD=new JPasswordField(15);
		t_newPWDAgain=new JPasswordField(15);
		b_ok=new JButton("ȷ��");
		b_cancel=new JButton("ȡ��");
		Container c=this.getContentPane();
		c.setLayout(new FlowLayout());
		c.add(l_oldPWD);
		c.add(t_oldPWD);
		c.add(l_newPWD);
		c.add(t_newPWD);
		c.add(l_newPWDAgain);
		c.add(t_newPWDAgain);
		c.add(b_ok);
		c.add(b_cancel);
		
		b_ok.addActionListener(this);
		b_cancel.addActionListener(this);
		
		this.setResizable(false);		
		this.setSize(280,160);
		Dimension screen = this.getToolkit().getScreenSize();
	    	this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(b_cancel==e.getSource()){
			 //��Ӵ���

		}else if(b_ok==e.getSource())		//�޸�����
		{
			 //��Ӵ���

		}
	}
}

//���ʱ༭����
class SalaryEditFrame extends JFrame implements ActionListener{
	private JLabel l_ps1,l_ps2,l_date,l_empID,l_empName,l_gongzi,l_jintie;
	private JTextField t_date,t_empID,t_empName,t_gongzi,t_jintie;
	private JButton b_update,b_delete,b_select,b_new,b_clear;
	private JPanel p1,p2,p3;
	private JScrollPane scrollpane;
	private JTable table;	
	
	public SalaryEditFrame()
	{
		super("���ʹ���");
		Container c=this.getContentPane();
		l_date = new JLabel("������Ϣ",JLabel.CENTER);
		l_empID = new JLabel("Ա����",JLabel.CENTER);
		l_empName = new JLabel("Ա������",JLabel.CENTER);
		l_gongzi = new JLabel("��������",JLabel.CENTER);
		l_jintie = new JLabel("����",JLabel.CENTER);
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
		p1=new JPanel();
        p1.setLayout(new GridLayout(5,2,10,10));
        p1.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("�༭������Ϣ"), 
        BorderFactory.createEmptyBorder(5,5,5,5)));
        
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
		
		p2=new JPanel();
		p2.setLayout(new GridLayout(5,1,10,10));
		p2.add(b_new);
		p2.add(b_update);
		p2.add(b_delete);
		p2.add(b_select);
		p2.add(b_clear);	    
		c.add(p2,BorderLayout.CENTER);


		p3 = new JPanel();
		p3.setBorder(BorderFactory.createCompoundBorder(
		BorderFactory.createTitledBorder("��ʾ������Ϣ"), 
		BorderFactory.createEmptyBorder(5,5,5,5)));
				
		String[] cloum = { "������Ϣ","Ա����", "����", "�»�������", "����","��н"};
		Object[][] row = new Object[50][6];
		table = new JTable(row, cloum);
		scrollpane = new JScrollPane(table);
		scrollpane.setViewportView(table);
		p3.add(scrollpane);
		c.add(p3,BorderLayout.EAST);
		
		b_new.addActionListener(this);
		b_update.addActionListener(this);
		b_delete.addActionListener(this);
		b_select.addActionListener(this);
		b_clear.addActionListener(this);
		
		//��Ӵ��룬Ϊtable���������¼�����addMouseListener
		
		this.setResizable(false);
		this.setSize(800,300);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
		
//		this.setBounds(200, 100, 500, 600);
//		this.setResizable(true);// ���Ե��������С
//		this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(b_select==e.getSource())			//��ѯ���еĹ�����Ϣ
		{
			 //��Ӵ���
			
			 
		}else if(b_update==e.getSource())		//�޸�ĳ�����ʼ�¼
		{
			 //��Ӵ���


		}else if(b_delete==e.getSource())		//ɾ��ĳ�����ʼ�¼
		{
			//��Ӵ���


		}else if(b_new==e.getSource())      		//����µĹ��ʼ�¼
		{
			//��Ӵ���

		}
		else if(b_clear==e.getSource())			//��������
		{
			//��Ӵ���
			
		}
	}
}







