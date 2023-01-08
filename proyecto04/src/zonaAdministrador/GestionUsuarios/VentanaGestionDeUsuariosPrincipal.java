package zonaAdministrador.GestionUsuarios;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import gestionBD.GestionUsuarios;
import personas.Administrador;
import personas.UsuarioExtendido;
import zonaAdministrador.VentanaPrincipalAdmin;

public class VentanaGestionDeUsuariosPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(VentanaGestionDeUsuariosPrincipal.class.getName());

	private JTable tablaUsuarios;
	private List<UsuarioExtendido> usuarios = GestionUsuarios.getAllUsuarios();
	private JTextField buscador;
	private VentanaPrincipalAdmin parent;
	private Administrador admin;
	private RendererTablaU renderer;

	public VentanaGestionDeUsuariosPrincipal(VentanaPrincipalAdmin parent, Administrador a) {
		this.parent = parent;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tablaUsuarios = new JTable();
		add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);
		setSize(800, 600);
		setVisible(true);

		renderer = new RendererTablaU();
		tablaUsuarios.setDefaultRenderer(Object.class, renderer);
		tablaUsuarios.setModel(new TablaUsuarios(usuarios));
		
		JPanel izquierda = new JPanel();
		JLabel buscadorDNI = new JLabel("Busca por DNI de usuario: ");
		buscador = new JTextField();
		buscador.setColumns(15);

		buscador.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				selectRows(buscador.getText());
				tablaUsuarios.repaint();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				selectRows(buscador.getText());
				tablaUsuarios.repaint();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				selectRows(buscador.getText());
				tablaUsuarios.repaint();
			}
		});
		
		JButton eliminar = new JButton("Eliminar usuario en rojo");
		eliminar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GestionUsuarios.eliminar(Integer.parseInt(buscador.getText()));
					usuarios = GestionUsuarios.getAllUsuarios();
					tablaUsuarios.repaint();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "No hay coincidencias", "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
				
			}
			
		});
		
		izquierda.add(buscadorDNI, BorderLayout.NORTH);
		izquierda.add(buscador, BorderLayout.CENTER);
		izquierda.add(eliminar, BorderLayout.SOUTH);
		add(izquierda, BorderLayout.WEST);

		
	}

	public void selectRows(String selectStr) {
		renderer.setSearch(selectStr);
	}

}
