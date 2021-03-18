package erp_students_dao;



import java.util.List;

import org.junit.After;

import org.junit.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_students_daoImpl.DepartmentDaoImpl;
import erp_students_dto.Department;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {

	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();

	

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testSelectByAll04() { 
		System.out.printf("%s() %n " , "testSelectByAll()" );
		
		List<Department> departmentList = dao.selectByAll();
		Assert.assertNotNull(departmentList);
		
		for(Department d : departmentList) {
			System.out.println(d);
		}
		
	}
	
	@Test
	public void testSelectDepartmentByNo05() {
		System.out.printf("%s() %n " , "testSelectDepartmentByNo()");
		
		Department department = new Department(5);
		Department searchDepartment = dao.selectDepartmentByNo(department);
		Assert.assertNull(searchDepartment);
	}
	
	@Test
	public void testInsertDepartment01() {
		System.out.printf("%s() %n " , " testInsertDepartment()");
		Department newDepartment = new Department(5, "레전드", 3);
		int res = dao.insertDepartment(newDepartment);
		Assert.assertEquals(1, res);
		
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}
	
	@Test
	public void testUpdateDepartment02() {
		System.out.printf("%s() %n " , "testUpdateDepartment()");
		Department newDepartment = new Department(5, "전똥찬");
		int res = dao.updateDepartment(newDepartment);
		Assert.assertEquals(1, res);
		
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}
	
	@Test
	public void testDeleteDepartment03() {
		System.out.printf("%s()%n", "testDeleteDepartment");
		int res = dao.deleteDepartment(5);
		Assert.assertEquals(1, res);
		System.out.println(res);
		
		
		
		Assert.assertEquals(1, res);
		
		dao.selectByAll().stream().forEach(System.out::println);
	}

}
