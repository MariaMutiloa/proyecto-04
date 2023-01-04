package testsgestionBD;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.ConexionBD;
import gestionBD.GestionPartidas;
import gestionBD.GestionUsuarios;
import personas.Administrador;
import personas.Usuario;

public class GestionUsuariosTest {

	private static Logger logger = Logger.getLogger(GestionUsuarios.class.getName());

	
	@Before
	public void inic() {
		ConexionBD.realizarConexion("jdbc:sqlite:DatosBingoTest.db");
	}
	
	
	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario(24356782, "Ruben", "Garcia", "ruben.garcia", "ruben01", 1, 0);
		assertEquals(u.getNombre(), GestionUsuarios.getUsuario("ruben.garcia", "ruben01").getNombre());
		assertEquals(u.getApellido(), GestionUsuarios.getUsuario("ruben.garcia", "ruben01").getApellido());
		assertEquals(u.getIdLigaActual(), GestionUsuarios.getUsuario("ruben.garcia", "ruben01").getIdLigaActual());
		assertNull(GestionUsuarios.getUsuario("juan", "prueba"));
		assertNull(GestionUsuarios.getUsuario("ruben.garcia", "prueba"));
	}
	
	@Test
	public void testGetAdministrador() {
		Administrador a = new Administrador(56735467, "Carmen", "Perez", "carmen.perez", "carpez");
		assertEquals(a.getNombre(), GestionUsuarios.getAdministrador("carmen.perez", "carpez").getNombre());
		assertEquals(a.getApellido(), GestionUsuarios.getAdministrador("carmen.perez", "carpez").getApellido());
		assertEquals(a.getDni(), GestionUsuarios.getAdministrador("carmen.perez", "carpez").getDni());
		assertNull(GestionUsuarios.getAdministrador("juan", "prueba"));
		assertNull(GestionUsuarios.getAdministrador("carmen.perez", "prueba"));
	}
	
	@Test
	public void testComprobarUsuario() {
		assertTrue(GestionUsuarios.comprobarUsuario("ruben.garcia"));
		assertFalse(GestionUsuarios.comprobarUsuario("juanjo"));
	} 
	
	
	@Test 
	public void testInsertarUsuario() {
		
		GestionUsuarios.insertarUsuario(11111111, "Ana", "Escudero", "anaescudero", "123");
		assertTrue(GestionUsuarios.comprobarUsuario("anaescudero"));
		
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")){
			System.out.println("entro try");
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE Usuario=?");
			stmt.setString(1, "anaescudero");
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error al eliminar usuario");
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testBuscarUsuarioPorID() {
		Usuario u = GestionUsuarios.buscarUsuarioPorID(24356782);
		
		assertEquals("Ruben", u.getNombre());
		assertEquals("Garcia", u.getApellido());
		assertEquals("ruben.garcia", u.getUsuario());
		assertEquals("ruben01", u.getContrasena());	
	}
	
	@Test
	public void testInsertarNumerosDelCarton() {
		
	}
	
	@Test
	public void testCartonNuevo() {
		
	}
	
	@Test
	public void testBuscarPartidaActiva() {
		Partida p = GestionUsuarios.buscarPartidaActiva();
		assertEquals(1, p.getIDPartida());
		assertNotEquals(0, p.getIDPartida());
	}
	
	@Test
	public void testActualizarBingoBD() {
//		GestionUsuarios.actualizarBingoBD(30);
//		Carton c = GestionPartidas.getCarton(30);
//		System.out.println(c.getIDCarton());
//		assertEquals(1, c.getBingo());
	}
	
	@Test
	public void testActualizarCarteraBD() {
		
	}
	
	

}
