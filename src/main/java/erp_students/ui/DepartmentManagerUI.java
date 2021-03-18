package erp_students.ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import erp_students.ui.content.AbstractContentPanel;
import erp_students.ui.content.DepartmentPanel;
import erp_students.ui.list.AbstractCustomTablePanel;
import erp_students.ui.list.DepartmentTablePanel;
import erp_students.ui.list.TitleTablePanel;
import erp_students.ui.service.DepartmentService;
import erp_students_dto.Department;
import erp_students_dto.Employee;

@SuppressWarnings("serial")
public class DepartmentManagerUI extends AbstractManagerUI<Department> {
	
	private DepartmentService service;
	
	
	
	public DepartmentManagerUI() {
		empListByTitleItem.setText(AbstractManagerUI.DEPT_MENU);
	}

	@Override
	protected void setService() {
		service = new DepartmentService();
		
	}

	@Override
	protected void tableLoadData() {
		((DepartmentTablePanel) pList).setService(service);
		pList.loadData();
		
	}

	@Override
	protected AbstractContentPanel<Department> createContentPanel() {
	
		return new DepartmentPanel();
	}

	@Override
	protected AbstractCustomTablePanel<Department> createTablePanel() {
		
		return new DepartmentTablePanel();
	}

	@Override
	protected void actionPerformedMenuGuBun() {
		Department dept = pList.getItem();
		List<Employee> list = service.showEmployeeGroupByDepartment(dept);
		
		if (list == null) {
			JOptionPane.showMessageDialog(null, "해당 사원이 없음", "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		List<String> strList = list
				.parallelStream()
				.map( s->{ return String.format("%s(%d)", s.getEmpName(), s.getEmpNo()); })
				.collect(Collectors.toList());
		
		JOptionPane.showMessageDialog(null, strList, "동일 부서 사원", JOptionPane.INFORMATION_MESSAGE);
		
	}

	@Override
	protected void acitonPerformedMenuUpdate() {
		btnAdd.setText("수정");
		
		Department updateDept = pList.getItem();
		pContent.setItem(updateDept);
		
	}

	@Override
	protected void actionPerformedMenuDelete(ActionEvent e) {
		Department delDept = pList.getItem();
		service.removeDepartment(delDept);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delDept + "삭제 되었습니다.");
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department addDept = (Department) pContent.getItem();
		service.addDepartment(addDept);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, addDept + " 추가했습니다.");
		
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		Department updateDept = (Department) pContent.getItem();
		service.modifyDepartment(updateDept);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, updateDept.getDeptName() + "정보가 수정되었습니다.");
		btnAdd.setText("추가");
		
	}

}
