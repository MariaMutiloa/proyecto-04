package zonaAdministrador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import personas.Administrador;
import zonaAdministrador.partidaNueva.ConfiguracionPart;

import java.awt.FlowLayout;
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
	private JPanel panel;
	
	public VentanaPrincipalAdmin(Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new GridLayout(2,1,5,5));
		
		
		JPanel superior = new JPanel();
		superior.setLayout( new FlowLayout());
		JLabel lblBienvenido = new JLabel("Bienvenidx " + admin.getNombre() + "!");
		superior.add(lblBienvenido);
		panel.add(superior);
		

		JPanel inferior = new JPanel();
		inferior.setLayout( new FlowLayout());
		
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
		
		JButton btnGestiónUsuarios = new JButton("Gesti\u00F3n Usuarios");
		inferior.add(btnGestiónUsuarios);
		
		panel.add(inferior);
	}

}
