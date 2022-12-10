package zonaAdministrador.partidaNueva;

import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import elementosOrganizacion.Carton;
import gestionBD.GestionPartidas;

public class Comprobar implements Runnable {

	private Carton cartonGanador;
	private List<Integer> numeros;
	private PartidaNueva partida;
	

	public Comprobar(int ganadorB, List<Integer> lista, PartidaNueva partidaNueva) {
		super();
		this.cartonGanador = GestionPartidas.getCarton(ganadorB);
		this.numeros = lista;
		this.partida = partidaNueva;
	}

	@Override
	public void run() {
		if(numeros.containsAll(cartonGanador.getListaNumeros())){
			GestionPartidas.setGanadorBingo(cartonGanador.getIDCarton(), partida.getPartidaActual());
			float bote = cartonGanador.getPropietario().getBote();
			(cartonGanador.getPropietario()).setBote(bote+partida.getPartidaActual().getBoteBingo());
			int result = JOptionPane.showConfirmDialog(null, "El bingo es correcto. Partida terminada. ¿Quiere guardar un resumen?");
			switch (result) {
	         case JOptionPane.YES_OPTION:
	        	 resumen();
	         break;
	         case JOptionPane.NO_OPTION:
	        	 
	      }
		}

	}
	
	//Escribir en un fichero los datos de la partida
	private void resumen() {
		File archivoResumen = new File(null);
		
		
	}

}
