package personas;

public class Usuario extends Persona{
	
	private int idLigaActual;
	private float bote;
	
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

	public float getBote() {
		return bote;
	}

	public void setBote(float f) {
		this.bote = f;
	}

	@Override
	public String toString() {
		return super.toString()+" IdLigaActual: " + idLigaActual + " Bote: " + bote;
	}

	
	
	
	 @Override
	    public boolean equals(Object o) {
	        if (!(o instanceof Usuario)) 
	            return false;

	        Usuario e = (Usuario) o;
	        return this.bote==(e.bote);
	      
	    }


	
	

}
