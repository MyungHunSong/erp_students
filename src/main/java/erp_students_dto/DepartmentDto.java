package erp_students_dto;

public class DepartmentDto {
	private int deptNo;
	private String deptName;
	private int floor;
	
	public DepartmentDto() {

	}
	
	
// private List<Employee> empList;
	public DepartmentDto(int deptNo) {
		this.deptNo = deptNo;
	}

	

	public DepartmentDto(String deptName) {
		this.deptName = deptName;
	}
	


	public DepartmentDto(int deptNo, String deptName, int floor) {
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.floor = floor;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return String.format("부서=%s, 부서명=%s, 위치=%s", deptNo, deptName, floor);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deptNo;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartmentDto other = (DepartmentDto) obj;
		if (deptNo != other.deptNo)
			return false;
		return true;
	}
	
	
	
	

}
