package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import personas.Usuario;

public class GestionLigasYEstadisticas {
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());

	public static Integer[] getLigas() {
		Set<Integer> ligas = new TreeSet<Integer>();
		logger.info("Extrayendo las ligas actuales");
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

			while (rs.next()) {
				ligas.add(rs.getInt(6));
			}

			logger.info("Ligas extraidas");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se han podido extraer las ligas", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido acceder a la base de datos");
		}

		Integer[] arrayLigas = new Integer[ligas.size()];
		int contador = 0;
		for (Integer i : ligas) {
			arrayLigas[contador] = i;
			contador++;
		}
		return arrayLigas;
	}


	public static List<Usuario> getUsuariosLiga(int selectedItem) {
		logger.info("Buscando usuarios de la liga " + selectedItem);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario where IDLigaActual = " + selectedItem);
			// recorremos fila a fila
			while (rs.next()) {
				// obtenemos columnas
				int dni = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String usuario = rs.getString(4);
				String contrasena = rs.getString(5);
				int idLigaActual = rs.getInt(6);
				int bote = rs.getInt(7);

				Usuario u = new Usuario(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
				listaUsuarios.add(u);
				logger.info("Añadido "+u);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		listaUsuarios.sort(new Comparator<Usuario>() {

			@Override
			public int compare(Usuario o1, Usuario o2) {
				if (o1.getBote() < o2.getBote()) {
					return -2;
				} else {
					return 1;
				}

			}

		});
		return listaUsuarios;
	}
}
