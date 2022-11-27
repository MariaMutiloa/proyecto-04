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

//Contiene todos los metodos de acceso a base de datos que tengan que se puedan usar durante la partida por parte del administrador


public class GestionPartidas {
	
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());
	
	
	//Crea una partida y la añade a la base de datos
	public static int nueva() {
		int IDPartida = 0;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
			logger.info("Conectado a la base de datos para añadir partida");
			
			
			PreparedStatement insertPartidaNueva = con.prepareStatement("INSERT INTO partida");
			logger.info("Parida añadida en la base de datos");
			insertPartidaNueva.executeUpdate();
			
			Statement stmtForId = con.createStatement();
			
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowIDPartida() AS id FROM partida");
			
			if (rs.next()) {
				IDPartida = rs.getInt("IDPartida");
			}
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		if (IDPartida != 0) {
			return IDPartida; 
		}
		else {
			JOptionPane.showMessageDialog(null,"No se ha podido crear una partida nueva", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido crear una partida nueva");
		}
		return IDPartida;
		
	}
	
	
	public static void actualizarDatosBotes(int IDPartida, float bLinea, float bBingo) {
	
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")){
			
			PreparedStatement actualizacion = con.prepareStatement("UPDATE partida SET PremioB = "+bBingo+" PremioL = " +bLinea+ " WHERE IDPartida = "+ IDPartida);
			actualizacion.executeUpdate();
			
			logger.info("Añadidos los botes de linea y de bingo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se han podido actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}
		
	}


	public static List<Usuario> numeroParticipantes(int IDPartida) {
		List<Usuario> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			logger.info("Conectado a la base de datos para extraer usuarios de la partida");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE IDPartida =" +IDPartida);

			while (rs.next()) {
				logger.info("Usuario jugador encontrado");
				Usuario persona= new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
				list.add(persona);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return list;

	}
}
