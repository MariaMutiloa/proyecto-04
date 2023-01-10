package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class GestionLigasYEstadisticas {
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());

	public static Integer[] getLigas() {
		Set<Integer> ligas = new TreeSet<Integer>();
		logger.info("Extrayendo las ligas actuales");
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

			while (rs.next()) {
				ligas.add(rs.getInt(6));
			}

			logger.info("Ligas extraidas");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se han podido extraer las ligas", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido acceder a la base de datos");
		}

		Integer[] arrayLigas = new Integer[ligas.size()];
		int contador = 0;
		for (Integer i : ligas) {
			arrayLigas[contador] = i;
			contador++;
		}
		return arrayLigas;
	}

}
