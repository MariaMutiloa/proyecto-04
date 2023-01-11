package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

/**
 * 
 * INCLUYE TODOS LOS MÉTODOS QUE ACCEDEN A BD PARA LA GESTION DE LIGAS
 *
 */

public class GestionLigasBD {
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());

	//	Recoge todas las ligas existentes
	public static Integer[] getLigas(String bd) {
		Set<Integer> ligas = new TreeSet<Integer>();
		logger.info("Extrayendo las ligas actuales");
		try (Connection con = DriverManager.getConnection(bd)) {

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

	//Devuelve todos los usuarios que estn asignados a una liga
	public static List<Usuario> getUsuariosLiga(int selectedItem, String bd) {
		logger.info("Buscando usuarios de la liga " + selectedItem);
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try (Connection con = DriverManager.getConnection(bd)) {
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
				logger.info("AÃ±adido "+u);
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
				if (o1.getBote() > o2.getBote()) {
					return -2;
				} else {
					return 1;
				}

			}

		});
		return listaUsuarios;
	}
	
	
	//Extrae todos los usuarios
	public static List<Usuario> getAllUsuarios(String bd) {
		logger.info("Extrayendo todos los usuarios");
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try (Connection con = DriverManager.getConnection(bd)) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
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
				logger.info("AÃ±adido "+u);
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
	
	//Dependiendo el numero de usuarios crea las ligas correspondientes (10 usuarios por liga) y asigna de manera orgenada las ligas según el bote
	public static void actualizarLigas(String bd) {
		logger.info("Actualizando ligas");
		String valor;
		List<Usuario> listaUsuarios = getAllUsuarios(bd);
		logger.info("Num usuarios: "+listaUsuarios.size());
		try (Connection con = DriverManager.getConnection(bd)) {
			logger.info("Conexion establecida");
			for (Integer contador = 1; contador < listaUsuarios.size(); contador++) {
				logger.info("Entrando para usuario " +contador);
				String[] digits = (contador.toString()).split("(?<=.)");
				System.out.println(digits);
				if (digits.length == 1) {
					valor = "0";

				} else {
					valor = digits[digits.length - 2];
				}
				logger.info("El valor es " + valor);
				PreparedStatement actualizacion = con
						.prepareStatement("UPDATE usuario SET IDLigaActual = " + valor + " WHERE Dni = " + listaUsuarios.get(contador-1).getDni());
				actualizacion.executeUpdate();
			}
			logger.info("Usuarios actualizados con ligas");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
	}
}
