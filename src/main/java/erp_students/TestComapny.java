package erp_students;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import erp_students.ui.list.EmployeeTablePanel;
import erp_students.ui.service.EmployeeDetailService;
import erp_students.ui.service.EmployeeService;
import erp_students_dto.Employee;
import erp_students_dto.EmployeeDetail;
import erp_students.ui.content.EmployeeDetailPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class TestComapny extends JFrame implements ActionListener {

	private JPanel contentPane;
	private EmployeeTablePanel pList;
	private EmployeeDetailPanel panel;
	private JPanel panel_1;
	private JButton btnGet;
	private JButton btnSet;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestComapny frame = new TestComapny();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TestComapny() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		EmployeeService service = new EmployeeService();
		
		pList = new EmployeeTablePanel();
		pList.setService(service);
		pList.loadData();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(pList, BorderLayout.CENTER);
		
		panel = new EmployeeDetailPanel();
		panel.setTfEmpno(new Employee(1003));
		
		contentPane.add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(1, 0, 0, 0));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnGet = new JButton("가져오기");
		btnGet.addActionListener(this);
		panel_1.add(btnGet);
		
		btnSet = new JButton("불러오기");
		btnSet.addActionListener(this);
		panel_1.add(btnSet);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSet) {
			actionPerformedBtnSet(e);
		}
		if (e.getSource() == btnGet) {
			actionPerformedBtnGet(e);
		}
	}
	protected void actionPerformedBtnGet(ActionEvent e) {
		EmployeeDetail employeeDetail = panel.getItem();
		JOptionPane.showMessageDialog(null,employeeDetail);
	}
	
	protected void actionPerformedBtnSet(ActionEvent e) {
		EmployeeDetailService service = new EmployeeDetailService();
		EmployeeDetail empDetail = service.selectEmployeeDetailByEmpNo(new Employee(1003));
		
		panel.setItem(empDetail);
	}
}
