import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalaryManager {
        public static void main(String[] args) {
       	    LoginFrame lf=new LoginFrame();
		    lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	    		    
    }
}

//登录界面
class LoginFrame extends JFrame implements ActionListener{
	private JLabel l_user,l_pwd;  //用户名标签，密码标签
	private JTextField t_user; //用户名文本框
	private JPasswordField t_pwd; //密码文本框
	private JButton b_ok,b_cancel; //登录按钮，退出按钮
	 
	public LoginFrame(){
		super("欢迎使用工资管理系统!");
		l_user=new JLabel("用户名：",JLabel.RIGHT);
		l_pwd=new JLabel(" 密码：",JLabel.RIGHT);
		t_user=new JTextField(31);
		t_pwd=new JPasswordField(31);
		b_ok=new JButton("登录");
		b_cancel=new JButton("退出");
		//布局方式FlowLayout，一行排满排下一行
		Container c=this.getContentPane();
		c.setLayout(new FlowLayout()); 
		c.add(l_user);
		c.add(t_user);
		c.add(l_pwd);
		c.add(t_pwd);
		c.add(b_ok);
		c.add(b_cancel);
		//为按钮添加监听事件
		b_ok.addActionListener(this);
		b_cancel.addActionListener(this);
        //界面大小不可调整 
		this.setResizable(false);
		this.setSize(455,150);
		//界面显示居中
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(b_cancel==e.getSource()){
			//添加退出代码

		}else if(b_ok==e.getSource()){
			//添加代码，验证身份成功后显示主界面
			  new MainFrame(t_user.getText().trim());
		}
	}
}

//主界面
class MainFrame extends JFrame implements ActionListener{
	private JMenuBar mb=new JMenuBar();
	private JMenu m_system=new JMenu("系统管理");
	private JMenu m_gongzi=new JMenu("工资管理");
	private JMenuItem mI[]={new JMenuItem("密码重置"),new JMenuItem("退出系统")};
	private JMenuItem m_FMEdit=new JMenuItem("工资编辑");
	private JLabel l_ps,l_empid,l_fromdate,l_todate;  
	private JTextField t_empid,t_fromdate,t_todate; 
	private JButton b_byempID,b_bydate,b_all;
	private JPanel p_condition,p_detail;
	private JTable table;
	
	public MainFrame(String username)
	{
		super(username+"欢迎使用企业员工工资管理系统!");
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
		
		l_ps = new JLabel("注意：时间格式均为(年月)，例如：201502");
		l_empid = new JLabel("员工工号");
		t_empid = new JTextField(8);		
		l_fromdate=new JLabel("起始时间");
		l_todate=new JLabel("终止时间");
        t_fromdate=new JTextField(8);
       	t_todate=new JTextField(8);
        b_byempID=new JButton("查询");
        b_all=new JButton("查询所有");
		b_bydate=new JButton("查询");
				
		p_condition=new JPanel();		
		p_condition.setBorder(BorderFactory.createCompoundBorder(
	    	BorderFactory.createTitledBorder("输入查询条件"), 
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
       	
		//添加3个按钮的监听事件

        p_detail=new JPanel();
        p_detail.setBorder(BorderFactory.createCompoundBorder(
	    BorderFactory.createTitledBorder("工资明细信息"), 
	    BorderFactory.createEmptyBorder(5,5,5,5)));
	    
		String[] cloum = { "年月信息","员工号", "姓名", "津贴", "月基本工资","月薪"};
		Object[][] row = new Object[50][6];
		table = new JTable(row, cloum);
		JScrollPane scrollpane = new JScrollPane(table);
		scrollpane.setPreferredSize(new Dimension(580,350));
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setViewportView(table);
		p_detail.add(scrollpane);

		c.add(p_detail,BorderLayout.SOUTH);
		  
				
		//添加代码，将所有员工工资信息显示在界面表格中

		

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
	    	 //添加退出系统的代码


	     }else if(temp==m_FMEdit){
	    	 SalaryEditFrame bef=new SalaryEditFrame();
	     }else if(temp==b_byempID)  //根据员工工号查询
	     {
	    	 //添加代码

 
	     }
	     else if(temp==b_all)  //查询所有员工工资
	     {
	    	  //添加代码


	     }
	     else if(temp==b_bydate)     //根据起始时间来查询
	    {
	    	  //添加代码

	     }
   }
}

//修改密码界面
class ModifyPwdFrame extends JFrame implements ActionListener{
	private JLabel l_oldPWD,l_newPWD,l_newPWDAgain;
	private JPasswordField t_oldPWD,t_newPWD,t_newPWDAgain;
	private JButton b_ok,b_cancel;
	
	public ModifyPwdFrame()
	{
		super("修改密码");
		l_oldPWD=new JLabel("  旧密码:          ");
		l_newPWD=new JLabel("   新密码：      ");
		l_newPWDAgain=new JLabel("确认新密码：");
		t_oldPWD=new JPasswordField(15);
		t_newPWD=new JPasswordField(15);
		t_newPWDAgain=new JPasswordField(15);
		b_ok=new JButton("确定");
		b_cancel=new JButton("取消");
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
			 //添加代码

		}else if(b_ok==e.getSource())		//修改密码
		{
			 //添加代码

		}
	}
}

//工资编辑界面
class SalaryEditFrame extends JFrame implements ActionListener{
	private JLabel l_ps1,l_ps2,l_date,l_empID,l_empName,l_gongzi,l_jintie;
	private JTextField t_date,t_empID,t_empName,t_gongzi,t_jintie;
	private JButton b_update,b_delete,b_select,b_new,b_clear;
	private JPanel p1,p2,p3;
	private JScrollPane scrollpane;
	private JTable table;	
	
	public SalaryEditFrame()
	{
		super("工资管理");
		Container c=this.getContentPane();
		l_date = new JLabel("年月信息",JLabel.CENTER);
		l_empID = new JLabel("员工号",JLabel.CENTER);
		l_empName = new JLabel("员工姓名",JLabel.CENTER);
		l_gongzi = new JLabel("基本工资",JLabel.CENTER);
		l_jintie = new JLabel("津贴",JLabel.CENTER);
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
		p1=new JPanel();
        p1.setLayout(new GridLayout(5,2,10,10));
        p1.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createTitledBorder("编辑工资信息"), 
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
		BorderFactory.createTitledBorder("显示工资信息"), 
		BorderFactory.createEmptyBorder(5,5,5,5)));
				
		String[] cloum = { "年月信息","员工号", "姓名", "月基本工资", "津贴","月薪"};
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
		
		//添加代码，为table添加鼠标点击事件监听addMouseListener
		
		this.setResizable(false);
		this.setSize(800,300);
		Dimension screen = this.getToolkit().getScreenSize();
	    this.setLocation((screen.width-this.getSize().width)/2,(screen.height-this.getSize().height)/2);
		this.setVisible(true);
		
//		this.setBounds(200, 100, 500, 600);
//		this.setResizable(true);// 可以调整界面大小
//		this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(b_select==e.getSource())			//查询所有的工资信息
		{
			 //添加代码
			
			 
		}else if(b_update==e.getSource())		//修改某条工资记录
		{
			 //添加代码


		}else if(b_delete==e.getSource())		//删除某条工资记录
		{
			//添加代码


		}else if(b_new==e.getSource())      		//添加新的工资记录
		{
			//添加代码

		}
		else if(b_clear==e.getSource())			//清空输入框
		{
			//添加代码
			
		}
	}
}







