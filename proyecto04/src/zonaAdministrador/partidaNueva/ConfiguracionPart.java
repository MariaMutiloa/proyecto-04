package zonaAdministrador.partidaNueva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import login.LogInVentana;
import personas.Administrador;
import personas.Usuario;
import zonaAdministrador.VentanaPrincipalAdmin;

import java.awt.BorderLayout;
import java.awt.Component;


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
		setBounds(100, 100, 200, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel superior = new JPanel();
			JLabel lblTitulo = new JLabel ("Configuración de partida ");
			superior.add(lblTitulo, BorderLayout.WEST);
			JButton btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					LogInVentana nueva = new LogInVentana();
					nueva.setVisible(true);
					ConfiguracionPart.this.dispose();
					
				}
				
			});
			superior.add(btnVolver, BorderLayout.EAST);
			
		JPanel central = new JPanel();
		central.setLayout(new BorderLayout());
			JPanel centralArriba = new JPanel();	
			centralArriba.setLayout(new BorderLayout());
				centralArriba.add(new JLabel("Bote Linea"), BorderLayout.WEST);
				JTextField txtLinea = new JTextField();
				txtLinea.setColumns(25);
				centralArriba.add(txtLinea, BorderLayout.EAST);
				
			JPanel centralMedio = new JPanel();	
			centralMedio.setLayout(new BorderLayout());
				centralMedio.add(new JLabel("Bote Bingo"), BorderLayout.WEST);
				JTextField txtBingo = new JTextField();
				txtBingo.setColumns(25);
				centralMedio.add(txtBingo, BorderLayout.EAST);
			
			JPanel centralBajo = new JPanel();	
			centralBajo.setLayout(new BorderLayout());
			centralBajo.add(new JLabel("IDLiga"), BorderLayout.WEST);
				JTextField txtLiga = new JTextField();					
				txtLiga.setColumns(25);
				centralBajo.add(txtLiga, BorderLayout.EAST);	
				
			central.add(centralArriba, BorderLayout.NORTH);	
			central.add(centralMedio, BorderLayout.CENTER);	
			central.add(centralBajo, BorderLayout.SOUTH);	

			JPanel inferior = new JPanel(); 
			inferior.setLayout(new BorderLayout());
				JPanel inferiorDerecha = new JPanel();
				inferiorDerecha.setLayout(new BorderLayout());
					JButton btnRefrescar = new JButton("Refrescar");
					btnRefrescar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							partidaActual.setParticipantes(GestionPartidas.participantes(partidaActual.getIDPartida()));
							float boteL = calculoBote(partidaActual.getParticipantes().size(), "linea");
							txtLinea.setText(boteL);
							
							float boteB = calculoBote(partidaActual.getParticipantes().size(), "bingo");
							GestionPartidas.actualizarDatos(partidaActual.getIDPartida(), boteL, boteB, Integer.parseInt(txtLiga.getText()));
						
						}

				
					});
					inferiorDerecha.add(btnRefrescar, BorderLayout.SOUTH);
					JButton btnEmpezar = new JButton("Empezar Partida");
					btnEmpezar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				
							PartidaNueva nuevaVentana = new PartidaNueva(partidaActual);
							nuevaVentana.setVisible(true);
							ConfiguracionPart.this.dispose();
						}
					});
					inferiorDerecha.add(btnEmpezar, BorderLayout.WEST);
					
				JPanel inferiorIzquierda = new JPanel();
				inferiorIzquierda.setLayout(new BorderLayout());
					inferiorIzquierda.add(new JLabel ("Participantes conectados"), BorderLayout.NORTH);
					JLabel lblNumero = new JLabel("");
					lblNumero.setIcon(imagenNumero(0));
					inferiorIzquierda.add(lblNumero, BorderLayout.CENTER);
					JButton  btbVerParticipantes = new JButton("Ver Participantes");
					
					inferiorIzquierda.add(btbVerParticipantes, BorderLayout.SOUTH);
				inferior.add(inferiorDerecha, BorderLayout.EAST);	
				inferior.add(inferiorIzquierda, BorderLayout.WEST);	

				
			contentPane.add(superior,BorderLayout.NORTH);	
			contentPane.add(central,BorderLayout.CENTER);	
			contentPane.add(inferior,BorderLayout.SOUTH);	
			setContentPane(contentPane);
	}

	//Extrae la imagen de la carpeta de imagenes que corresponde
	private Icon imagenNumero(int numero) {
		Icon icono = null;
		logger.info("Buscando imagen correspondiente a " +numero);
		if(numero > 9) {
			icono = new ImageIcon(getClass().getResource("/otro.jpg"));
		}else if (numero == 0){
			icono = new ImageIcon(getClass().getResource("/0.jpeg"));
		}else {
			icono = new ImageIcon(getClass().getResource("/"+String.valueOf(numero) + ".jpg") );
		}
		icono = ajustarAEspacio(icono);
		return icono;
	}
	
	//Ajusta la imagen al tamaño disponible
	private Icon ajustarAEspacio(Icon icono) {
		//FALTA AÑADIR ESTOO
		return icono; 
	}
	
	private float calculoBote(int tamaño, String tipoBote) {
		float ponderador = 0;
		logger.info("Calculando los botes correspondientes");
		try (FileReader reader = new FileReader("configuracion/configCostes.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            
            ponderador = Float.parseFloat(properties.getProperty(tipoBote));
           

        } catch (IOException e) {
            
        }
		 return ponderador;
	}
}
