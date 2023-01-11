package zonaAdministrador.Estadisticas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionBD.GestionEstadisticas;
import zonaAdministrador.VentanaPrincipalAdmin;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VerEstadisticas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel textNumMaxRep;
	private JLabel lblEstadisticas;
	private JLabel lblNewLabel_2;
	private JLabel txtBoteMax;
	private JLabel txtUsuarioCartera;
	
	private static String bd = "jdbc:sqlite:DatosBingo.db";

	public VerEstadisticas(VentanaPrincipalAdmin parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textNumMaxRep = new JLabel();
		textNumMaxRep.setText(String.valueOf(GestionEstadisticas.numMasVecesCantado(bd)));
		textNumMaxRep.setBounds(222, 64, 130, 26);
		contentPane.add(textNumMaxRep);

		JLabel lblNewLabel = new JLabel("Números mas veces cantado:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 69, 200, 16);
		contentPane.add(lblNewLabel);

		lblEstadisticas = new JLabel("ESTADISTICAS");
		lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEstadisticas.setEnabled(false);
		lblEstadisticas.setBounds(96, 22, 200, 16);
		contentPane.add(lblEstadisticas);

		lblNewLabel_2 = new JLabel("Bote mï¿½ximo de partida:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 130, 200, 16);
		contentPane.add(lblNewLabel_2);

		txtBoteMax = new JLabel();
		txtBoteMax.setText(String.valueOf(GestionEstadisticas.boteMaxPartida(bd)));
		txtBoteMax.setBounds(222, 125, 130, 26);
		contentPane.add(txtBoteMax);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(363,222,63,30);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				parent.setVisible(true);
				VerEstadisticas.this.dispose();
				
			}
			
		});
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Usuario con mayor cartera:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(10, 144, 200, 16);
		contentPane.add(lblNewLabel_2_1);
		
		txtUsuarioCartera = new JLabel();
		txtUsuarioCartera.setText(GestionEstadisticas.usuarioMayorCartera(bd));
		txtUsuarioCartera.setBounds(222, 138, 204, 26);
		contentPane.add(txtUsuarioCartera);
		btnVolver.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				parent.setVisible(true);
				VerEstadisticas.this.dispose();
				
			}
			
		});
	}
	
	
	

	public void masUsado() {

		int[] ai = {};
		List<Integer> numeros = GestionEstadisticas.anyadirNum(bd);
		for (Integer integer : numeros) {
			for (int i = 0; i < ai.length; i++) {
				ai[i] = integer;
			}

		}
		int ocurrencias = 0;
		HashMap h = new HashMap(); // numero de veces que sale

		for (int i = 0; i < ai.length; i++) {
			if ((Integer) h.get(String.valueOf(ai[i])) == null) {
				// Si es la primera
				h.put(String.valueOf(ai[i]), new Integer(1));
			} else {
				ocurrencias = ((Integer) h.get(String.valueOf(ai[i]))).intValue();
				h.put(String.valueOf(ai[i]), new Integer(++ocurrencias));
			}
		}

		Iterator it = h.keySet().iterator();
		HashMap h2 = new HashMap(); // para coger las veces maximas
		TreeSet s = null;
		Integer maxOcurrencias = new Integer(0);

		while (it.hasNext()) {
			String numeroArray = (String) it.next();
			Integer ocurrenciasNumArray = (Integer) h.get(numeroArray);

			if (ocurrenciasNumArray.intValue() > maxOcurrencias.intValue()) {
				maxOcurrencias = ocurrenciasNumArray;
			}

			s = (TreeSet) h2.get(ocurrenciasNumArray);
			if (s == null) {
				// Primera vez que encontramos un nï¿½mero con esa cantidad de ocurrencias
				// Creamos el conjunto de nï¿½meros para esa ocurrencia aï¿½adimos el nï¿½mero

				s = new TreeSet();
				s.add(numeroArray);
				h2.put(ocurrenciasNumArray, s);
			} else {
				s.add(numeroArray);
			}
		}
		textNumMaxRep.setText("Los nï¿½meros " + (TreeSet) h2.get(maxOcurrencias) + " tienen ocurrencias mï¿½ximas:"
				+ maxOcurrencias.intValue());
	}

	public void boteMaximo() {

		String a = "";
		String b = "Son" + GestionEstadisticas.getBoteMax(bd) + "euros";
		a.replaceAll(a, b);
		txtBoteMax.setText("Son" + a + "euros");
	}

}
