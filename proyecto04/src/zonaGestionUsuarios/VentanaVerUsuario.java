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

import personas.Usuario;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;

public class VentanaVerUsuario extends JFrame {

	private JPanel contentPane;
	private JList list;
	private ArrayList<Usuario> listaUsuarios;
	private DefaultListModel<Usuario> model;

	public VentanaVerUsuario() {
		this.list = new JList();
		this.model = new DefaultListModel();
		this.listaUsuarios= new ArrayList<Usuario>();
		this.listaUsuarios = anyadirUsuarios(listaUsuarios);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
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

	public static ArrayList<Usuario> anyadirUsuarios(ArrayList<Usuario> listaUsuarios) {

		try (Connection con = DriverManager.getConnection("jdbc:sqlite:DatosBingo.db")) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");
			while (rs.next()) {
				Usuario persona= new Usuario(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
				listaUsuarios.add(persona);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsuarios;

	}

	public void cargarJList(ArrayList<Usuario> listaUsuarios) {
		for (Usuario persona : listaUsuarios) {
			model.addElement(persona);
		}
		list.setModel(model);
	}
}
