package zonaAdministrador.partidaNueva;

import java.util.List;

import elementosOrganizacion.Carton;
import gestionBD.GestionPartidas;

public class Comprobar implements Runnable {

	private Carton cartonGanador;
	private List<Integer> numeros;
	
	public Comprobar(int ganadorB, List<Integer> lista) {
		super();
		this.cartonGanador = GestionPartidas.getCarton(ganadorB);
		this.numeros = lista;
	}

	@Override
	public void run() {
		boolean bingo = false;
		if(numeros.containsAll(cartonGanador.))

	}

}
