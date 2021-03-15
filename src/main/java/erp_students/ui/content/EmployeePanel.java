package erp_students.ui.content;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import erp_students.ui.service.EmployeeService;
import erp_students.ui.service.TitleService;
import erp_students_dto.DepartmentDto;
import erp_students_dto.EmployeeDto;
import erp_students_dto.TitleDto;

import javax.swing.SpinnerNumberModel;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class EmployeePanel extends JPanel implements ItemListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JComboBox<TitleDto> cmbTitle;
	private JComboBox<EmployeeDto> cmbManager;
	private JSpinner spinSalary;
	private JComboBox<DepartmentDto> cmbDept;
	private EmployeeService service;

	public EmployeePanel() {

		initialize();
	}
	
	
	
	private void initialize() {
		setBorder(new TitledBorder(null, "사원 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pItem = new JPanel();
		add(pItem);
		pItem.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblNo = new JLabel("사원번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setColumns(10);
		pItem.add(tfNo);
		
		JLabel lblName = new JLabel("사원명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblName);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		pItem.add(tfName);
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblDept);
		
		cmbDept = new JComboBox<>();
		cmbDept.addItemListener(this);
		pItem.add(cmbDept);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblManager);
		
		cmbManager = new JComboBox<>();
		pItem.add(cmbManager);
		
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblTitle);
		
		cmbTitle = new JComboBox<>();
		pItem.add(cmbTitle);
		
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblSalary);
		
		spinSalary = new JSpinner();
		spinSalary.setModel(new SpinnerNumberModel(2000000, 1500000, 5000000, 100000)); // 모델로 인해 들어온
		pItem.add(spinSalary);
		
		
	}
	
	
	
	
//	protected void itemStateCahngedCmbDEpt(ItemEvent e) {
//		if(e.getSource() == ItemEvent.SELECTED) {
//			Department dept = (Department)
//			List<Employee> empList =  
//		}
//	}
	
	

	public void setEmployee(EmployeeDto e) {
		tfNo.setText(e.getEmpNo()+ "");
		tfName.setText(e.getEmpName());
		cmbTitle.setSelectedItem(e.getTitle());
		cmbDept.setSelectedItem(e.getDept());
		cmbManager.setSelectedItem(e.getManager());
		spinSalary.setValue(e.getSalary());
		
		
		
		//Employee(int empNo, String empName,
		//Title title, Employee manager,
		//int salary, Department dept)
	}
	
	public EmployeeDto getEmployee() {
		int empNo = Integer.parseInt(tfNo.getText().trim());
		String empName = tfName.getText().trim();
		TitleDto title = (TitleDto) cmbTitle.getSelectedItem();
		EmployeeDto manager = (EmployeeDto)cmbManager.getSelectedItem();
		int salary = (int) spinSalary.getValue();
		DepartmentDto dept = (DepartmentDto)cmbDept.getSelectedItem();
		
		return new EmployeeDto(empNo, empName, title, manager, salary, dept);
	}
	
	public void clearTf() {
		tfNo.setText("");
		tfName.setText("");
		
	}
	
	public void setService(EmployeeService service) {
		this.service = service;
		
		List<DepartmentDto> deptList = service.showDeptList();
		DefaultComboBoxModel deptModel = new DefaultComboBoxModel<>(new Vector<>(deptList));
		cmbDept.setModel(deptModel);

		List<TitleDto> titleList = service.showTitleList();
		DefaultComboBoxModel titleModel = new DefaultComboBoxModel<>(new Vector<> (titleList));
		cmbTitle.setModel(titleModel);
		
		cmbDept.setSelectedIndex(-1);
		cmbTitle.setSelectedIndex(-1);
		
	
	}

	
	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbDept) {
			itemStateChangedCmbDept(e);
		}
	}
	protected void itemStateChangedCmbDept(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
			DepartmentDto dept = (DepartmentDto) cmbDept.getSelectedItem();
			List<EmployeeDto> empByDeptList = service.showEmployeeByDept(dept);
			
			//직속 상사가 없는 경우 추가
			if(empByDeptList == null) {
				empByDeptList = new ArrayList<>();
			}
			
			DefaultComboBoxModel model = new DefaultComboBoxModel<>(new Vector<>(empByDeptList));
			cmbManager.setModel(model);
			cmbManager.setSelectedItem(-1);
		}
			
	}
}
