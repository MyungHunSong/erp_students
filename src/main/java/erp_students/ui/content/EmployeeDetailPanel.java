package erp_students.ui.content;

import javax.swing.JPanel;

import erp_students_dto.Employee;
import erp_students_dto.EmployeeDetail;

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
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.util.Date;
import com.toedter.calendar.JDateChooser;

import erp_students.ui.exception.InvalidCheckException;

import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EmployeeDetailPanel extends AbstractContentPanel<EmployeeDetail> implements ActionListener {

	private JPasswordField pfPass1;
	private JPasswordField pfPass2;
	
	private String imgPath = System.getProperty("user.dir") + File.separator + "images" + File.separator;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));


	private JLabel lblPic;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnAddPic;
	private JLabel lblConfirm;

	private JTextField tfempNo;
	private JRadioButton rdbtnFeMale;
	private JRadioButton rdbtnMale;
	private JDateChooser dateHire;
	
	
	
	public EmployeeDetailPanel() {

		initialize();
		loadPic(null);
	}
	
	

	private void loadPic(String imgFilePath) {
		Image changeImage = null;
		if (imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "noimage.jpg");
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		} else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon changeIcon = new ImageIcon(changeImage);
		lblPic.setIcon(changeIcon);
	}
	
	
	
	// setTfEmpno이다.
	public void setTfEmpno(Employee empNo) {
		tfempNo.setText(String.valueOf(empNo.getEmpNo()));
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

		JLabel lblempNo = new JLabel("사원번호");
		lblempNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblempNo);

		tfempNo = new JTextField();
		tfempNo.setEditable(false);
		pContent.add(tfempNo);
		tfempNo.setColumns(10);

		JLabel lblhireDate = new JLabel("입사일");
		lblhireDate.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblhireDate);

		dateHire = new JDateChooser();
		pContent.add(dateHire);

		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);

		JPanel pGender = new JPanel();
		pContent.add(pGender);

		rdbtnFeMale = new JRadioButton("여");
		buttonGroup.add(rdbtnFeMale);
		rdbtnFeMale.setSelected(true);
		pGender.add(rdbtnFeMale);

		rdbtnMale = new JRadioButton("남");
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

		lblConfirm = new JLabel();
		lblConfirm.setFont(new Font("굴림", Font.PLAIN, 20));
		lblConfirm.setForeground(Color.RED);
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		pContent.add(lblConfirm);
	}

	@Override
	public void setItem(EmployeeDetail item) {
		tfempNo.setText(String.valueOf(item.getEmpNo()));
		byte[] iconBytes = item.getPic();
		
		ImageIcon icon = new ImageIcon(iconBytes);
		lblPic.setIcon(icon);
		dateHire.setDate(item.getHireDate());
		if(item.isGender()) {
			rdbtnFeMale.setSelected(true);
		}else {
			rdbtnMale.setSelected(false);
		}
		
	}
	
	

	@Override
	public EmployeeDetail getItem() {
		int empNo = Integer.parseInt(tfempNo.getText().trim() );
		boolean gender = rdbtnFeMale.isSelected()?true:false;
		Date hireDate = dateHire.getDate();
		String pass = String.valueOf(pfPass1.getPassword());
		byte[] pic = getImage();
		
		return new EmployeeDetail(empNo, gender, hireDate, pass, pic);
	}
	
	

	private byte[] getImage() {

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage b1 = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

			// 요 3개가 실제 이미지로 만들어주는 방법이다
			Graphics2D g2 = b1.createGraphics(); // 받아와서 그래픽을 만들겟다
			g2.drawImage(icon.getImage(), 0, 0, null);
			g2.dispose();
			//

			ImageIO.write(b1, "jpg", baos);
			return baos.toByteArray(); // 바이트 형식으로
			
		} catch (Exception e) {

		}
		return null;
	}
	

	@Override
	public void validCheck() {
		if (lblConfirm.getText().equals("불일치")) {
			throw new InvalidCheckException("비밀번호 불일치");
		}

	}

	@Override
	public void clearTf() {
		loadPic(null);
		dateHire.setDate(new Date());
		rdbtnFeMale.setSelected(true);
		pfPass1.setText("");
		pfPass2.setText("");
		lblConfirm.setText("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddPic) {
			if (e.getActionCommand().equals("사진추가")) {
				actionPerformedBtnAddPic(e);
			}
		}
	}

	protected void actionPerformedBtnAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
		chooser.setFileFilter(filter);
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		chooser.showOpenDialog(null);

		int res = chooser.showOpenDialog(null);
		if (res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "옵션 선택 않함");
			return;
		}
		String chooserFilePath = chooser.getSelectedFile().getPath();
		loadPic(chooserFilePath);

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

			if (pw1.equals(pw2)) {
				lblConfirm.setText("일치");
			} else {
				lblConfirm.setText("불일치");
			}

		}

	};

	

	

}
