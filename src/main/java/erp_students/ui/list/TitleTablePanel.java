package erp_students.ui.list;

import javax.swing.SwingConstants;

import erp_students.ui.service.TitleService;
import erp_students_dto.Title;

@SuppressWarnings("serial")
public class TitleTablePanel extends AbstractCustomTablePanel<Title> {
	public TitleTablePanel() {
	}
	private TitleService service; // 여기서 똑같은 객체가 2마리 잇을필요 없음
	
	
	@Override
	public String[] getColumnNames() {
		
		return new String[] {"직책번호", "직책명"};
	}

	@Override
	protected void setAlignAndWidth() {
		
		 //컬럼내용 정렬
		 setTableCellAlign(SwingConstants.CENTER, 0, 1);
		 
		 //컬럼 너비 조정
		 setTableCellWidth( 100, 250);
		
	}

	@Override
	protected Object[] toArray(Title t) {
		
		return new Object[] {t.gettNo(),t.gettName()};
	}

	@Override
	public void initList() {
		list = service.showTitles(); // list에 있는 service.showTitles(); 를 호출하게 되면 dao.selectByAll 에 잇는 치구들이 호출됨
		
	}

	public void setService(TitleService service) {
		 this.service = service;
		
	}
	
	

}
