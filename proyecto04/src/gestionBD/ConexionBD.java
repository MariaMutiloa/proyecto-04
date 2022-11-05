package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import personas.Administrador;
import personas.Usuario;

public class ConexionBD {

	public static void realizarConexion() {
		
		//Carga del drive JDBC para SQLite
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		
		//Con el driver cargado ya se pueden establecer conexiones a la BD
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}	

	}
	
	public static Usuario getUsuario(String miUsuario, String miContrasena){ //Encuentra el usuario que necesitamos
		
		Usuario u = null;
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			
			//recorremos fila a fila
			while (rs.next()) {
				
				if(miUsuario==rs.getString(4) && miContrasena==rs.getString(5)) {
					//obtenemos columnas
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);
					int idLigaActual = rs.getInt(6);
					int bote = rs.getInt(7);
					
					u = new Usuario(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
				}
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}
		return u;
	}
	
	
	public static Administrador getAdministrador(String miUsuario, String miContrasena){ //Busca el usuario que queremos
		realizarConexion();
		
		Administrador a = null;
		
		/*
		 * Con el driver cargado ya se pueden establecer conexiones a la BD
		 */
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM administrador");
			
			while (rs.next()) {
				
				if(miUsuario==rs.getString(4) && miContrasena==rs.getString(5)) {
					//obtenemos columnas
					int dni = rs.getInt(1);
					String nombre = rs.getString(2);
					String apellido = rs.getString(3);
					String usuario = rs.getString(4);
					String contrasena = rs.getString(5);
					
					a = new Administrador(dni, nombre, apellido, usuario, contrasena);
				}
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}
		return a;
	}
	
	
	
	
	

}
