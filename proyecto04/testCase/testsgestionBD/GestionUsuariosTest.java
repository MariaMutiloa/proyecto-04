package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gestionBD.ConexionBD;
import gestionBD.GestionUsuarios;
import personas.Administrador;
import personas.Usuario;

public class GestionUsuariosTest {

	@Before
	public void inic() {
		ConexionBD.realizarConexion("jdbc:sqlite:DatosBingoTest.db");
	}
	
	@Test
	public void testGetAdministrador() {
		Administrador a = new Administrador(56735467, "Carmen", "Perez", "carmen.perez", "carpez");
		assertEquals(a.getNombre(), GestionUsuarios.getAdministrador("carmen.perez", "carperez").getNombre());
		assertEquals(a.getApellido(), GestionUsuarios.getAdministrador("carmen.perez", "carperez").getDni());
		assertEquals(a.getDni(), GestionUsuarios.getAdministrador("carmen.perez", "carperez").getNombre());
		assertNull(GestionUsuarios.getAdministrador("juan", "prueba"));
		assertNull(GestionUsuarios.getAdministrador("carmen.perez", "prueba"));
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
	public void testComprobarUsuario() {
		assertTrue(GestionUsuarios.comprobarUsuario("Ruben"));
		assertFalse(GestionUsuarios.comprobarUsuario("Juanjo"));
	}
	
	@Test 
	public void testInsertarUsuario() {
		
	}
	
	

}
