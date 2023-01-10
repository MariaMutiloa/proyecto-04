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
import gestionBD.ConexionBD;
import gestionBD.GestionZonaUsuario;
import personas.Administrador;
import personas.Usuario;
import zonaAdministrador.VentanaPrincipalAdmin;
import zonaUsuario.RegistroUsuarioVentana;
import zonaUsuario.UsuarioVentana;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LogInVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LogInVentana.class.getName()); 
	private static String bd = "jdbc:sqlite:DatosBingo.db";
	
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
					ConexionBD.realizarConexion(bd);
					
				} catch (Exception e) {
				   JOptionPane.showMessageDialog(null,  e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				   logger.log(Level.SEVERE, "No se ha podido inicializar la applicacion");
				}
			}
		});
	}


	
	public LogInVentana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setSize( 566, 320 );
		setTitle( "Juego BINGO!" );
		
		/*
		 * PANEL NORTE
		 * - imagen bingo
		 */
		JPanel pNorte = new JPanel(); // Panel norte
		pNorte.setBackground(Color.WHITE);
		getContentPane().add( pNorte, BorderLayout.NORTH );
		
		//imagen bingo
		JLabel lblBingo = new JLabel("");
		lblBingo.setIcon(new ImageIcon(getClass().getResource("/bingo.png")));
		pNorte.add(lblBingo);
		
		/*
		 * PANEL PRINCIPAL
		 * panel central
		 * - usuario + introducir
		 * - contraseña + introducir
		 * panel inferior
		 * - boton entrar
		 */
		
		JPanel pPrincipal = new JPanel( new BorderLayout() );
		pPrincipal.setLayout(new GridLayout(3,2));
		pPrincipal.setBackground(Color.WHITE);
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		
		//en el panel principal creamos un panel central para centrar todos los componentes
		
		
		//usuario
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		pPrincipal.add(lblUsuario);
		
		//txtUsuario
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsuario.setColumns(10);
		pPrincipal.add(txtUsuario);
		
		//Contraseña
		JLabel lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 18));
		pPrincipal.add(lblContrasena);
	
		//JpassClave
		jpassClave = new JPasswordField();
		jpassClave.setBounds(215, 187, 144, 22);
		jpassClave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pPrincipal.add(jpassClave);
		
		
		//en el panel principal creamos un panel inferior que va a ir el boton aceptar

		JPanel pInferiorPrincipal = new JPanel();
		pInferiorPrincipal.setBackground(Color.WHITE);
		pPrincipal.add(pInferiorPrincipal);
		
		//boton aceptar
		btnAceptar = new JButton("Aceptar");
		pPrincipal.add(btnAceptar, BorderLayout.SOUTH);
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
					GestionZonaUsuario.getUsuario(txtUsuario.getText(), claveFinal, bd);
					
					if(GestionZonaUsuario.getUsuario(txtUsuario.getText(), claveFinal, bd)!=null) { //hay coincidencia usuario
						logger.info("Se ha encontrado el usuario");	
						Usuario u = GestionZonaUsuario.getUsuario(txtUsuario.getText(), claveFinal, bd);
						
						//ABRO UsuarioVentana
						UsuarioVentana ventanaNueva = new UsuarioVentana(u);
						ventanaNueva.setVisible(true);
						LogInVentana.this.dispose();
						
					}else if (GestionZonaUsuario.getAdministrador(txtUsuario.getText(), claveFinal, bd)!=null) {
						logger.info("Se ha encontrado el administrador");		
						Administrador a = GestionZonaUsuario.getAdministrador(txtUsuario.getText(), claveFinal, bd);
						
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
				btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		
		/*
		 * PANEL INFERIOR
		 * - boton crear usuario
		 */
		
		JPanel pInferior = new JPanel();
		pInferior.setBackground(Color.WHITE);
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		
		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				RegistroUsuarioVentana ventanaNueva = new RegistroUsuarioVentana(LogInVentana.this);
				ventanaNueva.setVisible(true);
				LogInVentana.this.dispose();				
			}
		});
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pInferior.add(btnCrearUsuario);
		

	}
}
