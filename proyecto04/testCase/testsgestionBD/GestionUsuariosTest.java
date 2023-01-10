package testsgestionBD;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import gestionBD.GestionUsuarios;
import personas.UsuarioExtendido;


public class GestionUsuariosTest {
	
	private static String bdReal = "jdbc:sqlite:DatosBingo.db";
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";

	
	@Test
	public void testGetPartidasJugadas() {
		int resultado = GestionUsuarios.getPartidasJugadas(24356782, bdTest);
		assertEquals(7, resultado);
	}
	
	@Test
	public void testGetAllUsuarios() {
		UsuarioExtendido u1 = new UsuarioExtendido(24356782, "ruben.garcia", "Ruben", "Garcia", "ruben01", 1, 8);
		UsuarioExtendido u2 = new UsuarioExtendido(72554022, "anepradera", "Ane", "Pradera", "ane", 1, 8);
		UsuarioExtendido u3 = new UsuarioExtendido(67456718, "jonlopez", "Jon", "Lopez", "lopezjon", 1, 9);
		UsuarioExtendido u4 = new UsuarioExtendido(56478903, "albarodri", "Alba", "Rodriguez", "rodriguez17", 1, 9);
		List<UsuarioExtendido> lista = GestionUsuarios.getAllUsuarios(bdTest);
		assertEquals(u1, lista.get(0));
		assertEquals(u2, lista.get(1));
		assertEquals(u3, lista.get(2));
		assertEquals(u4, lista.get(3));
	}

}
