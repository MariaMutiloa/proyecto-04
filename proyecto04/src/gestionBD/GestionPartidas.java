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

import personas.Usuario;

public class GestionPartidas {
	
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());
	
	public static void nueva(int IDPartida, float PremioB, float PremioL, int IDLiga) {
		 
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
			logger.info("Conectado a la base de datos apra añadir partida");
			
			PreparedStatement insertPartidaNueva = con.prepareStatement("INSERT INTO partida (IDPartida, Activa, PremioB, PremioL, IDLiga"
					+ ", IDCartonB, IDCartonL VALUES (?, ?, ?)");
			insertPartidaNueva.setInt(1, IDPartida);
			insertPartidaNueva.setInt(2, 1);
			insertPartidaNueva.setFloat(3, PremioB);
			insertPartidaNueva.setFloat(4, PremioL);
			insertPartidaNueva.setFloat(5, IDLiga);
			
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		 //ESTO FALTA POR MIRAR (NO AUTOMATIZADO)
		
	}

	public static int numNuevo() {
		//calcular nuevo ID PARTIDA
		return 1;
	}

	public static ArrayList<Usuario> numeroParticipantes(int IDPartida) {
		List<Usuario> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			logger.info("Conectado a la base de datos para extraer usuarios de la partida");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM carton");

			while (rs.next()) {
				logger.info("Usuario jugador encontrado");
				if (IDPartida == rs.getInt(4)) {
					Usuario persona= new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
					list.add(persona);
				}
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return list;

	}
}
