package erp_students.ui.service;

import java.util.List;

import erp_students_dao.TitleDao;
import erp_students_daoImpl.TitleDaoImpl;
import erp_students_dto.Title;

public class TitleService { // 여기서 가져야 하는것은 title dao
	private TitleDao dao = TitleDaoImpl.getInstance();
	
	public List<Title> showTitles(){ // 다장 필요한것은 이것이다 (타이틀 타입 리스트를 보여달라 이새끼야)
		return dao.selectTitleByAll(); //요렇게 받아와서 수행하면 그만이잖아
	}
	
	public void addTitle(Title title) {
		dao.insertTitle(title);
		
	}
	
	public void removeTitle(Title title) {
		dao.deleteTitle(title.gettNo());
	}
	
	public void modify(Title title) {
		dao.updateTitle(title);
	}
	
			
}
