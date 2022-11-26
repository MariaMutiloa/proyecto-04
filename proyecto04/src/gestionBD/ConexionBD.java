package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import personas.Administrador;
import personas.Usuario;

//Contiene los metodos de creacion e inicializacion de base de datos. 

public class ConexionBD {
	
	 private static Logger logger = Logger.getLogger(ConexionBD.class.getName());

	public static void realizarConexion() {
		
		
		//Carga del drive JDBC para SQLite
		try {
			Class.forName("org.sqlite.JDBC");
			logger.info("Driver cargado");

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,  "No se ha podido cargar el driver de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido cargar el driver de base de datos");
			//System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		
		//Con el driver cargado ya se pueden establecer conexiones a la BD
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			logger.info("Conexion con 'DatosBingo.db' realizada correctamente");
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			//System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}	

	}
	
	//Devuelve el usuario que tiene el usuario y contraseña que pasan como paramenros, en caso de no encontrarlo se devuelve null
	public static Usuario getUsuario(String miUsuario, String miContrasena){ //Encuentra el usuario que necesitamos
		
		logger.info("Buscando "+ miUsuario +" en la base de datos");
		
		Usuario u = null;
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

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
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
			
			
			boolean encontrado = false;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM administrador");
			
			while (rs.next() && encontrado == false) {
				
				//
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
	
	
	
	
	

}
