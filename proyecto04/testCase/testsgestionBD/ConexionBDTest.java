package testsgestionBD;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gestionBD.ConexionBD;
import personas.Administrador;
import personas.Usuario;

public class ConexionBDTest {
	
	@Before
	public void inic() {
		ConexionBD.realizarConexion("jdbc:sqlite:DatosBingoTest.db");
	}
	
	

}
