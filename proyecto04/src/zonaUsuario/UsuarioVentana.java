package zonaUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.LogInVentana;
import personas.Usuario;
import zonaAdministrador.VentanaPrincipalAdmin;

public class UsuarioVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * Create the frame.
	 */
	public UsuarioVentana(Usuario u) {
		setBounds(100, 100, 647, 319);
		setTitle( "Ventana de usuario" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel superior = new JPanel();
		superior.setSize(150, 50);
		superior.setLayout(new GridLayout(1,4));
		getContentPane().add(superior, BorderLayout.NORTH);

		
		JLabel lblBienvenido = new JLabel("¡Bienvenidx " + u.getNombre() + "!");
		superior.add(lblBienvenido);
		//superior.add(lblBienvenido, BorderLayout.EAST);
		
		JLabel lblCartera = new JLabel("Cartera: "+u.getBote());
		superior.add(lblCartera);
		
		JButton btnSalir = new JButton("Salir");
		superior.add(btnSalir);
		//inferior.add(btnVolver, BorderLayout.SOUTH);
		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInVentana parent = new LogInVentana();
				parent.setVisible(true);
				UsuarioVentana.this.dispose();	
			}
		});
		
		JPanel inferior = new JPanel();
		getContentPane().add(inferior, BorderLayout.SOUTH);
		
		
		
		
	}

}
