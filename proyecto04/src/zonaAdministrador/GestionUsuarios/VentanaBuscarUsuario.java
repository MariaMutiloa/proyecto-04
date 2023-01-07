package zonaAdministrador.GestionUsuarios;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import personas.Usuario;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class VentanaBuscarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JList<Usuario> list;
	private List<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> model;
	private String url;

	public VentanaBuscarUsuario() {
		this.url = "jdbc:sqlite:DatosBingo.db";
		this.list = new JList<Usuario>();
		this.model = new DefaultListModel<Usuario>();
		this.listaUsuarios = new ArrayList<Usuario>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		list = new JList<Usuario>();
		list.setBounds(10, 66, 219, 186);
		contentPane.add(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					Usuario u = (Usuario) list.getSelectedValue();
					VentanaVerDatosUsuarioBuscado nuevaVentanaDatos = new VentanaVerDatosUsuarioBuscado(VentanaBuscarUsuario.this, u);
					nuevaVentanaDatos.setVisible(true);
					VentanaBuscarUsuario.this.setVisible(false);

				}

			}

		});


		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 35, 219, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaUsuarios = anyadirUsuarios(listaUsuarios, textFieldNombre, url);
				cargarJList(listaUsuarios);
			}
		});
		
		btnNewButton.setBounds(244, 34, 141, 23);
		contentPane.add(btnNewButton);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(212, 66, 17, 186);
		contentPane.add(scrollBar);
		
		JButton btnNewButton_1 = new JButton("Volver Gestion de Usuarios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipalGestionUsuarios ventanaPrincipal = new VentanaPrincipalGestionUsuarios();
				ventanaPrincipal.setVisible(true);
				VentanaBuscarUsuario.this.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(230, 209, 206, 29);
		contentPane.add(btnNewButton_1);
	}

	private static Logger logger = Logger.getLogger(VentanaVerUsuario.class.getName());

	// carga la lista con los usuarios que est�n en la Base de Datos y coinciden con
	// el nombre que se quiere buscar

	public static List<Usuario> anyadirUsuarios(List<Usuario> listaUsuarios, JTextField text, String url) {

		try (Connection con = DriverManager.getConnection(url)) {
			logger.info("Conectado a la base de datos para hacer la busqueda");
			String nombre = text.getText();
			try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM usuario WHERE Nombre == ?")) {
				pstmt.setString(1, nombre);
				ResultSet rs = pstmt.executeQuery();
				logger.info(
						"Select hecha para sacar los usuarios en la Base de Datos que tengan el nombre que se ha seleccionado");
				while (rs.next()) {
					Usuario persona = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getInt(6), rs.getInt(7));
					listaUsuarios.add(persona);
					logger.info("Usuario encontrtado y agrgado a lista de usuarios");
				}
				if (listaUsuarios.isEmpty()) {
					JOptionPane.showMessageDialog(null,"No se ha encontrado ning�n usuario con ese nombre");
				}
				rs.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				logger.log(Level.SEVERE, "No se ha podido realizar la consulta");

			}

		} catch (SQLException e1) {
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
