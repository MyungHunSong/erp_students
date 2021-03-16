package erp_students.ui.service;

import erp_students.ui.content.EmployeeDetail;
import erp_students_dao.EmployeeDetailDao;
import erp_students_daoImpl.EmployeeDetailDaoImpl;
import erp_students_dto.Employee;

public class EmployeeDetailService {
	private EmployeeDetailDao empDetailDao = EmployeeDetailDaoImpl.getInstance();
	
	
	public EmployeeDetail selectEmployeeDetailByEmpNo(Employee employee) {
		return empDetailDao.selectEmployeeDetailByNo(employee);
	}
	
	public void addEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.insertEmployeeDetail(empDetail);
	}
	
	public void removeEmployeeDetail(int empDetail) {
		empDetailDao.deleteEmployeeDetail(empDetail);
	}
	
	public void modifyEmployeeDetail(EmployeeDetail empDetail) {
		empDetailDao.updateEmployeeDetail(empDetail);
	}
}
