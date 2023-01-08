package personas;

public class Usuario extends Persona {

	private int idLigaActual;
	private float bote;


	public Usuario(int dni, String nombre, String apellido, String usuario, String contrasena, int idLigaActual,
			int bote) {
		super(dni, nombre, apellido, usuario, contrasena);
		this.idLigaActual = 1;
		this.bote = bote;
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
