package zonaAdministrador.partidaNueva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import login.LogInVentana;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;


//ventana que deja al administrador configurar la partida a su gusto

public class ConfiguracionPart extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Partida partidaActual = new Partida();
	private static Logger logger = Logger.getLogger(ConfiguracionPart.class.getName());


	public ConfiguracionPart(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setVisible(true);
		
		JPanel superior = new JPanel();
			JLabel lblTitulo = new JLabel ("Configuración de partida ");
			superior.add(lblTitulo, BorderLayout.WEST);
			JButton btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					LogInVentana nueva = new LogInVentana();
					nueva.setVisible(true);
					ConfiguracionPart.this.setVisible(false);
					
				}
				
			});
			superior.add(btnVolver, BorderLayout.EAST);
			
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
				
			JPanel centralArriba = new JPanel();	
			centralArriba.setLayout(new BorderLayout());
			centralArriba.add(new JLabel("Bote Bingo"), BorderLayout.WEST);
				JTextField txtBingo = new JTextField();
				txtBingo.setColumns(25);
				centralArriba.add(txtBingo, BorderLayout.EAST);
			
			JPanel centralBajo = new JPanel();	
			centralBajo.setLayout(new BorderLayout());
			centralBajo.add(new JLabel("IDLiga"), BorderLayout.WEST);
			//Integer[] ligas = GestionPartidas.getLigas();
			Integer[] ligas = {1, 2, 3};
 				JComboBox<Integer> CBliga = new JComboBox<Integer>(ligas);	
				CBliga.setSize(200,5);
				centralBajo.add(CBliga, BorderLayout.EAST);	
				
			central.add(centralArriba, BorderLayout.NORTH);	
			central.add(centralBajo, BorderLayout.SOUTH);	

			JPanel inferior = new JPanel(); 
			inferior.setLayout(new BorderLayout());
				JPanel inferiorDerecha = new JPanel();
				inferiorDerecha.setLayout(new BorderLayout());
					JButton btnRefrescar = new JButton("Refrescar");
					btnRefrescar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							partidaActual.setParticipantes(GestionPartidas.participantes(partidaActual.getIDPartida()));
							float boteB = getPonderador()*partidaActual.getParticipantes().size()*Carton.costeCarton();
							GestionPartidas.actualizarDatos(partidaActual.getIDPartida(), boteB);
						
						}

				
					});
					inferiorDerecha.add(btnRefrescar, BorderLayout.SOUTH);
					JButton btnEmpezar = new JButton("Empezar Partida");
					btnEmpezar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (txtBingo.getText().equals("")) {
								JOptionPane.showMessageDialog(null, "Los campos IDLiga y Bote bingo deben ser rellenados","Datos incompletos", JOptionPane.WARNING_MESSAGE);
				
							} else {
								GestionPartidas.empezada(partidaActual.getIDPartida(), (Integer)CBliga.getSelectedItem());
								PartidaNueva nuevaVentana = new PartidaNueva(partidaActual, ConfiguracionPart.this);
								nuevaVentana.setVisible(true);
								ConfiguracionPart.this.dispose();	  
							}
						}	
					});
					inferiorDerecha.add(btnEmpezar, BorderLayout.WEST);
					
				JPanel inferiorIzquierda = new JPanel();
				inferiorIzquierda.setLayout(new BorderLayout());
					inferiorIzquierda.add(new JLabel ("Participantes conectados"), BorderLayout.NORTH);
					JLabel lblNumero = new JLabel("");
					lblNumero.setIcon(imagenNumero(0));
					JPanel imagen = new JPanel();
					imagen.setLayout(new FlowLayout());
					imagen.add(lblNumero);
					inferiorIzquierda.add(imagen, BorderLayout.CENTER);
				inferior.add(inferiorDerecha, BorderLayout.EAST);	
				inferior.add(inferiorIzquierda, BorderLayout.WEST);	

				
			contentPane.add(superior,BorderLayout.NORTH);	
			contentPane.add(central,BorderLayout.CENTER);	
			contentPane.add(inferior,BorderLayout.SOUTH);	
			setContentPane(contentPane);
	}

	//Extrae la imagen de la carpeta de imagenes que corresponde
	public Icon imagenNumero(int numero) {
		Icon icono = null;
		logger.info("Buscando imagen correspondiente a " +numero);
		if(numero > 9) {
			icono = new ImageIcon(getClass().getResource("/otro.jpg"));
		}else {
			icono = new ImageIcon(getClass().getResource("/"+String.valueOf(numero) + ".jpg") );
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
        	JOptionPane.showMessageDialog(null, "No se pueden acceder al fichero de propiedades","Error en properties", JOptionPane.WARNING_MESSAGE);
			 
        }
		 return ponderador;
	}
	
	
}
