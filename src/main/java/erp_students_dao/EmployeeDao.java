package erp_students_dao;

import java.util.List;

import erp_students_dto.Department;
import erp_students_dto.Employee;
import erp_students_dto.Title;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByNo(Employee emp);

	
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(Employee emp);
	
	
	List<Employee> selectEmployeeByAllTitle(Title title); // 조인조건 구하기
	List<Employee> selectEmployeeByDept(Department dept);
	

}
