package zonaAdministrador.partidaNueva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;

import java.awt.BorderLayout;

public class ConfiguracionPart extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Partida partidaActual = new Partida();


	public ConfiguracionPart(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel superior = new JPanel();
			JLabel lblTitulo = new JLabel ("Configuración de partida ");
			superior.add(lblTitulo, BorderLayout.WEST);
	
	

		JLabel participantes = new JLabel(Integer.toString((GestionPartidas.numeroParticipantes(IDPartida)).size())+ " participantes conectados");
		contentPane.add(participantes);
		
		JLabel boteL = new JLabel("Bote de linea");
		contentPane.add(boteL);
		
		JTextField TFBoteL = new JTextField();
		contentPane.add(TFBoteL);
		
		JButton boteLButton = new JButton("Calcular Bote Linea");
		contentPane.add(boteLButton);
		boteLButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				participantes.setText(Integer.toString((GestionPartidas.numeroParticipantes(IDPartida)).size()));
				float bote = calculoBoteL();
				TFBoteL.setText(Float.toString(bote));
				
			}
			
		});
		
		JLabel boteB = new JLabel("Bote de bingo");
		contentPane.add(boteL);
		
		JTextField TFBoteB = new JTextField();
		contentPane.add(TFBoteB);
		
	
		JButton boteBButton = new JButton("Calcular Bote Bingo");
		contentPane.add(boteBButton);
		boteBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				participantes.setText(Integer.toString((GestionPartidas.numeroParticipantes(IDPartida)).size()));
				float bote = calculoBoteB();
				TFBoteB.setText(Float.toString(bote));
				
			}
			
		});
		
		JTextField TFLiga = new JTextField();
		contentPane.add(TFBoteB);
		
		
		JButton empezar = new JButton("Empezar partida");
		contentPane.add(empezar);
		boteBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float boteL = Integer.parseInt(TFBoteL.getText()); //Dependera de la gente conectada
				float boteB = Integer.parseInt(TFBoteB.getText());
				int liga = Integer.parseInt(TFLiga.getText()); //Despues se usará mismo componente que se use en la visialización de ligas
				
				GestionPartidas.nueva(IDPartida, boteB, boteL, liga);
				PartidaNueva nuevaPar = new PartidaNueva(IDPartida, boteB, boteL, liga); 
				nuevaPar.setVisible(true);
				ConfiguracionPart.this.dispose();
				
			}
			
		});
		
		setContentPane(contentPane);
		
	}


	private float calculoBoteB() { //Igual con un documento properties???
		// TODO Auto-generated method stub
		return 0;
	}


	private float calculoBoteL() {
		// TODO Auto-generated method stub
		return 0;
	}

}
