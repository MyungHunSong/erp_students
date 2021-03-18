package erp_students.ui.service;

import java.util.List;

import erp_students_dao.DepartmentDao;
import erp_students_dao.EmployeeDao;
import erp_students_dao.TitleDao;
import erp_students_daoImpl.DepartmentDaoImpl;
import erp_students_daoImpl.EmployeeDaoImpl;
import erp_students_daoImpl.TitleDaoImpl;
import erp_students_dto.Department;
import erp_students_dto.Employee;
import erp_students_dto.Title;

public class EmployeeService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
	
	
	public List<Employee> showEmployeeList(){
		return employeeDao.selectEmployeeByAll();
	}
	
	public List<Department> showDeptList(){
		return deptDao.selectByAll();
	}
	
	
	public List<Title> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public  List<Employee> showEmployeeByDept(Department dept){
		return employeeDao.selectEmployeeByDept(dept);
	}
	
	// 추가 삭제
	public void addEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
		
	}
	
	public void removeEmployee(Employee emp) {
		employeeDao.deleteEmployee(emp);
	}
	
	public void modifyEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}
	
}
