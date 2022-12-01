package zonaAdministrador.Estadisticas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionBD.ConexionBD;
import login.LogInVentana;
import zonaAdministrador.VentanaPrincipalAdmin;
import zonaAdministrador.partidaNueva.ConfiguracionPart;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VerEstadisticas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEstadisticas;
	private JLabel lblNewLabel_2;
	private JTextField textField_1;

	public VerEstadisticas(VentanaPrincipalAdmin parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(222, 64, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Números mas veces repetidos:");
		lblNewLabel.setBounds(10, 69, 200, 16);
		contentPane.add(lblNewLabel);

		lblEstadisticas = new JLabel("ESTADISTICAS");
		lblEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadisticas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEstadisticas.setEnabled(false);
		lblEstadisticas.setBounds(96, 22, 200, 16);
		contentPane.add(lblEstadisticas);

		lblNewLabel_2 = new JLabel("Bote máximo");
		lblNewLabel_2.setBounds(10, 130, 200, 16);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBounds(222, 125, 130, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
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
		
		
	}
	
	
	

	public void masUsado() {

		int[] ai = {};
		List<Integer> numeros = ConexionBD.anyadirNum();
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
				// Primera vez que encontramos un número con esa cantidad de ocurrencias
				// Creamos el conjunto de números para esa ocurrencia añadimos el número

				s = new TreeSet();
				s.add(numeroArray);
				h2.put(ocurrenciasNumArray, s);
			} else {
				s.add(numeroArray);
			}
		}
		textField.setText("Los números " + (TreeSet) h2.get(maxOcurrencias) + " tienen ocurrencias máximas:"
				+ maxOcurrencias.intValue());
	}

	public void boteMaximo() {

		String a = "";
		String b = "Son" + ConexionBD.getBoteMax() + "euros";
		a.replaceAll(a, b);
		textField_1.setText("Son" + a + "euros");
	}

}
