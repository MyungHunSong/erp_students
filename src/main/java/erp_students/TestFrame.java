package erp_students;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_students.ui.content.list.DepartmentTablePanel;
import erp_students.ui.content.list.TitleTablePanel;

public class TestFrame extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TestFrame() {
		initialize();
	}
	
	
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		TitleTablePanel panel = new TitleTablePanel();
		panel.loadData();
		contentPane.setLayout(new GridLayout(0, 1, 10, 10));
		contentPane.add(panel);
		
		DepartmentTablePanel panel2 = new DepartmentTablePanel();
		panel2.loadData();
		contentPane.add(panel2);
	}

}
