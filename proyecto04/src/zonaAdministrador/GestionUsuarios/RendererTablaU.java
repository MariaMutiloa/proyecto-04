package zonaAdministrador.GestionUsuarios;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class RendererTablaU extends JLabel implements TableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String searchStr = null; 
	
	public void setSearch(String searchStr) {
		this.searchStr = searchStr;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		this.setText(value.toString());
		this.setFont(new Font("Helvetica Bold", Font.PLAIN,12));
		
		String Dni =  (table.getModel().getValueAt(row, 0)).toString();
		if (searchStr != null && !searchStr.isEmpty() && Dni.contains(searchStr)) {
			this.setOpaque(true);
			this.setBackground(new Color(255, 200, 200));
		} else {
			this.setOpaque(false);
		}
		
		return this;
	}
}