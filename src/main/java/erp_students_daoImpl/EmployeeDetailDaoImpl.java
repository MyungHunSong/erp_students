package erp_students_daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;



import erp_students.erpdatabase.JdbcConn;
import erp_students.ui.content.EmployeeDetail;
import erp_students.ui.exception.SqlConstraintException;
import erp_students_dao.EmployeeDetailDao;
import erp_students_dto.Employee;



public class EmployeeDetailDaoImpl implements EmployeeDetailDao {
	

	private static EmployeeDetailDaoImpl instance = new EmployeeDetailDaoImpl(); // 이게 싱글톤 패턴 --> 하나 생성하더라도 하나의 값으로 리턴 해준다

	public static EmployeeDetailDaoImpl getInstance() {
		if (instance == null) { // 원래 이게 들어가는게 맞다.
			instance = new EmployeeDetailDaoImpl();
		}
		return instance;
	}
	
	private EmployeeDetailDaoImpl() {}
	
	
	@Override
	public EmployeeDetail selectEmployeeDetailByNo(Employee employee) {
		String sql = "select empno, pic, gendel, startDate, pass from erp_detail where empno = ?";
		
		try(Connection con = JdbcConn.getconnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1, employee.getEmpNo());
			
			
			try(ResultSet rs = pstmt.executeQuery()){
				return getEmployeeDetail(rs);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	
}
	
	
	
	
	
	

	private EmployeeDetail getEmployeeDetail(ResultSet rs) throws SQLException{
		int empNo = rs.getInt("empno");
		boolean gender = rs.getBoolean("gendel");
		Timestamp hireDate = rs.getTimestamp("startDate");
		byte[] pic = rs.getBytes("pic");
		return new EmployeeDetail(empNo, gender, hireDate, pic);
	}


	


	
	
	
	
	
	

	@Override
	public int insertEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "insert into erp_detail(empno, pic, gendel, startDate, pass) values(?,?,?,?,?)"; // 패스어드
			try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, empDetail.getEmpNo());
			pstmt.setBytes(2, empDetail.getPic());
			pstmt.setBoolean(3, empDetail.isGender());
			pstmt.setTimestamp(4, new Timestamp(empDetail.getHireDate().getTime()));
			pstmt.setString(5, empDetail.getPass());
			

			return pstmt.executeUpdate(); // 리턴

		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}

	}

	

	@Override
	public int deleteEmployeeDetail(int employeeNo) {
		String sql = "delete from erp_detail where empno = ?";
		try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, employeeNo);

			return pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int updateEmployeeDetail(EmployeeDetail empDetail) {
		String sql = "update erp_detail  set pic=?, gendel =?, startDate = ?, pass = ? where empno = ?;";
		try(Connection con = JdbcConn.getconnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setBytes(1, empDetail.getPic());
			pstmt.setBoolean(2, empDetail.isGender());
			pstmt.setTimestamp(3, new Timestamp(empDetail.getHireDate().getTime()));
			pstmt.setString(4, empDetail.getPass());
			pstmt.setInt(5, empDetail.getEmpNo());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("고장");
			e.printStackTrace();
		}
		return 0;
	}

	

}
