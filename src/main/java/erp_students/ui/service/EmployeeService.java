package erp_students.ui.service;

import java.util.List;

import erp_students_dao.DepartmentDao;
import erp_students_dao.EmployeeDao;
import erp_students_dao.TitleDao;
import erp_students_daoImpl.DepartmentDaoImpl;
import erp_students_daoImpl.EmployeeDaoImpl;
import erp_students_daoImpl.TitleDaoImpl;
import erp_students_dto.DepartmentDto;
import erp_students_dto.EmployeeDto;
import erp_students_dto.TitleDto;

public class EmployeeService {
	private DepartmentDao deptDao = DepartmentDaoImpl.getInstance();
	private TitleDao titleDao = TitleDaoImpl.getInstance();
	private EmployeeDao employeeDao = EmployeeDaoImpl.getInstance();
	
	
	public List<EmployeeDto> showEmployeeList(){
		return employeeDao.selectEmployeeByAll();
	}
	
	public List<DepartmentDto> showDeptList(){
		return deptDao.selectByAll();
	}
	
	
	public List<TitleDto> showTitleList(){
		return titleDao.selectTitleByAll();
	}
	
	public  List<EmployeeDto> showEmployeeByDept(DepartmentDto dept){
		return employeeDao.selectEmployeeByDept(dept);
	}
	
	// 추가 삭제
	public void addEmployee(EmployeeDto employee) {
		employeeDao.insertEmployee(employee);
		
	}
	
	public void removeEmployee(EmployeeDto employee) {
		employeeDao.deleteEmployee(employee.getEmpNo());
	}
	
	
	
}
