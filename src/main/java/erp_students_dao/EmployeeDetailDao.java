package erp_students_dao;

import erp_students_dto.Employee;
import erp_students_dto.EmployeeDetail;

public interface EmployeeDetailDao {
	
	EmployeeDetail selectEmployeeDetailByNo(Employee employee);
	
	int insertEmployeeDetail(EmployeeDetail empDetail);
	int updateEmployeeDetail(EmployeeDetail empDetail);
	int deleteEmployeeDetail(Employee emp);

	
}
