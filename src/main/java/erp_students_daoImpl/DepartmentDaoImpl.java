package erp_students_daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_students.erpdatabase.JdbcConn;
import erp_students_dao.DepartmentDao;
import erp_students_dto.DepartmentDto;


public class DepartmentDaoImpl implements DepartmentDao {

	private static final  DepartmentDaoImpl instance = new DepartmentDaoImpl();
	
	
	

	public static DepartmentDaoImpl getInstance() {
		
		return instance;
	}
	
	

	public DepartmentDaoImpl() {
		
	}
	
	
	



	@Override
	public List<DepartmentDto> selectByAll() {
		String sql = "select deptNo, deptName, floor from department";
		 try(Connection con = JdbcConn.getconnection();){
			PreparedStatement pstmt = con.prepareStatement(sql); // 이거 머하는놈임?
			ResultSet rs = pstmt.executeQuery();{
				if(rs.next()) {
					List<DepartmentDto> list = new ArrayList<>();
					
					do { 
						list.add(getDepartment(rs)); // getDepartment 에서 작업한 내용을 여기에다가 추가
						
					}while(rs.next()); 
					
					return list; // 리스트를 리턴해돌라
				}
			}
			 
		 }catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private DepartmentDto getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptNo");
		String deptName = rs.getString("deptName");
		int floor = rs.getInt("floor");
		return new DepartmentDto(deptNo, deptName,floor);
	}
	
	
	
	
	



	@Override
	public DepartmentDto selectDepartmentByNo(DepartmentDto department) {
		String sql = "select  deptNo, deptName, floor from department where deptNo = ?";
		try(Connection con = JdbcConn.getconnection();
			PreparedStatement ptsmt = con.prepareStatement(sql)){
			ptsmt.setString(1, department.getDeptName());
			System.out.println(ptsmt);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
		return null;
	}
	
	
	
	
	
	
	
	

	@Override
	public int insertDepartment(DepartmentDto department) {
		String sql = "insert into department values (?, ?, ?)";
		try(Connection con = JdbcConn.getconnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
		pstmt.setInt(1, department.getDeptNo());
		pstmt.setString(2, department.getDeptName());
		pstmt.setInt(3, department.getFloor());
		
		return pstmt.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return 0;
}
	
	
	
	
	
	
	
	
	
	@Override
	public int updateDepartment(DepartmentDto department) {
		
		String sql = "update department set deptNo = ? where deptName = ?";
		try(Connection con = JdbcConn.getconnection();
				
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			
			
			
			return pstmt.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public int deleteDepartment(int departmentNo) {
		
		String sql = "delete from department where deptNo = ?";
		try(Connection con = JdbcConn.getconnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setInt(1, departmentNo);		
			
			return pstmt.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return 0;
	}



	
	
	
}
