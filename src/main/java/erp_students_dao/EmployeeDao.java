package erp_students_dao;

import java.util.List;

import erp_students_dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmployeeByAll();
	Employee selectEmployeeByNo(Employee emp);

	
	int insertEmployee(Employee emp);
	int updateEmployee(Employee emp);
	int deleteEmployee(int employeeNo);
	
	
	List<Employee> selectEmployeeByAllSimple();

	

}
