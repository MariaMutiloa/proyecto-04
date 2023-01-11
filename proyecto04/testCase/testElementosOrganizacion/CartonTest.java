package testElementosOrganizacion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elementosOrganizacion.Carton;
import gestionBD.GestionUsuarios;
import gestionBD.GestionZonaUsuario;
import personas.Usuario;

public class CartonTest {

	@Test
	public void testGetIDCarton() {
		Carton c = new Carton(12, 13, 1, true);
		assertEquals(12, c.getIDCarton());
	}
	
	@Test
	public void testGenerarCarton() {
		List<Integer> numeros = Carton.generarCarton();
		assertEquals(15, numeros.size());
	}
	
	@Test
	public void testDibujarCarton() {
		int[][] carton = Carton.dibujarCarton();
		assertEquals(3, carton.length);
		assertEquals(5, carton[0].length);
	}
	
	@Test
	public void testSacarNumero() {
		List<Integer> carton = new ArrayList<>();
	    Carton.sacarNumero(carton);
	    Carton.sacarNumero(carton);
	    assertEquals(2, carton.size());
	}
	

	@Test
	public void testGetListaNumeros() {
		Carton obj = new Carton(1,1,1, true);
		List<Integer> listaNumeros = obj.getListaNumeros();
		assertEquals(15, listaNumeros.size());
	}
	
	@Test
	public void testGetIDUsuario() {
		Carton c = new Carton(12, 13, 1, true);
		assertEquals(13, c.getIDUsuario());
	}
	
	@Test
	public void testSetIDUsuario() {
		Carton c = new Carton(12,13,1, true);
		c.setIDUsuario(14);
		assertEquals(14, c.getIDUsuario());
	}
	
	@Test
	public void testGetCoste() {
		Carton c = new Carton(12, 13, 1, true);
		assertEquals(2f, c.getCoste(), 0f);
	}
	
	@Test
	public void testSetCoste() {
		Carton c = new Carton(12, 13, 1, true);
		c.setCoste(1);
		assertEquals(1f, c.getCoste(), 0f);
	}
	
	@Test
	public void testGetIDPartida() {
		Carton c = new Carton(12, 13, 1, true);
		assertEquals(1, c.getIDPartida());
	}
	
	@Test
	public void testSetIDPartida() {
		Carton c = new Carton(12, 13, 1, true);
		c.setIDPartida(2);
		assertEquals(2, c.getIDPartida());
	}
	
	@Test
	public void testGetBingo() {
		Carton c = new Carton(12, 13, 1, true);
		assertEquals(0, c.getBingo());
	}
	
	@Test
	public void testSetBingo() {
		Carton c = new Carton(12, 13, 1, true);
		c.setBingo(1);
		assertEquals(1, c.getBingo());
	}
	
	@Test
	public void testSetIDCarton() {
		Carton c = new Carton(12, 13, 1, true);
		c.setIDCarton(11);
		assertEquals(11, c.getIDCarton());
	}
	
	

	@Test
	public void testBajarCartera() {
		Usuario u = new Usuario(1, "Prueba", "Prueba", "prueba", "prueba", 1, 10);
		GestionZonaUsuario.insertarUsuario(u.getDni(), u.getNombre(), u.getApellido(), u.getUsuario(), u.getContrasena(), u.getIdLigaActual(), u.getBote() ,
				"jdbc:sqlite:DatosBingoTest.db");
		Carton.bajarCartera(u, "jdbc:sqlite:DatosBingoTest.db");
		Usuario uPost = GestionZonaUsuario.getUsuario("prueba", "prueba", "jdbc:sqlite:DatosBingoTest.db");
		assertEquals(8, uPost.getBote(), 0);
		//ELIMINAR USUARIO
		GestionUsuarios.eliminar(1, "jdbc:sqlite:DatosBingoTest.db");
	}
	
	

}
