package testsPersonas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import personas.Usuario;

public class UsuarioTest {

	Usuario u;
	@Before
	public void inicializar() {
		u = new Usuario(35468792, "Pedro", "Rodriguez", "pedro.rodriguez", "rodri01", 1, 10);
	}

	
	@Test
	public void testGetIdLigaActual() {
		assertEquals(1,u.getIdLigaActual());
	}
	
	@Test
	public void testSetIdLigaActual() {
		u.setIdLigaActual(2);
		assertEquals(2,u.getIdLigaActual());
	}
	
	
	@Test
	public void testGetBote() {
		assertEquals(10, u.getBote(), 0);
	}
	
	@Test
	public void testSetBote() {
		u.setBote(2030);
		assertEquals(2030, u.getBote(), 0);
	}
	
	@Test
	public void testToString() {
		String ms = "Pedro Rodriguez";
		assertEquals(ms, u.toString());
	}
	
	@Test
	public void testGetDni() {
		assertEquals(35468792, u.getDni());
	}
	
	@Test
	public void testSetDni() {
		u.setDni(111111);
		assertEquals(111111, u.getDni());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Pedro", u.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		u.setNombre("Jon");
		assertEquals("Jon", u.getNombre());
	}
	
	@Test
	public void testGetApellido() {
		assertEquals("Rodriguez", u.getApellido());
	}
	
	@Test
	public void testSetApellido() {
		u.setApellido("Saez");
		assertEquals("Saez", u.getApellido());
	}
	
	@Test
	public void testGetUsuario() {
		assertEquals("pedro.rodriguez", u.getUsuario());
	}
	
	@Test
	public void testSetUsuario() {
		u.setUsuario("pedrorodriguez");
		assertEquals("pedrorodriguez", u.getUsuario());
	}
	
	@Test
	public void testGetContrasena() {
		assertEquals("rodri01", u.getContrasena());
	}
	
	@Test
	public void testSetContrasena() {
		u.setContrasena("123");
		assertEquals("123", u.getContrasena());
	}

}
