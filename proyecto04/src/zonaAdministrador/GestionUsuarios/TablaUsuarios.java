package zonaAdministrador.GestionUsuarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import personas.Usuario;
import personas.UsuarioExtendido;


public class TablaUsuarios extends AbstractTableModel {
	
	 
	private String[] cabecera = { "DNI", "Usuario", "Nombre", "Apelllido", "Bote", "Partidas Jugadas",  "Partidas Ganadas", "Liga Actual"};
	private List<UsuarioExtendido> usuarios;

	public TablaUsuarios(List<UsuarioExtendido> usuarios) {
		this.usuarios = usuarios;
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
		return cabecera.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (usuarios.size() != 0) {
			UsuarioExtendido u = usuarios.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return u.getDni();
			case 1:
				return u.getUsuario();
			case 2:
				return u.getNombre();
			case 3:
				return u.getApellido();
			case 4:
				return u.getBote();
			case 5:
				return u.getPartidasJugadas();
			case 6:
				return u.getPartidasGanadas();	
			case 7:
				return u.getIdLigaActual();		
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
