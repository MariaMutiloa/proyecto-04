package visualizacionGenericos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import personas.Usuario;


public class NuestraTabla extends AbstractTableModel {
	
	 
	private String[] cabecera = { "Usuario", "Nombre", "Apelllido", "Bote" };
	private List<Usuario> usuarios;

	public NuestraTabla(List<Usuario> lista) {
		this.usuarios = lista;
	}


	@Override
	public int getRowCount() {
		return usuarios.size();
		
	}
	
	  @Override
      public String getColumnName(int index) {
          return cabecera[index];
      }

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (usuarios.size() != 0) {
			Usuario u = usuarios.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return u.getUsuario();
			case 1:
				return u.getNombre();
			case 2:
				return u.getApellido();
			case 3:
				return u.getBote();
			default:
				return null;
			}
		} else {
			return "";
		}
	}
	
	 @Override
     public boolean isCellEditable(int row, int column) {
         return false; //No es editable
     }

}
