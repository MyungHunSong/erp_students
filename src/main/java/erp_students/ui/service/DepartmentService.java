package erp_students.ui.service;

import java.util.List;

import erp_students_dao.DepartmentDao;
import erp_students_dao.EmployeeDao;
import erp_students_daoImpl.DepartmentDaoImpl;
import erp_students_daoImpl.EmployeeDaoImpl;
import erp_students_dto.Department;
import erp_students_dto.Employee; 

public class DepartmentService {
	private DepartmentDao dao = DepartmentDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	
	public List<Department> showDepartment(){
		
		return dao.selectByAll();
	}



	public void addDepartment(Department dept) {
		dao.insertDepartment(dept);
	}
	
	public void removeDepartment(Department dept) {
		dao.deleteDepartment(dept.getDeptNo());
	}
	
	public void modifyDepartment(Department dept) {
		dao.updateDepartment(dept);
	}
	
	

	public List<Employee> showEmployeeGroupByDepartment(Department dept) {
		
		return empDao.selectEmployeeByDept(dept);
	}
	
	
}
