package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import personas.Administrador;
import personas.Usuario;
import zonaAdministrador.VentanaPrincipalAdmin;
import zonaAdministrador.partidaNueva.ConfiguracionPart;
import zonaRegistroUsuario.RegistroUsuarioVentana;

import javax.swing.JButton;

public class VentanaPrincipalGestionUsuarios extends JFrame {

	private JPanel contentPane;

	public VentanaPrincipalGestionUsuarios() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ver Usuarios");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaVerUsuario nuevaVentanaVer = new VentanaVerUsuario();
				nuevaVentanaVer.setVisible(true);
				VentanaPrincipalGestionUsuarios.this.setVisible(false);

			}

		});
		btnNewButton.setBounds(31, 76, 136, 34);
		contentPane.add(btnNewButton);

		JButton botonCrearUsuario = new JButton("Crear usuario");
		botonCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistroUsuarioVentana nuevaUsuarioRegistro = new RegistroUsuarioVentana();
				nuevaUsuarioRegistro.setVisible(true);
				VentanaPrincipalGestionUsuarios.this.setVisible(false);

			}

		});
		botonCrearUsuario.setBounds(31, 157, 136, 34);
		contentPane.add(botonCrearUsuario);

		JButton botonEliminar = new JButton("Eliminar usuario");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaEliminarUsuario eliminarUsuario = new VentanaEliminarUsuario();
				eliminarUsuario.setVisible(true);
				VentanaPrincipalGestionUsuarios.this.setVisible(false);
			}
		});
		botonEliminar.setBounds(247, 76, 136, 34);
		contentPane.add(botonEliminar);

		JButton btnNewButton_1 = new JButton("Buscar usuario");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBuscarUsuario buscarUsuario = new VentanaBuscarUsuario();
				buscarUsuario.setVisible(true);
				VentanaPrincipalGestionUsuarios.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(247, 157, 136, 34);
		contentPane.add(btnNewButton_1);
	}

}
