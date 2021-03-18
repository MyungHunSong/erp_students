package erp_students;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_students.ui.DepartmentManagerUI;
import erp_students.ui.EmployeeManagerUI;
import erp_students.ui.TitleManagerUI;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnTitle;
	private JButton btnDepartment;
	private JButton btnEmployee;
	private TitleManagerUI titleframe;
	private EmployeeManagerUI empframe;
	private DepartmentManagerUI deptframe;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Main() {
		creatFrame();
		
		initialize();
	}


	private void creatFrame() {
		titleframe = new TitleManagerUI();
		setTitle("직책관리");
		
		deptframe = new DepartmentManagerUI();
		setTitle("부서관리");
		
		empframe = new EmployeeManagerUI();
		setTitle("사원관리");
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 10));
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
		
		btnDepartment = new JButton("부서관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);
		
		
		
		btnEmployee = new JButton("사원관리");
		btnEmployee.addActionListener(this);
		contentPane.add(btnEmployee);
	}
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmployee) {
			actionPerformedBtnEmployee(e);
		}
		if (e.getSource() == btnDepartment) {
			actionPerformedBtnDepartment(e);
		}
		
		if (e.getSource() == btnTitle) {
			actionPerformedBtnTitle(e);
		}
		
		
	}
	
	
	protected void actionPerformedBtnTitle(ActionEvent e) {
		
		titleframe.setVisible(true);
	}
	
	protected void actionPerformedBtnDepartment(ActionEvent e) {
		
		deptframe.setVisible(true);
	}
	protected void actionPerformedBtnEmployee(ActionEvent e) {
		
		empframe.setVisible(true);
	}
}
