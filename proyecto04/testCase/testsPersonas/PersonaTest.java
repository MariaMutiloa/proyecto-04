package testsPersonas;

import static org.junit.Assert.*;

import org.junit.Test;

import personas.Administrador;

public class PersonaTest {

	@Test
	public void testEquals() {
		Administrador a1 = new Administrador(1, "a", "b", "c", "d");
		Administrador a2 = new Administrador(1, "a2", "b2", "c2", "d2");
		Administrador a3 = new Administrador(2, "a3", "b3", "c3", "d3");
		boolean e = a1.equals(a2);
		assertTrue(e);
		boolean f = a1.equals(a3);
		assertFalse(f);
		String s = "prueba";
		assertFalse(a1.equals(s));
		
		
	}

}
