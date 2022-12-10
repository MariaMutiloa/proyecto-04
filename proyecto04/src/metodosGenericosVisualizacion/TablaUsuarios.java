package metodosGenericosVisualizacion;

import java.util.List;


import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import personas.Usuario;

//Una tabla que se puede añadir a cualquier ventana que visualiza una lista de usuarios

public class TablaUsuarios  {
	
	public JTable tablaNueva(List<Usuario> lista) {
		
        DefaultTableModel modelo = new DefaultTableModel();
      
        modelo.addColumn("Usuario");
        modelo.addColumn("Liga Actual");
        modelo.addColumn("Cartera");
		
        for (Usuario u: lista) {
			modelo.addRow(new Object[] {u.getUsuario(), u.getIdLigaActual(), u.getBote()});
		}
		
		JTable tabla = new JTable(modelo);
		
		
		return tabla;
		
	}
}

