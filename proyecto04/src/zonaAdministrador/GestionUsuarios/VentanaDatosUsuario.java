package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import personas.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDatosUsuario extends JFrame {

	private JPanel contentPane;
	private Usuario usuario;

	/**
	 * Create the frame.
	 * 
	 * @param u
	 * @param ventanaVerUsuario
	 */
	public VentanaDatosUsuario(VentanaVerUsuario ventanaVerUsuario, Usuario u) {
		this.usuario = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(31, 11, 49, 14);
		contentPane.add(labelNombre);

		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(145, 11, 49, 14);
		contentPane.add(labelApellido);

		JLabel labelPartidasJugadas = new JLabel("Partidas jugadas");
		labelPartidasJugadas.setBounds(10, 67, 111, 14);
		contentPane.add(labelPartidasJugadas);

		JLabel lblPartidasGanadas = new JLabel("Partidas ganadas");
		lblPartidasGanadas.setBounds(145, 67, 111, 14);
		contentPane.add(lblPartidasGanadas);

		JLabel lblPartidasPerdidas = new JLabel("Partidas perdidas");
		lblPartidasPerdidas.setBounds(10, 123, 111, 14);
		contentPane.add(lblPartidasPerdidas);

		JLabel labelLiga = new JLabel("Liga");
		labelLiga.setBounds(145, 123, 111, 14);
		contentPane.add(labelLiga);

		JLabel labelPuesto = new JLabel("Puesto");
		labelPuesto.setBounds(10, 179, 111, 14);
		contentPane.add(labelPuesto);

		JLabel labelPuntos = new JLabel("Puntos/Bote");
		labelPuntos.setBounds(145, 179, 111, 14);
		contentPane.add(labelPuntos);

		JButton botonVolverVerUsuarios = new JButton("Volver a Ver Usuarios");
		botonVolverVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerUsuario nuevaVentanaVerUsuarios = new VentanaVerUsuario();
				nuevaVentanaVerUsuarios.setVisible(true);
				VentanaDatosUsuario.this.setVisible(false);
			}
		});
		botonVolverVerUsuarios.setBounds(287, 92, 139, 20);
		contentPane.add(botonVolverVerUsuarios);

		JButton botonGestionarUsuarios = new JButton("Volver a Gesti\u00F3n de usuarios");
		botonGestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalGestionUsuarios nuevaVentanaGestion = new VentanaPrincipalGestionUsuarios();
				nuevaVentanaGestion.setVisible(true);
				VentanaDatosUsuario.this.setVisible(false);
			}
		});
		botonGestionarUsuarios.setBounds(255, 148, 181, 20);
		contentPane.add(botonGestionarUsuarios);

		JLabel lblNombrePuesto = new JLabel(usuario.getNombre());
		lblNombrePuesto.setBackground(Color.WHITE);
		lblNombrePuesto.setBounds(31, 36, 104, 14);
		contentPane.add(lblNombrePuesto);

		JLabel lblApellidoPuesto = new JLabel(usuario.getApellido());
		lblApellidoPuesto.setBackground(Color.WHITE);
		lblApellidoPuesto.setBounds(145, 36, 121, 14);
		contentPane.add(lblApellidoPuesto);

		JLabel lblPartidasJugadas = new JLabel("New label");
		lblPartidasJugadas.setBackground(Color.WHITE);
		lblPartidasJugadas.setBounds(31, 92, 49, 14);
		contentPane.add(lblPartidasJugadas);

		JLabel lblPartidasG = new JLabel("New label");
		lblPartidasG.setBackground(Color.WHITE);
		lblPartidasG.setBounds(145, 92, 49, 14);
		contentPane.add(lblPartidasG);

		JLabel lblPartidasP = new JLabel("New label");
		lblPartidasP.setBackground(Color.WHITE);
		lblPartidasP.setBounds(31, 148, 49, 14);
		contentPane.add(lblPartidasP);

		String idLiga = String.valueOf(usuario.getIdLigaActual());
		JLabel lblLiga = new JLabel(idLiga);
		lblLiga.setBackground(Color.WHITE);
		lblLiga.setBounds(145, 148, 49, 14);
		contentPane.add(lblLiga);
		;

		JLabel lblPuesto = new JLabel("New label");
		lblPuesto.setBackground(Color.WHITE);
		lblPuesto.setBounds(31, 204, 49, 14);
		contentPane.add(lblPuesto);

		String bote = String.valueOf(usuario.getBote());
		JLabel lblPuntos = new JLabel(bote);
		lblPuntos.setBackground(Color.WHITE);
		lblPuntos.setBounds(145, 204, 49, 14);
		contentPane.add(lblPuntos);

	}

}
