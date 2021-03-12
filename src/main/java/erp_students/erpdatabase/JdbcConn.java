package erp_students.erpdatabase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class JdbcConn {
		Connection con = JdbcConn.getconnection();
		 // con > com.mysql.jdbc.JDBC4Connection@2cdf8d8a 연결 성공 표시
	

	public static Connection getconnection() {

		String propertiesPath = "db.properties"; // 이게 이름이 동일해야한다
		Connection con = null;
		try (InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)) { // 리소스에 있는 파일을 불러 올려면 이방법 을 써야한다
																						// 이걸 stream 의 형태로 가져온다
			Properties prop = new Properties(); // 요기다가 담고 키 = 벨류(값) 타입으로 돌려준다
			prop.load(in);
			con = DriverManager.getConnection(prop.getProperty("url"), prop);

			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}// properties 의 특징이라 
