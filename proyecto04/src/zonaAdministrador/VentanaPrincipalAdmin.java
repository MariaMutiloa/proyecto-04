package zonaAdministrador;

import javax.swing.JFrame;
import javax.swing.JPanel;

import login.LogInVentana;
import personas.Administrador;
import zonaAdministrador.GestionUsuarios.VentanaPrincipalGestionUsuarios;
import zonaAdministrador.partidaNueva.ConfiguracionPart;

import java.awt.BorderLayout;
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
		setBounds(100, 100, 160, 200);
		setTitle( "Ventana de administrador" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel superior = new JPanel();
		superior.setSize(150, 50);
		JLabel lblBienvenido = new JLabel("¡Bienvenidx " + admin.getNombre() + "!");
		superior.add(lblBienvenido, BorderLayout.NORTH);
		getContentPane().add(superior, BorderLayout.NORTH);
		

		JPanel inferior = new JPanel();
	
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConfiguracionPart nuevaPartida = new ConfiguracionPart(VentanaPrincipalAdmin.this, admin);
				nuevaPartida.setVisible(true);
				VentanaPrincipalAdmin.this.setVisible(false);
				
			}
			
		});
		
		inferior.add(btnNuevaPartida, BorderLayout.NORTH);
		
		JButton btnEstadisticas = new JButton("Estad\u00EDsticas");
		inferior.add(btnEstadisticas, BorderLayout.CENTER);
		
		JButton btnGestiónUsuarios = new JButton("Gesti\u00F3n Usuarios");
		inferior.add(btnGestiónUsuarios, BorderLayout.SOUTH);
		btnGestiónUsuarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalGestionUsuarios nuevaVentanaPrincipal = new VentanaPrincipalGestionUsuarios();
				nuevaVentanaPrincipal.setVisible(true);
				VentanaPrincipalAdmin.this.setVisible(false);
				
			}
			
		});
		
		JButton btnVolver = new JButton("Volver");
		inferior.add(btnVolver, BorderLayout.SOUTH);
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
