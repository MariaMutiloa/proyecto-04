package personas;

public abstract class Persona {
	

	private int dni;
	private String nombre;
	private String apellido;
	private String usuario;
	private String contrasena;
	int partidasJ;
	int partidasG;
	int partidasE;
	int partidasP;
	int liga;
	int puesto;
	int bote;
	
	public Persona(int dni, String nombre, String apellido, String usuario, String contrasena) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public Persona (int dni, String nombre,String apellido,String usuario,String contrasena, int partidasJ, int partidasG, int partidasE,int partidasP, int liga, int puesto,int bote) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.partidasJ=partidasJ;
		this.partidasG=partidasG;
		this.partidasE=partidasE;
		this.partidasP=partidasP;
		this.liga=liga;
		this.puesto=puesto;
		this.bote=bote;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	
	
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
	
	
	
	
	
	

}
