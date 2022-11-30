package zonaAdministrador.partidaNueva;

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
	}

	@Override
	public void run() {
		if(numeros.containsAll(cartonGanador.getListaNumeros())){
			GestionPartidas.setGanadorBingo(cartonGanador.getIDCarton(), partida.getPartidaActual());
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
		// TODO Auto-generated method stub
		
	}

}
