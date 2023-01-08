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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import personas.Usuario;
import personas.UsuarioExtendido;

public class GestionUsuarios {
	
	private static Logger logger = Logger.getLogger(GestionUsuarios.class.getName());

	public static int getPartidasJugadas(int dni) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public static List<UsuarioExtendido> getAllUsuarios() {
		logger.info("Extrayendo todos los usuarios");
		List<UsuarioExtendido> listaUsuarios = new ArrayList<UsuarioExtendido>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
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

				UsuarioExtendido u = new UsuarioExtendido(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
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


	public static int getPartidasGanadas(int dni) {
		// TODO Auto-generated method stub
		return 0;
	}


	public static void eliminar(int ParteDni) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			logger.info("Conectado a la base de datos para eliminar");
			String sql = "DELETE from usuario where DNI like %" +ParteDni + "%";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				logger.info("Delete hecho");
				rs.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido realizar la consulta");
			}

		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		
	}

}
