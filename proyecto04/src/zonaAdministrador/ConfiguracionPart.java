package zonaAdministrador;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gestionBD.GestionPartidas;
import personas.Administrador;
import java.awt.FlowLayout;

public class ConfiguracionPart extends JFrame {

	private JPanel contentPane;


	public ConfiguracionPart(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel participantes = new JLabel(Integer.toString(GestionPartidas.numeroParticipantes())+ " participantes conectados");
		contentPane.add(participantes);
		
		JTextField TFBoteL = new JTextField();
		contentPane.add(TFBoteL);
		
		JButton boteLButton = new JButton("Calcular Bote Linea");
		contentPane.add(boteLButton);
		boteLButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				participantes.setText(Integer.toString(GestionPartidas.numeroParticipantes()));
				float bote = calculoBoteL();
				TFBoteL.setText(Float.toString(bote));
				
			}
			
		});
		
		JTextField TFBoteB = new JTextField();
		contentPane.add(TFBoteB);
		
		int numeroPartida = 1; //Va a tener que ser automatico, en teoria ocn SQLite
		JLabel numPartida = new JLabel(Integer.toString(numeroPartida));
		contentPane.add(numPartida);
		
		JButton boteBButton = new JButton("Calcular Bote Bingo");
		contentPane.add(boteBButton);
		boteBButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				participantes.setText(Integer.toString(GestionPartidas.numeroParticipantes(numPartida)));
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
				int liga = Integer.parseInt(TFLiga.getText()); //Despues se usar� mismo componente que se use en la visializaci�n de ligas
				
				int numeroPartida = GestionPartidas.nueva(numPartida, boteB, boteL, liga);
				PartidaNueva nuevaPar = new PartidaNueva(numPartida, boteB, boteL, liga); 
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
