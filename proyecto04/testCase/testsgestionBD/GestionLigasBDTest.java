package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Test;

import gestionBD.GestionLigasBD;
import gestionBD.GestionUsuarios;
import gestionBD.GestionZonaUsuario;
import personas.Usuario;

public class GestionLigasBDTest {
	
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";

	@Test
	public void testGetLigas() {
		Usuario u1 = new Usuario(1, "prueba", "prueba", "prueba1", "prueba1", 1, 10);
		Usuario u2 = new Usuario(2, "prueba", "prueba", "prueba2", "prueba1", 2, 10);
		Usuario u3 = new Usuario(3, "prueba", "prueba", "prueba3", "prueba1", 3, 10);
		GestionZonaUsuario.insertarUsuario(u1.getDni(), u1.getNombre(), u1.getApellido(), u1.getUsuario(), u1.getContrasena(), u1.getIdLigaActual(), u1.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u2.getDni(), u2.getNombre(), u2.getApellido(), u2.getUsuario(), u2.getContrasena(), u2.getIdLigaActual(), u2.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u3.getDni(), u3.getNombre(), u3.getApellido(), u3.getUsuario(), u3.getContrasena(), u3.getIdLigaActual(), u3.getBote(), bdTest);
		Integer [] ligas = GestionLigasBD.getLigas(bdTest);
		assertEquals(4, ligas.length);
		GestionUsuarios.eliminar(1, bdTest);
		GestionUsuarios.eliminar(2, bdTest);
		GestionUsuarios.eliminar(3, bdTest);	
	}
	
	
	@Test
	public void getUsuariosLigaTest() {
		Usuario u1 = new Usuario(1, "prueba", "prueba", "prueba1", "prueba1", 3, 10);
		Usuario u2 = new Usuario(2, "prueba", "prueba", "prueba2", "prueba2", 3, 10);
		Usuario u3 = new Usuario(3, "prueba", "prueba", "prueba3", "prueba3", 3, 10);
		Usuario u4 = new Usuario(1, "prueba", "prueba", "prueba4", "prueba4", 3, 10);
		Usuario u5 = new Usuario(2, "prueba", "prueba", "prueba5", "prueba5", 3, 10);
		Usuario u6 = new Usuario(3, "prueba", "prueba", "prueba6", "prueba6", 3, 10);
		GestionZonaUsuario.insertarUsuario(u1.getDni(), u1.getNombre(), u1.getApellido(), u1.getUsuario(), u1.getContrasena(), u1.getIdLigaActual(), u1.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u2.getDni(), u2.getNombre(), u2.getApellido(), u2.getUsuario(), u2.getContrasena(), u2.getIdLigaActual(), u2.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u3.getDni(), u3.getNombre(), u3.getApellido(), u3.getUsuario(), u3.getContrasena(), u3.getIdLigaActual(), u3.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u4.getDni(), u4.getNombre(), u4.getApellido(), u4.getUsuario(), u1.getContrasena(), u4.getIdLigaActual(), u4.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u5.getDni(), u5.getNombre(), u5.getApellido(), u2.getUsuario(), u2.getContrasena(), u5.getIdLigaActual(), u5.getBote(), bdTest);
		GestionZonaUsuario.insertarUsuario(u6.getDni(), u6.getNombre(), u6.getApellido(), u3.getUsuario(), u3.getContrasena(), u6.getIdLigaActual(), u6.getBote(), bdTest);
		assertEquals(6,(GestionLigasBD.getUsuariosLiga(3, bdTest)).size());
		GestionUsuarios.eliminar(1, bdTest);
		GestionUsuarios.eliminar(2, bdTest);
		GestionUsuarios.eliminar(3, bdTest);
		GestionUsuarios.eliminar(4, bdTest);
		GestionUsuarios.eliminar(5, bdTest);
		GestionUsuarios.eliminar(6, bdTest);	
	}
	
	@Test
	public void getAllUsuariosTest() {
		assertEquals(6, GestionLigasBD.getAllUsuarios(bdTest).size());
	}
	
	@Test
	public void actualizarLigasTest() {
		GestionLigasBD.actualizarLigas(bdTest);
		assertEquals("Ruben", GestionLigasBD.getAllUsuarios(bdTest).get(0).getNombre());
		
	}
	

}
