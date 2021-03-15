package erp_students_dto;

public class EmployeeDto {
	private int empNo;
	private String empName;
	private TitleDto title; // 외래키의 참조 땜에 밑에거도 이럼
	private EmployeeDto manager;
	private int salary;
	private DepartmentDto dept;


	public EmployeeDto(int empNo) {
		this.empNo = empNo;
	}
	
	

	public EmployeeDto(int empNo, String empName) {
		this.empNo = empNo;
		this.empName = empName;
	}



	public EmployeeDto(int empNo, String empName, TitleDto title, EmployeeDto manager, int salary, DepartmentDto dept) {
		this.empNo = empNo;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}
	
	
	
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public TitleDto getTitle() {
		return title;
	}

	public void setTitle(TitleDto title) {
		this.title = title;
	}

	public EmployeeDto getManager() {
		return manager;
	}

	public void setManager(EmployeeDto manager) {
		this.manager = manager;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public DepartmentDto getDept() {
		return dept;
	}

	public void setDept(DepartmentDto dept) {
		this.dept = dept;
	}



	@Override
	public String toString() {
		return String.format("Employee [empNo=%s, empName=%s, title=%s, manager=%s, salary=%s, dept=%s]", empNo,
				empName, title, manager, salary, dept);
	}


}
