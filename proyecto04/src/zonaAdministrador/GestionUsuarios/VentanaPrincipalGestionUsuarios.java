package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import zonaAdministrador.VentanaPrincipalAdmin;
import zonaAdministrador.partidaNueva.ConfiguracionPart;

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
	}
}
