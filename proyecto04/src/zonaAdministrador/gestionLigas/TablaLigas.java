package zonaAdministrador.gestionLigas;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import personas.Usuario;

public class TablaLigas extends AbstractTableModel{


	private String[] cabecera = { "Usuario", "Nombre", "Apelllido", "Bote" };
	private List<Usuario> usuarios;

	public TablaLigas(List<Usuario> lista) {
		this.usuarios = lista;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}
