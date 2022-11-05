package zonaAdministrador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import personas.Administrador;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaPrincipalAdmin extends JFrame {

	private JPanel panel;
	
	public VentanaPrincipalAdmin(Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 368);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblBienvenido = new JLabel("Bienvenidx " + admin.getNombre() + "!");
		panel.add(lblBienvenido);
		

		setContentPane(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		
		panel.add(btnNuevaPartida);
		
		JButton btnEstadisticas = new JButton("Estad\u00EDsticas");
		panel.add(btnEstadisticas);
		
		JButton btnGesti�nUsuarios = new JButton("Gesti\u00F3n Usuarios");
		panel.add(btnGesti�nUsuarios);
	}

}
