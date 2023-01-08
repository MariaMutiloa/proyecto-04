package personas;

import gestionBD.GestionUsuarios;

public class UsuarioExtendido extends Usuario {
	
	private int partidasJugadas;
	private int partidasGanadas;

	public UsuarioExtendido(int dni, String nombre, String apellido, String usuario, String contrasena,
			int idLigaActual, int bote) {
		super(dni, nombre, apellido, usuario, contrasena, idLigaActual, bote);
		this.partidasJugadas = GestionUsuarios.getPartidasJugadas(dni);
		this.partidasGanadas = GestionUsuarios.getPartidasGanadas(dni);
		
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}



}
