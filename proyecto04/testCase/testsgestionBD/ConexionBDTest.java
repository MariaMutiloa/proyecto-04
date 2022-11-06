package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Test;

import gestionBD.ConexionBD;
import personas.Administrador;
import personas.Usuario;

public class ConexionBDTest {

	@Test
	public void testGetAdministrador() {
		Administrador a = new Administrador(56735467, "Carmen", "Perez", "carmen.perez", "carpez");
		assertEquals(a, ConexionBD.getUsuario("carmen.perez", "carperez"));
		assertNull(ConexionBD.getUsuario("juan", "prueba"));
		assertNull(ConexionBD.getUsuario("carmen.perez", "prueba"));
	}
	
	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario(24356782, "Ruben", "Garcia", "ruben.garcia", "ruben01", 1, 0);
		assertEquals(u, ConexionBD.getUsuario("ruben.garcia", "ruben01"));
		assertNull(ConexionBD.getUsuario("juan", "prueba"));
		assertNull(ConexionBD.getUsuario("ruben.garcia", "prueba"));
	}

}
