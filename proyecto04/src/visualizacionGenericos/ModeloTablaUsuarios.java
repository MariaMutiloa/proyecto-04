package visualizacionGenericos;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import elementosOrganizacion.Carton;


public class ModeloTablaUsuarios implements TableModel {
	
	private List<Carton> cartones;
	
	public ModeloTablaUsuarios(List<Carton> cartones) {
		this.cartones = cartones;
	}

	@Override
	public int getRowCount() {
		return cartones.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 1:
			return "Numero de carton";
		case 2:
			return "ID Usuario";
		case 3: 
			return "Nombre del usuario";
		default:
			return null;
		}
	}

	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Carton cartonSel = cartones.get(rowIndex);
		switch (columnIndex) {
		case 1:
			return cartonSel.getIDCarton();
		case 2:
			return cartonSel.getIDUsuario();
		case 3: 
			return cartonSel.getIDUsuario();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
