package elementosOrganizacion;

import java.util.List;

import gestionBD.ConexionBD;
import personas.Usuario;

public class Carton {
	
	private int IDCarton; 
	private int IDUsuario; 
	private int IDPartida;
	private Usuario propietario;
	
	

	public Carton(int IDCarton, int IDUsuario, int IDPartida) {
		this.IDCarton = IDCarton;
		this.IDUsuario = IDUsuario;
		this.IDPartida = IDPartida;
		this.propietario = ConexionBD.buscarUsuarioPorID(IDUsuario);
		
	}
}
