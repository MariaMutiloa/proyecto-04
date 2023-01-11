package testElementosOrganizacion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;

public class PartidaTest {

	@Test
	public void testGetParticipantes() {
		Partida partida = new Partida(true);
		List<Carton> participantes = new ArrayList<Carton>();
		participantes.add(new Carton(1,1,1, true));
	    participantes.add(new Carton(2,2,2, true));
	    
	    partida.setParticipantes(participantes);
	    
	    assertEquals(participantes, partida.getParticipantes());
	}
	
	@Test
	public void testGetIDPartida() {
	    Partida partida = new Partida(true);
	    int idEsperada = 123456;
	    partida.setIDPartida(idEsperada);

	    assertEquals(idEsperada, partida.getIDPartida());
	}
	
	@Test
	public void testGetBoteBingo() {
	    Partida partida = new Partida(true);
	    float boteEsperado = 100.50f;
	    partida.setBoteBingo(boteEsperado);

	    assertEquals(boteEsperado, partida.getBoteBingo(), 0.001);
	}
	
	@Test
	public void testGetGanadorBingo() {
		Partida partida = new Partida(true);
		Carton carton = new Carton(1, 1, 1, true);
		partida.setGanadorBingo(carton);
		
		assertEquals(carton, partida.getGanadorBingo());
	}
	
	@Test
	public void testPartida() {
		Partida p = new Partida(1, 2, 1, 1);
		assertEquals(1, p.getIDPartida());
	}

}
