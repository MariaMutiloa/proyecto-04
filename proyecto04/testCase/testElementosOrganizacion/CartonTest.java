package testElementosOrganizacion;

import static org.junit.Assert.*;

import org.junit.Test;

import elementosOrganizacion.Carton;

public class CartonTest {

	@Test
	public void testGetIDCarton() {
		Carton c = new Carton(12, 13, 1);
		assertEquals(12, c.getIDCarton());
	}
	


}
