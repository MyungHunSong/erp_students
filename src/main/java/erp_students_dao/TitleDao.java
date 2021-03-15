package erp_students_dao;

import java.util.List;

import erp_students_dto.EmployeeDto;
import erp_students_dto.TitleDto;

/*	
    R(Select, select where, )
	Create(insert)
	U(update)
	D(delete)
*/
public interface TitleDao {
	List<TitleDto> selectTitleByAll();
	TitleDto selectTitleByNo(TitleDto title); // 하나의 결과 값만 나오기에 (객체지향언어 이기에 안에다 객체로 주고받는다.)
	
	
	int insertTitle(TitleDto title);
	int updateTitle(TitleDto title);
	int deleteTitle(int titleNo);
	
	//int empListByTitleItem(Title empNo); 
	
	
}
