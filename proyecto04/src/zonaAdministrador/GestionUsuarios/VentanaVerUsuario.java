package zonaAdministrador.GestionUsuarios;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

public class VentanaVerUsuario extends JFrame {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList list;
	private ArrayList<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> model;

	public VentanaVerUsuario() {
		this.list = new JList();
		this.model = new DefaultListModel();
		this.listaUsuarios = new ArrayList<Usuario>();
		this.listaUsuarios = anyadirUsuarios(listaUsuarios);
		Usuario u = new Usuario(0, " ", " ", " ", " ", 0, 0);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList list = new JList();
		list.setBounds(30, 28, 154, 224);
		cargarJList(listaUsuarios);
		contentPane.add(list);

		JButton btnNewButton = new JButton("Ver usuario");
		btnNewButton.setBounds(262, 122, 116, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setEnabled(false);
		// solo cuando el Administrador tiene un Usuario seleccionado deja pulsar el
		// botón para ver sus datos
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					btnNewButton.setEnabled(true);
					Usuario u = (Usuario) list.getSelectedValue();
				} else {
					btnNewButton.setEnabled(false);
				}

			}

			// para poder ir a la Ventana de los datos cuando hace click en el botón Ver
			// Usuario
			public void actionPerformed(ActionEvent e) {
				VentanaDatosUsuario nuevaVentanaDatos = new VentanaDatosUsuario(VentanaVerUsuario.this, u);
				nuevaVentanaDatos.setVisible(true);
				VentanaVerUsuario.this.setVisible(false);

			}
		});

	}

	// crea una lista con todos los usuarios que están registrados en la BD y la
	// devuleve
	public static ArrayList<Usuario> anyadirUsuarios(ArrayList<Usuario> listaUsuarios) {

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				Usuario persona = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getInt(7));
				listaUsuarios.add(persona);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}
		return listaUsuarios;

	}

	// cargo el Jlist que tiene que aparecer a través de la lista que me devuelve el
	// método anterior

	public void cargarJList(ArrayList<Usuario> listaUsuarios) {
		for (Usuario persona : listaUsuarios) {
			model.addElement(persona);
		}
		list.setModel(model);
	}
}
