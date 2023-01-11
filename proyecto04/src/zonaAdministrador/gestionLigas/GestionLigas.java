package zonaAdministrador.gestionLigas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import gestionBD.GestionLigasBD;
import zonaAdministrador.VentanaPrincipalAdmin;

public class GestionLigas extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String bd = "jdbc:sqlite:DatosBingo.db";
	
	private JPanel contentPane;
	private JComboBox<Integer> ligas = new JComboBox<Integer>(GestionLigasBD.getLigas(bd));
	private JTable tablaLigas = new JTable(new TablaLigas(GestionLigasBD.getUsuariosLiga((int) ligas.getSelectedItem(), bd)));
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
		volver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				GestionLigas.this.dispose();
				
			}
			
		});
		
		
		JPanel derecha = new JPanel();
		JScrollPane tabla = new JScrollPane(ligas);
		derecha.add(tabla, BorderLayout.NORTH);
		ligas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tablaLigas.setModel(new TablaLigas(GestionLigasBD.getUsuariosLiga((int) ligas.getSelectedItem(), bd)));
			}
			
		});
		
		JButton refrescar = new JButton("Actualizar las ligas");
		refrescar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GestionLigasBD.actualizarLigas(bd);
				ligas = new JComboBox<Integer>(GestionLigasBD.getLigas(bd));
				tablaLigas.setModel(new TablaLigas(GestionLigasBD.getUsuariosLiga((int) ligas.getSelectedItem(), bd)));
				
			}
			
		});
		
		derecha.add(refrescar,BorderLayout.SOUTH);
		contentPane.add(superior, BorderLayout.NORTH);
		contentPane.add(derecha, BorderLayout.EAST);
		contentPane.add(tablaLigas,BorderLayout.WEST);
		
	}
	
}
