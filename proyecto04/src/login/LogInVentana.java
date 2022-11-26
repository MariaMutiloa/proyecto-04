package login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gestionBD.ConexionBD;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;
import zonaRegistroUsuario.RegistroUsuarioVentana;
import zonaUsuario.UsuarioVentana;

import java.awt.Color;

public class LogInVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private static Logger logger = Logger.getLogger(LogInVentana.class.getName()); 
	private JTextField txtUsuario;
	private JPasswordField jpassClave; 
	private JButton btnAceptar;
	private JButton btnCrearUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInVentana frame = new LogInVentana();
					frame.setVisible(true);
					ConexionBD.realizarConexion();
					
				} catch (Exception e) {
					//e.printStackTrace();
				   JOptionPane.showMessageDialog(null,  e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				   logger.log(Level.SEVERE, "No se ha podido inicializar la applicacion");
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setBounds(68, 129, 95, 29);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(68, 180, 113, 29);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(lblContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(215, 136, 139, 22);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(215, 187, 144, 22);
		jpassClave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(jpassClave);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//Extraemos la contraseña
				char[] clave = jpassClave.getPassword();
				String claveFinal = new String(clave);
		
				
				
				if(txtUsuario.getText().length()==0 || claveFinal.length()==0) { 	//Comprobamos que los campos no estan vacios
					JOptionPane.showMessageDialog(null, "Introduce los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
					logger.log(Level.WARNING, "NO se han rellenado los campos");
				}else {
					
					//Comprueba si existe usuario o administrador -->JOptionPane Bienvenido "nombre" 
					ConexionBD.getUsuario(txtUsuario.getText(), claveFinal);
					
					if(ConexionBD.getUsuario(txtUsuario.getText(), claveFinal)!=null) { //hay coincidencia usuario
						logger.info("Se ha encontrado el usuario");						
						//ABRO UsuarioVentana
						UsuarioVentana ventanaNueva = new UsuarioVentana();
						ventanaNueva.setVisible(true);
						LogInVentana.this.dispose();
						
						
					}else if (ConexionBD.getAdministrador(txtUsuario.getText(), claveFinal)!=null) {
						logger.info("Se ha encontrado el administrador");		
						Administrador a = ConexionBD.getAdministrador(txtUsuario.getText(), claveFinal);
						
						//ABRO VentanaPrincipalAdmin
						VentanaPrincipalAdmin ventanaNueva = new VentanaPrincipalAdmin(a);
						ventanaNueva.setVisible(true);
						LogInVentana.this.dispose();
					}else {
						logger.log(Level.WARNING, "El usuario/admin no existe o los datos introducidos no son correctos");
						JOptionPane.showMessageDialog(null, "El usuario o la contraseña son incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
						

					}
				}
	
			
		});
		btnAceptar.setBounds(155, 242, 132, 29);
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnAceptar);

		
		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				RegistroUsuarioVentana ventanaNueva = new RegistroUsuarioVentana();
				ventanaNueva.setVisible(true);
				LogInVentana.this.dispose();
				
			}
		});
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCrearUsuario.setBounds(305, 334, 157, 29);
		contentPane.add(btnCrearUsuario);
		
		JLabel lblBingo = new JLabel("");
		lblBingo.setIcon(new ImageIcon(getClass().getResource("/bingo.png")));
		lblBingo.setBounds(10, 11, 463, 107);
		contentPane.add(lblBingo);
		
		
	}
}
