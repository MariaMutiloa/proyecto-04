package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexionBD {

	public static void realizarConexion() {
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido cargar el driver de la base de datos");
		}

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			PreparedStatement stmt = con.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido,Usuario,Contraseña) VALUES (?,?,?,?,?)");
			stmt.setInt(1, 24356782);
			stmt.setString(2, "Ruben");
			stmt.setString(3, "Garcia");
			stmt.setString(4, "ruben.garcia");
			stmt.setString(5, "ruben01");

			PreparedStatement stmt1 = con.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido,Usuario,Contraseña) VALUES (?,?,?,?,?)");
			stmt1.setInt(1, 56478903);
			stmt1.setString(2, "Alba");
			stmt1.setString(3, "Rodriguez");
			stmt1.setString(4, "albarodri");
			stmt1.setString(5, "rodriguez17");

			PreparedStatement stmt2 = con.prepareStatement(
					"INSERT INTO usuario (DNI, Nombre, Apellido,Usuario,Contraseña) VALUES (?,?,?,?,?)");
			stmt2.setInt(1, 67456718);
			stmt2.setString(2, "Jon");
			stmt2.setString(3, "Lopez");
			stmt2.setString(4, "jonlopez");
			stmt2.setString(5, "lopezjon");

			PreparedStatement stmt3 = con.prepareStatement(
					"INSERT INTO Administrador (DNI, Nombre, Apellidos,Usuario,Contraseña) VALUES (?,?,?,?,?)");
			stmt3.setInt(1, 56735467);
			stmt3.setString(2, "Carmen");
			stmt3.setString(3, "Perez");
			stmt3.setString(4, "carmen.perez");
			stmt3.setString(5, "carpez");

			PreparedStatement stmt4 = con.prepareStatement(
					"INSERT INTO Administrador (DNI, Nombre, Apellidos,Usuario,Contraseña) VALUES (?,?,?,?,?)");
			stmt4.setInt(1, 98476892);
			stmt4.setString(2, "Josu");
			stmt4.setString(3, "Sanchez");
			stmt4.setString(4, "sanchez09");
			stmt4.setString(5, "josu.sanchez");

			PreparedStatement stmt5 = con.prepareStatement(
					"INSERT INTO Administrador (DNI, Nombre, Apellidos,Usuario,Contraseña) VALUES (?,?,?,?,?)");
			stmt5.setInt(1, 56726378);
			stmt5.setString(2, "Miriam");
			stmt5.setString(3, "Rodriguez");
			stmt5.setString(4, "miriam.rodriguez");
			stmt5.setString(5, "rodriguez23");

			con.close();
			
		} catch (SQLException e) {
			// No se ha podido obtener la conexión a la base de datos
			System.out.println("Error. No se ha podido conectar a la base de datos " + e.getMessage());
		}

	}

}
