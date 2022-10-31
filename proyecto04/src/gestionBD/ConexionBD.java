package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {

	public static void realizarConexion() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:ficheros/ejemplo.db")) {
	}

}
