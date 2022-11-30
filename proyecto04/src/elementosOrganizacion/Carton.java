package elementosOrganizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import gestionBD.ConexionBD;
import personas.Usuario;

public class Carton {

	private int IDCarton;
	private int IDUsuario;
	private int IDPartida;
	private Usuario propietario;
	
	//private static List<Integer> listaNumeros; 
	
	private static Logger logger = Logger.getLogger(Carton.class.getName());

	public Carton(int IDCarton, int IDUsuario, int IDPartida) {
		this.IDCarton = IDCarton;
		this.IDUsuario = IDUsuario;
		this.IDPartida = IDPartida;
		this.propietario = ConexionBD.buscarUsuarioPorID(IDUsuario);
	}

	// METODO GUARDA LOS NUMEROS DEL CARTON EN UNA LISTA
	public static int[][] dibujarCarton() {

		List<Integer> numerosGenerados = generarCarton();
		int[][] carton = new int[3][5];

		int numfilas = carton.length;
		int numcolumnas = carton[0].length;
		int index = 0;

		for (int i = 0; i < numfilas; i++) {
			for (int j = 0; j < numcolumnas; j++) {
				int numero = numerosGenerados.get(index);
				carton[i][j] = numero;
				// System.out.println(carton[i][j]);
				index++;
			}
		}
		return carton;
	}

	public static List<Integer> generarCarton() {
		List<Integer> numerosGenerados = new ArrayList<>();

		for (int i = 0; i < 15; i++) {
			sacarNumero(numerosGenerados);
		}
		return numerosGenerados;
	}

	public static void sacarNumero(List<Integer> carton) {
		Random ra = new Random();
		int numero = ra.nextInt(99);
		if (!carton.contains(numero) && numero != 0) {
			carton.add(numero);
			//listaNumeros.add(numero);
		} else {
			logger.info("Numero ya esta repetido");
			sacarNumero(carton);
		}
	}

//	public List<Integer> getListaNumeros() {
//		return listaNumeros;
//	}
	
	public int getIDCarton() {
		return IDCarton;
	}

	
	
}
