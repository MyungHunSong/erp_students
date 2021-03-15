package erp_students.ui.content;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import erp_students.ui.exception.InvalidCheckException;
import erp_students_dto.TitleDto;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel {
	private JTextField tfTno;
	private JTextField tfTname;

	
	public TitlePanel() {

		initialize();
	}
	
	
	
	//set으로 세팅
	public void setTitle(TitleDto title) {
		tfTno.setText(title.gettNo() + "");
		tfTname.setText(title.gettName());
	}
	
	
	
	// get으로 받아오는것
	public TitleDto getTitle() { // 여기서 중요한것 입력 않햇는데도 타이틀 개체가 생성될수 있다. 그러니 밸리드 체크해줘야함
		validCheck();
		
		int tno = Integer.parseInt(tfTno.getText().trim());
		String tName = tfTname.getText().trim();
		
		return new TitleDto(tno, tName);
	}
	
	
	
	// add 를 위한 validCheck
	private void validCheck() {
		if(tfTno.getText().contentEquals("") || tfTname.getText().equals("")) {
			
			throw new InvalidCheckException();
		}
	}
	
	
	
	
	public void clearTf() {
		tfTno.setText("");
		tfTname.setText("");
	}
	
	
	
	
	
	
	
	
	private void initialize() {
		setBorder(new TitledBorder(null, "직책 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblTno = new JLabel("직책 번호");
		lblTno.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTno);
		
		tfTno = new JTextField();
		add(tfTno);
		tfTno.setColumns(10);
		
		JLabel lalTname = new JLabel("직책 이름");
		lalTname.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lalTname);
		
		tfTname = new JTextField();
		tfTname.setColumns(10);
		add(tfTname);

//		EmployeeService service = new EmployeeService();
//		EmployeePanel pEmpItem = new EmployeePanel();
//		pEmpItem.setService(service);
//		
//		contentPane.add(pEmpItem);
	}
	
	
	

}
