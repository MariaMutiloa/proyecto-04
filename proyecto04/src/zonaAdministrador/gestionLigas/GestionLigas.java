package zonaAdministrador.gestionLigas;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import gestionBD.GestionLigasYEstadisticas;
import zonaAdministrador.VentanaPrincipalAdmin;

public class GestionLigas extends JFrame{

	private JPanel contentPane;
	private JComboBox<Integer> ligas = new JComboBox<Integer>(GestionLigasYEstadisticas.getLigas());
	private JTable tablaLigas = new JTable(new NuestraTabla(GestionLigasYEstadisticas.getUsuariosLiga((int) ligas.getSelectedItem())));
	private VentanaPrincipalAdmin parent;
	
	public GestionLigas(VentanaPrincipalAdmin parent) {
		this.parent = parent;
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JPanel superior = new JPanel();
		JButton volver = new JButton("Volver");
		superior.add(volver, BorderLayout.CENTER);
		
		
		JPanel derecha = new JPanel();
		JScrollPane tabla = new JScrollPane(ligas);
		derecha.add(tabla, BorderLayout.NORTH);
		
		JButton refrescar = new JButton("Actualizar las ligas");
		
		derecha.add(refrescar,BorderLayout.SOUTH);
		contentPane.add(superior, BorderLayout.NORTH);
		contentPane.add(derecha, BorderLayout.EAST);
		contentPane.add(tablaLigas,BorderLayout.WEST);
		
	}
	
}
