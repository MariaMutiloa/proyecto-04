package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gestionBD.GestionUsuarios;
import personas.Administrador;
import zonaUsuario.RegistroUsuarioVentana;

public class VentanaNuevoUsuario extends JFrame {
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(RegistroUsuarioVentana.class.getName());

	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private JButton btnCrear;
	private JLabel lblInfo;
	public VentanaNuevoUsuario(VentanaGestionDeUsuariosPrincipal parent, Administrador ad) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(551, 203);
		setTitle("Juego BINGO!");

		/*
		 * PANEL SUPERIOR - mensaje
		 */
		JPanel pNorte = new JPanel(); // Panel inferior
		getContentPane().add(pNorte, BorderLayout.NORTH);

		lblInfo = new JLabel("Est\u00E1s creando un usuario nuevo");
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(113, 32, 532, 40);
		pNorte.add(lblInfo);

		/*
		 * PANEL CENTRAL - insertar datos
		 */
		JPanel pCentral = new JPanel();
		pCentral.setLayout(new GridLayout(3, 4));
		getContentPane().add(pCentral, BorderLayout.CENTER);

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
		txtDni.setToolTipText("SIN LETRA");
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
		// HAY QUE COMPROBAR QUE NO HAYA UN USUARIO CON EL MISMO NOMBRE --> si hay
		// podemos usar renderer de si esta repetido se ponga en rojo

		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setForeground(Color.BLACK);
		lblContrasena.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblContrasena.setBounds(368, 182, 118, 29);
		pCentral.add(lblContrasena);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(486, 182, 185, 22);
		pCentral.add(passwordField);

		/*
		 * PANEL INFERIOR - boton crear usuario
		 */
		JPanel pSur = new JPanel(); // Panel inferior
		getContentPane().add(pSur, BorderLayout.SOUTH);

		btnCrear = new JButton("Crear usuario");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// SI HAY ALGUN CAMPO VACIO, POR FAVOR, RELLENAR
				if (txtDni.getText().length() == 0 || txtNombre.getText().length() == 0
						|| txtApellido.getText().length() == 0 || txtUsuario.getText().length() == 0
						|| passwordField.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "ERROR",
							JOptionPane.ERROR_MESSAGE);
					logger.log(Level.WARNING, "Todos los campos no están completos.");

				} else {

					// COMPROBAR QUE DNI NO TENGA LETRA PORQUE VA A SER UN INT
					try {
						Integer.parseInt(txtDni.getText());

						// TIENE QUE VERIFICAR QUE NO HAYA USUARIO REPETIDO


						if (GestionUsuarios.comprobarUsuario(txtUsuario.getText())) {
							// usuario ya esta en bd
							logger.log(Level.WARNING, "El usuario ya existe en la base de datos.");
							JOptionPane.showMessageDialog(null, "El usuario no es válido.", "ERROR",
									JOptionPane.ERROR_MESSAGE);
							txtUsuario.setText("");
						} else {
							// el usuario es valido
							// METER USUARIO EN LA BASE DE DATOS

							// extraer contraseña a String
							char[] clave = passwordField.getPassword();
							String claveFinal = new String(clave);

							GestionUsuarios.insertarUsuario(Integer.parseInt(txtDni.getText()), txtNombre.getText(),
									txtApellido.getText(), txtUsuario.getText(), claveFinal);
							txtDni.setText("");
							txtNombre.setText("");
							txtApellido.setText("");
							txtUsuario.setText("");
							passwordField.setText("");

						}
					} catch (Exception e2) {
						logger.log(Level.WARNING, "El DNI hay que ponerlo sin letra");
						JOptionPane.showMessageDialog(null, "El DNI no es valido. Introduzca sin ninguna letra.",
								"ERROR", JOptionPane.ERROR_MESSAGE);
						txtDni.setText("");
					}
				}
			}
		});

		btnCrear.setBounds(288, 293, 169, 29);
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pSur.add(btnCrear, BorderLayout.EAST);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(288, 293, 169, 29);
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pSur.add(btnVolver, BorderLayout.WEST);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VentanaNuevoUsuario.this.setVisible(false);
				VentanaNuevoUsuario.this.dispose();
			}
		});

	}

	public static boolean isNumeric(String cadena) {
		boolean esNumerico;
		try {
			Integer.parseInt(cadena);
			esNumerico = true;
		} catch (NumberFormatException excepcion) {
			esNumerico = false;
			logger.log(Level.WARNING, "El DNI hay que ponerlo sin letra");
		}

		return esNumerico;
	}

}


