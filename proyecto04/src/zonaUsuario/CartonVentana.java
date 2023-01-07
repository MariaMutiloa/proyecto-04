package zonaUsuario;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.ConexionBD;
import gestionBD.GestionUsuarios;
import personas.Usuario;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import java.awt.Font;

public class CartonVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	
	private int[][] datosColores;
	private JTable table;
	
	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());


	public CartonVentana(Usuario u, Partida p) {
		setBounds(100, 100, 576, 240);
		setTitle("Tu carton");
				
		 
		//AQUI SE VA A VER EL CARTON
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		//RELLENA EL CARTON CON LOS NUMEROS ALEATORIOS
		int[][] miCarton = Carton.dibujarCarton();
		
		//GUARDO CARTON EN BD (carton)
		int idCarton = GestionUsuarios.cartonNuevo(u.getDni(), p.getIDPartida());
		
		Carton c = new Carton(idCarton, u.getDni(), p.getIDPartida());	
		
		
		//GUARDO CARTON EN BD (numerocarton)
		GestionUsuarios.insertarNumerosDelCarton(miCarton, c.getIDCarton());		
		
        MyTableModel tableModel = new MyTableModel(miCarton);
        datosColores = new int[3][5];
        table = new JTable(tableModel);
        table.setFont(new Font("Tahoma", Font.PLAIN, 24));
        table.setRowHeight(50);
        table.setDefaultRenderer(Object.class, new RendererTabla(datosColores));
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			    // Get the index of the selected row
			    int row = table.getSelectedRow();

			    // Get the index of the selected column
			    int col = table.getSelectedColumn();
			    if (datosColores[row][col] == 1) {
			    	datosColores[row][col] = 0;
			    }else {
			    	datosColores[row][col] = 1;
			    }
			    
			    
			    table.repaint();
				}
			});
		pCentral.add(table);

		
		JPanel pInferior = new JPanel();
		pInferior.setLayout(new GridLayout(1,2));
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		JPanel mitad = new JPanel();
		pInferior.add(mitad, BorderLayout.CENTER);
		
		JButton btnBingo = new JButton("BINGO!");
		btnBingo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.setBingo(1);
				GestionUsuarios.actualizarBingoBD(c.getIDCarton());
				JOptionPane.showMessageDialog(null, "Se esta comprobando el bingo","Espere a que el administrador compruebe su bingo", JOptionPane.WARNING_MESSAGE);
				 
				
			}
		});
		mitad.add(btnBingo);
		
		
		}
	


}


