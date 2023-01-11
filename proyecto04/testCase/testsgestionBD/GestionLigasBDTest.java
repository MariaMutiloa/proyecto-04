package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Test;

import gestionBD.GestionLigasBD;
import gestionBD.GestionUsuarios;
import gestionBD.GestionZonaUsuario;
import personas.Usuario;

public class GestionLigasBDTest {
	
	private static String bdReal = "jdbc:sqlite:DatosBingo.db";
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";

	@Test
	public void testGetLigas() {
		Usuario u1 = new Usuario(1, "prueba", "prueba", "prueba1", "prueba1", 1, 10);
		Usuario u2 = new Usuario(2, "prueba", "prueba", "prueba2", "prueba1", 2, 10);
		Usuario u3 = new Usuario(3, "prueba", "prueba", "prueba3", "prueba1", 3, 10);
		GestionZonaUsuario.insertarUsuario(u1.getDni(), u1.getNombre(), u1.getApellido(), u1.getUsuario(), u1.getContrasena(), bdTest);
		GestionZonaUsuario.insertarUsuario(u2.getDni(), u2.getNombre(), u2.getApellido(), u2.getUsuario(), u2.getContrasena(), bdTest);
		GestionZonaUsuario.insertarUsuario(u3.getDni(), u3.getNombre(), u3.getApellido(), u3.getUsuario(), u3.getContrasena(), bdTest);
		Integer [] ligas = GestionLigasBD.getLigas(bdTest);
		assertFalse(ligas.length>=3);
		GestionUsuarios.eliminar(1, bdTest);
		GestionUsuarios.eliminar(2, bdTest);
		GestionUsuarios.eliminar(3, bdTest);	
	}

	

}
