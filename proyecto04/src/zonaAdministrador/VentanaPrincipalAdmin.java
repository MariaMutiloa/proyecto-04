package zonaAdministrador;

import javax.swing.JFrame;
import javax.swing.JPanel;

import login.LogInVentana;
import personas.Administrador;
import zonaAdministrador.Estadisticas.VerEstadisticas;
import zonaAdministrador.GestionUsuarios.VentanaGestionDeUsuariosPrincipal;
import zonaAdministrador.gestionLigas.GestionLigas;
import zonaAdministrador.partidaNueva.ConfiguracionPart;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;

public class VentanaPrincipalAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VentanaPrincipalAdmin(Administrador admin) {
		setBounds(100, 100, 320, 215);
		setTitle( "Ventana de administrador" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel superior = new JPanel();
		superior.setSize(150, 50);
		JLabel lblBienvenido = new JLabel("¡Bienvenidx " + admin.getNombre() + "!");
		superior.add(lblBienvenido, BorderLayout.NORTH);
		getContentPane().add(superior, BorderLayout.NORTH);
		
		//PANEL DE BOTONES
		JPanel inferior = new JPanel();
		
		GridLayout layoutGrid = new GridLayout(5,0);
		inferior.setLayout(layoutGrid);
	
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConfiguracionPart nuevaPartida = new ConfiguracionPart(VentanaPrincipalAdmin.this, admin);
				nuevaPartida.setVisible(true);
				VentanaPrincipalAdmin.this.setVisible(false);
				
			}
			
		});
		
		inferior.add(btnNuevaPartida);
		
		JButton btnEstadisticas = new JButton("Estad\u00EDsticas");
		inferior.add(btnEstadisticas);
		btnEstadisticas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VerEstadisticas ventanaEstadisticas = new VerEstadisticas(VentanaPrincipalAdmin.this);
				ventanaEstadisticas.setVisible(true);
				VentanaPrincipalAdmin.this.setVisible(false);
				
			}
			
		});
		
		JButton btnGestionUsuarios = new JButton("Gesti\u00F3n Usuarios");
		inferior.add(btnGestionUsuarios);
		btnGestionUsuarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaGestionDeUsuariosPrincipal nuevaVentanaPrincipal = new VentanaGestionDeUsuariosPrincipal(VentanaPrincipalAdmin.this, admin);
				nuevaVentanaPrincipal.setVisible(true);
				VentanaPrincipalAdmin.this.setVisible(false);
				
			}
			
		});
		
		JButton btnGestionLigas = new JButton("Gestión Ligas");
		inferior.add(btnGestionLigas);
		btnGestionLigas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GestionLigas parent = new GestionLigas(VentanaPrincipalAdmin.this);
				parent.setVisible(true);
				VentanaPrincipalAdmin.this.dispose();
				
			}
			
		});
		
		
		JButton btnVolver = new JButton("Volver");
		inferior.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInVentana parent = new LogInVentana();
				parent.setVisible(true);
				VentanaPrincipalAdmin.this.dispose();
				
			}
			
		});
		
		getContentPane().add(inferior, BorderLayout.CENTER);
		
	}

}
