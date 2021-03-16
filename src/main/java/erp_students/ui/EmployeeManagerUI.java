package erp_students.ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import erp_students.ui.content.AbstractContentPanel;
import erp_students.ui.content.EmployeePanel;
import erp_students.ui.list.AbstractCustomTablePanel;
import erp_students.ui.list.EmployeeTablePanel;
import erp_students.ui.service.EmployeeService;
import erp_students_dto.Employee;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractManagerUI<Employee> {
	
	private EmployeeService service;
	
	@Override
	protected void setService() {
		service = new EmployeeService();
		
	}

	@Override
	protected void tableLoadData() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		
	}

	@Override
	protected AbstractContentPanel<Employee> createContentPanel() {
		
		return new EmployeePanel();
	}

	@Override
	protected AbstractCustomTablePanel<Employee> createTablePanel() {
		
		return new EmployeeTablePanel();
	}

	@Override
	protected void actionPerformedMenuGuBun() {
		
		 throw new UnsupportedOperationException("구현하지 않음");
	}

	@Override
	protected void acitonPerformedMenuUpdate() {
		Employee updateEmployee = pList.getItem();
		pContent.setItem(updateEmployee);
		btnAdd.setText("수정");
		
	}

	@Override
	protected void actionPerformedMenuDelete(ActionEvent e) {
		Employee delEmp = pList.getItem();
		service.removeEmployee(delEmp);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delEmp + "삭제 되었습니다.");
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee employee = pContent.getItem();
		service.addEmployee(employee);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, employee.getEmpName() + " 추가했습니다.");
		
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Employee updateEmp = pContent.getItem();
		service.modifyEmployee(updateEmp);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateEmp.getEmpName() + "정보가 수정되었습니다.");
		
	}

	
	// catcfh (SQLException e){ throw new sqlConstraintException(e.getMesage(), e);
}
