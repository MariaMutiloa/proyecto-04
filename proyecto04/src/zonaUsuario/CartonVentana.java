package zonaUsuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Carton;
import gestionBD.ConexionBD;
import personas.Usuario;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;
import java.awt.Font;

public class CartonVentana extends JFrame {

	private JPanel contentPane;
	
	private int[][] datosColores;
	private JTable table;
	
	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());


	public CartonVentana(Usuario u) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 240);
		setTitle("Tu carton");
		
		JPanel pInferior = new JPanel();
		pInferior.setLayout(new GridLayout(1,2));
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		JPanel mitad = new JPanel();
		pInferior.add(mitad, BorderLayout.CENTER);
		
		JButton btnBingo = new JButton("BINGO!");
		mitad.add(btnBingo);
		
		
		 
		//AQUI SE VA A VER EL CARTON
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		//RELLENA EL CARTON CON LOS NUMEROS ALEATORIOS
		int[][] miCarton = Carton.dibujarCarton();
		
		//GUARDO CARTON EN BD (carton)
		int idCarton = ConexionBD.cartonNuevo(u.getDni(), 1);//HE PUESTO IDPartida "1", PERO ESTO DESPUES SERA UNA VARIALBE, DEPENDIENDO DE QUÉ PARTIDA ESTÉ ACTIVA	
		
		Carton c = new Carton(idCarton, u.getDni(), 1);	//HE PUESTO IDPartida "1", PERO ESTO DESPUES SERA UNA VARIALBE, DEPENDIENDO DE QUÉ PARTIDA ESTÉ ACTIVA
		
		
		//GUARDO CARTON EN BD (numerocarton)
		ConexionBD.insertarNumerosDelCarton(miCarton, c.getIDCarton());		
		
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

		
		}
	


}


