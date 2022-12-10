package zonaUsuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Carton;
import elementosOrganizacion.Partida;
import gestionBD.ConexionBD;
import login.LogInVentana;
import personas.Usuario;
import zonaAdministrador.VentanaPrincipalAdmin;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		//PANEL CENTRAL DERECHA
				//aquí tendremos nuestro carton con los numeros
				//hay posibilidad de clickar en el numero y se cambie de color 
		JPanel pCentral = new JPanel();
		getContentPane().add(pCentral, BorderLayout.CENTER);
		
		//PANEL SUPERIOR
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
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//click en boton --> crea carton
				
				//CARTON --> hay que general el carton con los numeros
				
				Carton c = new Carton(u.getDni(), 1);	//HE PUESTO IDPartida "1", PERO ESTO DESPUES SERA UNA VARIALBE, DEPENDIENDO DE QUÉ PARTIDA ESTÉ ACTIVA
				
				//RELLENA EL CARTON CON LOS NUMEROS ALEATORIOS
				int[][] miCarton = Carton.dibujarCarton();
				
				//GUARDO CARTON EN BD (carton)
				ConexionBD.guardarInfoCartonEnBD(c);
				
				//GUARDO CARTON EN BD (numerocarton)
				ConexionBD.insertarCartonEnBD(miCarton, c.getIDCarton());		
				
		        MyTableModel tableModel = new MyTableModel(miCarton);
		        
				table = new JTable(tableModel);
				pCentral.add(table);
				
				
			}
		});
		pInfDerecha.add(btnJugar);
		btnJugar.setEnabled(false);
		
		JButton btnBingo = new JButton("BINGO!");
		btnBingo.setEnabled(false);
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AVISA AL ADMINISTRADOR EL BINGO
			}
		});
		pInfDerecha.add(btnBingo, BorderLayout.WEST);
				
		
		//PANEL IZQUIERDA
		//aqui va a salir el número en grande y también podremos ver qué numeros han salido hasta ahora
		JPanel pIzquierda = new JPanel();
		getContentPane().add(pIzquierda, BorderLayout.WEST);
		
		//AQUI VA A APARECER EL NUMERO Q SACA EL ADMINISTRADOR
		JLabel lblNumero = new JLabel("New label");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 20));
		pIzquierda.add(lblNumero, BorderLayout.CENTER);
		
			
		
		
		
		
		/*
		 * TIENE QUE HABER UNA COMPROBARCION CON LA BASE DE DATOS Y MIRAR SI HAY ALGUNA PARTIDA ACTIVA
		 * SI NO HAY PARTIDA ACTIVA --> NO PUEDE COMPRAR NINGUN CARTON
		 * SI HAY PARTIDA ACTIVA --> BOTON JUGAR Y APARECE CARTON
		 */
		
		
		Partida p = ConexionBD.buscarPartidaActiva();
		if(p==null) {
			btnJugar.setEnabled(false);
			btnBingo.setEnabled(false);
			JOptionPane.showMessageDialog(null, "No hay ninguna partida activa. Inténtelo más tarde.","", JOptionPane.WARNING_MESSAGE);
		}else {
			//boton jugar 
			btnJugar.setEnabled(true);
			
		}
		
//		//CARTON --> hay que general el carton con los numeros
//		
//		Carton c = new Carton(u.getDni(), 1);	//HE PUESTO IDPartida "1", PERO ESTO DESPUES SERA UNA VARIALBE, DEPENDIENDO DE QUÉ PARTIDA ESTÉ ACTIVA
//		
//		//RELLENA EL CARTON CON LOS NUMEROS ALEATORIOS
//		int[][] miCarton = Carton.dibujarCarton();
//		
//		//GUARDO CARTON EN BD (carton)
//		ConexionBD.guardarInfoCartonEnBD(c);
//		
//		//GUARDO CARTON EN BD (numerocarton)
//		ConexionBD.insertarCartonEnBD(miCarton, c.getIDCarton());		
//		
//        MyTableModel tableModel = new MyTableModel(miCarton);
//        
//		table = new JTable(tableModel);
//		pCentral.add(table);
		
		
		
		
		
	}

}
