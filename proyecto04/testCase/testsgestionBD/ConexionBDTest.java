package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gestionBD.ConexionBD;
import personas.Administrador;
import personas.Usuario;

public class ConexionBDTest {
	ConexionBD conn = new ConexionBD();
	
	@Before
	public void inic() {
		conn.realizarConexion("jdbc:sqlite:DatosBingoTest.db");
	}
	
	@Test
	public void testGetAdministrador() {
		Administrador a = new Administrador(56735467, "Carmen", "Perez", "carmen.perez", "carpez");
		assertEquals(a.getNombre(), conn.getUsuario("carmen.perez", "carperez").getNombre());
		assertEquals(a.getApellido(), conn.getUsuario("carmen.perez", "carperez").getDni());
		assertEquals(a.getDni(), conn.getUsuario("carmen.perez", "carperez").getNombre());
		assertNull(ConexionBD.getUsuario("juan", "prueba"));
		assertNull(ConexionBD.getUsuario("carmen.perez", "prueba"));
	}
	
	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario(24356782, "Ruben", "Garcia", "ruben.garcia", "ruben01", 1, 0);
		assertEquals(u.getNombre(), conn.getUsuario("ruben.garcia", "ruben01").getNombre());
		assertEquals(u.getApellido(), conn.getUsuario("ruben.garcia", "ruben01").getApellido());
		assertEquals(u.getIdLigaActual(), conn.getUsuario("ruben.garcia", "ruben01").getIdLigaActual());
		assertNull(conn.getUsuario("juan", "prueba"));
		assertNull(conn.getUsuario("ruben.garcia", "prueba"));
	}

}
