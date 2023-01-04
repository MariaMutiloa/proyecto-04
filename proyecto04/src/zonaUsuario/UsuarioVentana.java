package zonaUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.ConexionBD;
import gestionBD.GestionUsuarios;
import login.LogInVentana;
import personas.Usuario;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsuarioVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnJugar;
	private Partida p;
	private JPanel pCentral;
	private JList<Integer> numeros;

	private int[][] datosColores;
	
	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());
	

	public UsuarioVentana(Usuario u) {
		setBounds(100, 100, 647, 319);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p = GestionUsuarios.buscarPartidaActiva();
		
		//PANEL SUPERIOR
		JPanel pSuperior = new JPanel();
		pSuperior.setSize(150, 50);
		pSuperior.setLayout(new GridLayout(1,4));
		getContentPane().add(pSuperior, BorderLayout.NORTH);

		
		JLabel lblBienvenido = new JLabel("�Bienvenidx " + u.getNombre() + "!");
		pSuperior.add(lblBienvenido);
		
		JLabel lblCartera = new JLabel("Cartera: "+u.getBote()+" �");
		pSuperior.add(lblCartera);
		
		JButton btnSalir = new JButton("Salir");
		pSuperior.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInVentana parent = new LogInVentana();
				parent.setVisible(true);
				UsuarioVentana.this.dispose();	
			}
		});
		
		JPanel pInferior = new JPanel();
		pInferior.setLayout(new GridLayout(1,2));
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		JPanel infIzquierda = new JPanel();
		pInferior.add(infIzquierda, BorderLayout.WEST);
		
		JButton btnEstadisticas = new JButton("Ver estadisticas");
		infIzquierda.add(btnEstadisticas);
		
			//METER FUNCIONALIDAD VER ESTADISTICAS
		
		JPanel pInfDerecha = new JPanel();
		pInferior.add(pInfDerecha, BorderLayout.EAST);
		
		
				
		//PANEL CENTRAL
		//aqui va a salir el n�mero en grande y tambi�n podremos ver qu� numeros han salido hasta ahora
		
		pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setVisible(false);
		
		
		numeros = new JList<>();
		numeros.setModel(GestionUsuarios.numerosPartida(p.getIDPartida()));
		JScrollPane scrollNumeros = new JScrollPane(numeros);
		pCentral.add(scrollNumeros, BorderLayout.WEST);
		
		
		//AQUI VA A APARECER EL NUMERO Q SACA EL ADMINISTRADOR
		JLabel lblNumero = new JLabel("New label");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 20));
		pCentral.add(lblNumero, BorderLayout.EAST);
		
		
		btnJugar = new JButton("JUGAR");
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//SALE JOptionPane diciendo si confirmar compra carton o no
				int confirmado = JOptionPane.showConfirmDialog(null, "El carton cuesta "+ Carton.costeCarton() +"�\n�Quieres comprar un carton?");

						if (JOptionPane.OK_OPTION == confirmado) {
							logger.info("El usuario ha aceptado comprar carton.");
							
							//POR COMPRAR UN CARTON(bote) LA CARTERA DEL USUARIO BAJA
							//comprar si tiene dinero suficiente y bajar cartera
							boolean suficienteDinero=comprobarSuficienteDinero(u);
							if(suficienteDinero) {
								//bajar cartera + actualizar en BD
								Carton.bajarCartera(u);
								lblCartera.setText("Cartera: "+u.getBote()+" �");
								
								//ABRE VENTANA DEL CARTON
								CartonVentana c = new CartonVentana(u, p);
								c.setVisible(true);
								pCentral.setVisible(true);
								logger.info("Empezamos a mirar los n�meros sacados en la partida con un hilo");
								Thread t = new Thread (new Runnable() {

									@Override
									public void run() {
										Timer timer = new Timer(3000, new ActionListener() {
											
											@Override
											public void actionPerformed(ActionEvent e) {
												//Por una parte cambia el modelo de la lista de datos
												ListModel<Integer> modeloNuevo = GestionUsuarios.numerosPartida(p.getIDPartida());
												numeros.setModel(modeloNuevo);
												
												//Por otra parte cambia el numero mostrado en grande
												int numero = modeloNuevo.getElementAt(modeloNuevo.getSize()-1);
												//TIENES EL NUMERO FALTA A�ADIRLOS A LAS LABELS (esta heco en partida nueva metodo actualizar)
												//seguramente tendras que hacer las labels de los nuemeros globales
											}
											
										});
										timer.start();	
									}
									
								});
								t.start();
								logger.info("Abro cart�n");
							}
							
							
						}else {
							logger.info("El usuario no quiere comprar carton");
						}
				
				
			}
		});
		pInfDerecha.add(btnJugar);
		btnJugar.setEnabled(false);
		
		/*
		 * TIENE QUE HABER UNA COMPROBARCION CON LA BASE DE DATOS Y MIRAR SI HAY ALGUNA PARTIDA ACTIVA
		 * SI NO HAY PARTIDA ACTIVA --> NO PUEDE COMPRAR NINGUN CARTON
		 * SI HAY PARTIDA ACTIVA --> BOTON JUGAR Y APARECE CARTON
		 */
		
		if (p.equals(null)) {
			btnJugar.setEnabled(false);
			JOptionPane.showMessageDialog(null, "No hay ninguna partida activa. Int�ntelo m�s tarde.","", JOptionPane.WARNING_MESSAGE);
		}else {
			//boton jugar 
			btnJugar.setEnabled(true);
			
		
		}
		
		
		
		
	}
	
	public static boolean comprobarSuficienteDinero(Usuario u) {
		float precioCarton = Carton.costeCarton();
		float carteraUsuario = u.getBote();
		
		if(carteraUsuario>=precioCarton) {
			logger.info("Tiene suficiente dinero.");
			return true;
		}else {
			logger.log(Level.WARNING, "No tiene suficiente dinero.");
			return false;
		}
	}
	
	private static void refrescarLista () {
		
	}


}
	


	

