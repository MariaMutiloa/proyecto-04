package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import personas.Administrador;
import personas.Usuario;

//Contiene los metodos de creacion e inicializacion de base de datos. 

public class ConexionBD {
		
	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());
	private static String connexion;

	public static void realizarConexion(String bd) {
		connexion = bd;
		//Carga del drive JDBC para SQLite
		try {
			Class.forName("org.sqlite.JDBC");
			logger.info("Driver cargado");

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,  "No se ha podido cargar el driver de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido cargar el driver de base de datos");
			//System.out.println("No se ha podido cargar el driver de la base de datos");
		}
	}
	
	//Devuelve el usuario que tiene el usuario y contraseña que pasan como paramenros, en caso de no encontrarlo se devuelve null
	public static Usuario getUsuario(String miUsuario, String miContrasena){ //Encuentra el usuario que necesitamos
		
		logger.info("Buscando "+ miUsuario +" en la base de datos");
		
		Usuario u = null;
		
		try (Connection con = DriverManager.getConnection(connexion)) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			
			//recorremos fila a fila
			while (rs.next()) {
				if(miUsuario.equals(rs.getString(4)) && miContrasena.equals(rs.getString(5))) {
					//obtenemos columnas
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);
					int idLigaActual = rs.getInt(6);
					int bote = rs.getInt(7);
					
					
					u = new Usuario(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
					
					logger.info(miUsuario +" encontrado");
				}
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			//System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return u;
	}
	
	//Devuelve el administrador que tiene el usuario y contraseña que pasan como paramenros, en caso de no encontrarlo se devuelve null
	public static Administrador getAdministrador(String miAdmin, String miContrasena){ //Busca el administrador que queremos

		logger.info("Buscando "+ miAdmin + " en la base de datos");
		Administrador a = null; 
		
		try (Connection con = DriverManager.getConnection(connexion)) {
			
			
			
			boolean encontrado = false;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM administrador");
			
			while (rs.next() && encontrado == false) {
				if(miAdmin.equals(rs.getString(4)) && miContrasena.equals(rs.getString(5))) {
					logger.info("Usuario encontrado");
					//obtenemos columnas
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);
					
					
					a = new Administrador(dni, nombre, apellido, usuario, contrasena);
					logger.info(miAdmin +" encontrado");
				}
			}
			
		
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			//System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return a;
		
	}
	
	
	//Comprobar si ya está el usuario en la base de datos, por nombre de usuario
	public static boolean comprobarUsuario(String miUsuario){
		
		logger.info("Buscando si "+ miUsuario +" está en la base de datos");
		
		boolean usado= false;
				
		try (Connection con = DriverManager.getConnection(connexion)) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Usuario FROM usuario");
			
			//recorremos fila a fila
			while (rs.next()) {
				if(miUsuario.equals(rs)) {
					
					//el usuario ya está cogido
					usado=true;
					logger.info(miUsuario +" encontrado.");

				}
			}	
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			//System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return usado;
	}
	
	
	
	public static void insertarUsuario(int dni, String nombre, String apellido, String usuario, String contrasena) {
		
		logger.info("Insertando en la BD el usuario "+ usuario);
		
		try {
		    Connection conn = DriverManager.getConnection(connexion);
		    	
		    PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuario (DNI, Nombre, Apellido, Usuario, Contraseña, IdLigaActual, Bote) VALUES (?, ?, ?, ?, ?, ?, ?)");
		    			    
		    // establecemos los datos en la prepared statement teniendo en cuenta el orden de los ?
		    stmt.setInt(1, dni);
		    stmt.setString(2, nombre);
		    stmt.setString(3, apellido);
		    stmt.setString(4, usuario);
		    stmt.setString(5, contrasena);
		    stmt.setInt(6, 1);
		    stmt.setInt(7, 0);

		    		    	
		    // ejecutamos la sentencia preparado como un update, en este caso
		    stmt.executeUpdate();
			logger.info(usuario+" guardado en la base de datos.");
			JOptionPane.showMessageDialog(null, "El usuario se ha creado correctamente");
			    
			stmt.close();
		    conn.close(); // es importante desconectar la conexiÃ³n al terminar
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");   
		}
	}

	public static Usuario buscarUsuarioPorID(int IDUsuario) {
		logger.info("Buscando "+ IDUsuario +" en la base de datos");
		
		Usuario u = null;
		
		try (Connection con = DriverManager.getConnection(connexion)) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE IDUsuario="+IDUsuario);
			
			//recorremos fila a fila
			while (rs.next()) {
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);
					int idLigaActual = rs.getInt(6);
					int bote = rs.getInt(7);
					
					
					u = new Usuario(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
					
					logger.info(IDUsuario +" encontrado");
				}
			
			rs.close();
			stmt.close();
			
		
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			//System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return u;
	}
	
	
	
	
	
	

}
