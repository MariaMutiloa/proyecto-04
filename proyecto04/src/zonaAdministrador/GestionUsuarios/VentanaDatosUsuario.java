package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hamcrest.core.IsNull;

import gestionBD.ConexionBD;
import personas.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class VentanaDatosUsuario extends JFrame {

	private JPanel contentPane;
	private Usuario usuario;
	private String url;

	/**
	 * Create the frame.
	 * 
	 * @param u
	 * @param ventanaVerUsuario
	 */
	public VentanaDatosUsuario(VentanaVerUsuario ventanaVerUsuario, Usuario u) {

		this.url = "jdbc:sqlite:DatosBingo.db";
		this.usuario = u;
		List<String> IdCartonesGanadoresLista = new ArrayList<String>();
		IdCartonesGanadoresLista = seleccionIdCartonesGanadores(IdCartonesGanadoresLista, url);
		List<String> IdGanadoresLista = new ArrayList<String>();
		IdGanadoresLista = seleccionIdCartonesGanadores(IdGanadoresLista, url);
		int numeroGanadas = 0;
		numeroGanadas = numeroDeGanadas(u, IdGanadoresLista);
		numeroGanadas = CambiarNull(numeroGanadas);
		List<String> IdUsuarioCartones = new ArrayList<>();
		IdUsuarioCartones = IdUsuarioCarton(IdUsuarioCartones, url);
		int numeroJugadas = 0;
		numeroJugadas = numeroDePartidas(u, IdUsuarioCartones);
		int numeroPerdidas = 0;
		numeroPerdidas = numeroJugadas - numeroGanadas;
		List<Usuario> UsuariosLista = new ArrayList<Usuario>();
		UsuariosLista = VentanaVerUsuario.anyadirUsuarios(UsuariosLista, url);
		UsuariosLista = ordenarUsuariosPuestos(UsuariosLista);
		int puesto = contadorPuesto(UsuariosLista, usuario);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(31, 11, 49, 14);
		contentPane.add(labelNombre);

		JLabel labelApellido = new JLabel("Apellido");
		labelApellido.setBounds(145, 11, 49, 14);
		contentPane.add(labelApellido);

		JLabel labelPartidasJugadas = new JLabel("Partidas jugadas");
		labelPartidasJugadas.setBounds(10, 67, 111, 14);
		contentPane.add(labelPartidasJugadas);

		JLabel lblPartidasGanadas = new JLabel("Partidas ganadas");
		lblPartidasGanadas.setBounds(145, 67, 111, 14);
		contentPane.add(lblPartidasGanadas);

		JLabel lblPartidasPerdidas = new JLabel("Partidas perdidas");
		lblPartidasPerdidas.setBounds(10, 123, 111, 14);
		contentPane.add(lblPartidasPerdidas);

		JLabel labelLiga = new JLabel("Liga");
		labelLiga.setBounds(145, 123, 111, 14);
		contentPane.add(labelLiga);

		JLabel labelPuesto = new JLabel("Puesto");
		labelPuesto.setBounds(10, 179, 111, 14);
		contentPane.add(labelPuesto);

		JLabel labelPuntos = new JLabel("Puntos/Bote");
		labelPuntos.setBounds(145, 179, 111, 14);
		contentPane.add(labelPuntos);

		JButton botonVolverVerUsuarios = new JButton("Volver a Ver Usuarios");
		botonVolverVerUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaVerUsuario nuevaVentanaVerUsuarios = new VentanaVerUsuario();
				nuevaVentanaVerUsuarios.setVisible(true);
				VentanaDatosUsuario.this.setVisible(false);
			}
		});
		botonVolverVerUsuarios.setBounds(218, 92, 208, 31);
		contentPane.add(botonVolverVerUsuarios);

		JButton botonGestionarUsuarios = new JButton("Volver a Gesti\u00F3n de usuarios");
		botonGestionarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalGestionUsuarios nuevaVentanaGestion = new VentanaPrincipalGestionUsuarios();
				nuevaVentanaGestion.setVisible(true);
				VentanaDatosUsuario.this.setVisible(false);
			}
		});
		botonGestionarUsuarios.setBounds(218, 134, 208, 34);
		contentPane.add(botonGestionarUsuarios);

		JLabel lblNombrePuesto = new JLabel(usuario.getNombre());
		lblNombrePuesto.setBackground(Color.WHITE);
		lblNombrePuesto.setBounds(31, 36, 104, 14);
		contentPane.add(lblNombrePuesto);

		JLabel lblApellidoPuesto = new JLabel(usuario.getApellido());
		lblApellidoPuesto.setBackground(Color.WHITE);
		lblApellidoPuesto.setBounds(145, 36, 121, 14);
		contentPane.add(lblApellidoPuesto);

		JLabel lblPartidasJugadas = new JLabel(String.valueOf(numeroJugadas));
		lblPartidasJugadas.setBackground(Color.WHITE);
		lblPartidasJugadas.setBounds(31, 92, 49, 14);
		contentPane.add(lblPartidasJugadas);

		JLabel lblPartidasG = new JLabel(String.valueOf(numeroGanadas));
		lblPartidasG.setBackground(Color.WHITE);
		lblPartidasG.setBounds(145, 92, 49, 14);
		contentPane.add(lblPartidasG);

		JLabel lblPartidasP = new JLabel(String.valueOf(numeroPerdidas));
		lblPartidasP.setBackground(Color.WHITE);
		lblPartidasP.setBounds(31, 148, 49, 14);
		contentPane.add(lblPartidasP);

		String idLiga = String.valueOf(usuario.getIdLigaActual());
		JLabel lblLiga = new JLabel(idLiga);
		lblLiga.setBackground(Color.WHITE);
		lblLiga.setBounds(145, 148, 49, 14);
		contentPane.add(lblLiga);
		;

		JLabel lblPuesto = new JLabel(String.valueOf(puesto));
		lblPuesto.setBackground(Color.WHITE);
		lblPuesto.setBounds(31, 204, 49, 14);
		contentPane.add(lblPuesto);

		String bote = String.valueOf(usuario.getBote());
		JLabel lblPuntos = new JLabel(bote);
		lblPuntos.setBackground(Color.WHITE);
		lblPuntos.setBounds(145, 204, 49, 14);
		contentPane.add(lblPuntos);

	}

	private static Logger logger = Logger.getLogger(VentanaVerUsuario.class.getName());

	// crea una lista con todos los cartones ganadores
	public static List<String> seleccionIdCartonesGanadores(List<String> IdCartonesGanadoresLista, String url) {

		try (Connection con = DriverManager.getConnection(url)) {
			logger.info("Conectado a la base de datos para hacer la búsqueda");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT IDCartonB from partida");
			logger.info("Select hecha para sacar los ID de los cartones ganadores");
			while (rs.next()) {
				String Id = "";
				Id = rs.getString(1);
				IdCartonesGanadoresLista.add(Id);
				logger.info("Id cartones seleccionados y agregados a un ArrayList");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return IdCartonesGanadoresLista;

	}

	// crea una lista con los ID de los usuarios ganadores
	public static List<String> IdUsuarioGanador(List<String> IdGanadoresLista, String url) {

		try (Connection con = DriverManager.getConnection(url)) {
			logger.info("Conectado a la base de datos para hacer la busqueda");
			for (String IdCartonGanador : IdGanadoresLista) {
				String sql = ("select  IDUsuario from carton  where IDCarton =?");
				try (PreparedStatement pstmt = con.prepareStatement(sql)) {
					pstmt.setString(1, IdCartonGanador);
					ResultSet rs = pstmt.executeQuery();
					logger.info("Select hecha para sacar los Id de usuarios de los cartones ganadores");
					while (rs.next()) {
						String Id = "";
						IdGanadoresLista.add(Id);
						logger.info("Id de usuarios agregados a la lista");

					}
					rs.close();
				} catch (SQLException e) {
					// e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					logger.log(Level.SEVERE, "No se ha podido realizar la consulta");

				}

			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return IdGanadoresLista;
	}

	// cuenta cuantas veces ha ganado un usuario
	public static int numeroDeGanadas(Usuario u, List<String> IdGanadoresLista) {
		int contador = 0;
		try {
			logger.info("Mirando los ganadores");
			for (String ID : IdGanadoresLista) {

				while (ID.equals(u.getUsuario())) {
					logger.info("Contando las veces ganadas");
					contador = contador + 1;
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay partidas en la BD");
			logger.log(Level.SEVERE, "No hay partidas en la BD");
		}

		return contador;

	}

	// cambia null por 0 para que no se vea null
	public static int CambiarNull(Integer numeroGanadas) {

		if (numeroGanadas == null) {
			numeroGanadas = 0;
			logger.info("Cambiando para que en los datos no se vea null si no 0");
		}
		return numeroGanadas;

	}

	// seleccionar los ID de los Usuarios de los cartones
	public static List<String> IdUsuarioCarton(List<String> IdUsuarioCarton, String url) {
		try {

		} catch (Exception e) {
			// TODO: handle exception
		}
		try (Connection con = DriverManager.getConnection(url)) {
			logger.info("Conectado a la base de datos para hacer la búsqueda");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT IDUsuario from carton");
			logger.info("Select hecha para sacar los Id de usuarios");
			while (rs.next()) {
				String Id = "";
				IdUsuarioCarton.add(Id);
				logger.info("Id añadido a la lista");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return IdUsuarioCarton;

	}

// contando cuantas veces ha jugado un usuario
	public static int numeroDePartidas(Usuario u, List<String> IdUsuarioCarton) {
		int contador = 0;
		try {
			logger.info("Mirando los ID de los Usuarios de los cartones");
			for (String ID : IdUsuarioCarton) {

				while (ID.equals(u.getUsuario())) {
					logger.info("Contando las veces jugadas");
					contador = contador + 1;
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay partidas en la BD");
			logger.log(Level.SEVERE, "No hay partidas");
		}

		return contador;

	}

// calcula el puesto
	public static int contadorPuesto(List<Usuario> UsuariosLista, Usuario u) {
		int contador = 0;
		int contadorFinal = 0;
		try {
			for (int i = 0; i < UsuariosLista.size(); i++) {
				logger.info("Hay usuarios");
				Usuario usuario = UsuariosLista.get(i);
				contador = contador + 1;
				if (usuario.getUsuario().equals(u.getUsuario())) {
					logger.info("Se ha encontrado una coincidencia");
					contadorFinal = contador;
				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay usuarios registardos");
			logger.log(Level.SEVERE, "No hay usuarios");
		}
		return contadorFinal;

	}

	// creo un comparador para la lista
	static class MiComparador implements Comparator<Usuario> {

		@Override
		public int compare(Usuario a, Usuario b) {

			return a.getUsuario().compareTo(b.getUsuario());
		}

	}

	// ordeno la lista
	public static List<Usuario> ordenarUsuariosPuestos(List<Usuario> usuariosPuesto) {
		Collections.sort(usuariosPuesto, new MiComparador());
		return usuariosPuesto;
	}
}
