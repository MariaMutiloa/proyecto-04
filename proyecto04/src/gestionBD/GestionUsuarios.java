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

	static List<Integer> IdCartonesGanadores = new ArrayList<>();

	public static int getPartidasJugadas(int dni) {
		int resultado = 0;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			logger.info("Conectado a la base de datos para eliminar");
			String sql = "SELECT * from carton";
			try (PreparedStatement psmt = con.prepareStatement(sql)) {
				ResultSet rs = psmt.executeQuery();
				logger.info("Consulta hecha");
				while (rs.next()) {
					if (rs.getInt(2) == dni) {
						resultado = resultado + 1;
					}

				}


				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido realizar la consulta");
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return resultado;
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

				UsuarioExtendido u = new UsuarioExtendido(dni, nombre, apellido, usuario, contrasena, idLigaActual,
						bote);
				listaUsuarios.add(u);
				logger.info("Añadido " + u);
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

	public static List<Integer> cartonesGanadores(List<Integer> idCartonesGanadores) {
		logger.info("Extrayendo los Id de los cartones ganadores");
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT IDCartonB FROM partida");
			while (rs.next()) {
				int id = rs.getInt(1);
				idCartonesGanadores.add(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return idCartonesGanadores;

	}

	public static int getPartidasGanadas(int dni) {
		int resultado = 0;
		int idUsuario = 0;
		List<Integer> idCartonesG = cartonesGanadores(IdCartonesGanadores);
		for (Integer id : idCartonesG) {
			try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
				logger.info("Conectado a la base de datos para realizar consulta");
				String sql = "SELECT IDUsuario from carton where  IDCarton = " + id;
				try (PreparedStatement psmt = con.prepareStatement(sql)) {
					ResultSet rs = psmt.executeQuery();
					logger.info("Consulta hecha");
					while (rs.next()) {
						idUsuario = rs.getInt(1);
						if (idUsuario == dni) {
							resultado = resultado + 1;
						}
					}

					rs.close();

				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return resultado;
	}

	public static void eliminar(int dni) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
			logger.info("Conectado a la base de datos para eliminar");
<<<<<<< HEAD
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM usuario WHERE DNI ="+dni);
			pstmt.executeUpdate();
			logger.info("Usuario eliminado");
			
=======
			String sql = "DELETE FROM usuario WHERE Dni =" + ParteDni;
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				logger.info("Delete hecho");
				rs.close();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido realizar la consulta");
			}

>>>>>>> branch 'master' of https://github.com/MariaMutiloa/proyecto-04.git
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}

	}

}
