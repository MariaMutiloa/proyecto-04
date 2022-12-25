package zonaAdministrador.partidaNueva;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import elementosOrganizacion.Carton;
import gestionBD.GestionPartidas;
import personas.Usuario;

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
	         break;	  
			}
		}else {
			JOptionPane.showMessageDialog(null, "El bingo no es correcto", "Bingo no correcto", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	//Escribir en un fichero los datos de la partida
	private void resumen() {
		File file = null; 
		JFileChooser fileChooser = new JFileChooser();
        // solo se admiten ficheros con extensión ".txt"
        FileFilter filter = new FileNameExtensionFilter("Fichero TXT", "txt");
        fileChooser.setFileFilter(filter);
        int chooser = fileChooser.showSaveDialog(fileChooser);
		if (chooser == JFileChooser.APPROVE_OPTION) {
			fileChooser.getSelectedFile();
			file = fileChooser.getSelectedFile();
		}	
		try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))){
			bf.write("Resumen partida "+ partida.getPartidaActual().getIDPartida()+"\n");
			bf.write("Ha habido un total de "+partida.getPartidaActual().getParticipantes().size()+" cartones jugando");
			bf.write("Los cartones han sido: ");
			for(Carton u: partida.getPartidaActual().getParticipantes()) {
				bf.write("Cartón número: "+u.getIDCarton()+" de " + u.getPropietario().getNombre()+ " "+u.getPropietario().getApellido());;
			}
			bf.write("El ganador ha sido el cartón "+partida.getPartidaActual().getGanadorBingo().getIDCarton()+ " que pertenece a " +
			partida.getPartidaActual().getGanadorBingo().getPropietario().getNombre() + " "+ partida.getPartidaActual().getGanadorBingo().getPropietario().getApellido()
			+ " con un bote de "+ partida.getPartidaActual().getBoteBingo());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
