package test;

import static org.junit.Assert.*;

import org.junit.Test;

import personas.Usuario;

public class UsuarioTest {

	@Test
	public void testGetIdLigaActual() {
		Usuario persona = new Usuario(56783687, "Pedro", "Rodriguez", "pedro.rodri", "rodri01", 1, 1000);
		assertEquals(1, persona.getIdLigaActual());
	}
}