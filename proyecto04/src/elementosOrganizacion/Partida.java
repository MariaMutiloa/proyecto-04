package elementosOrganizacion;

import java.util.List;

import gestionBD.GestionPartidas;

public class Partida {
	
	private int IDPartida;
	private float boteBingo;
	private Carton ganadorBingo;
	private List<Carton> participantes;
	
	private static String bd = "jdbc:sqlite:DatosBingo.db";
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";
	
	public Partida() {
		super();
		this.IDPartida = GestionPartidas.nueva(bd);
	}
	
	public Partida(boolean test) {
		super();
		this.IDPartida = GestionPartidas.nueva(bdTest);
	}
	
	public Partida(int idPartida, int activa, int premioB, int idCartonB) {
		this.IDPartida=idPartida;
	}
	
	public List<Carton> getParticipantes(){
		return this.participantes;
	}

	public void setParticipantes(List<Carton> participantes) {
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
	public Carton getGanadorBingo() {
		return ganadorBingo;
	}
	public void setGanadorBingo(Carton ganadorBingo) {
		this.ganadorBingo = ganadorBingo;
	}

	
}
