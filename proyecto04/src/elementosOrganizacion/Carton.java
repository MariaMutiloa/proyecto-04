package elementosOrganizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import gestionBD.ConexionBD;
import personas.Usuario;

public class Carton {
	
	public static int IDActual=1; //cada vez que creo un nuevo objeto ese id lo voy a aumentar
	/*
	 * PROBLEMA!!!!!!
	 * TODO EL RATO SE GUARDA EL IDCarton a 1 YA QUE CADA VEZ QUE INICIA EL PROGRAMA SE REESTABLECE EL IDActual a 1
	 */

	private int IDCarton;
	private int IDUsuario;
	private float coste;
	private int IDPartida;
	private int bingo;
	private Usuario propietario;
	
	private static List<Integer> listaNumeros; 
	
	private static Logger logger = Logger.getLogger(Carton.class.getName());

	public Carton(int IDUsuario, int IDPartida) {
		this.IDCarton = IDActual++;
		this.IDUsuario = IDUsuario;
		this.coste = 2;	//todos los cartones valen 2�
		this.IDPartida = IDPartida;
		this.bingo=0;		//por defecto es 0, cuando tenga bingo y de a un boton de bingo! este se va a poner a 1
		this.propietario = ConexionBD.buscarUsuarioPorID(IDUsuario);
	}
	
	public Carton(int IDCarton, int IDUsuario, int IDPartida) {
		this.IDCarton=IDCarton;
		this.IDUsuario=IDUsuario;
		this.IDPartida=IDPartida;
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

	public List<Integer> getListaNumeros() {
		this.listaNumeros = generarCarton();
		return listaNumeros;
	}
	
	public int getIDCarton() {
		return IDCarton;
	}

	public int getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(int iDUsuario) {
		IDUsuario = iDUsuario;
	}

	public float getCoste() {
		return coste;
	}

	public void setCoste(float coste) {
		this.coste = coste;
	}

	public int getIDPartida() {
		return IDPartida;
	}

	public void setIDPartida(int iDPartida) {
		IDPartida = iDPartida;
	}

	public int getBingo() {
		return bingo;
	}

	public void setBingo(int bingo) {
		this.bingo = bingo;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public void setIDCarton(int iDCarton) {
		IDCarton = iDCarton;
	}
	


	
	
}
