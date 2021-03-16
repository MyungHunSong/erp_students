package erp_students_dao;

import erp_students.ui.content.EmployeeDetail;
import erp_students_dto.Employee;

public interface EmployeeDetailDao {
	
	EmployeeDetail selectEmployeeDetailByNo(Employee employee);
	
	int insertEmployeeDetail(EmployeeDetail empDetail);
	int updateEmployeeDetail(EmployeeDetail empDetail);
	int deleteEmployeeDetail(int employeeNo);

	
}
