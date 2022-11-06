package testsPersonas;

import static org.junit.Assert.*;

import org.junit.Test;

import personas.Usuario;

public class UsuarioTest {

	@Test
	public void testGetIdLigaActual() {
		Usuario u = new Usuario(35468792, "Pedro", "Rodriguez", "pedro.rodriguez", "rodri01", 1, 1000);
		assertEquals(1,u.getIdLigaActual());
	}
	
	@Test
	public void testSetIdLigaActual() {
		Usuario u = new Usuario(35468792, "Pedro", "Rodriguez", "pedro.rodriguez", "rodri01", 1, 1000);
		u.setIdLigaActual(2);
		assertEquals(2,u.getIdLigaActual());
	}
	
	
	@Test
	public void testGetBote() {
		Usuario u = new Usuario(35468792, "Pedro", "Rodriguez", "pedro.rodriguez", "rodri01", 1, 1000);
		assertEquals(1000, u.getBote());
	}
	
	@Test
	public void testSetBote() {
		Usuario u = new Usuario(35468792, "Pedro", "Rodriguez", "pedro.rodriguez", "rodri01", 1, 1000);
		u.setBote(2030);
		assertEquals(2030, u.getBote());
	}

}
