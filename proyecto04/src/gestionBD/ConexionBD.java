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


	public static ArrayList<Integer> anyadirNum() {// devuelve todos los numeros con repeticiones

		logger.info("Añadiendo todos los numeros");

		ArrayList<Integer> todosNumeros = new ArrayList<>();

		try (Connection con = DriverManager.getConnection(connexion)) {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT Valor FROM Numero-Carton");

			// recorremos fila a fila

			while (rs.next()) {

				todosNumeros.add(rs.getInt("Valor"));

			}

			rs.close();

			stmt.close();

		} catch (SQLException e) {

			// No se ha podido obtener la conexi?n a la base de datos

			// System.out.println("Error. No se ha podido conectar a la base de datos " +
			// e.getMessage());

			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);

			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}

		return todosNumeros;
	}
	
	
	//COGE EL MAYOR BOTE
	public static int getBoteMax() {

		logger.info("Buscando usuarios en la base de datos");

		int boteMax = 0;

		try (Connection con = DriverManager.getConnection(connexion)) {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			// recorremos fila a fila
			try {
				while (rs.next()) {
					boteMax = rs.getInt(5);
					if (boteMax <= rs.getInt(5)) {// si es menor que e anterior se le asigna el nuevo
						boteMax = rs.getInt(5);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs.close();
			stmt.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return boteMax;
	}
	

	
	

}
