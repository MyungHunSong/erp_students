package erp_students.ui.service;

import java.util.List;

import erp_students_dao.DepartmentDao;
import erp_students_daoImpl.DepartmentDaoImpl;
import erp_students_dto.Department; 

public class DepartmentService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	
	
	
	public List<Department> showDepartment(){
		
		return dao.selectByAll();
	}
}
