package erp_students.ui.list;

import javax.swing.SwingConstants;

import erp_students.ui.service.EmployeeService;
import erp_students_dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {
	public EmployeeTablePanel() {
	}
	
	private EmployeeService service;
	
	
	@Override
	public void initList() {
		list = service.showEmployeeList();
	}

	@Override
	public String[] getColumnNames() {
		
		return new String[] {"사원번호", "사원명", "직책", "직속상사", "급여", "부서"};
	}

	@Override
	protected Object[] toArray(Employee t) {
		
		return new Object[] {
			t.getEmpNo(),
			t.getEmpName()
			,String.format("%s(%d)", t.getTitle().gettName(), t.getTitle().gettNo())
			,String.format("%s(%d)", t.getManager(),t.getEmpNo())
			,t.getSalary()
			,String.format("%s(%s)", t.getDept().getDeptNo(),t.getDept().getDeptName())
		};
	}

	@Override
	protected void setAlignAndWidth() {
		 //컬럼내용 정렬
		 setTableCellAlign(SwingConstants.CENTER, 1,2,5);
		 setTableCellAlign(SwingConstants.RIGHT, 0,2,3,4);
		 
		 
		 //컬럼 너비 조정
		 setTableCellWidth( 100, 250);
		
	}
	
	public void setService(EmployeeService service) {
		 this.service = service;
		
	}

}
