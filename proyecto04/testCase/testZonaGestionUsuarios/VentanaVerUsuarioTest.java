package testZonaGestionUsuarios;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import personas.Usuario;
import zonaGestionUsuarios.VentanaVerUsuario;

public class VentanaVerUsuarioTest {

	@Test
	public void testAnyadirUsuarios() {
		Usuario u = new Usuario(35468792, "Pedro", "Rodriguez", "pedro.rodriguez", "rodri01", 1, 1000);
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios.add(u);
		assertEquals(listaUsuarios, VentanaVerUsuario.anyadirUsuarios(listaUsuarios));

	}
}
