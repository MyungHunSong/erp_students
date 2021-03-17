package erp_students_dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import erp_students.ui.content.EmployeeDetail;
import erp_students_daoImpl.EmployeeDetailDaoImpl;
import erp_students_dto.Department;
import erp_students_dto.Employee;
import erp_students_dto.Title;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeDetailDaoTest {
	
	private EmployeeDetailDao dao = EmployeeDetailDaoImpl.getInstance();

	
	@After
	public void tearDown() throws Exception {
		System.out.println();
	}
	
	

	@Test
	public void test02SelectEmployeeDetailByNo() {
		System.out.printf("%s()%n", "testSelectEmployeeDetailByNo()");
		
		EmployeeDetail employeeDetail = dao.selectEmployeeDetailByNo(new Employee(1003));
		Assert.assertNotNull(employeeDetail);
		
		System.out.println(employeeDetail);
	}

	
	

	@Test
	public void test01InsertEmployeeDetail() {
		System.out.printf("%s()%n", "test01InsertEmployeeDetail()");
		EmployeeDetail detail = new EmployeeDetail(1003, true, new Date(), "1234", getImage("noimage.jpg"));
		int res = dao.insertEmployeeDetail(detail);
		
		Assert.assertEquals(1,res);
	}
	
	private byte[] getImage(String imgName) {
		byte[] pic = null;
			
		File file = new File(System.getProperty("user.dir") + File.separator + "images", imgName);
		try(InputStream is = new FileInputStream(file)){
			
			pic = new byte[is.available()];
			is.read(pic);
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pic;		
	}
		
		
	

	@Test
	public void test03UpdateEmployeeDetail() {
		System.out.printf("%s()%n", "test03UpdateEmployeeDetail()");
		EmployeeDetail newEmpDetail = new EmployeeDetail(1003 , false, new Date(), "1111", getImage("nicegirl.jpg"));
		int res = dao.updateEmployeeDetail(newEmpDetail);
		Assert.assertEquals(1, res); 
		
		
		
		
		System.out.println(newEmpDetail);
	}

	
	
	
	@Test
	public void test04DeleteEmployeeDetail() {
		System.out.printf("%s()%n", "testDeleteEmployeeDetail()");
		int res = dao.deleteEmployeeDetail(1003);
		Assert.assertEquals(1, res);
		
		
	}

}
