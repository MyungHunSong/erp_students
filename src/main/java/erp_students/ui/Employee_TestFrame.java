package erp_students.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_students.ui.content.EmployeePanel;
import erp_students.ui.list.EmployeeTablePanel;
import erp_students.ui.service.EmployeeService;
import erp_students.ui.service.TitleService;
import erp_students_dto.Department;
import erp_students_dto.Employee;
import erp_students_dto.Title;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Employee_TestFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnSet;
	private EmployeePanel pItem;
	private JButton butAdd;
	private EmployeeTablePanel pList;
	private EmployeeService service;
	private JButton butCansle;
	
	
	public Employee_TestFrame() { // service 친구를 위에다가 무조건 뺴줘야한다.
		
		initialize();
	
		
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));

		EmployeeService service = new EmployeeService();
		pItem = new EmployeePanel();
		pItem.setService(service);

		contentPane.add(pItem);

		JPanel panel = new JPanel();
		pItem.add(panel, BorderLayout.SOUTH);

		butAdd = new JButton("추가");
		butAdd.addActionListener(this);
		panel.add(butAdd);

		btnSet = new JButton("Set");
		btnSet.addActionListener(this);

		panel.add(btnSet);

		butCansle = new JButton("취소");
		butCansle.addActionListener(this);
		panel.add(butCansle);
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.add(pList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butCansle) {
			actionPerformedButCansle(e);
		}
		if (e.getSource() == butAdd) {
			actionPerformedButton(e);
		}
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
	}

	protected void actionPerformedBtnSet(ActionEvent e) {

		Employee item = new Employee(1003, "조민희", new Title(3), new Employee(4377), 3000000, new Department(2));
		
		pItem.setItem(item);
		
	}
	protected void actionPerformedButton(ActionEvent e) {
		
		Employee employee = pItem.getItem();
		
		service.addEmployee(employee);
		
		pList.loadData();
		JOptionPane.showMessageDialog(null, employee + " 추가됬음.");
	}
	protected void actionPerformedButCansle(ActionEvent e) {
	}
}
