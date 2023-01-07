package zonaAdministrador.GestionUsuarios;

import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import personas.Usuario;

public class ModeloTablaUsuariosDatos implements TableModel {
	
	private List<Usuario> usuarios;
	
	public void ModeloTablaUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
	}

	@Override
	public int getColumnCount() {
		return 11;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 1:
			return "Nombre";
		case 2:
			return "Apellido";
		case 3: 
			return "Nombre Usuario";
		case 4:
			return "DNI";
		case 5:
			return "Contraseña";
		case 6:
			return "Part.jugadas";
		case 7:
			return "Part.ganadas";
		case 8:
			return "Part.perdidas";
		case 9:
			return "Liga";
		case 10:
			return "Puesto";
		case 11:
			return "Bote";
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
		String valor;
		Usuario usuariosSel = usuarios.get(rowIndex);
		switch (columnIndex) {
		case 1:
			return usuariosSel.getNombre();
		case 2:
			return usuariosSel.getApellido();
		case 3: 
			return usuariosSel.getUsuario();
		case 4:
			return usuariosSel.getDni();
		case 5:
			return usuariosSel.getContrasena();
		case 6:
			return usuariosSel.getPartidasJ();
		case 7:
			return usuariosSel.getPartidasG();
		case 8:
			return usuariosSel.getPartidasP();
		case 9:
			return usuariosSel.getIdLigaActual();
		case 10:
			return usuariosSel.getPuesto();
		case 11:
			return usuariosSel.getBote();
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


