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
import javax.swing.JTable;

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
		
		JLabel lblCartera = new JLabel("Cartera: "+u.getBote()+" €");
		superior.add(lblCartera);
		
		JButton btnSalir = new JButton("Salir");
		superior.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInVentana parent = new LogInVentana();
				parent.setVisible(true);
				UsuarioVentana.this.dispose();	
			}
		});
		
		JPanel inferior = new JPanel();
		inferior.setLayout(new GridLayout(1,2));
		getContentPane().add(inferior, BorderLayout.SOUTH);
		
		JPanel infIzquierda = new JPanel();
		inferior.add(infIzquierda, BorderLayout.WEST);
		
		JButton btnEstadisticas = new JButton("Ver estadisticas");
		infIzquierda.add(btnEstadisticas);
		
		//METER VER ESTADISTICAS
		
		JPanel infDerecha = new JPanel();
		inferior.add(infDerecha, BorderLayout.EAST);

		//METEMOS LOS BOTONES DEL JUEGO
		JButton btnLinea = new JButton("LINEA");
		infDerecha.add(btnLinea, BorderLayout.EAST);
		
		JButton btnBingo = new JButton("BINGO!");
		infDerecha.add(btnBingo, BorderLayout.WEST);
		
		
		//PANEL IZQUIERDA
		//aqui va a salir el número en grande y también podremos ver qué numeros han salido hasta ahora
		
		
		//PANEL CENTRAL DERECHA
		//aquí tendremos nuestro carton con los numeros
		//hay posibilidad de clickar en el numero y se cambie de color 
		
		
		
	}

}
