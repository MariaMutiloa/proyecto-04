package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

	public static void realizarConexion() {
		
		/*
		 * Carga del drive JDBC para SQLite
		 */
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}
		
		
		/*
		 * Con el driver cargado ya se pueden establecer conexiones a la BD
		 */
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {

			
			/*
			 * Hemos hecho INSERTS en la tabla usuario y administrador
			 */
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido, Usuario, Contraseña, IDLigaActual, Bote) VALUES (?,?,?,?,?,?,?)");
			stmt.setInt(1, 24356782);
			stmt.setString(2, "Ruben");
			stmt.setString(3, "Garcia");
			stmt.setString(4, "ruben.garcia");
			stmt.setString(5, "ruben01");
			stmt.setInt(6, 1);
			stmt.setInt(7, 0);
			stmt.executeUpdate();

			PreparedStatement stmt1 = con.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido, Usuario, Contraseña, IDLigaActual, Bote) VALUES (?,?,?,?,?,?,?)");
			stmt1.setInt(1, 56478903);
			stmt1.setString(2, "Alba");
			stmt1.setString(3, "Rodriguez");
			stmt1.setString(4, "albarodri");
			stmt1.setString(5, "rodriguez17");
			stmt1.setInt(6,  1);
			stmt1.setInt(7, 0);
			stmt1.executeUpdate();

			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido, Usuario, Contraseña, IDLigaActual, Bote) VALUES (?,?,?,?,?,?,?)");
			stmt2.setInt(1, 67456718);
			stmt2.setString(2, "Jon");
			stmt2.setString(3, "Lopez");
			stmt2.setString(4, "jonlopez");
			stmt2.setString(5, "lopezjon");
			stmt2.setInt(6, 1);
			stmt2.setInt(7, 0);
			stmt2.executeUpdate();

			PreparedStatement stmt3 = con.prepareStatement(
					"INSERT INTO administrador (DNI, Nombre, Apellidos, Usuario, Contraseña) VALUES (?,?,?,?,?)");
			stmt3.setInt(1, 56735467);
			stmt3.setString(2, "Carmen");
			stmt3.setString(3, "Perez");
			stmt3.setString(4, "carmen.perez");
			stmt3.setString(5, "carpez");
			stmt3.executeUpdate();

			PreparedStatement stmt4 = con.prepareStatement(
					"INSERT INTO administrador (DNI, Nombre, Apellidos, Usuario, Contraseña) VALUES (?,?,?,?,?)");
			stmt4.setInt(1, 98476892);
			stmt4.setString(2, "Josu");
			stmt4.setString(3, "Sanchez");
			stmt4.setString(4, "sanchez09");
			stmt4.setString(5, "josu.sanchez");
			stmt4.executeUpdate();

			PreparedStatement stmt5 = con.prepareStatement(
					"INSERT INTO Administrador (DNI, Nombre, Apellidos, Usuario, Contraseña) VALUES (?,?,?,?,?)");
			stmt5.setInt(1, 56726378);
			stmt5.setString(2, "Miriam");
			stmt5.setString(3, "Rodriguez");
			stmt5.setString(4, "miriam.rodriguez");
			stmt5.setString(5, "rodriguez23");
			stmt5.executeUpdate();
			
			stmt.close();
			stmt1.close();
			stmt2.close();
			stmt3.close();
			stmt4.close();
			stmt5.close();
			System.out.println("Insertadas");

//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
//			
//			//recorremos fila a fila
//			while (rs.next()) {
//				//obtenemos columnas
//				int dni = rs.getInt(1);
//				String nombre = rs.getString(2);
//				String apellido = rs.getString(3);
//				String usuario = rs.getString(4);
//				String contrasena = rs.getString(5);
//				
//				System.out.println(dni +" "+nombre+" "+apellido+" "+usuario+" "+contrasena);
//			}
//			
//			rs.close();
//			stmt.close();
//			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}
		
		

	}
	
//	public static void sacarDatosBD(){
//		/*
//		 * Con el driver cargado ya se pueden establecer conexiones a la BD
//		 */
//		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
//
//			Statement stmt = con.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
//			
//			//recorremos fila a fila
//			while (rs.next()) {
//				//obtenemos columnas
//				int dni = rs.getInt(1);
//				String nombre = rs.getString(2);
//				String apellido = rs.getString(3);
//				String usuario = rs.getString(4);
//				String contrasena = rs.getString(5);
//				
//				System.out.println(dni +" "+nombre+" "+apellido+" "+usuario+" "+contrasena);
//			}
//			
//			rs.close();
//			stmt.close();
//			
//		} catch (SQLException e) {
//			// No se ha podido obtener la conexión a la base de datos
//			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
//		}
//	}

}
