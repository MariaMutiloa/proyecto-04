package zonaRegistroUsuario;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

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
		setBounds(100, 100, 745, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setBounds(67, 182, 95, 29);
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setForeground(Color.BLACK);
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblApellido.setBounds(67, 243, 95, 29);
		contentPane.add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDni.setBounds(67, 118, 95, 29);
		contentPane.add(lblDni);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(368, 118, 118, 29);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContrasena.setBounds(368, 182, 118, 29);
		contentPane.add(lblContrasena);
		
		txtDni = new JTextField();
		txtDni.setBounds(160, 122, 185, 22);
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(txtDni);
		txtDni.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(160, 182, 185, 22);
		contentPane.add(txtNombre);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtApellido.setColumns(10);
		txtApellido.setBounds(160, 243, 185, 22);
		contentPane.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(486, 118, 185, 22);
		contentPane.add(txtUsuario);
		
		//HAY QUE COMPROBAR QUE NO HAYA UN USUARIO CON EL MISMO NOMBRE
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(486, 182, 185, 22);
		contentPane.add(passwordField);
		
		//ALGUNA CONDICION? minimo 8 caracteres, letras y numeros...?
		
		btnCrear = new JButton("Crear usuario");
		btnCrear.setBounds(288, 293, 169, 29);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnCrear);
		
		lblInfo = new JLabel("Est\u00E1s creando un usuario nuevo");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(113, 32, 532, 40);
		contentPane.add(lblInfo);
		
		
		
	}
}
