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
		Partida partida = new Partida();
		List<Carton> participantes = new ArrayList<Carton>();
		participantes.add(new Carton(1,1,1));
	    participantes.add(new Carton(2,2,2));
	    
	    partida.setParticipantes(participantes);
	    
	    assertEquals(participantes, partida.getParticipantes());
	}
	
	@Test
	public void testGetIDPartida() {
	    Partida partida = new Partida();
	    int idEsperada = 123456;
	    partida.setIDPartida(idEsperada);

	    assertEquals(idEsperada, partida.getIDPartida());
	}
	
	@Test
	public void testGetBoteBingo() {
	    Partida partida = new Partida();
	    float boteEsperado = 100.50f;
	    partida.setBoteBingo(boteEsperado);

	    assertEquals(boteEsperado, partida.getBoteBingo(), 0.001);
	}
	
	@Test
	public void testGetGanadorBingo() {
		Partida partida = new Partida();
		Carton carton = new Carton(1, 1, 1);
		partida.setGanadorBingo(carton);
		
		assertEquals(carton, partida.getGanadorBingo());
	}

}
