package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Test;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import gestionBD.GestionUsuarios;
import gestionBD.GestionZonaUsuario;
import personas.Administrador;
import personas.Usuario;

public class GestionZonaUsuarioTest {

	private static String bdReal = "jdbc:sqlite:DatosBingo.db";
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";
	
	@Test
	public void testGetUsuario() {
		Usuario u = new Usuario(4356728, "Nora", "Fernandez", "norafernandez", "fernandeznora", 1, 10);
		Usuario comparar = GestionZonaUsuario.getUsuario("norafernandez", "fernandeznora", bdTest);
		assertEquals(u, comparar);
	}
	
	@Test
	public void testGetAdministrador() {
		Administrador ad = new Administrador(56726378, "Miriam", "Rodriguez", "miriam.rodriguez", "rodriguez23");
		Administrador comparar = GestionZonaUsuario.getAdministrador("miriam.rodriguez", "rodriguez23", bdTest);
		assertEquals(ad, comparar);
	}
	
	@Test
	public void testComprobarUsuario() {
		boolean resultadoMetodo = GestionZonaUsuario.comprobarUsuario("jon.fernandez", bdTest);
		assertFalse(resultadoMetodo);
	}
	
	@Test
	public void testBuscarUsuarioPorID() {
		Usuario u = new Usuario(1, "prueba", "prueba", "prueba", "prueba", 1, 10);
		GestionZonaUsuario.insertarUsuario(u.getDni(), u.getNombre(), u.getApellido(), u.getUsuario(), u.getContrasena(), bdTest);
		Usuario uSacado = GestionZonaUsuario.buscarUsuarioPorID(1, bdTest);
		assertTrue(u.equals(uSacado));
		GestionUsuarios.eliminar(1, bdTest);
	}
	
	@Test
	public void testBuscarPartidaActiva() {
		GestionPartidas.nueva(bdTest);
		Partida p = GestionZonaUsuario.buscarPartidaActiva(bdTest);
		assertNotNull(p);
		GestionPartidas.setGanadorBingo(0, p, 0, bdTest);
	}
	
	@Test
	public void testActualizarCartera() {
		GestionZonaUsuario.actualizarCarteraBD(72554022, 12, bdTest);
		Usuario u = GestionZonaUsuario.getUsuario("anepradera", "ane", bdTest);
		assertEquals(12, u.getBote(), 0);
	}
	
	

}
