package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gestionBD.ConexionBD;

public class GestionPartidasTest {
	
	private static String bdReal = "jdbc:sqlite:DatosBingo.db";
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";

	@Before
	public void inic() {
		ConexionBD.realizarConexion("jdbc:sqlite:DatosBingoTest.db");
	}
	
	

}
