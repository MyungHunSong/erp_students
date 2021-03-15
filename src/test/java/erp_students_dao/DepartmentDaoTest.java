package erp_students_dao;



import java.util.List;

import org.junit.After;

import org.junit.Assert;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_students_daoImpl.DepartmentDaoImpl;
import erp_students_dto.DepartmentDto;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoTest {

	private static DepartmentDao dao = DepartmentDaoImpl.getInstance();

	

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	public void testSelectByAll04() { 
		System.out.printf("%s() %n " , "testSelectByAll()" );
		
		List<DepartmentDto> departmentList = dao.selectByAll();
		Assert.assertNotNull(departmentList);
		
		for(DepartmentDto d : departmentList) {
			System.out.println(d);
		}
		
	}
	
	@Test
	public void testSelectDepartmentByNo05() {
		System.out.printf("%s() %n " , "testSelectDepartmentByNo()");
		
		DepartmentDto department = new DepartmentDto(5);
		DepartmentDto searchDepartment = dao.selectDepartmentByNo(department);
		Assert.assertNull(searchDepartment);
	}
	
	@Test
	public void testInsertDepartment01() {
		System.out.printf("%s() %n " , " testInsertDepartment()");
		DepartmentDto newDepartment = new DepartmentDto(6, "학살", 3);
		int res = dao.insertDepartment(newDepartment);
		Assert.assertEquals(1, res);
		
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}
	
	@Test
	public void testUpdateDepartment02() {
		System.out.printf("%s() %n " , "testUpdateDepartment()");
		DepartmentDto newDepartment = new DepartmentDto(6, "학살", 5);
		int res = dao.updateDepartment(newDepartment);
		Assert.assertEquals(1, res);
		
		System.out.println(dao.selectDepartmentByNo(newDepartment));
	}
	
	@Test
	public void testDeleteDepartment03() {
		System.out.printf("%s()%n", "testDeleteDepartment");
		int res = dao.deleteDepartment(6);
		Assert.assertEquals(1, res);
		System.out.println(res);
		
		
		
		Assert.assertEquals(1, res);
		
		dao.selectByAll().stream().forEach(System.out::println);
	}

}
