package zonaUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Carton;
import gestionBD.ConexionBD;
import login.LogInVentana;
import personas.Usuario;
import zonaAdministrador.VentanaPrincipalAdmin;
import javax.swing.JTable;
import java.awt.Font;

public class UsuarioVentana extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	private static Logger logger = Logger.getLogger(ConexionBD.class.getName());
	

	public UsuarioVentana(Usuario u) {
		setBounds(100, 100, 647, 319);
		setTitle( "Ventana de usuario" );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pSuperior = new JPanel();
		pSuperior.setSize(150, 50);
		pSuperior.setLayout(new GridLayout(1,4));
		getContentPane().add(pSuperior, BorderLayout.NORTH);

		
		JLabel lblBienvenido = new JLabel("¡Bienvenidx " + u.getNombre() + "!");
		pSuperior.add(lblBienvenido);
		
		JLabel lblCartera = new JLabel("Cartera: "+u.getBote()+" €");
		pSuperior.add(lblCartera);
		
		JButton btnSalir = new JButton("Salir");
		pSuperior.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogInVentana parent = new LogInVentana();
				parent.setVisible(true);
				UsuarioVentana.this.dispose();	
			}
		});
		
		JPanel pInferior = new JPanel();
		pInferior.setLayout(new GridLayout(1,2));
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		JPanel infIzquierda = new JPanel();
		pInferior.add(infIzquierda, BorderLayout.WEST);
		
		JButton btnEstadisticas = new JButton("Ver estadisticas");
		infIzquierda.add(btnEstadisticas);
		
		//METER VER ESTADISTICAS
		
		JPanel pInfDerecha = new JPanel();
		pInferior.add(pInfDerecha, BorderLayout.EAST);
		
		JButton btnBingo = new JButton("BINGO!");
		pInfDerecha.add(btnBingo, BorderLayout.WEST);
				
		
		//PANEL IZQUIERDA
		//aqui va a salir el número en grande y también podremos ver qué numeros han salido hasta ahora
		JPanel pIzquierda = new JPanel();
		getContentPane().add(pIzquierda, BorderLayout.WEST);
		
		//AQUI VA A APARECER EL NUMERO Q SACA EL ADMINISTRADOR
		JLabel lblNumero = new JLabel("New label");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 20));
		pIzquierda.add(lblNumero, BorderLayout.CENTER);
		
			
		
		//PANEL CENTRAL DERECHA
		//aquí tendremos nuestro carton con los numeros
		//hay posibilidad de clickar en el numero y se cambie de color 
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		//CARTON --> hay que general el carton con los numeros
		
		Carton c = new Carton(u.getDni(), 1);
		int[][] miCarton = Carton.dibujarCarton();
		//GUARDO CARTON EN BD
		ConexionBD.insertarCartonEnBD(miCarton, c.getIDCarton());
		logger.info("Carton guardado correctamente en la BD");
		
		
        MyTableModel tableModel = new MyTableModel(miCarton);
        
		table = new JTable(tableModel);
		pCentral.add(table);
		
		
		
		
		
	}

}
