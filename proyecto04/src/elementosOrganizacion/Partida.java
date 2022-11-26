package elementosOrganizacion;

import personas.Usuario;

public class Partida {
	
	private int IDPartida;
	private float boteBingo;
	private float boteLinea;
	private Carton ganadorLinea;
	private Carton ganadorBingo;
	
	public Partida() {
		super();
		insertarPartida();
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
