package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;

//Contiene todos los metodos de acceso a base de datos que tengan que se puedan usar durante la partida por parte del administrador


public class GestionPartidas {
	
	/**
	 *  RECOGE TODOS LOS METODOS DE ACCESO A BD QUE TENGAN QUE VER CON LA GESTIÓN DE PARTIDAS
	 */
	
	private static Logger logger = Logger.getLogger(GestionPartidas.class.getName());
	
	
	//Crea una partida y la añade a la base de datos
	public static int nueva(String bd) {
		int IDPartida = 0;
		try (Connection con = DriverManager.getConnection(bd)) {
			
			logger.info("Conectado a la base de datos para añadir partida");
			
			
			PreparedStatement insertPartidaNueva = con.prepareStatement("INSERT INTO partida (Activa) VALUES (?)");
			insertPartidaNueva.setInt(1, 1);
			logger.info("Partida añadida en la base de datos");
			insertPartidaNueva.executeUpdate();
			
			Statement stmtForId = con.createStatement();
			
			ResultSet rs = stmtForId.executeQuery("SELECT last_insert_rowId() AS IDPartida FROM partida");
			
			if (rs.next()) {
				IDPartida = rs.getInt("IDPartida");
			}
			
			
		} catch (SQLException e) {
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
	
	//Actualiza una partida con un ganador y su respectivo bote
	public static void actualizarDatos(int IDPartida, float bBingo, String bd) {
	
		try (Connection con = DriverManager.getConnection(bd)){
			
			PreparedStatement actualizacion = con.prepareStatement("UPDATE partida SET PremioB = "+bBingo+", activa = 0 WHERE IDPartida = "+ IDPartida);
			actualizacion.executeUpdate();
			
			logger.info("Añadido el bote");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se han podido actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}
		
	}

	//Selecciona todos los cartones conectados a una partida
	public static List<Carton> participantes(int IDPartida, String bd) {
		List<Carton> list = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(bd)) {

			logger.info("Conectado a la base de datos para extraer usuarios de la partida");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE IDPartida =" +IDPartida);

			while (rs.next()) {
				logger.info("Cartón encontrado");
				Carton jugador = new Carton(rs.getInt(1),rs.getInt(2),rs.getInt(3), false);
				list.add(jugador);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return list;
	}


	//Revisa si hay algun jugador que haya cantado bingo
	public static int revisar(int IDPartida, String bd) {
		int IDGanador = 0;
		try (Connection con = DriverManager.getConnection(bd)) {

			logger.info("Conectado a la base de datos para revisar que no haya ganadores");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE Bingo = 1 AND IDPartida = "+IDPartida);

			while (rs.next()) {
				logger.info("Cartón ganador encontrado");
				IDGanador = rs.getInt(1);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return IDGanador;
	}

	//Añade numero cantado en la base de datos para que los usuarios puedan acceder a el
	public static void añadirNumero(int numero, int indexOf, int IDPartida, String bd) {
		logger.info("Insertando en la BD el numero "+ numero);
		
		try (Connection conn = DriverManager.getConnection(bd)){
		    
		    	
		    PreparedStatement stmt = conn.prepareStatement("INSERT INTO numeropartida (valor, IDpartida, orden) VALUES (?, ?, ?)");
		    			    
		    stmt.setInt(1, numero);
		    stmt.setInt(2, IDPartida);
		    stmt.setInt(3, indexOf);
		  

		    		    
		    stmt.executeUpdate();
			logger.info(numero+" guardado en la base de datos.");
			    
			stmt.close();
					
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,  "Error. No se ha podido conectar a la base de datos" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");   
		}
		
	}

	//Estrae el cartón con un ID especifico
	public static Carton getCarton(int ganadorB, String bd) {
			Carton ganador = null;
			try (Connection con = DriverManager.getConnection(bd)) {

				logger.info("Conectado a la base de datos para revisar que no haya ganadores");
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM carton WHERE IDCarton = "+ganadorB);

				while (rs.next()) {
					logger.info("Cartón ganador encontrado");
					ganador = new Carton(rs.getInt(1), rs.getInt(2), rs.getInt(3), false);
				}

				rs.close();
				stmt.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

			}
			return ganador;
	}

	//Añade a la BD el ganador del bingo
	public static void setGanadorBingo(int idCarton, Partida partida, float botePartida, String bd) {
		try (Connection con = DriverManager.getConnection(bd)){
			
			PreparedStatement actualizacion = con.prepareStatement("UPDATE partida SET Activa = 0, IDCartonB = "+idCarton+ " WHERE IDPartida = "+ partida.getIDPartida());
			actualizacion.executeUpdate();
			
			logger.info("Actualizado el ganador");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se han podido actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}
		
		//HAY QUE SUMAR EL BOTE EN LA CARTERA DEL GANADOR
		sumarBoteGanador(idCarton, botePartida, bd);
		
	}
	
	//Suma el bote al ganador de la partida --> incrementa cartera
	public static void sumarBoteGanador(int idCarton, float botePartida, String bd) {
		try (Connection con = DriverManager.getConnection(bd)){

			int dniGanador;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT IDUsuario FROM carton WHERE IDCarton = "+idCarton);
			dniGanador=rs.getInt(1);
			rs.close();
			
			ResultSet rs1 = stmt.executeQuery("SELECT Bote FROM usuario WHERE DNI = "+dniGanador);
			float bote = rs1.getFloat(1);
			rs.close();
			stmt.close();
						
			float nuevoBote = botePartida+bote;
			PreparedStatement stmt2 = con.prepareStatement("UPDATE usuario SET Bote = ? WHERE DNI = ?");
			stmt2.setFloat(1, nuevoBote);
			stmt2.setInt(2, dniGanador);
			stmt2.executeUpdate();
			
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"No se han podido actualizar los datos", "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}
	}




	public static void noEsBingo(Carton cartonGanador, String bd) {
		try (Connection con = DriverManager.getConnection(bd)) {

			PreparedStatement actualizacion = con.prepareStatement("UPDATE carton SET Bingo = 0 WHERE IDCarton = " + cartonGanador.getIDCarton());
			actualizacion.executeUpdate();

			logger.info("Eliminada marca de ganador");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se han podido actualizar los datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se han podido actualizar los datos");
		}

	}


}
