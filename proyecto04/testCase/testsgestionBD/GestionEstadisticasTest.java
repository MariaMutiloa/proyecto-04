package testsgestionBD;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import gestionBD.GestionEstadisticas;

public class GestionEstadisticasTest {

	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";
	
	@Test
	public void numMasVecesCantadoTest() {
		assertEquals(40, GestionEstadisticas.numMasVecesCantado(bdTest));
		
	}
	

}
