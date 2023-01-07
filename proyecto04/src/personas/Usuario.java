package personas;

public class Usuario extends Persona {

	private int idLigaActual;
	private float bote;
	private int partidasJ;
	private int partidasG;
	private int partidasP;
	private int puesto;

	public Usuario(int dni, String nombre, String apellido, String usuario, String contrasena, int idLigaActual,
			int bote) {
		super(dni, nombre, apellido, usuario, contrasena);
		this.idLigaActual = 1;
		this.bote = 10;
	}

	public Usuario(int dni, String nombre, String apellido, String usuario, String contrasena, int idLigaActual,
			float bote, int partidasJ, int partidasG, int partidasP, int puesto) {
		super(dni, nombre, apellido, usuario, contrasena);
		this.partidasJ = partidasJ;
		this.partidasG = partidasG;
		this.partidasP = partidasP;
		this.puesto = puesto;

	}

	public int getPartidasJ() {
		return partidasJ;
	}

	public void setPartidasJ(int partidasJ) {
		this.partidasJ = partidasJ;
	}

	public int getPartidasG() {
		return partidasG;
	}

	public void setPartidasG(int partidasG) {
		this.partidasG = partidasG;
	}

	public int getPartidasP() {
		return partidasP;
	}

	public void setPartidasP(int partidasP) {
		this.partidasP = partidasP;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public int getIdLigaActual() {
		return idLigaActual;
	}

	public void setIdLigaActual(int idLigaActual) {
		this.idLigaActual = idLigaActual;
	}

	public float getBote() {
		return bote;
	}

	public void setBote(float bote) {
		this.bote = bote;
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
