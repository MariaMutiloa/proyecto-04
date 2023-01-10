package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class GestionEstadisticas {
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());


	// NUMERO MÁS VECES CANTADO
	public static int numMasVecesCantado() {
		logger.info("Buscando en la base de datos numero mas veces cantado");
		int numeroMax = 0;
		int veces = 0;
		Map<Integer, Integer> mapaNumeros = new HashMap<>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
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
			logger.info("Numeros añadidos al mapa");
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
			// No se ha podido obtener la conexión a la base de datos
			JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return numeroMax;
	}
	// BOTE MAX DE PARTIDA - PremioB en la tabla
		public static float boteMaxPartida() {
			logger.info("Buscando en la base de datos bote maximo de partida");
			float boteMax = 0;

			try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
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
				// No se ha podido obtener la conexión a la base de datos
				JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
			}
			return boteMax;
		}

		// USUARIO CON MAYOR CARTERA
		public static String usuarioMayorCartera() {
			logger.info("Buscando en la base de datos usuario con mayor cartera.");
			float carteraMax = 0;
			String usuario = null;

			try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
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
				// No se ha podido obtener la conexión a la base de datos
				JOptionPane.showMessageDialog(null, "Error. No se ha podido conectar a la base de datos" + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
			}
			DecimalFormat format = new DecimalFormat("#.00");
			String carteraMaxFormat = format.format(carteraMax);
			
			return String.format("%s con %s €", usuario, carteraMaxFormat);
		}
		
			

		
}
