package erp_students.ui.content;


import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import erp_students.ui.content.list.TitleTablePanel;
import erp_students.ui.exception.InvalidCheckException;
import erp_students.ui.exception.NotSelectedException;
import erp_students.ui.exception.SqlConstraintException;
import erp_students.ui.service.TitleService;
import erp_students_dto.TitleDto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TitleManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnAdd;
	private TitlePanel pContent;
	private TitleTablePanel pList;
	private TitleService service;
	private JButton btnClear;
	
	

	public TitleManager() { // service 친구를 위에다가 무조건 뺴줘야한다.
		service = new TitleService();
		initialize();
		
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "직책관리", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pContent = new TitlePanel();
		contentPane.add(pContent);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtn.add(btnAdd);
		
		btnClear = new JButton("삭제");
		btnClear.addActionListener(popupMenuListener);
		pBtn.add(btnClear);
		
		pList = new TitleTablePanel();
		pList.setService(service); // 셋서비스를 만들어준 후에
		pList.loadData();   // 로드 데이타!
		contentPane.add(pList);
		
		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
		
		
		
	
	}
	
	// 팝업 매뉴맨
		private JPopupMenu createPopupMenu() {
			
			JPopupMenu popMenu = new  JPopupMenu();
			
			JMenuItem updateItem = new JMenuItem("수정");
			updateItem.addActionListener(popupMenuListener);
			popMenu.add(updateItem);
			
			JMenuItem deleteItem = new JMenuItem("삭제");
			deleteItem.addActionListener(popupMenuListener);
			popMenu.add(deleteItem);
			
			JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
			empListByTitleItem.addActionListener(popupMenuListener);
			popMenu.add(empListByTitleItem);
			
			return popMenu;
		}
		
	

	ActionListener popupMenuListener = new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
			if(e.getActionCommand().equals("삭제")) {
				TitleDto delTitle = pList.getItem();
				service.removeTitle(delTitle);
				pList.loadData();
				JOptionPane.showMessageDialog(null, delTitle + "삭제 되었습니다.");
			}
			if(e.getActionCommand().equals("수정")) {
				TitleDto updateTitle = pList.getItem();
				pContent.setTitle(updateTitle);
				btnAdd.setText("수정");
			}
			
			
			}catch (NotSelectedException | SqlConstraintException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			}
		};
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
				if(e.getActionCommand().contentEquals("추가")) {
				actionPerformedBtnAdd(e);
			}else {
				actionPerformedBtnUpdate(e);
			}
		}
		}catch (InvalidCheckException | SqlConstraintException e1) { // 요길 잘바도라 여기가 결국 액션퍼폼 메인이다
			JOptionPane.showMessageDialog(null, e1.getMessage());
			pContent.clearTf();
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
		
	}
	
	private void actionPerformedBtnUpdate(ActionEvent e) {
		
		TitleDto updateTitle = pContent.getTitle(); // pContent에서 수정된 title 가져오기
		service.modify(updateTitle); //sql에 들어가는것 (update 수행)
		
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, "변경 되엇다.");
		
	}
	protected void actionPerformedBtnAdd(ActionEvent e) {
		TitleDto title = pContent.getTitle();
		
		service.addTitle(title);
	
		pList.loadData(); // 추가후에 리스트도 새로고침이 되어야 하잔아 그레서 마지막엔 이걸 써주는거다
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, title + "추가됬음.");
		
		btnAdd.setText("수정");
		
	}
	
	
	
	
	
}
