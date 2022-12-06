package testElementosOrganizacion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import elementosOrganizacion.Carton;

public class CartonTest {

	@Test
	public void testGetIDCarton() {
		Carton c = new Carton(12, 13, 1);
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
	    //assertEquals(1, (new HashSet<>(carton)).size());
	}
	
	/*
	 * // Crear una instancia del objeto
MiObjeto obj = new MiObjeto();

// Invocar el método getListaNumeros
List<Integer> resultado = obj.getListaNumeros();

// Comparar el resultado con lo que se espera
assertEquals(resultado, [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]);

// Si la comparación falla, lanzar un mensaje de error
if (resultado != [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]) {
  throw new AssertionError("Error: el resultado no es el esperado");
}
	 */

	@Test
	public void testGetListaNumeros() {
		Carton obj = new Carton(1,1);
		List<Integer> listaNumeros = obj.getListaNumeros();
		assertEquals(15, listaNumeros.size());
	}

}
