package erp_students.ui.content.list;

import javax.swing.SwingConstants;

import erp_students.ui.service.DepartmentService;
import erp_students_dto.DepartmentDto;

@SuppressWarnings("serial")
public class DepartmentTablePanel extends AbstractCustomTablePanel<DepartmentDto> {
	private DepartmentService service = new DepartmentService();
	
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
	protected Object[] toArray(DepartmentDto t) {
		
		return new Object[] {t.getDeptNo(), t.getDeptName(), t.getFloor()};
	}

}
