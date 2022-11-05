package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionPartidas {
	
	public static int nueva(int IDPartida, float PremioB, float PremioL, int IDLiga) {
		
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
			PreparedStatement insertPartidaNueva = con.prepareStatement("INSERT INTO partida (IDPartida, Activa, PremioB, PremioL, IDLiga"
					+ ", IDCartonB, IDCartonL VALUES (?, ?, ?)");
			insertPartidaNueva.setInt(1, IDPartida);
			insertPartidaNueva.setInt(2, 1);
			insertPartidaNueva.setFloat(3, PremioB);
			insertPartidaNueva.setFloat(4, PremioL);
			insertPartidaNueva.setFloat(5, IDLiga);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0; //ESTO FALTA POR MIRAR (NO AUTOMATIZADO)
		
	}

	public static int numeroParticipantes() {
try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			
		int total = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return total;
	}
}	

