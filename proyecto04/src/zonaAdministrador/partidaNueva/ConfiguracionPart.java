package zonaAdministrador.partidaNueva;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputFilter.Config;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import elementosOrganizacion.Partida;
import gestionBD.GestionPartidas;
import login.LogInVentana;
import personas.Administrador;
import zonaAdministrador.VentanaPrincipalAdmin;

import java.awt.BorderLayout;
import java.awt.Component;

public class ConfiguracionPart extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Partida partidaActual = new Partida();


	public ConfiguracionPart(VentanaPrincipalAdmin parent, Administrador admin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel superior = new JPanel();
			JLabel lblTitulo = new JLabel ("Configuración de partida ");
			superior.add(lblTitulo, BorderLayout.WEST);
			JButton btnVolver = new JButton("Volver");
			btnVolver.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					LogInVentana nueva = new LogInVentana();
					nueva.setVisible(true);
					ConfiguracionPart.this.dispose();
					
				}
				
			});
	
		JPanel central = new JPanel();
			JPanel centralArriba = new JPanel();	
				centralArriba.add(new JLabel("Bote Linea"), BorderLayout.WEST);
				JTextField txtLinea = new JTextField();
				centralArriba.add(txtLinea, BorderLayout.EAST);
			JPanel centralAbajo = new JPanel();	
				centralAbajo.add(new JLabel("Bote Bingo"), BorderLayout.WEST);
				JTextField txtBingo = new JTextField();
				centralAbajo.add(txtBingo, BorderLayout.EAST);
				centralArriba.setBounds(100, 100, 200, 50);
			central.add(centralArriba, BorderLayout.NORTH);	
			central.add(centralAbajo, BorderLayout.SOUTH);	

			JPanel inferior = new JPanel(); 
				JPanel inferiorDerecha = new JPanel();
					JButton btnEmpezar = new JButton("Empezar Partida");
					btnEmpezar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
				
							PartidaNueva nuevaVentana = new PartidaNueva(partidaActual);
							nuevaVentana.setVisible(true);
							ConfiguracionPart.this.dispose();
						}
					});
					inferiorDerecha.add(btnEmpezar, BorderLayout.WEST);
				JPanel inferiorIzquierda = new JPanel();
					inferiorIzquierda.add(new JLabel ("Participantes conectados"), BorderLayout.NORTH);
					JLabel lblNumero = new JLabel("");
					//(partidaActual.getParticipantes()).size())
					lblNumero.setIcon(imagenNumero(62));
					inferiorIzquierda.add(lblNumero, BorderLayout.CENTER);
					JButton  btbVerParticipantes = new JButton("Ver Participantes");
					
					inferiorIzquierda.add(btbVerParticipantes, BorderLayout.SOUTH);
				inferior.add(inferiorDerecha, BorderLayout.WEST);	
				inferior.add(inferiorIzquierda, BorderLayout.EAST);	

				
			contentPane.add(superior,BorderLayout.NORTH);	
			contentPane.add(central,BorderLayout.CENTER);	
			contentPane.add(inferior,BorderLayout.SOUTH);	
			setContentPane(contentPane);
	}


	private Icon imagenNumero(int numero) {
		Icon icono = null;
		if(numero > 9) {
			icono = new ImageIcon(getClass().getResource("/otro.jpg"));
		}else if (numero == 0){
			icono = new ImageIcon(getClass().getResource("/0.jpeg"));
		}else {
			icono = new ImageIcon(getClass().getResource("/"+String.valueOf(numero) + ".jpg") );
		}
		icono = ajustarAEspacio(icono);
		return icono;
	}
	
	private Icon ajustarAEspacio(Icon icono) {
		return null; 
	}
}
