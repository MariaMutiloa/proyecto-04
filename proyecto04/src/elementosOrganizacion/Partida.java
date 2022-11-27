package elementosOrganizacion;

import java.util.List;

import gestionBD.GestionPartidas;
import personas.Usuario;

public class Partida {
	
	private int IDPartida;
	private float boteBingo;
	private float boteLinea;
	private Carton ganadorLinea;
	private Carton ganadorBingo;
	private List<Usuario> participantes;
	
	public Partida() {
		super();
		this.IDPartida = GestionPartidas.nueva();
	}
	
	public List<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Usuario> participantes) {
		this.participantes = participantes;
	}

	public int getIDPartida() {
		return IDPartida;
	}
	public void setIDPartida(int iDPartida) {
		IDPartida = iDPartida;
	}
	public float getBoteBingo() {
		return boteBingo;
	}
	public void setBoteBingo(float boteBingo) {
		this.boteBingo = boteBingo;
	}
	public float getBoteLinea() {
		return boteLinea;
	}
	public void setBoteLinea(float boteLinea) {
		this.boteLinea = boteLinea;
	}
	public Carton getGanadorLinea() {
		return ganadorLinea;
	}
	public void setGanadorLinea(Carton ganadorLinea) {
		this.ganadorLinea = ganadorLinea;
	}
	public Carton getGanadorBingo() {
		return ganadorBingo;
	}
	public void setGanadorBingo(Carton ganadorBingo) {
		this.ganadorBingo = ganadorBingo;
	}

	
}
