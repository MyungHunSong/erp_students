package erp_students.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import erp_students.ui.content.AbstractContentPanel;
import erp_students.ui.exception.InvalidCheckException;
import erp_students.ui.exception.SqlConstraintException;
import erp_students.ui.list.AbstractCustomTablePanel;
import erp_students.ui.list.TitleTablePanel;
import erp_students.ui.service.TitleService;
import erp_students_dto.Title;

@SuppressWarnings("serial")
public abstract class AbstractManagerUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;

	protected  AbstractContentPanel<T> pContent;
	protected  AbstractCustomTablePanel<T> pList;

	private TitleService service;

	public AbstractManagerUI() {

		setService(); // setService

		initialize();

		tableLoadData(); // component loaddata
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = createContentPanel();
		contentPane.add(pContent);

		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		JButton btnClear = new JButton("취소");
		pBtns.add(btnClear);

		pList = createTablePanel();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	// abstract 추상들

	

	private JPopupMenu createPopupMenu() {
		JPopupMenu popMenu = new JPopupMenu();

		JMenuItem updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		JMenuItem deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);

		JMenuItem empListByTitleItem = new JMenuItem("동일 직책 사원 보기");
		empListByTitleItem.addActionListener(this);
		popMenu.add(empListByTitleItem);

		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			//만약 소스가 jMenu 랑 동일하다면 지금 if 문수행
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete(e);
				}

				if (e.getActionCommand().equals("수정")) {
					acitonPerformedMenuUpdate();
				}

				if (e.getActionCommand().contentEquals("동일 직책 사원 보기")) {
					actionPerformedMenuGuBun();
				}
			} else {

				// 위쪽과 아래쪽은 구분 해줘야 한다.
				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	
	protected abstract void setService();

	protected abstract void tableLoadData();
	
	protected abstract AbstractContentPanel<T> createContentPanel();

	protected abstract AbstractCustomTablePanel<T> createTablePanel();

	

	// 동일직책사원
	protected abstract void actionPerformedMenuGuBun(); 
	
	protected abstract void acitonPerformedMenuUpdate();
	
	protected abstract void actionPerformedMenuDelete(ActionEvent e);
	
	// 
	protected abstract void actionPerformedBtnAdd(ActionEvent e); 
	
	protected abstract void actionPerformedBtnUpdate(ActionEvent e);

	
}