package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import java.awt.Color;
import javax.swing.SwingConstants;

public class LogInVentana extends JFrame {

	private JPanel contentPane;
	
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
					
					//ConexionBD.getUsuario();
					//ConexionBD.getAdministrador();
					
				} catch (Exception e) {
					e.printStackTrace();
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
				
				//contraseña
				char[] clave = jpassClave.getPassword();
				String claveFinal = new String(clave);
				
				if(txtUsuario.getText().length()==0 || claveFinal.length()==0) {
					//campos vacios
					JOptionPane.showMessageDialog(null, "Introduce los datos", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					//sino comprueba si existe usuario o administrador -->JOptionPane Bienvenido "nombre" 
					ConexionBD.getUsuario(txtUsuario.getText(), claveFinal);
					if(ConexionBD.getUsuario(txtUsuario.getText(), claveFinal)==null) {	//si no hay ninguna coincidencia
						ConexionBD.getAdministrador(txtUsuario.getText(), claveFinal);
						System.out.println("HE ACCEDIDO como administrador "+ txtUsuario.getText());
					}else {
						System.out.println("HE ACCEDIDO como usuario" + txtUsuario.getText());
					}
				}
				
				
				
			}
		});
		btnAceptar.setBounds(155, 242, 132, 29);
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnAceptar);
		

		
		/*
		 * //BOTON ACEPTAR
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(155, 242, 132, 29);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//en las contraseñas hemos puesto passwordfield
				char[] clave = jpassClave.getPassword();
				String claveFinal = new String(clave);
				
				//boolean existe=false;
				boolean existe=miPlantilla.probarPass(plantilla, LogeoVentana.this);
				int posicion=-1;
				if (existe) {
					posicion=miPlantilla.cogerPosicion(plantilla);
				}
				
				if(existe) {
					//dispose(); //ventana actual se va a cerrar
					LogeoVentana.this.setVisible(false);
					
					String elUsuario=getTxtUsuario();
					String miUsuario="";
					for (int i = 0; i < getPlantilla().size(); i++) {
						if (elUsuario.equals(plantilla.get(i).getUsuario())) {
							miUsuario=plantilla.get(i).getNombre()+" "+plantilla.get(i).getApellidos();
						}
					}
					
					JOptionPane.showMessageDialog(null, "Bienvenido al Sistema "+miUsuario,"INGRESASTE",JOptionPane.PLAIN_MESSAGE);
					
					//Ahora depende del puesto de trabajo vamos a abrir una ventana u otra
					if(plantilla.get(posicion).getPuesto().equals("DJ")) {						
						//ABRIR VENTANA DJ
						DjVentana djVentana = new DjVentana(LogeoVentana.this);
						djVentana.setVisible(true);
						
					}else if (plantilla.get(posicion).getPuesto().equals("Camarero")) { 		
						//ABRIR VENTANA CAMARERO
						CamareroVentana camareroVentana = new CamareroVentana(LogeoVentana.this);
						camareroVentana.setVisible(true);
						
					}else if (plantilla.get(posicion).getPuesto().equals("Taquillero")) {
						//ABRIR VENTANA TAQUILLERO
						TaquillaVentana taquillaVentana = new TaquillaVentana(LogeoVentana.this);
						taquillaVentana.setVisible(true);
						
					}else if (plantilla.get(posicion).getPuesto().equals("Guardarropas")) {		
						//ABRIR VENTANA GUARDARROPAS
						GuardarropasVentana guardarropasVentana = new GuardarropasVentana(LogeoVentana.this);
						guardarropasVentana.setVisible(true);
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
					//despues del error queremos que los campos otra vez esten vacios
					txtUsuario.setText("");
					jpassClave.setText("");
					txtUsuario.requestFocus(); //al momento que sale contraseña incorrecta ese va a aparecer de frente en el txtUsuario para escribir
				}
			}
		});
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(btnAceptar);
		 */
		
		
		btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCrearUsuario.setBounds(305, 334, 157, 29);
		contentPane.add(btnCrearUsuario);
		
		JLabel lblBingo = new JLabel("");
		lblBingo.setIcon(new ImageIcon(LogInVentana.class.getResource("/login/images/bingo.jpg")));
		lblBingo.setBounds(10, 11, 463, 107);
		contentPane.add(lblBingo);
		
		
	}
}
