package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import personas.Administrador;
import personas.Usuario;

//Contiene todos los metodos de acceso a base de datos que tengan que se puedan usar durante la partida por parte del usuario

public class GestionZonaUsuario {

	private static Logger logger = Logger.getLogger(GestionZonaUsuario.class.getName());

	// Devuelve el usuario que tiene el usuario y contrase�a que pasan como
	// paramenros, en caso de no encontrarlo se devuelve null
	public static Usuario getUsuario(String miUsuario, String miContrasena, String bd) { // Encuentra el usuario que necesitamos
		logger.info("Buscando " + miUsuario + " en la base de datos");
		Usuario u = null;
		try (Connection con = DriverManager.getConnection(bd)) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			// recorremos fila a fila
			while (rs.next()) {
				if (miUsuario.equals(rs.getString(4)) && miContrasena.equals(rs.getString(5))) {
					// obtenemos columnas
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);
					int idLigaActual = rs.getInt(6);
					int bote = rs.getInt(7);

					u = new Usuario(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
					logger.info(miUsuario + " encontrado");
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// No se ha podido obtener la conexi�n a la base de datos
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return u;
	}

	// Devuelve el administrador que tiene el usuario y contrase�a que pasan como
	// paramenros, en caso de no encontrarlo se devuelve null
	public static Administrador getAdministrador(String miAdmin, String miContrasena, String bd) { // Busca el administrador que queremos
		logger.info("Buscando " + miAdmin + " en la base de datos");
		Administrador a = null;
		try (Connection con = DriverManager.getConnection(bd)) {
			boolean encontrado = false;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM administrador");
			while (rs.next() && encontrado == false) {
				if (miAdmin.equals(rs.getString(4)) && miContrasena.equals(rs.getString(5))) {
					logger.info("Usuario encontrado");
					// obtenemos columnas
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);

					a = new Administrador(dni, nombre, apellido, usuario, contrasena);
					logger.info(miAdmin + " encontrado");
				}
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// No se ha podido obtener la conexi�n a la base de datos
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return a;
	}

	// Comprobar si ya est� el usuario en la base de datos, por nombre de usuario
	// si es true ya est� usado
	public static boolean comprobarUsuario(String miUsuario, String bd) {

		logger.info("Buscando si " + miUsuario + " est� en la base de datos");

		boolean usado = false;

		try (Connection con = DriverManager.getConnection(bd)) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE Usuario='" + miUsuario + "'");

			// recorremos fila a fila
			while (rs.next()) {
				// si ya esta repetido rs va a tener next por lo que entraria en el while
				usado = true;
				logger.info(miUsuario + " encontrado.");

			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// No se ha podido obtener la conexi�n a la base de datos
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		System.out.println("bucle terminado");
		return usado;
	}

	public static void insertarUsuario(int dni, String nombre, String apellido, String usuario, String contrasena, String bd) {

		logger.info("Insertando en la BD el usuario " + usuario);

		try {
			Connection conn = DriverManager.getConnection(bd);

			PreparedStatement stmt = conn.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido, Usuario, Contrase�a, IdLigaActual, Bote) VALUES (?, ?, ?, ?, ?, ?, ?)");

			// establecemos los datos en la prepared statement teniendo en cuenta el orden
			// de los ?
			stmt.setInt(1, dni);
			stmt.setString(2, nombre);
			stmt.setString(3, apellido);
			stmt.setString(4, usuario);
			stmt.setString(5, contrasena);
			stmt.setInt(6, 1);
			stmt.setInt(7, 10);

			// ejecutamos la sentencia preparado como un update, en este caso
			stmt.executeUpdate();
			logger.info(usuario + " guardado en la base de datos.");
			JOptionPane.showMessageDialog(null, "El usuario se ha creado correctamente");

			stmt.close();
			conn.close(); // es importante desconectar la conexión al terminar

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
	}

	public static Usuario buscarUsuarioPorID(int IDUsuario, String bd) {
		logger.info("Buscando " + IDUsuario + " en la base de datos");

		Usuario u = null;

		try (Connection con = DriverManager.getConnection(bd)) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE DNI=" + IDUsuario);

			// recorremos fila a fila
			while (rs.next()) {
				int dni = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String usuario = rs.getString(4);
				String contrasena = rs.getString(5);
				int idLigaActual = rs.getInt(6);
				int bote = rs.getInt(7);

				u = new Usuario(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);

				logger.info(IDUsuario + " encontrado");
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			// No se ha podido obtener la conexi�n a la base de datos
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return u;
	}

	// Inserta los numeros del carton
	public static void insertarNumerosDelCarton(int[][] miCarton, int IDCarton, String bd) {

		logger.info("Insertando en la BD los numeros del carton " + IDCarton);

		try {
			Connection conn = DriverManager.getConnection(bd);

			for (int i = 0; i < miCarton.length; i++) {
				for (int j = 0; j < miCarton[i].length; j++) {

					PreparedStatement stmt = conn
							.prepareStatement("INSERT INTO numerocarton (Valor, IDCarton) VALUES (?, ?)");
					// establecemos los datos en la prepared statement teniendo en cuenta el orden
					// de los ?
					stmt.setInt(1, miCarton[i][j]);
					stmt.setInt(2, IDCarton);

					// ejecutamos la sentencia preparado como un update, en este caso
					stmt.executeUpdate();
					stmt.close();
				}
			}
			logger.info("Carton " + IDCarton + " guardado en la base de datos.");
			conn.close(); // es importante desconectar la conexión al terminar

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
	}

	// PARA GUARDAR EL CARTON EN LA BD EN LA TABLA carton
	public static int cartonNuevo(int IDUsuario, int IDPartida, String bd) {
		int IDCarton = 0;
		logger.info("Insertando en la BD el carton nuevo");

		try {
			Connection conn = DriverManager.getConnection(bd);

			PreparedStatement stmt = conn
					.prepareStatement("INSERT INTO carton (IDUsuario, IDPartida, Coste) VALUES (?,?,?)");

			// metemos los valores en los ?
			stmt.setInt(1, IDUsuario);
			stmt.setInt(2, IDPartida);
			stmt.setFloat(3, Carton.costeCarton()); // desde properties

			// ejecutamos sentencia
			stmt.executeUpdate();
			logger.info("Carton guardado en la base de datos.");

			stmt.close();

			Statement stmtForId = conn.createStatement();

			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowId() AS IDCarton FROM carton");

			if (rs.next()) {
				IDCarton = rs.getInt("IDCarton");
			}
			conn.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		if (IDCarton != 0) {
			return IDCarton;
		} else {
			logger.log(Level.SEVERE, "No se ha podido extraer el ID del cart�n");
			return IDCarton;
		}

	}

	public static Partida buscarPartidaActiva(String bd) {

		// VA A BUSCAR EN LA BD SI HAY ALGUNA PARTIDA ACTIVA --> activa=1
		// SI HAY VARIAS ACTIVAS ENTRA EN ALGUNA RANDOM

		logger.info("Buscando partidas activas...");

		Partida p = null;

		try (Connection conn = DriverManager.getConnection(bd)) {

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM partida");

			int encontrado = 0;
			while (rs.next() && encontrado == 0) {
				if (rs.getInt(2) == 1) {
					int idPartida = rs.getInt(1);
					int activa = rs.getInt(2);
					int premioB = rs.getInt(3);
					int idCartonB = rs.getInt(4);

					p = new Partida(idPartida, activa, premioB, idCartonB);
					logger.info("Partida activa encontrada: " + idPartida);

					encontrado = 1;

				}
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}

		return p;
	}

	// ACTUALIZAR TABLA CARTON COLUMNA BINGO
	public static void actualizarBingoBD(int IDCarton, String bd) {

		try (Connection con = DriverManager.getConnection(bd)) {

			PreparedStatement actualizacion = con
					.prepareStatement("UPDATE carton SET Bingo = 1 WHERE IDCarton = " + IDCarton);
			actualizacion.executeUpdate();

			logger.info("Actualizado el bingo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se han podido actualizar los datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}

	}

	// ACTUALIZAR TABLA CARTON COLUMNA BINGO
	public static void actualizarCarteraBD(int IDUsuario, float cartera, String bd) {

		try (Connection con = DriverManager.getConnection(bd)) {

			PreparedStatement stmt = con.prepareStatement("UPDATE usuario SET Bote=? WHERE DNI=?");
			stmt.setFloat(1, cartera);
			stmt.setInt(2, IDUsuario);
			stmt.executeUpdate();

			logger.info("Actualizado el bote del usuario");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se han podido actualizar los datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}

	}
	
	//Extrae todos los numeros cantados a la partida
	public static List<Integer> numerosPartida(int idPartida, String bd) {
		//HACER EL ACCESO A BD QUE RECOJA TODOS LOS NUMEROS Y A�ADIR AL MODELO
		
		List<Integer> todosLosNumeros = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(bd)) {
			
			logger.info("Buscando los numeros de la partida");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Valor FROM numeropartida WHERE IDPartida="+idPartida+ " ORDER BY Orden ASC");
			//tengo en orden los valores cantados
			while (rs.next()) {
				todosLosNumeros.add(rs.getInt(1));
			}
			
			rs.close();
			stmt.close();

			
			
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se han podido obtener los numeros cantados");
		}
		return todosLosNumeros;
		
	
	}
	
	public static Integer comprobarSiGanador(int idPartida, String bd) {
		Integer ganador = null;
		try (Connection conn = DriverManager.getConnection(bd)) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM partida WHERE IDPartida=" + idPartida);
			
			while (rs.next()) {
				ganador = rs.getInt(4);
				System.out.println(ganador);
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "No se han podido obtener los numeros cantados");
		}
		return ganador;
	}

}
