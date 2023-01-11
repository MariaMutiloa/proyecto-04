package testsgestionBD;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
	

}
