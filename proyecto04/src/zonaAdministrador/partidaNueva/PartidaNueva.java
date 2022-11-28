package zonaAdministrador.partidaNueva;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;


public class PartidaNueva extends JFrame {

	private JPanel contentPane;
	private Partida partidaActual;
	 
	private static final long serialVersionUID = 1L;


	
	public PartidaNueva(Partida partidaActual) {
		this.partidaActual = partidaActual;
		GestionPartidas.empezada(partidaActual.getIDPartida());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel superior = new JPanel();
		JLabel titulo = new JLabel("Partida "+partidaActual.getIDPartida());
		contentPane.add(superior, BorderLayout.NORTH);
		
		JPanel inferior = new JPanel();
		
			//Panel izquierda con tabla de participantes
			JTable tablaCartones = new JTable();
			inferior.add(tablaCartones, BorderLayout.WEST);
			GestionPartidas.participantes(partidaActual.getIDPartida());
		
		
			//Con añadir numero
			JPanel derecha = new JPanel();
			JButton btnNuevoNum = new JButton("Nuevo número");
			derecha.add(btnNuevoNum, BorderLayout.SOUTH);
				JPanel numero = new JPanel();
				JLabel decenas = new JLabel();
				numero.add(decenas, BorderLayout.WEST);
				JLabel unidades = new JLabel();
				numero.add(unidades, BorderLayout.EAST);
				derecha.add(numero, BorderLayout.NORTH);
			derecha.setVisible(false);
			inferior.add(derecha, BorderLayout.EAST);	
		
		contentPane.add(inferior, BorderLayout.SOUTH);
		
	}

}
