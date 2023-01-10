package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

//Contiene los metodos de creacion e inicializacion de base de datos. 

public class ConexionBD {

	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());
	private static String connexion;

	public static void realizarConexion(String bd) {
		connexion = bd;
		// Carga del drive JDBC para SQLite
		try {
			Class.forName("org.sqlite.JDBC");
			logger.info("Driver cargado");

		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar el driver de la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido cargar el driver de base de datos");
		}
	}

	

}
