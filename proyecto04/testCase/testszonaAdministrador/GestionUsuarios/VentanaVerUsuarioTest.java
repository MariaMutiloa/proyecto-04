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
		String url = "jdbc:sqlite:DatosBingo.db";
		try {
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			Usuario u1 = new Usuario(24356782, "Ruben", "Garcia", "ruben.garcia", "ruben01", 1, 10);
			Usuario u2 = new Usuario(56478903, "Alba", "Rodriguez", "albarodri", "rodriguez17", 1, 10);
			Usuario u3 = new Usuario(67456718, "Jon", "Lopez", "jonlopez", "lopezjon", 1, 10);
			Usuario u4 = new Usuario(72554022, "Ane", "Pradera", "anepradera", "ane", 1, 10);
			listaUsuarios.add(u1);
			listaUsuarios.add(u2);
			listaUsuarios.add(u3);
			listaUsuarios.add(u4);

			assertEquals(listaUsuarios, VentanaVerUsuario.anyadirUsuarios(listaUsuarios,url));
		} catch (Exception e) {

		}

	}

}