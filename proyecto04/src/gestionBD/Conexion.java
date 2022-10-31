package gestionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Conexion {

//Hacemos la conexi�n a la base de datos
	
	try {
		Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				// Puede ser que no encuentre la clase a cargar.
				// Esto puede deberse a que el .jar necesario no est� referenciado.
				System.out.println("No se ha podido cargar el driver de la base de datos");
			}
			
			// Ahora se usa JDBC para conectar con la base de datos.
			// Este proceso es com�n a todas las bases de datos compatibles
			// con JDBC. Pero cambiar� el string de conexi�n seg�n el modelo
			// de base de datos utilizado. Es necesario consultar la documentaci�n
			// del driver y/o de la base de datos.
			
			// Para SQLite es suficiente con indicarle la ruta al fichero que
			// contiene la base de datos completa.
			// El formato de este string de conexi�n para SQLIte es:
			// jdbc:sqlite:ruta_al_fichero
			
			try (Connection conn = DriverManager.getConnection("jdbc:sqlite:ficheros/ejemplo.db")) {

				// A partir de una conexi�n activa obtenemos el objeto para ejecutar
				// sentencias SQL en la base de datos.
				try (Statement stmt = conn.createStatement()) {
				
					// Como vamos a realizar una consulta usamos el m�todo para queries.
					// La base de datos usada en este ejemplo contiene una tabla "usuarios".
					// Esta tabla contiene dos columnas: nombre y apellido
					
					// Si la consulta se ejecuta correctamente el resultado se devuelve
					// en un objeto de tipo ResultSet sobre el que podemos iterar para obtener
					// cada fila devuelta como resultado de la consulta.
					try (ResultSet rs = stmt.executeQuery("SELECT nombre, apellido FROM usuarios")) {
						while (rs.next()) {
							// Mientras tenga filas
							// Cogemos cada columna contenida en la fila
							
							String name = rs.getString("nombre");
							String surname = rs.getString("apellido");
							
							// y hacemos algo con esos datos: imprimir, crear un objeto, etc
							System.out.println(name + " " + surname);
						}
					} catch (SQLException e) {
						System.out.println("No se ha podido ejecutar la sentencia SQL." + e.getMessage());
					}

				} catch (SQLException e) {
					// No se ha podido obtener la conexi�n a la base de datos
					System.out.println("Error. No se ha podido crear el statement " + e.getMessage());
				}

				// Insertamos una nueva fila en la base de datos. Leemos los datos de la entrada.
				try (Scanner sc = new Scanner(System.in)) {
					System.out.println();
					System.out.println();
					System.out.println("Datos del nuevo usuario:");
					System.out.print("Nombre: ");
					String name = sc.nextLine();

					System.out.print("Apellido: ");
					String surname = sc.nextLine();

					System.out.print("Edad: ");
					int age = sc.nextInt();

					// Se construye la sentencia SQL con los datos introducidos por el usuario
					// Se utiliza un PreparedStatement que evita los problemas al concatenar datos
					// Tambi�n evita que haya que poner las comillas manualmente

					try (PreparedStatement insert = conn.prepareStatement("INSERT INTO usuarios VALUES (?, ?, ?)")) {
						// Se rellenan los huecos de la PreparedStatement con los datos
						insert.setString(1, name);
						insert.setString(2, surname);
						insert.setInt(3, age);

						int rows = insert.executeUpdate();

						// el m�todo devuelve el n�mero de filas afectadas por la actualizaci�n
						System.out.print("Filas actualizadas: " + rows);
					} catch (SQLException e) {
						System.out.println("Error. No se han podido insertar los datos. " + e.getMessage());
					}
				}
				
			} catch (SQLException e) {
				// No se ha podido obtener la conexi�n a la base de datos
				System.out.println("Error. No se ha podido conectar a la base de datos. " + e.getMessage());
			}
		}
}
