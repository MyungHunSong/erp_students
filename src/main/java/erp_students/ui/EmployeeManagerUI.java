package erp_students.ui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import erp_students.ui.content.AbstractContentPanel;
import erp_students.ui.content.EmployeeDetailPanel;
import erp_students.ui.content.EmployeePanel;
import erp_students.ui.list.AbstractCustomTablePanel;
import erp_students.ui.list.EmployeeTablePanel;
import erp_students.ui.service.EmployeeDetailService;
import erp_students.ui.service.EmployeeService;
import erp_students_dto.Employee;
import erp_students_dto.EmployeeDetail;

@SuppressWarnings("serial")
public class EmployeeManagerUI extends AbstractManagerUI<Employee> {
	private EmployeeService service;
	private EmployeeDetailService detailService;
	
	
	public EmployeeManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.EMP_MENU);
	}
	
	
	
	
	
	@Override
	protected void setService() {
		service = new EmployeeService();
		detailService = new EmployeeDetailService();
	}

	@Override
	protected void tableLoadData() {
		((EmployeeTablePanel)pList).setService(service);
		pList.loadData();
		
	}

	@Override
	protected AbstractContentPanel<Employee> createContentPanel() {
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setService(service);
		
		return empPanel;
	}

	@Override
	protected AbstractCustomTablePanel<Employee> createTablePanel() {
		
		return new EmployeeTablePanel();
	}
	
	

	@Override
	protected void actionPerformedMenuGuBun() {
		Employee emp = pList.getItem();
		
		//System.out.println(emp);
		
		EmployeeDetail empDetail = detailService.selectEmployeeDetailByEmpNo(emp);
		
		
		
		// 나중에처리
		
		if(empDetail == null) {
			JOptionPane.showMessageDialog(null, "세부정보 없음");
			return;
		}
			
	
			JFrame subFrame = new JFrame("사원 세부 정보");
			subFrame.setBounds(this.getWidth(), this.getHeight(), 450, 500);
			
			EmployeeDetailPanel subDetailPanel = new EmployeeDetailPanel();
			subDetailPanel.setItem(empDetail);
			
			subFrame.add(subDetailPanel,BorderLayout.CENTER);
			
			subFrame.setVisible(true);
	}
//		// 사원세부정보를 뛰워주는 창
//		JFrame subFrame = new JFrame("사원 세부 정보");
//		subFrame.setBounds(this.getWidth(), this.getHeight(), 450, 500);
//		
//		
//		EmployeeDetail subDetailPanel = new EmployeeDetailPanel();
//		subDetailPanel.setItem(empDetail);
//		
//		subFrame.add(subDetailPanel, BorderLayout.CENTER);
//		
//		subFrame.setVisible(true);
	
	
	
	

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

	
	// catch (SQLException e){ throw new sqlConstraintException(e.getMesage(), e);
}
