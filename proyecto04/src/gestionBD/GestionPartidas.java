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

//Contiene todos los metodos de acceso a base de datos que tengan que se puedan usar durante la partida por parte del administrador


public class GestionPartidas {
	
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());
	
	
	//Crea una partida y la a�ade a la base de datos
	public static int nueva() {
		int IDPartida = 0;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			
			logger.info("Conectado a la base de datos para a�adir partida");
			
			
			PreparedStatement insertPartidaNueva = con.prepareStatement("INSERT INTO partida (Activa, IDLiga) VALUES (?,?)");
			insertPartidaNueva.setInt(1, 0);
			insertPartidaNueva.setInt(2, 0);
			logger.info("Partida a�adida en la base de datos");
			insertPartidaNueva.executeUpdate();
			
			Statement stmtForId = con.createStatement();
			
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowId() AS IDPartida FROM partida");
			
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
	
	
	public static void actualizarDatos(int IDPartida, float bBingo) {
	
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")){
			
			PreparedStatement actualizacion = con.prepareStatement("UPDATE partida SET PremioB = "+bBingo+" WHERE IDPartida = "+ IDPartida);
			actualizacion.executeUpdate();
			
			logger.info("A�adido el bote");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se han podido actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}
		
	}

	//Esto se tiene que cambiar
	public static List<Carton> participantes(int IDPartida) {
		List<Carton> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			logger.info("Conectado a la base de datos para extraer usuarios de la partida");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE IDPartida =" +IDPartida);

			while (rs.next()) {
				logger.info("Cart�n encontrado");
				Carton jugador = new Carton(rs.getInt(1),rs.getInt(2),rs.getInt(3));
				list.add(jugador);
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


	public static void empezada(int idPartida, int IDLiga) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")){
			
			PreparedStatement actualizacion = con.prepareStatement("UPDATE partida SET Activa = 1, IDLiga = "+IDLiga+ " WHERE IDPartida = "+ idPartida);
			actualizacion.executeUpdate();
			
			logger.info("A�adidos los botes de linea y de bingo");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"No se han podido actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}
		
	}


	public static int revisar() {
		int IDGanador = 0;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			logger.info("Conectado a la base de datos para revisar que no haya ganadores");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE bingo = true");

			while (rs.next()) {
				logger.info("Cart�n ganador encontrado");
				IDGanador = rs.getInt(1);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return IDGanador;
	}

	//A�ade el numero en la base de datos para que los usuarios puedan acceder a el
	public static void a�adirNumero(int numero, int indexOf, int IDPartida) {
		logger.info("Insertando en la BD el numero "+ numero);
		
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")){
		    
		    	
		    PreparedStatement stmt = conn.prepareStatement("INSERT INTO numero-partida (valor, posicion, partida) VALUES (?, ?, ?)");
		    			    
		    stmt.setInt(1, numero);
		    stmt.setInt(2, indexOf);
		    stmt.setInt(3, IDPartida);
		  

		    		    
		    stmt.executeUpdate();
			logger.info(numero+" guardado en la base de datos.");
			    
			stmt.close();
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");   
		}
		
	}


	public static Carton getCarton(int ganadorB) {
			Carton ganador;
			try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

				logger.info("Conectado a la base de datos para revisar que no haya ganadores");
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE IDCarton = "+ganadorB);

				while (rs.next()) {
					logger.info("Cart�n ganador encontrado");
					IDGanador = rs.getInt(1);
				}

				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

			}
			return IDGanador;
	}
}
