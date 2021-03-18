package erp_students.ui.list;

import javax.swing.SwingConstants;

import erp_students.ui.exception.NotSelectedException;
import erp_students.ui.service.EmployeeService;

import erp_students_dto.Employee;

@SuppressWarnings("serial")
public class EmployeeTablePanel extends AbstractCustomTablePanel<Employee> {
	private EmployeeService service;
	
	public EmployeeTablePanel() {
	}
	
	
	
	
	@Override
	public void initList() {
		list = service.showEmployeeList();
		System.out.println(list);
	}
	
	public void setService(EmployeeService service) {
		 this.service = service;
		
	}
	
	

	@Override
	public String[] getColumnNames() {
		
		return new String[] {"사원번호", "사원명", "직책", "직속상사", "급여", "부서"};
	}
	
	
	@Override
	protected void setAlignAndWidth() {
		 //컬럼내용 정렬
		 setTableCellAlign(SwingConstants.CENTER, 1,2,5);
		 setTableCellAlign(SwingConstants.RIGHT, 0,3,4);
		 
		 
		 //컬럼 너비 조정
		 setTableCellWidth( 100, 150, 100, 130, 100, 100);
		
	}

	@Override
	protected Object[] toArray(Employee t) {
		
		return new Object[] {
			t.getEmpNo(),
			t.getEmpName()
			,String.format("%s(%d)", t.getTitle().gettName(), t.getTitle().gettNo())
			,t.getManager().getEmpNo() == 0?"":String.format("%s(d)", t.getManager().getEmpName(),t.getManager().getEmpNo())
			,String.format("%,d", t.getSalary())
			,String.format("%s(%s)", t.getDept().getDeptNo(),t.getDept().getDeptName())
		};
	}

	
	
	



	@Override
	public Employee getItem() {
		int row = table.getSelectedRow(); 
		int empNo = (int)table.getValueAt(row, 0);
		
		if(row == -1) {
			
			throw new NotSelectedException();
		}
		
		return list.get(list.indexOf(new Employee(empNo)));
	}

}
