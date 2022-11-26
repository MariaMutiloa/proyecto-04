package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class VentanaDatosUsuario extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaDatosUsuario frame = new VentanaDatosUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaDatosUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(31, 11, 49, 14);
		contentPane.add(labelNombre);
		
		JTextPane textPaneNombre = new JTextPane();
		textPaneNombre.setBounds(10, 36, 70, 20);
		contentPane.add(textPaneNombre);
		
		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(145, 11, 49, 14);
		contentPane.add(labelApellido);
		
		JTextPane textPaneApellido = new JTextPane();
		textPaneApellido.setBounds(145, 36, 70, 20);
		contentPane.add(textPaneApellido);
		
		JTextPane textPanePartidasJugadas = new JTextPane();
		textPanePartidasJugadas.setBounds(10, 92, 70, 20);
		contentPane.add(textPanePartidasJugadas);
		
		JLabel labelPartidasJugadas = new JLabel("Partidas jugadas");
		labelPartidasJugadas.setBounds(10, 67, 111, 14);
		contentPane.add(labelPartidasJugadas);
		
		JLabel lblPartidasGanadas = new JLabel("Partidas ganadas");
		lblPartidasGanadas.setBounds(145, 67, 111, 14);
		contentPane.add(lblPartidasGanadas);
		
		JTextPane textPanePartidasGanadas = new JTextPane();
		textPanePartidasGanadas.setBounds(145, 92, 70, 20);
		contentPane.add(textPanePartidasGanadas);
		
		JLabel lblPartidasPerdidas = new JLabel("Partidas perdidas");
		lblPartidasPerdidas.setBounds(10, 123, 111, 14);
		contentPane.add(lblPartidasPerdidas);
		
		JTextPane textPanePartidasPerdidas = new JTextPane();
		textPanePartidasPerdidas.setBounds(10, 148, 70, 20);
		contentPane.add(textPanePartidasPerdidas);
		
		JLabel labelLiga = new JLabel("Liga");
		labelLiga.setBounds(145, 123, 111, 14);
		contentPane.add(labelLiga);
		
		JTextPane textPaneLiga = new JTextPane();
		textPaneLiga.setBounds(145, 148, 70, 20);
		contentPane.add(textPaneLiga);
		
		JLabel labelPuesto = new JLabel("Puesto");
		labelPuesto.setBounds(10, 179, 111, 14);
		contentPane.add(labelPuesto);
		
		JTextPane textPanePartidasPerdidas_1 = new JTextPane();
		textPanePartidasPerdidas_1.setBounds(10, 204, 70, 20);
		contentPane.add(textPanePartidasPerdidas_1);
		
		JLabel labelPuntos = new JLabel("Puntos/Bote");
		labelPuntos.setBounds(145, 179, 111, 14);
		contentPane.add(labelPuntos);
		
		JTextPane textPanePartidasPerdidas_1_1 = new JTextPane();
		textPanePartidasPerdidas_1_1.setBounds(145, 204, 70, 20);
		contentPane.add(textPanePartidasPerdidas_1_1);
		
		JButton botonVolverVerUsuarios = new JButton("Volver a Ver Usuarios");
		botonVolverVerUsuarios.setBounds(287, 92, 139, 20);
		contentPane.add(botonVolverVerUsuarios);
		
		JButton botonGestionarUsuarios = new JButton("Volver a Gesti\u00F3n de usuarios");
		botonGestionarUsuarios.setBounds(255, 148, 181, 20);
		contentPane.add(botonGestionarUsuarios);
	}
}
