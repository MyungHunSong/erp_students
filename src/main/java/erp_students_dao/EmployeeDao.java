package erp_students_dao;

import java.util.List;

import erp_students_dto.DepartmentDto;
import erp_students_dto.EmployeeDto;
import erp_students_dto.TitleDto;

public interface EmployeeDao {
	List<EmployeeDto> selectEmployeeByAll();
	EmployeeDto selectEmployeeByNo(EmployeeDto emp);

	
	int insertEmployee(EmployeeDto emp);
	int updateEmployee(EmployeeDto emp);
	int deleteEmployee(int employeeNo);
	
	
	List<EmployeeDto> selectEmployeeByAllTitle(TitleDto title); // 조인조건 구하기
	List<EmployeeDto> selectEmployeeByDept(DepartmentDto department);
	

}
