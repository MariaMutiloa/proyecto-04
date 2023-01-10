package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
