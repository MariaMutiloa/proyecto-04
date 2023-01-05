package testszonaAdministrador.GestionUsuarios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import personas.Usuario;
import zonaAdministrador.GestionUsuarios.VentanaVerUsuario;

public class VentanaVerUsuarioTest {

	@Test
	public void testAnyadirUsuarios() {
		String url = "jdbc:sqlite:DatosBingoTest.db";
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			Usuario u1 = new Usuario(24356782, "Ruben", "Garcia", "ruben.garcia", "ruben01", 1, 0);
			Usuario u2 = new Usuario(56478903, "Alba", "Rodriguez", "albarodri", "rodriguez17", 1, 0);
			Usuario u3 = new Usuario(67456718, "Jon", "Lopez", "jonlopez", "lopezjon", 1, 0);
			Usuario u4 = new Usuario(72554022, "Ane", "Pradera", "anepradera", "ane", 1, 0);
			Usuario u5 = new Usuario(765436, "Miren", "Gaztelumendi", "anepredera", "123", 1, 0);
			listaUsuarios = VentanaVerUsuario.anyadirUsuarios(listaUsuarios, url);
			assertEquals("Alba", listaUsuarios.get(0).getNombre());
			assertEquals("Ane", listaUsuarios.get(1).getNombre());
			assertEquals("Jon", listaUsuarios.get(2).getNombre());
			assertEquals("Miren", listaUsuarios.get(3).getNombre());
			assertEquals("Ruben", listaUsuarios.get(4).getNombre());
			assertEquals(u2, listaUsuarios.get(0));
			assertEquals(u4, listaUsuarios.get(1));
			assertEquals(u3, listaUsuarios.get(2));
			assertEquals(u5, listaUsuarios.get(3));
			assertEquals(u1, listaUsuarios.get(4));
		} catch (Exception e) {
		}

	}

}
