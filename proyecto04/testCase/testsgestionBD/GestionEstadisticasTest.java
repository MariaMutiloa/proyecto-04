package testsgestionBD;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gestionBD.GestionEstadisticas;

public class GestionEstadisticasTest {

	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";
	
	@Test
	public void testNumMasVecesCantado() {
		assertEquals(40, GestionEstadisticas.numMasVecesCantado(bdTest));
		
	}
	
	@Test
	public void testBoteMaxPartida() {
		assertEquals(1,2, GestionEstadisticas.boteMaxPartida(bdTest));
		
	}
	
	@Test
	public void testUsuarioMayorCartera() {
		assertEquals("anepradera con 12,00", GestionEstadisticas.usuarioMayorCartera(bdTest));
		
	}
	
	@Test
	public void testanyadirNum() {
		List<Integer> comprobar = GestionEstadisticas.anyadirNum(bdTest);
		assertEquals(1110, comprobar.size());
	}
	
	@Test
	public void testGetBoteMax() {
		assertEquals(12,00, GestionEstadisticas.getBoteMax(bdTest));
	}
	

}
