package erp_students_daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import erp_students.erpdatabase.JdbcConn;
import erp_students.ui.exception.SqlConstraintException;
import erp_students_dao.EmployeeDao;
import erp_students_dto.Department;
import erp_students_dto.Employee;
import erp_students_dto.Title;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl instance = new EmployeeDaoImpl(); // 이게 싱글톤 패턴 --> 하나 생성하더라도 하나의 값으로 리턴 해준다

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) { // 원래 이게 들어가는게 맞다.
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	private EmployeeDaoImpl() {

	}

	@Override
	public List<Employee> selectEmployeeByAll() {
		String sql = "select empno,empname,title_no,title_name,manager_no,manager_name,salary,deptNo,deptName,floor from vw_full_employee";
		try (Connection con = JdbcConn.getconnection();) {
			PreparedStatement pstmt = con.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(); // select 할때만 사용
			{
				if (rs.next()) {
					List<Employee> list = new ArrayList<>();
					do {
						list.add(getEmployee(rs));
					} while (rs.next());

					return list;
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	// -------------------- GetEmployee ----------------------
	private Employee getEmployee(ResultSet rs) throws SQLException { // 조인쉨들 처주는 방법
		int empNo = 0;
		String empName = null;
		Title title = null;
		Employee manager = null;
		int salary = 0;
		Department dept = null;

		try {
		empNo = rs.getInt("empno");

		empName = rs.getString("empName");
		} catch (SQLException e) {}
		try {
		title = new Title(rs.getInt("title_no"));
		
		manager = new Employee(rs.getInt("manager_no"));
		
		salary = rs.getInt("salary");
		
		dept = new Department(rs.getInt("deptNo"));
		} catch (SQLException e) {}
		try {
			title.settName(rs.getString("title_name"));
		} catch (SQLException e) {}
		
		try {
			manager.setEmpName(rs.getString("manager_name"));
		} catch (SQLException e) {}

		try {
			dept.setDeptName(rs.getString("deptName"));
			dept.setFloor(rs.getInt("floor"));
		} catch (SQLException e) {
		}

		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	// -------------------- SelectEmployeeByNo ----------------------
	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select empno, empname, title as title_no, manager as manager_no, salary, dept as deptNo from employee where empno = ?";
		try (Connection con = JdbcConn.getconnection();) {
			PreparedStatement pstmt = con.prepareStatement(sql);
			{
				pstmt.setInt(1, emp.getEmpNo()); // 위에 있는거 쿼리문 세팅(where 절)
				System.out.println(pstmt);

				try (ResultSet rs = pstmt.executeQuery()) { // where 조건절 을 위해서 결과를 가져오는
					if (rs.next()) {
						return getEmployee(rs);
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// -------------------- InsertEmployee ----------------------
	@Override
	public int insertEmployee(Employee emp) {
		String sql = "insert into employee values(?, ?, ?, ? , ?, ?)";
		try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setInt(3, emp.getTitle().gettNo());
			pstmt.setInt(4, emp.getManager().getEmpNo());
			pstmt.setInt(5, emp.getSalary());
			pstmt.setInt(6, emp.getDept().getDeptNo());

			return pstmt.executeUpdate(); // 리턴

		} catch (SQLException e) {
			throw new SqlConstraintException(e.getMessage(), e);
		}

	}

	// -------------------- updateEmployee ----------------------
	@Override
	public int updateEmployee(Employee emp) {

		String sql = "update employee" + "	set  empname=?, title=?,manager=?, salary=?, dept=?"
				+ "	where empno = ?";
		try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getTitle().gettNo());
			pstmt.setInt(3, emp.getManager().getEmpNo());
			pstmt.setInt(4, emp.getSalary());
			pstmt.setInt(5, emp.getDept().getDeptNo());
			pstmt.setInt(6, emp.getEmpNo());

//			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where empno = ?";
		try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setInt(1, emp.getEmpNo());

			return pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Employee> selectEmployeeByAllTitle(Title title) {

		String sql = "select empname, empno from vw_full_employee where title_no = ?";
		try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, title.gettNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Employee> list = new ArrayList<>();
					do {
						list.add(getEmployee(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Employee> selectEmployeeByDept(Department department) {
		String sql = "select empname, empno" + "  from employee e " + "  join department d"
				+ "    on e.dept = d.deptNo " + " where dept = ?";
		try (Connection con = JdbcConn.getconnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, department.getDeptNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					List<Employee> list = new ArrayList<>();
					do {
						list.add(getEmployee3(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Employee getEmployee3(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empName");
		return new Employee(empNo, empName);
	}

}
