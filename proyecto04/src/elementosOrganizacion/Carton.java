package elementosOrganizacion;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import gestionBD.ConexionBD;
import gestionBD.GestionUsuarios;
import personas.Usuario;

public class Carton {

	private int IDCarton;
	private int IDUsuario;
	private float coste;
	private int IDPartida;
	private int bingo;
	private Usuario propietario;
	
	private static List<Integer> listaNumeros; 
	
	private static Logger logger = Logger.getLogger(Carton.class.getName());

	public Carton(int IDCarton, int IDUsuario, int IDPartida) {
		this.IDCarton = IDCarton;
		this.IDUsuario = IDUsuario;
		this.coste = costeCarton();	//esta en properties coste=2
		this.IDPartida = IDPartida;
		this.bingo=0;		//por defecto es 0, cuando tenga bingo y de a un boton de bingo! este se va a poner a 1
		this.propietario = GestionUsuarios.buscarUsuarioPorID(IDUsuario);
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
	
	
	//PARA SACAR DESDE PROPERTIES EL COSTE DEL CARTON
	public static float costeCarton() {
		float coste = 1;

		logger.info("Calculando coste correspondiente");
		try (FileReader reader = new FileReader("src/configuracion/configCostes.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            
            coste = Float.parseFloat(properties.getProperty("coste"));
           

        } catch (IOException e) {
        	logger.info("No se ha podido acceder al fichero properties");
        	JOptionPane.showMessageDialog(null, "No se pueden acceder al fichero de propiedades","Error en properties", JOptionPane.WARNING_MESSAGE);
			 
        }
		 return coste;
	}
	
	//AL COMPRAR CARTON SE ME BAJA LA CARTERA(bote)
	public static void bajarCartera(Usuario u) {
		float precioCarton = costeCarton();
		float nuevoBote = u.getBote()-precioCarton;
		u.setBote(nuevoBote);
		
		//GUARDAR EN BD
		GestionUsuarios.actualizarCarteraBD(u.getDni(), nuevoBote);
		logger.info("Cartera actualizada");
		
		
	}
	
	


	
	
}
