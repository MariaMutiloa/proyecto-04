package zonaAdministrador.partidaNueva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * 
 * La ventana deja que el administrador pueda configurar la partida y deja que
 * los usuarios se conecten
 *
 */

public class ConfiguracionPart extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Partida partidaActual = new Partida();
	private JLabel lblNumero;
	private static Logger logger = Logger.getLogger(ConfiguracionPart.class.getName());

	public ConfiguracionPart(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		setTitle("Configuración de partida");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);

		JPanel superior = new JPanel();
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				ConfiguracionPart.this.setVisible(false);
			}
		});
		superior.add(btnVolver, BorderLayout.EAST);

		JPanel central = new JPanel();

		JLabel lblbingo = new JLabel("Bote bingo");
		central.add(lblbingo, BorderLayout.WEST);

		JTextField txtBingo = new JTextField();
		txtBingo.setColumns(25);
		central.add(txtBingo, BorderLayout.EAST);

		JPanel inferior = new JPanel();
		inferior.setLayout(new BorderLayout());
		
		JPanel inferiorDerecha = new JPanel();
		inferiorDerecha.setLayout(new BorderLayout());
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				partidaActual.setParticipantes(GestionPartidas.participantes(partidaActual.getIDPartida()));
				lblNumero.setIcon(imagenNumero(partidaActual.getParticipantes().size()));
				float boteB = getPonderador() * partidaActual.getParticipantes().size() * Carton.costeCarton();
				partidaActual.setBoteBingo(boteB);
				txtBingo.setText(String.valueOf(boteB));

			}

		});
		inferiorDerecha.add(btnRefrescar, BorderLayout.SOUTH);
		
		JButton btnEmpezar = new JButton("Empezar Partida");
		btnEmpezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtBingo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Los campos IDLiga y Bote bingo deben ser rellenados",
							"Datos incompletos", JOptionPane.WARNING_MESSAGE);

				} else {
					GestionPartidas.actualizarDatos(partidaActual.getIDPartida(), Float.parseFloat(txtBingo.getText()));
					PartidaNueva nuevaVentana = new PartidaNueva(partidaActual, admin, Float.valueOf(txtBingo.getText()));
					nuevaVentana.setVisible(true);
					ConfiguracionPart.this.dispose();
				}
			}
		});
		inferiorDerecha.add(btnEmpezar, BorderLayout.WEST);

		JPanel inferiorIzquierda = new JPanel();
		inferiorIzquierda.setLayout(new BorderLayout());
		
		inferiorIzquierda.add(new JLabel("Participantes conectados"), BorderLayout.NORTH);
		
		lblNumero = new JLabel("");
		lblNumero.setIcon(imagenNumero(0));
		
		JPanel imagen = new JPanel();
		imagen.setLayout(new FlowLayout());
		imagen.add(lblNumero);
		inferiorIzquierda.add(imagen, BorderLayout.CENTER);
		inferior.add(inferiorDerecha, BorderLayout.EAST);
		inferior.add(inferiorIzquierda, BorderLayout.WEST);

		contentPane.add(superior, BorderLayout.NORTH);
		contentPane.add(central, BorderLayout.CENTER);
		contentPane.add(inferior, BorderLayout.SOUTH);
		setContentPane(contentPane);
	}

	// Extrae la imagen de la carpeta de imagenes que corresponde
	public Icon imagenNumero(int numero) {
		Icon icono = null;
		logger.info("Buscando imagen correspondiente a " + numero);
		if (numero > 9) {
			icono = new ImageIcon(getClass().getResource("/otro.jpg"));
		} else {
			icono = new ImageIcon(getClass().getResource("/" + String.valueOf(numero) + ".jpg"));
		}
		return icono;
	}

	public static float getPonderador() {
		float ponderador = 0;
		logger.info("Calculando bote correspondiente");
		try (FileReader reader = new FileReader("src/configuracion/configCostes.properties")) {
			Properties properties = new Properties();
			properties.load(reader);

			ponderador = Float.parseFloat(properties.getProperty("bingo"));

		} catch (IOException e) {
			logger.info("No se ha podido acceder al fichero properties");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pueden acceder al fichero de propiedades", "Error en properties",
					JOptionPane.WARNING_MESSAGE);

		}
		return ponderador;
	}

}
