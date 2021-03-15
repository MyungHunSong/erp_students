package erp_students_dao;

import java.util.List;

import erp_students_dto.DepartmentDto;

public interface DepartmentDao {
	List<DepartmentDto> selectByAll();
	DepartmentDto selectDepartmentByNo(DepartmentDto department);
	
	int insertDepartment(DepartmentDto department);
	int updateDepartment(DepartmentDto department);
	int deleteDepartment(int departmentNo);

}
