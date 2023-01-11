package testsgestionBD;

import org.junit.Test;

import gestionBD.ConexionBD;

public class ConexionBDTest {
	
	@Test
	public void realizarConexionTest() {
		ConexionBD.realizarConexion("jdbc:sqlite:DatosBingoTest.db");
	}
	
	

}
