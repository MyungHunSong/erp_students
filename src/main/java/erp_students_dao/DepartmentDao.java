package erp_students_dao;

import java.util.List;

import erp_students.ui.content.EmployeeDetail;
import erp_students_dto.Department;

public interface DepartmentDao {
	List<Department> selectByAll();
	Department selectDepartmentByNo(Department department);
	
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(int departmentNo);
	

}
