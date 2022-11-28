package zonaAdministrador.partidaNueva;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;


public class PartidaNueva extends JFrame {

	private JPanel contentPane;
	private Partida partidaActual;
	 
	private static final long serialVersionUID = 1L;


	
	public PartidaNueva(Partida partidaActual) {
		this.partidaActual = partidaActual;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Panel izquierda con tabla de participantes
		JTable tablaCartones = new JTable(); 
		GestionPartidas.participantes(partidaActual.get());
		
		//Con añadir numero
		
	
		
		//
	}

}
