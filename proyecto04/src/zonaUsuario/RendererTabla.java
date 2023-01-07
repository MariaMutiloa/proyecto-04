package zonaUsuario;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;

public class RendererTabla extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[][] pintar;

	public RendererTabla(int[][] datosColores) {
		this.pintar = datosColores;
		}
	
	public void setColores(int[][] datosColores) {
		this.pintar = datosColores;
	}
	
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row,int column) {
		JLabel label = new JLabel();
		label.setAlignmentX(CENTER_ALIGNMENT);
		label.setText(((Object)value).toString());
		if(pintar[row][column] == 1) {
			label.setOpaque(true);
			label.setBackground(Color.CYAN);
		}else {
			label.setOpaque(true);
			label.setBackground(Color.GRAY);
		}
		
		return label;
		
	}

}
