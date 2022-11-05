package zonaGestionUsuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;

public class VentanaVerUsuario extends JFrame {

	private JPanel contentPane;
	private JList list;
	private ArrayList<String> listaUsuarios;
	private DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaVerUsuario frame = new VentanaVerUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaVerUsuario() {
		this.list = list;
		this.model = new DefaultListModel();
		this.listaUsuarios = anyadirUsuarios();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JList list = new JList();
		list.setBounds(30, 28, 154, 224);
		cargarJList();
		contentPane.add(list);

		JButton btnNewButton = new JButton("Ver usuario");
		btnNewButton.setBounds(262, 122, 116, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setEnabled(false);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) {
					btnNewButton.setEnabled(true);
				} else {
					btnNewButton.setEnabled(false);
				}

			}
		});

	}

	public static ArrayList<String> anyadirUsuarios() {

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			ArrayList<String> listaUsuarios = new ArrayList<String>();
			String nombre = new String();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				nombre = rs.getString(2);
				listaUsuarios.add(nombre);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsuarios;

	}

	public void cargarJList() {
		for (String nombreUsuario : listaUsuarios) {
			model.addElement(nombreUsuario);
		}
		list.setModel(model);
	}
}
