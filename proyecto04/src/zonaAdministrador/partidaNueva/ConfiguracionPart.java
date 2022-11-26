package zonaAdministrador.partidaNueva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;

import java.awt.BorderLayout;
import java.awt.Component;

public class ConfiguracionPart extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Partida partidaActual = new Partida();


	public ConfiguracionPart(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel superior = new JPanel();
			JLabel lblTitulo = new JLabel ("Configuración de partida ");
			superior.add(lblTitulo, BorderLayout.WEST);
	
		JPanel central = new JPanel();
			JPanel centralArriba = new JPanel();	
				centralArriba.add(new JLabel("Bote Linea"), BorderLayout.WEST);
				JTextField txtLinea = new JTextField();
				centralArriba.add(txtLinea, BorderLayout.EAST);
			JPanel centralAbajo = new JPanel();	
				centralAbajo.add(new JLabel("Bote Bingo"), BorderLayout.WEST);
				JTextField txtBingo = new JTextField();
				centralAbajo.add(txtBingo, BorderLayout.EAST);
			central.add(centralArriba, BorderLayout.NORTH);	
			central.add(centralAbajo, BorderLayout.SOUTH);	

			JPanel inferior = new JPanel(); 
				JPanel inferiorDerecha = new JPanel();
					JButton btnEmpezar = new JButton("Empezar Partida");
					inferiorDerecha.add(btnEmpezar, BorderLayout.WEST);
				JPanel inferiorIzquierda = new JPanel();
					inferiorIzquierda.add(new JLabel ("Participantes conectados"), BorderLayout.NORTH);
					inferiorIzquierda.add(imagenNumero((partidaActual.getParticipantes()).size()));
					JButton  btbVerParticipantes = new JButton("Ver Participantes");
					inferiorIzquierda.add(btbVerParticipantes, BorderLayout.SOUTH);
				inferior.add(inferiorDerecha, BorderLayout.WEST);	
				inferior.add(inferiorIzquierda, BorderLayout.EAST);	
				
			contentPane.add(superior,BorderLayout.NORTH);	
			contentPane.add(central,BorderLayout.CENTER);	
			contentPane.add(inferior,BorderLayout.SOUTH);	
	}


	private Component imagenNumero(int numero) {
		// TODO Auto-generated method stub
		return null;
	}

}
