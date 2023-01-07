package zonaAdministrador.partidaNueva;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;

public class PartidaNueva extends JFrame {

	private Partida partidaActual;
	private static JLabel unidades = new JLabel();
	private static JLabel decenas = new JLabel();
	private static List<Integer> numeros = new ArrayList<>();
	private static Logger logger = Logger.getLogger(PartidaNueva.class.getName());

	private static final long serialVersionUID = 1L;

	public PartidaNueva(Partida partidaActual, Administrador admin, float botePartida) {
		logger.info("Abriendo venta nueva partida");
		this.partidaActual = partidaActual;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
			@Override
			public void windowClosing(WindowEvent e) {

				GestionPartidas.setGanadorBingo(1, partidaActual, 0);

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

		});

		setBounds(100, 100, 500, 400);
		
		
		//Partida X + btnVolver
		JPanel pNorte = new JPanel();
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		JLabel titulo = new JLabel("Partida " + partidaActual.getIDPartida());
		pNorte.add(titulo, BorderLayout.WEST);
		
		JButton btnVolver = new JButton("Volver");
		pNorte.add(btnVolver, BorderLayout.EAST);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalAdmin nueva = new VentanaPrincipalAdmin(admin);
				PartidaNueva.this.dispose();
			}
		});
		
		
		//btnNuevoNumero + imagenes numeros
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		JPanel pCentralDerecha = new JPanel();
		pCentral.add(pCentralDerecha, BorderLayout.EAST);
		
		JButton btnNuevoNum = new JButton("Nuevo número");
		pCentralDerecha.add(btnNuevoNum, BorderLayout.EAST);
		btnNuevoNum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int ganadorB = GestionPartidas.revisar(partidaActual.getIDPartida());

				if (ganadorB != 0) {
					int result = JOptionPane.showConfirmDialog(null, "¿Quiere comprobar el bingo?", "Han cantado bingo", JOptionPane.YES_NO_OPTION);
					switch (result) {
					case JOptionPane.YES_OPTION:
						Thread comprobar = new Thread((Runnable) new Comprobar(ganadorB, numeros, PartidaNueva.this, admin, botePartida));
						comprobar.start();
						break;
					case JOptionPane.NO_OPTION:
					}
				} else {
					PartidaNueva.this.actualizar();
				}

			}

		});
		
		JPanel pCentralNumero = new JPanel();
		pCentral.add(pCentralNumero, BorderLayout.CENTER);
		pCentralNumero.add(decenas, BorderLayout.WEST);
		pCentralNumero.add(unidades, BorderLayout.EAST);
		
		
		
		//btnFinalizarPartida
		JPanel pSur = new JPanel();
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		JButton btnFinalizar = new JButton("Finalizar Partida");
		pSur.add(btnFinalizar);
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,
						"Aún no se ha cantado bingo, ¿Seguro que quieres terminar la partida?");
				switch (result) {
				case JOptionPane.YES_OPTION:
					GestionPartidas.setGanadorBingo(1, partidaActual, 0);
					VentanaPrincipalAdmin nueva = new VentanaPrincipalAdmin(admin);
					PartidaNueva.this.dispose();
					break;
				case JOptionPane.NO_OPTION:
					break;
				}
			}
		});
		
		

	}

	// Extrae nuevo numero random, lo muestra por pantalla y lo añade a la base de
	// datos
	private void actualizar() {
		logger.info("Extrayendo nuevo número");
		int nuevoNumero = numeroRandom();
		numeros.add(nuevoNumero);
		GestionPartidas.añadirNumero(nuevoNumero, numeros.indexOf(nuevoNumero), partidaActual.getIDPartida());
		String number = String.valueOf(nuevoNumero);
		String[] digits = number.split("(?<=.)");
		logger.info("Numero nuevo conseguido");
		if (digits.length == 1) {
			decenas.setIcon(this.imagenNumero(Integer.toString(0)));
			unidades.setIcon(this.imagenNumero(digits[0]));
		} else {
			decenas.setIcon(this.imagenNumero(digits[0]));
			unidades.setIcon(this.imagenNumero(digits[1]));
		}
	}

	// Crea un numero random y comprueba que no haya sido ya usado (recursividad)
	private int numeroRandom() {
		logger.info("creando un número random");
		Random rand = new Random();
		int numero = rand.nextInt(99);
		if (numeros.contains(numero)) {
			numero = numeroRandom();
		}
		return numero;
	}

	// Extrae las imagenes correspondientes al numero
	private Icon imagenNumero(String i) {
		Icon icono = null;
		logger.info("Buscando imagen correspondiente a " + i);
		if (Integer.parseInt(i) > 9) {
			icono = new ImageIcon(getClass().getResource("/otro.jpg"));
		} else {
			icono = new ImageIcon(getClass().getResource("/" + String.valueOf(i) + ".jpg"));
		}
		return icono;
	}

	public Partida getPartidaActual() {
		return partidaActual;
	}
}
