package testsPersonas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import personas.Administrador;

public class AdministradorTest {
	
	Administrador a;
	@Before
	public void inicializar() {
		a = new Administrador(123456789, "Sara", "Martinez", "sara.martinez", "123");
	}
	
	@Test
	public void testToString() {
		String ms = "123456789: Sara Martinez Usuario: sara.martinez Contra: 123";
		assertEquals(ms, a.toString());
	}

}
