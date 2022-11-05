package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

	public static void realizarConexion() {
		
		/*
		 * Carga del drive JDBC para SQLite
		 */
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		
		/*
		 * Con el driver cargado ya se pueden establecer conexiones a la BD
		 */
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}	

	}
	
	public static void sacarDatosBD(){
		
		realizarConexion();
		/*
		 * Con el driver cargado ya se pueden establecer conexiones a la BD
		 */
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			
			//recorremos fila a fila
			System.out.println("USUARIOS:");
			while (rs.next()) {
				//obtenemos columnas
				int dni = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String usuario = rs.getString(4);
				String contrasena = rs.getString(5);
				
				System.out.println(dni +" "+nombre+" "+apellido+" "+usuario+" "+contrasena);
			}
			
			rs = stmt.executeQuery("SELECT * FROM administrador");
			System.out.println("ADMINISTRADORES:");
			while (rs.next()) {
				//obtenemos columnas
				int dni = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido = rs.getString(3);
				String usuario = rs.getString(4);
				String contrasena = rs.getString(5);
				
				System.out.println(dni +" "+nombre+" "+apellido+" "+usuario+" "+contrasena);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}
	}

}
