package erp_students.ui.list;

import javax.swing.SwingConstants;

import erp_students.ui.exception.NotSelectedException;
import erp_students.ui.service.DepartmentService;
import erp_students_dto.Department;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractCustomTablePanel<Department> {
	public DepartmentTablePanel() {
	}
	private DepartmentService service;
	
	@Override
	public void initList() {
		
		 list = service.showDepartment();
	}

	@Override
	public String[] getColumnNames() {
		
		return new String[] {"부서번호", "부서명", "위치"};
	}

	@Override
	protected void setAlignAndWidth() {
		// 컬럼 내용 정렬
		setTableCellAlign(SwingConstants.CENTER, 1);
		setTableCellAlign(SwingConstants.RIGHT,0 ,2);
		
		//컬럼 너비 조정
		setTableCellWidth(100, 250, 200);
		
	}

	@Override
	protected Object[] toArray(Department t) {
		
		return new Object[] {t.getDeptNo(), t.getDeptName(), t.getFloor()};
	}

	public void setService(DepartmentService service) {
		this.service = service;
		
	}

	@Override
	public Department getItem() {
		int row = table.getSelectedRow(); //  겟로우 (위치를 가져 올수 있는기능)
		int deptNo = (int)table.getValueAt(row, 0);
		
		if(row == -1) {
			
			throw new NotSelectedException();
		}
		
		return list.get(list.indexOf(new Department(deptNo)));
	}

	

}
