package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


/**
 * 
 * CONTIENE LOS METODOS DE BD NECESARIOS APRA LA EXTRACI—”N DE ESTADÕSTICAS
 *
 */

public class GestionEstadisticas {
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());

	//bd = "jdbc:sqlite:DatosBingo.db";

	// NUMERO M√S VECES CANTADO
	public static int numMasVecesCantado(String bd) {
		logger.info("Buscando en la base de datos numero mas veces cantado");
		int numeroMax = 0;
		int veces = 0;
		Map<Integer, Integer> mapaNumeros = new HashMap<>();
		try (Connection con = DriverManager.getConnection(bd)) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Valor FROM numeropartida");
			// recorremos fila a fila
			while (rs.next()) {
				int numActual = rs.getInt(1);

				mapaNumeros.putIfAbsent(numActual, 0);
				for (Entry<Integer, Integer> e : mapaNumeros.entrySet()) {
					if (e.getKey() == numActual) {
						mapaNumeros.put(e.getKey(), e.getValue() + 1);
					}
				}
			}
			logger.info("Numeros a√±adidos al mapa");
			rs.close();
			stmt.close();

			for (Entry<Integer, Integer> n : mapaNumeros.entrySet()) {
				if (n.getValue() > veces) {
					numeroMax = n.getKey();
					veces = n.getValue();
				}
			}
			logger.info("Ya tenemos el numero mas cantado");

		} catch (SQLException e) {
			// No se ha podido obtener la conexi√≥n a la base de datos
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return numeroMax;
	}
	
	
	// BOTE MAX DE PARTIDA - PremioB en la tabla
		public static float boteMaxPartida(String bd) {
			logger.info("Buscando en la base de datos bote maximo de partida");
			float boteMax = 0;

			try (Connection con = DriverManager.getConnection(bd)) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT PremioB FROM partida");
				// recorremos fila a fila
				while (rs.next()) {
					
					if(rs.getFloat(1)>boteMax) {
						boteMax=rs.getFloat(1);
					}

				}
				logger.info("Ya tenemos el bote maximos");
				rs.close();
				stmt.close();

			} catch (SQLException e) {
				// No se ha podido obtener la conexi√≥n a la base de datos
				JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
			}
			return boteMax;
		}

		
		// USUARIO CON MAYOR CARTERA
		public static String usuarioMayorCartera(String bd) {
			logger.info("Buscando en la base de datos usuario con mayor cartera.");
			float carteraMax = 0;
			String usuario = null;

			try (Connection con = DriverManager.getConnection(bd)) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT Usuario, Bote FROM usuario");
				// recorremos fila a fila
				while (rs.next()) {
					
					if(rs.getFloat(2)>carteraMax) {
						carteraMax=rs.getFloat(2);
						usuario=rs.getString(1);
					}

				}
				logger.info("Ya tenemos el usuario con mayor cartera");
				rs.close();
				stmt.close();

			} catch (SQLException e) {
				// No se ha podido obtener la conexi√≥n a la base de datos
				JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
			}
			DecimalFormat format = new DecimalFormat("#.00");
			String carteraMaxFormat = format.format(carteraMax);
			
			return String.format("%s con %sÄ", usuario, carteraMaxFormat);
		}
		
		
		// devuelve todos los numeros cantados en todas las partidas con repeticiones
		public static ArrayList<Integer> anyadirNum(String bd) {
			logger.info("AÒadiendo todos los numeros");
			ArrayList<Integer> todosNumeros = new ArrayList<>();
			try (Connection con = DriverManager.getConnection(bd)) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT Valor FROM numerocarton");
				// recorremos fila a fila
				while (rs.next()) {
					todosNumeros.add(rs.getInt("Valor"));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
			}
			return todosNumeros;
		}
		
		//Extrae el mayor bote de todos los usuarios
		public static float getBoteMax(String bd) {
			logger.info("Buscando usuarios en la base de datos");
			float boteMax = 0;
			try (Connection con = DriverManager.getConnection(bd)) {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
				// recorremos fila a fila
				try {
					boteMax=(rs.getFloat(7));
					while (rs.next()) {
						if (boteMax <= rs.getFloat(7)) {// si es menor que e anterior se le asigna el nuevo
							boteMax = rs.getFloat(7);
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
