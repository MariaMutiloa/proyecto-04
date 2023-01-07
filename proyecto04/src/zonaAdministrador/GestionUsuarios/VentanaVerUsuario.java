package zonaAdministrador.GestionUsuarios;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personas.Usuario;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class VentanaVerUsuario extends JFrame {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Usuario> list;
	private List<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> model;
	private String url;

	public VentanaVerUsuario() {

		this.url = "jdbc:sqlite:DatosBingo.db";
		this.list = new JList<Usuario>();
		this.model = new DefaultListModel<Usuario>();
		this.listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios = anyadirUsuarios(listaUsuarios, url);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<Usuario>();
		list.setBounds(30, 28, 154, 224);
		cargarJList(listaUsuarios);
		contentPane.add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					Usuario u = (Usuario) list.getSelectedValue();
					VentanaDatosUsuario nuevaVentanaDatos = new VentanaDatosUsuario(VentanaVerUsuario.this, u);
					nuevaVentanaDatos.setVisible(true);
					VentanaVerUsuario.this.setVisible(false);

				}

			}

		});

		JButton btnNewButton = new JButton("Volver Gestion de usuarios");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalGestionUsuarios nuevaVentanaGestion = new VentanaPrincipalGestionUsuarios();
				nuevaVentanaGestion.setVisible(true);
				VentanaVerUsuario.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(262, 122, 116, 23);
		contentPane.add(btnNewButton);

	}

	private static Logger logger = Logger.getLogger(VentanaVerUsuario.class.getName());

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
				listaUsuarios= ordenarUsuariosNombre(listaUsuarios);
				logger.info("Lista ordenada");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");

		}
		return listaUsuarios;

	}

	// cargo el Jlist que tiene que aparecer a través de la lista que me devuelve el
	// método anterior

	public void cargarJList(List<Usuario> listaUsuarios) {
		for (Usuario persona : listaUsuarios) {
			model.addElement(persona);
		}
		list.setModel(model);
	}
	
	// creo un comparador para la lista
		static class MiComparador implements Comparator<Usuario> {

			@Override
			public int compare(Usuario a, Usuario b) {

				return a.getNombre().compareTo(b.getNombre());
			}

		}

		// ordeno la lista
		public static List<Usuario> ordenarUsuariosNombre(List<Usuario> usuariosPuesto) {
			Collections.sort(usuariosPuesto, new MiComparador());
			return usuariosPuesto;
		}
}
