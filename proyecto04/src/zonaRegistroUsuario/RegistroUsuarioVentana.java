package zonaRegistroUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;


import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistroUsuarioVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JButton btnCrear;
	private JLabel lblInfo;

	
	public RegistroUsuarioVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 551, 203 );
		setTitle( "Juego BINGO!" );

		/*
		 * PANEL SUPERIOR
		 * - mensaje
		 */
		JPanel pNorte = new JPanel(); // Panel inferior
		getContentPane().add( pNorte, BorderLayout.NORTH );
		
		lblInfo = new JLabel("Est\u00E1s creando un usuario nuevo");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(113, 32, 532, 40);
		pNorte.add(lblInfo);
		
		
		/*
		 * PANEL CENTRAL
		 * - insertar datos
		 */
		JPanel pCentral = new JPanel();
		pCentral.setLayout(new GridLayout(3,4));
		getContentPane().add( pCentral, BorderLayout.CENTER );
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setBounds(67, 182, 95, 29);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		pCentral.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(160, 182, 185, 22);
		pCentral.add(txtNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(Color.BLACK);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblApellido.setBounds(67, 243, 95, 29);
		pCentral.add(lblApellido);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtApellido.setColumns(10);
		txtApellido.setBounds(160, 243, 185, 22);
		pCentral.add(txtApellido);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDni.setBounds(67, 118, 95, 29);
		pCentral.add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(160, 122, 185, 22);
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtDni.setColumns(10);
		pCentral.add(txtDni);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(368, 118, 118, 29);
		pCentral.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(486, 118, 185, 22);
		pCentral.add(txtUsuario);
		//HAY QUE COMPROBAR QUE NO HAYA UN USUARIO CON EL MISMO NOMBRE --> si hay podemos usar renderer de si esta repetido se ponga en rojo
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContrasena.setBounds(368, 182, 118, 29);
		pCentral.add(lblContrasena);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(486, 182, 185, 22);
		pCentral.add(passwordField);
		//ALGUNA CONDICION? minimo 8 caracteres, letras y numeros...?
		

		
		
		/*
		 * PANEL INFERIOR
		 * - boton crear usuario
		 */
		JPanel pSur = new JPanel(); // Panel inferior
		getContentPane().add( pSur, BorderLayout.SOUTH );
		
		btnCrear = new JButton("Crear usuario");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//TIENE QUE VERIFICAR QUE NO HAYA USUARIO REPETIDO
				
				//METER USUARIO EN LA BASE DE DATOS
				
			}
		});
		btnCrear.setBounds(288, 293, 169, 29);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pSur.add(btnCrear);
		

		
		
		
	}
}
