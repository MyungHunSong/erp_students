package erp_students.erpdatabase;



import java.sql.Connection;


import org.junit.Assert;
import org.junit.Test;


public class JdbcConnTest { // 차피 이것만 테스트 할꺼다.
	
	@Test
	public void testGetconnection() {
		System.out.printf("%s()%n", "testGetconnection()");
		Connection con = JdbcConn.getconnection();
		System.out.println("con > " + con);
		Assert.assertNotNull(con);
	}

}
