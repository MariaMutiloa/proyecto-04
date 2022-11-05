package zonaAdministrador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionBD.GestionPartidas;
import personas.Administrador;

public class VentanaPartida extends JFrame {

	private JPanel contentPane;


	public VentanaPartida(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setContentPane(contentPane);
		
		int num = 1; //Va a tener que ser automatico, en teoria ocn SQLite
		float boteL = calculoBoteL(); //Dependera de la gente conectada
		float boteB = calculoBoteB();
		int liga = table.getSelected(); //Usara mismo list que administracion de ligas
		
		int numeroPartida = GestionPartidas.nueva(num, Bote1, bote2, liga);
		
		
	}


	private float calculoBoteB() {
		// TODO Auto-generated method stub
		return 0;
	}


	private float calculoBoteL() {
		// TODO Auto-generated method stub
		return 0;
	}

}
