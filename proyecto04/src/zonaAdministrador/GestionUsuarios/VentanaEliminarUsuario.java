package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personas.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEliminarUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JList list;
	private List<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> model;

	/**
	 * Create the frame.
	 */
	public VentanaEliminarUsuario() {
		this.list = new JList();
		this.model = new DefaultListModel();
		this.listaUsuarios = new ArrayList<Usuario>();

		Usuario u = new Usuario(0, " ", " ", " ", " ", 0, 0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel labelNombre = new JLabel("Nombre");
		labelNombre.setBounds(72, 45, 49, 14);
		contentPane.add(labelNombre);

		textNombre = new JTextField();
		textNombre.setBounds(51, 69, 96, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);

		JButton botonBuscar = new JButton("Buscar");

		botonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaUsuarios = anyadirUsuarios(listaUsuarios, textNombre);
				cargarJList(listaUsuarios);
			}
		});
		botonBuscar.setBounds(263, 65, 89, 23);
		contentPane.add(botonBuscar);

		JList list = new JList();
		list.setBounds(46, 114, 113, 138);
		cargarJList(listaUsuarios);
		contentPane.add(list);

		JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setEnabled(false);
		// solo cuando el Administrador tiene un Usuario seleccionado deja pulsar el
		// bot�n para eliminar
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					botonEliminar.setEnabled(true);
					Usuario u = (Usuario) list.getSelectedValue();
				} else {
					botonEliminar.setEnabled(false);
				}

			}

			// cuando se selecciona un usuario y se da a eliminar se elimina de la Base de
			// Datos
			public void actionPerformed(ActionEvent e) {
				try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
					logger.info("Conectado a la base de datos para eliminar");
					int dni = u.getDni();
					String sql = "DELETE from usuario where Usuario =?";
					try (PreparedStatement pstmt = con.prepareStatement(sql)) {
						pstmt.setInt(1, dni);
						ResultSet rs = pstmt.executeQuery();
						logger.info("Delete hecho");
						rs.close();
					} catch (SQLException e1) {
						// e.printStackTrace();
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						logger.log(Level.SEVERE, "No se ha podido realizar la consulta");
					}

				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
				}
			}

		});
		botonEliminar.setBounds(263, 161, 89, 23);
		contentPane.add(botonEliminar);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(142, 114, 17, 138);
		contentPane.add(scrollBar);
	}

	private static Logger logger = Logger.getLogger(VentanaVerUsuario.class.getName());

	// carga la lista con los usuarios que est�n en la Base de Datos y coinciden con
	// el nombre que se quiere buscar

	public static List<Usuario> anyadirUsuarios(List<Usuario> listaUsuarios, JTextField text) {

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			logger.info("Conectado a la base de datos para hacer la busqueda");
			String nombre = text.getSelectedText();
			String sql = "select * from usuario where Usuario >=?";
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, nombre);
				ResultSet rs = pstmt.executeQuery();
				logger.info(
						"Select hecha para sacar los usuarios en la Base de Datos que tengan el nombre que se ha seleccionado");
				while (rs.next()) {
					Usuario persona = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getInt(6), rs.getInt(7));
					listaUsuarios.add(persona);
					logger.info("Usuario creado y agrgado a lista de usuarios");
				}
				rs.close();
			} catch (SQLException e) {
				// e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido realizar la consulta");

			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			logger.log(Level.SEVERE, "No se ha podido conectar a la base de datos");
		}
		return listaUsuarios;
	}

	// carga la lista con el array que devuelve el m�todo anterior
	public void cargarJList(List<Usuario> listaUsuarios) {
		for (Usuario persona : listaUsuarios) {
			model.addElement(persona);
		}
		list.setModel(model);
		logger.info("Lista cargada");
	}
}
