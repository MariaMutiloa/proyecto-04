package testsPersonas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gestionBD.GestionUsuarios;
import personas.UsuarioExtendido;

public class UsuarioExtendidoTest {

	UsuarioExtendido u;

	@Before
	public void inicializar() {
		u = new UsuarioExtendido(35468792, "Maria", "Mutiloa", "maria.mutiloa", "maria10", 1, 10);
	}

	@Test
	public void testSetPartidasGanadas() {
		u.setPartidasGanadas(3);
		assertEquals(3, u.getPartidasGanadas());
	}

	@Test
	public void testSetPartidasJugadas() {
		u.setPartidasJugadas(2);
		assertEquals(2, u.getPartidasJugadas());
	}

}
