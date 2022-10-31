package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

	public static void realizarConexion() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:ficheros/ejemplo.db")) {
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexi�n a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}
	}

}
