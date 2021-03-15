package erp_students_dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_students_daoImpl.EmployeeDaoImpl;
import erp_students_dto.DepartmentDto;
import erp_students_dto.EmployeeDto;
import erp_students_dto.TitleDto;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDaoTest {
	
	
	private static  EmployeeDao dao = EmployeeDaoImpl.getInstance();

	



	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test05SelectEmployeeByAll() {
		System.out.printf("%s()%n", "testSelectEmployeeByAll()");
		
		List<EmployeeDto> employeeList = dao.selectEmployeeByAll();
		Assert.assertNotNull(employeeList);
		
		for(EmployeeDto e : employeeList) {
			System.out.println(e);
		}
		
	}
	
	
	

	@Test
	public void test04SelectEmployeeByNo() {
		System.out.printf("%s()%n", "testSelectEmployeeByNo()");
		
		EmployeeDto selEmp = new EmployeeDto(2106);
		EmployeeDto searchEmployee = dao.selectEmployeeByNo(selEmp);
		
		Assert.assertNotNull(searchEmployee);
		System.out.println(searchEmployee);
	}

	
	
	
	
	
	
	
	
	@Test
	public void test01InsertEmployee() {
		System.out.printf("%s()%n", "testInsertEmployee()");
		EmployeeDto newEmp = new EmployeeDto(1004, "천사",  new TitleDto(5) ,new EmployeeDto(4377), 2000000, new DepartmentDto(1));
		int res = dao.insertEmployee(newEmp);
		
		Assert.assertEquals(1, res);
		
		
		
		System.out.println(dao.selectEmployeeByNo(newEmp));
	}

	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void test02UpdateEmployee() {
		System.out.printf("%s()%n", "testUpdateEmployee()");
		EmployeeDto newEmployee = new EmployeeDto(1004, "니하오",  new TitleDto(4) ,new EmployeeDto(1003), 3000000, new DepartmentDto(2));
		int res = dao.updateEmployee(newEmployee);
		// Assert.assertEquals(1, res); 
		
		
		
		
		System.out.println(newEmployee);
	}

	
	
	
	
	
	
	
	
	
	@Test
	public void test03DeleteEmployee() {
		System.out.printf("%s()%n", "testDeleteEmployee()");
		int res = dao.deleteEmployee(1004);
		Assert.assertEquals(1, res);
		
		dao.selectEmployeeByAll().stream().forEach(System.out::println);
		
		

	}

}
