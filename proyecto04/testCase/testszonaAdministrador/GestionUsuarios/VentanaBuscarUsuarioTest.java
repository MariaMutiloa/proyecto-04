package testszonaAdministrador.GestionUsuarios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import org.junit.Test;

import personas.Usuario;
import zonaAdministrador.GestionUsuarios.VentanaBuscarUsuario;

public class VentanaBuscarUsuarioTest {

	@Test
	public void testAnyadirUsuarios() {
		JTextField jTextField = new JTextField();
		jTextField.setText("Ane");
		String url = "jdbc:sqlite:DatosBingoTest.db";
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		Usuario u1 = new Usuario(72554022, "Ane", "Pradera", "anepradera", "ane", 1, 0);
		try {
			listaUsuarios = VentanaBuscarUsuario.anyadirUsuarios(listaUsuarios, jTextField, url);
			assertEquals(1, listaUsuarios.size());
			assertEquals(u1, listaUsuarios.get(0));
		} catch (Exception e) {
		}

	}

}
