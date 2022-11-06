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

	public VentanaVerUsuario() {
		this.list = new JList();
		this.model = new DefaultListModel();
		this.listaUsuarios = anyadirUsuarios(listaUsuarios);
		
		
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

	public static ArrayList<String> anyadirUsuarios(ArrayList<String> listaUsuarios) {

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			String nombre;
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
