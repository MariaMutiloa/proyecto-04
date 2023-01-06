package zonaUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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
import visualizacionGenericos.ModeloListaValoresCantados;
import zonaAdministrador.partidaNueva.PartidaNueva;

import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.swing.plaf.DimensionUIResource;

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
	private Usuario u;
	private boolean interrumpir;

	private static JLabel unidades = new JLabel();
	private static JLabel decenas = new JLabel();

	private int[][] datosColores;

	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());

	public UsuarioVentana(Usuario u) {
		this.u = u;
		setBounds(100, 100, 647, 319);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// PANEL SUPERIOR
		JPanel pSuperior = new JPanel();
		pSuperior.setSize(150, 50);
		pSuperior.setLayout(new GridLayout(1, 4));
		getContentPane().add(pSuperior, BorderLayout.NORTH);

		JLabel lblBienvenido = new JLabel("¡Bienvenidx " + u.getNombre() + "!");
		pSuperior.add(lblBienvenido);

		JLabel lblCartera = new JLabel("Cartera: " + u.getBote() + " €");
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
		pInferior.setLayout(new GridLayout(1, 2));
		getContentPane().add(pInferior, BorderLayout.SOUTH);

		JPanel infIzquierda = new JPanel();
		pInferior.add(infIzquierda, BorderLayout.WEST);

		JPanel pInfDerecha = new JPanel();
		pInferior.add(pInfDerecha, BorderLayout.EAST);

		// PANEL CENTRAL
		// aqui va a salir el número en grande y también podremos ver qué numeros han
		// salido hasta ahora

		pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		pCentral.setVisible(false);

		// muestra todos los numeros que han salido en la partida
		numeros = new JList<>();
		JScrollPane scrollNumeros = new JScrollPane(numeros);
		scrollNumeros.setPreferredSize(new Dimension(50, 175));
		pCentral.add(scrollNumeros, BorderLayout.WEST);

		// Panel central east --> el numero que ha salido en grande
		JPanel pCentralEast = new JPanel();
		getContentPane().add(pCentralEast, BorderLayout.EAST);
		// AQUI VA A APARECER EL NUMERO Q SACA EL ADMINISTRADOR
		pCentralEast.add(decenas, BorderLayout.WEST);
		pCentralEast.add(unidades, BorderLayout.EAST);

		btnJugar = new JButton("JUGAR");
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				p = GestionUsuarios.buscarPartidaActiva();
				logger.info("Comprobando si existen partidas activas");
				if (p != null) {
					// SALE JOptionPane diciendo si confirmar compra carton o no
					int confirmado = JOptionPane.showConfirmDialog(null,
							"El carton cuesta " + Carton.costeCarton() + "€\n¿Quieres comprar un carton?");

					if (JOptionPane.OK_OPTION == confirmado) {
						logger.info("El usuario ha aceptado comprar carton.");

						// POR COMPRAR UN CARTON(bote) LA CARTERA DEL USUARIO BAJA
						// comprar si tiene dinero suficiente y bajar cartera
						boolean suficienteDinero = comprobarSuficienteDinero(u);
						if (suficienteDinero) {
							// bajar cartera
							Carton.bajarCartera(u);
							lblCartera.setText("Cartera: " + u.getBote() + " €");
							// actualizar cartera en BD

							// ABRE VENTANA DEL CARTON
							CartonVentana c = new CartonVentana(u, p);
							c.setVisible(true);
							pCentral.setVisible(true);
							logger.info("Empezamos a mirar los números sacados en la partida con un hilo");

							interrumpir = false;
							Thread t = new Thread(new Runnable() {

								@Override
								public void run() {

									Timer timer = new Timer(3000, new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {

											logger.info("Mirando si alguien ha ganado el bingo");

											// Primero mira si se ha cantado bingo

											Integer ganador = GestionUsuarios.comprobarSiGanador(p.getIDPartida());
											logger.info("el ganador es: " + ganador);
											if (ganador != 0) {
												if (ganador == u.getDni()) {
													JOptionPane.showMessageDialog(null, "Enhorabuena",
															"El bingo es correcto, el bote se ha añadido a tu cartera",
															JOptionPane.INFORMATION_MESSAGE);
												} else {
													JOptionPane.showMessageDialog(null, "Partida terminada",
															"Alguien ha cantado un bingo correcto",
															JOptionPane.INFORMATION_MESSAGE);
													c.dispose();
													// SE TIENE QUE PARAR EL HILO --> vamos a cerrar esa ventana usuario
													// y crear una nueva
													UsuarioVentana nuevaUsuarioVentana = new UsuarioVentana(u);
													nuevaUsuarioVentana.setVisible(true);
													UsuarioVentana.this.dispose();
													interrumpir = true;
													Thread.currentThread().stop();
													

												}
											} else {
												logger.info("La partida sigue abierta");
												logger.info("Buscando nuevos numeros");

												// Por una parte cambia el modelo de la lista de datos
												List<Integer> numerosCantados = GestionUsuarios
														.numerosPartida(p.getIDPartida());
												ListModel<Integer> modeloNuevo = new ModeloListaValoresCantados(
														numerosCantados);
												numeros.setModel(modeloNuevo);

												// Por otra parte cambia el numero mostrado en grande
												if (numerosCantados.size() > 0) {
													int numero = numerosCantados.get(numerosCantados.size() - 1);
													String number = String.valueOf(numero);
													String[] digits = number.split("(?<=.)");
													System.out.println(digits);
													logger.info("Numero nuevo conseguido");
													if (digits.length == 1) {
														decenas.setIcon(new ImageIcon(getClass()
																.getResource("/" + String.valueOf(0) + ".jpg")));
														unidades.setIcon(new ImageIcon(getClass().getResource(
																"/" + String.valueOf(digits[0]) + ".jpg")));

													} else {
														decenas.setIcon(new ImageIcon(getClass().getResource(
																"/" + String.valueOf(digits[0]) + ".jpg")));
														unidades.setIcon(new ImageIcon(getClass().getResource(
																"/" + String.valueOf(digits[1]) + ".jpg")));

													}

												}
											}

										}

									});
									timer.start();

								}

							});
							t.start();
							logger.info("Abro cartón");
						}

					} else {
						logger.info("El usuario no quiere comprar carton");
					}

				} else {
					JOptionPane.showMessageDialog(null, "No hay ninguna partida activa. Inténtelo más tarde.", "",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		pInfDerecha.add(btnJugar);

	}

	public static boolean comprobarSuficienteDinero(Usuario u) {
		float precioCarton = Carton.costeCarton();
		float carteraUsuario = u.getBote();

		if (carteraUsuario >= precioCarton) {
			logger.info("Tiene suficiente dinero.");
			return true;
		} else {
			logger.log(Level.WARNING, "No tiene suficiente dinero.");
			return false;
		}
	}

}
