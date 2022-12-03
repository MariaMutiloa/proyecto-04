package personas;

public class Usuario extends Persona{
	
	private int idLigaActual;
	private int bote;
	
	public Usuario(int dni, String nombre, String apellido, String usuario, String contrasena, int idLigaActual,
			int bote) {
		super(dni, nombre, apellido, usuario, contrasena);
		this.idLigaActual = 1;
		this.bote = 10;
	}

	public int getIdLigaActual() {
		return idLigaActual;
	}

	public void setIdLigaActual(int idLigaActual) {
		this.idLigaActual = idLigaActual;
	}

	public int getBote() {
		return bote;
	}

	public void setBote(int bote) {
		this.bote = bote;
	}

	@Override
	public String toString() {
		return super.toString()+" IdLigaActual: " + idLigaActual + " Bote: " + bote;
	}

	
	
	
	

	
	

}
