package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.AttributeSet.ColorAttribute;

import gestionBD.GestionUsuarios;
import personas.Usuario;
import zonaAdministrador.GestionUsuarios.VentanaVerDatosUsuarioBuscado.MiComparador;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaGestionDeUsuariosPrincipal extends JFrame {

	private JPanel contentPane;
	private String url;
	private int seleccionFila =-1;
	//seleccion de fila
	private void tuTablaMouseClicked(java.awt.event.MouseEvent evt) {
		seleccionFila = table.rowAtPoint(evt.getPoint());
	}

	/**
	 * Create the frame.
	 */
	public VentanaGestionDeUsuariosPrincipal() {
		int contadorG=0;
		VentanaGestionDeUsuariosPrincipal ventana = VentanaGestionDeUsuariosPrincipal.this;
		this.url = "jdbc:sqlite:DatosBingo.db";
		GestionUsuarios gUsuarios = new GestionUsuarios();
		List<Usuario> usuarios = new ArrayList<>();
		usuarios = anyadirUsuarios(usuarios, url);
		usuarios = ordenarUsuariosPuestos(usuarios);
		logger.info("Lista ordenada");
		List<String> IdGanadoresLista = new ArrayList<String>();
		IdGanadoresLista = seleccionIdCartonesGanadores(IdGanadoresLista, url);
		List<String> IdUsuarioCartones = new ArrayList<>();
		IdUsuarioCartones = IdUsuarioCarton(IdUsuarioCartones, url);
		List<Usuario> usuariosDatos = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			String nombre = usuario.getNombre();
			String apellido = usuario.getApellido();
			String nombreUsuario = usuario.getUsuario();
			int liga = usuario.getIdLigaActual();
			int puesto = (usuarios.indexOf(usuario)) + 1;
			float bote = usuario.getBote();
			int dni = usuario.getDni();
			String contrasena = usuario.getContrasena();
			int partidasJ = 0;
			try {
				for (String g : IdGanadoresLista) {
				logger.info("Mirando los ganadores");
				int dniComparar = Integer.parseInt(g);
					while (dniComparar==usuario.getDni()) {
						logger.info("Contando las veces ganadas");
						contadorG = contadorG + 1;
					}
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No hay partidas en la BD");
				logger.log(Level.SEVERE, "No hay partidas en la BD");
			}
			int partidasG = 0;
			try {
				for (String g : IdUsuarioCartones) {
				logger.info("Mirando los ganadores");
				int dniComparar2 = Integer.parseInt(g);
					while (dniComparar2 == usuario.getDni()) {
						logger.info("Contando las veces ganadas");
						contadorG = contadorG + 1;
					}
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No hay partidas en la BD");
				logger.log(Level.SEVERE, "No hay partidas en la BD");
			}
			
			int partidasP = partidasJ - partidasG;
			Usuario u = new Usuario(dni, nombre, apellido, nombreUsuario, contrasena, liga, bote, partidasJ, partidasG,
					partidasP, puesto);
			usuariosDatos.add(u);
			logger.info("Añadida la persona");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTable table = new JTable(new ModeloTablaUsuariosDatos(usuariosDatos));
		table.setBounds(10, 36, 294, 216);
		contentPane.add(table);

		textField = new JTextField();
		textField.setBounds(314, 95, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnBuscar = new JButton("Nuevo usuario");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaNuevoUsuario nuevoUsuario = new VentanaNuevoUsuario(ventana);
				nuevoUsuario.setVisible(true);
				VentanaGestionDeUsuariosPrincipal.this.setVisible(false);
			}
		});
		btnBuscar.setBounds(315, 208, 111, 20);
		contentPane.add(btnBuscar);

		JLabel lblNewLabel = new JLabel("DNI Usuario Buscar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(314, 70, 122, 14);
		contentPane.add(lblNewLabel);

		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(289, 36, 15, 216);
		getContentPane().add(scroll);

		JButton btnNewButton = new JButton("Eliminar");
		btnNewButton.addActionListener(new ActionListener() {
			//Elimino la persona que selecciono
			public void actionPerformed(ActionEvent e) {
				try (Connection con = DriverManager.getConnection(url)) {
					logger.info("Conectado a la base de datos para eliminar");
					Usuario u= usuariosDatos.get(seleccionFila);
					int dni = u.getDni();
					String sql = "DELETE from usuario where DNI =?";
					try (PreparedStatement pstmt = con.prepareStatement(sql)) {
						pstmt.setInt(1, dni);
						ResultSet rs = pstmt.executeQuery();
						logger.info("Delete hecho");
						rs.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						logger.log(Level.SEVERE, "No se ha podido realizar la consulta");
					}

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
				}

			}
		});
		btnNewButton.setBounds(314, 174, 112, 23);
		contentPane.add(btnNewButton);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				String texto = (String) table.getModel().getValueAt(row, 3);
				if (texto == textField.getText()) {
					((JComponent) c).setBackground(Color.RED);
					((JComponent) c).setForeground(Color.black);
				} else {
					((JComponent) c).setBackground(Color.white);
					((JComponent) c).setForeground(Color.black);
				}
				return c;
			}
		});
	}

	private static Logger logger = Logger.getLogger(VentanaGestionDeUsuariosPrincipal.class.getName());
	private JTable table;
	private JTextField textField;

	// crea una lista con todos los usuarios y la
	// devuleve
	public static List<Usuario> anyadirUsuarios(List<Usuario> listaUsuarios, String url) {

		try (Connection con = DriverManager.getConnection(url)) {
			logger.info("Conectado a la base de datos para hacer la búsqueda");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			logger.info("Select hecha para sacar los usuarios en la Base de Datos");
			while (rs.next()) {
				Usuario persona = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7));
				listaUsuarios.add(persona);
				logger.info("Usuario creado y agrgado a lista de usuarios");
				listaUsuarios = ordenarUsuariosPuestos(listaUsuarios);
				logger.info("Lista ordenada");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return listaUsuarios;

	}

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

	// creo un comparador para la lista
	static class MiComparador implements Comparator<Usuario> {

		@Override
		public int compare(Usuario a, Usuario b) {

			if (a.getBote() < b.getBote()) {
				return -1;
			} else {
				return 1;
			}
		}

	}

	// ordeno la lista
	public static List<Usuario> ordenarUsuariosPuestos(List<Usuario> usuariosPuesto) {
		Collections.sort(usuariosPuesto, new MiComparador());
		return usuariosPuesto;
	}

	
}
