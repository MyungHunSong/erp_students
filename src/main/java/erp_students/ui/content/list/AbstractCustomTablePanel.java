package erp_students.ui.content.list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import erp_students.ui.exception.NotSelectedException;
import erp_students_dto.TitleDto;



@SuppressWarnings("serial")
public abstract class AbstractCustomTablePanel<T> extends JPanel {
	protected JTable table;
	protected List<T>  list;
	
	

	
	public AbstractCustomTablePanel() {
		
		initialize();
		
	}
	
	
	public T getItem() {
		int idx = table.getSelectedRow(); //  겟로우 (위치를 가져 올수 있는기능)
		if(idx == -1) {
			throw new NotSelectedException();
		}
		
		return list.get(idx);
	}

	
	
	
	// 	 요게 데이터를 로드해주는 기능을 한다
	public void loadData() {
		initList();
		setList(); // 갖다 넣자 마자 여기에서 받아서 다들어옴
		
	}
	
	
	//팝업 매뉴 메서드
	public void setPopupMenu(JPopupMenu popMenu) {
		table.setComponentPopupMenu(popMenu);
	}
	
	
	public abstract void initList(); // 다틀리니까 하위에서 알아서해라
		
		
	


	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(getModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
	}
	
	
	
	private DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel(getData(), getColumnNames());
		
		return model;	
	}
	
	
	
	
	// ------getData-----
	private Object[][] getData() {
		return new Object[][] {
			{null, null, null},};
	}
	
	
	// ------getColumn----
	public abstract String[] getColumnNames();
	
	protected abstract  Object[] toArray(T t);
	
	
	
	
	
// 	---------setList abstract 로 만든것-------
	public  void setList() {
		Object[][]data = new Object[list.size()][]; // 
		 
		 for(int i =0; i < data.length; i++) {
			 data[i] = toArray(list.get(i));
		 }
		 CustomTableModel model = new CustomTableModel(data, getColumnNames());
		 table.setModel(model); // 원래 없는디 data가 들어가서 model을 세팅해줘서 만들어 주는것
		 
		 RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		 table.setRowSorter(sorter); // 정렬뺌
		 
		 
		 setAlignAndWidth();
	}
	
	
	
	
	
	

	
	
	protected abstract void setAlignAndWidth();
	
	
	
	
	// -------------
	public void setTableCellAlign(int align, int...idx) {
		TableColumnModel tcm = table.getColumnModel();
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		for(int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	
	}
	
	
	
	

	
	
	
	
	
	// -----------
	public void setTableCellWidth(int...width) {
		TableColumnModel tcm = table.getColumnModel();
		

		for(int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}
	
	
	
	
	





	// customtableModel 이다.
	class CustomTableModel extends DefaultTableModel {

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);

		}

		
		@Override
		public boolean isCellEditable(int row, int column) {

			return false;
		}
	}
	

}
