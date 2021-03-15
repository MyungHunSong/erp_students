package erp_students.ui.service;

import java.util.List;

import erp_students_dao.EmployeeDao;
import erp_students_dao.TitleDao;
import erp_students_daoImpl.EmployeeDaoImpl;
import erp_students_daoImpl.TitleDaoImpl;
import erp_students_dto.EmployeeDto;
import erp_students_dto.TitleDto;

public class TitleService { // 여기서 가져야 하는것은 title dao
	private TitleDao dao = TitleDaoImpl.getInstance();
	private EmployeeDao empDao = EmployeeDaoImpl.getInstance();
	
	public List<TitleDto> showTitles(){ // 다장 필요한것은 이것이다 (타이틀 타입 리스트를 보여달라 이새끼야)
		return dao.selectTitleByAll(); //요렇게 받아와서 수행하면 그만이잖아
	}
	
	public void addTitle(TitleDto title) {
		dao.insertTitle(title);
		
	}
	
	public void removeTitle(TitleDto title) {
		dao.deleteTitle(title.gettNo());
	}
	
	public void modify(TitleDto title) {
		dao.updateTitle(title);
	}
	
	public List<EmployeeDto> showEmployeeGroupBy(TitleDto title){
		return empDao.selectEmployeeByAllTitle(title);
	}
			
}
