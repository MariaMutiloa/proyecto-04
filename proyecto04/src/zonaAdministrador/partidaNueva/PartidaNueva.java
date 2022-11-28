package zonaAdministrador.partidaNueva;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;


public class PartidaNueva extends JFrame {

	private JPanel contentPane;
	private Partida partidaActual;
	private static JLabel unidades = new JLabel();
	private static JLabel decenas = new JLabel();
	private static List<Integer> numeros = new ArrayList();
	private static Logger logger = Logger.getLogger(PartidaNueva.class.getName());
	 
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
				numero.add(decenas, BorderLayout.WEST);
				numero.add(unidades, BorderLayout.EAST);
				derecha.add(numero, BorderLayout.NORTH);
			derecha.setVisible(false);
			inferior.add(derecha, BorderLayout.EAST);	
			
			btnNuevoNum.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
		
					int ganadorB = GestionPartidas.revisar();
				
					if (ganadorB != 0) {
						int result = JOptionPane.showConfirmDialog(null, "Han cantado bingo");
					    switch (result) {
					         case JOptionPane.YES_OPTION:
					         //Nuevo hilo que combruebe;
					         break;
					         case JOptionPane.NO_OPTION:
					      }
					}  else {
						PartidaNueva.this.actualizar();	
					}
					
				}
				
			});
		
		contentPane.add(inferior, BorderLayout.SOUTH);
		
	}
	
	private void actualizar() {
		logger.info("Extrayendo nuevo número");
		Random rand = new Random(); //instance of random class
	    int nuevoNumero = rand.nextInt(99); 
	    numeros.add(nuevoNumero);
	    String number = String.valueOf(nuevoNumero);
	   	char[] digits = number.toCharArray();
	   	logger.info("Numero nuevo conseguido");
	    if(digits.length == 1) {
	    	decenas.setIcon(this.imagenNumero(0));
	    	unidades.setIcon(this.imagenNumero(digits[0]));
	    }else {
	    	decenas.setIcon(this.imagenNumero(digits[0]));
	    	unidades.setIcon(this.imagenNumero(digits[1]));
	    }
	}

	private Icon imagenNumero(int i) {
		Icon icono = null;
		logger.info("Buscando imagen correspondiente a " +i);
		if(i > 9) {				
			icono = new ImageIcon(getClass().getResource("/otro.jpg"));
		}else if (i == 0){
			icono = new ImageIcon(getClass().getResource("/0.jpeg"));
		}else {
			icono = new ImageIcon(getClass().getResource("/"+String.valueOf(i) + ".jpg") );
		}
		return icono;
	}

}
