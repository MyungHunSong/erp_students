package erp_students.ui.content;

import javax.swing.JPanel;

import erp_students_dto.Employee;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EmployeeDetailPanel extends AbstractContentPanel<Employee> implements ActionListener {
	private JPasswordField pfPass1;
	private JPasswordField pfPass2;
	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddPic;
	private JLabel lblConfirm;
	
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	public EmployeeDetailPanel() {
		
		initialize();
		loadPic(null);
	}
	private void loadPic(String imgFilePath) {
		Image changeImage = null;
		if(imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "noimage.jpg");
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
			ImageIcon changeIcon = new ImageIcon(changeImage);
			lblPic.setIcon(changeIcon);
	}
	
	
	
	private void initialize() {
		setBorder(new TitledBorder(null, "사원 세부 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);
		
		JPanel pic = new JPanel();
		pTop.add(pic);
		pic.setLayout(new BorderLayout(0, 0));
		
		lblPic = new JLabel("");
		lblPic.setPreferredSize(new Dimension(100, 150));
		lblPic.setHorizontalAlignment(SwingConstants.CENTER);
		pic.add(lblPic, BorderLayout.NORTH);
		
		btnAddPic = new JButton("사진추가");
		btnAddPic.addActionListener(this);
		pic.add(btnAddPic, BorderLayout.SOUTH);
		
		JPanel pItem = new JPanel();
		add(pItem, BorderLayout.CENTER);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 10, 0));
		
		JLabel lblTitleDate = new JLabel("입사일");
		lblTitleDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblTitleDate);
		
		JDateChooser TitleDate = new JDateChooser();
		pContent.add(TitleDate);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);
		
		JPanel pGender = new JPanel();
		pContent.add(pGender);
		
		JRadioButton rdbtnFeMale = new JRadioButton("여성");
		buttonGroup.add(rdbtnFeMale);
		rdbtnFeMale.setSelected(true);
		pGender.add(rdbtnFeMale);
		
		JRadioButton rdbtnMale = new JRadioButton("남성");
		buttonGroup.add(rdbtnMale);
		pGender.add(rdbtnMale);
		
		JLabel lblPass1 = new JLabel("비밀번호");
		lblPass1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		pContent.add(lblPass1);
		
		pfPass1 = new JPasswordField();
		
		pContent.add(pfPass1);
		
		JLabel lalPass2 = new JLabel("비밀번호 확인");
		lalPass2.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lalPass2);
		
		pfPass2 = new JPasswordField();
		pContent.add(pfPass2);
		
		JPanel pSpace = new JPanel();
		pContent.add(pSpace);
		
		lblConfirm = new JLabel("New label");
		lblConfirm.setFont(new Font("굴림", Font.PLAIN, 20));
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblConfirm);
	}

	@Override
	public void setItem(Employee item) {
		
		
	}

	@Override
	public Employee getItem() {
		
		return null;
	}

	@Override
	public void validCheck() {
		
		
	}

	@Override
	public void clearTf() {
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			if(e.getActionCommand().equals("사진추가")) {
				actionPerformedBtnAddPic(e);
			}
		}
	}
	

	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG","jpg","png");
		
		
		chooser.showOpenDialog(null);
		

		int res = chooser.showOpenDialog(null);
		if(res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "옵션 선택 않함");
		}
		imgPath = chooser.getSelectedFile().getPath();
		lblPic.setIcon(new ImageIcon(imgPath));
		
	}
	
	// 비번확인 
	
	DocumentListener listener = new DocumentListener() {
		
		@Override
		public void removeUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		

		@Override
		public void insertUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		@Override
		public void changedUpdate(DocumentEvent e) {
			getMessage();
			
		}
		
		private void getMessage() {
			String pw1 = new String(pfPass1.getPassword());
			String pw2 = String.valueOf(pfPass2.getPassword());
			
			if(pw1.equals(pw2)) {
				lblConfirm.setText("일치");
			}else {
				lblConfirm.setText("FireEgg");
			}
			
		}
		
	};
	
}
