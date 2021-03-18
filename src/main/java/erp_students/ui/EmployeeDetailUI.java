package erp_students.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_students.ui.content.EmployeeDetailPanel;
import erp_students.ui.service.EmployeeDetailService;
import erp_students_dto.Employee;
import erp_students_dto.EmployeeDetail;

@SuppressWarnings("serial")
public class EmployeeDetailUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private EmployeeDetailPanel pItem;
	private JPanel pBtns;
	private JButton btnAdd;

	private EmployeeDetailService service;
	private JButton btnCansle;
	
	
	
	
	
	public EmployeeDetailUI(boolean isBtns, EmployeeDetailService service) {
		this.service = service;
		initialize(isBtns);
	}
	
	
	
	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pItem = new EmployeeDetailPanel();
		contentPane.add(pItem, BorderLayout.CENTER);
		
		pBtns = new JPanel();
		pItem.add(pBtns, BorderLayout.SOUTH);
		

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCansle = new JButton();
		btnCansle.addActionListener(this);
		pBtns.add(btnCansle);
		
		if(isBtns) {
			btnAdd.setText("추가");
			btnCansle.setText("취소");
		}else {
			
			btnAdd.setText("수정");
			btnCansle.setText("삭제");
			}
		}
	
	
	
	public void setEmpNo(Employee empNo) {
		pItem.setTfEmpno(empNo);
	}
	
	public void setDetailItem(EmployeeDetail empDetail) {
		btnAdd.setText("수정");
		pItem.setItem(empDetail);
	}
	
	
	

// --------------------------------------------------------
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().contentEquals("삭제")) {
			actionPerformedBtnDel(e);
		}
		
		if (e.getActionCommand().contentEquals("취소")) {
			actionPerformedBtnCansle(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		EmployeeDetail updateEmpDetail = pItem.getItem();
		service.modifyEmployeeDetail(updateEmpDetail);
		pItem.clearTf();
		JOptionPane.showInternalMessageDialog(null, "수정되엇다");
		dispose();
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		//1. 입력된 empdetail 가져오기.
		//2. service에 적용하기.
		EmployeeDetail newEmpDetail = pItem.getItem(); //가져오기
		service.addEmployeeDetail(newEmpDetail); // 적용하기
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "추가완료");
		dispose();
	}
	
	protected void actionPerformedBtnCansle(ActionEvent e) {
		pItem.clearTf();
		if(btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
		
	}
	
	
	protected void actionPerformedBtnDel(ActionEvent e) {
		EmployeeDetail empDetail = pItem.getItem();
		service.removeEmployeeDetail(new Employee(empDetail.getEmpNo()));
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "삭제완료");
		
	}
}
