package testsgestionBD;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import gestionBD.GestionUsuarios;
import personas.UsuarioExtendido;


public class GestionUsuariosTest {
	
	private static String bdReal = "jdbc:sqlite:DatosBingo.db";
	private static String bdTest = "jdbc:sqlite:DatosBingoTest.db";

	@Before
	public void insertarUsuario() {
		try(Connection conn = DriverManager.getConnection(bdTest);
		   
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery("INSERT INTO usuario VALUES (45236798,Maria,Mutiloa,mariamutiloa,maria.mutiloa,1,10)")) {
			conn.close();
		} catch (Exception e) {
			 System.out.println("No se ha podido conectar a la base de datos.");
			    System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGetPartidasJugadas() {
		int resultado = GestionUsuarios.getPartidasJugadas(24356782, bdTest);
		assertEquals(7, resultado);
	}
	
	@Test
	public void testGetAllUsuarios() {
		UsuarioExtendido u1 = new UsuarioExtendido(24356782, "ruben.garcia", "Ruben", "Garcia", "ruben01", 1, 8);
		UsuarioExtendido u2 = new UsuarioExtendido(72554022, "anepradera", "Ane", "Pradera", "ane", 1, 8);
		UsuarioExtendido u3 = new UsuarioExtendido(67456718, "jonlopez", "Jon", "Lopez", "lopezjon", 1, 9);
		UsuarioExtendido u4 = new UsuarioExtendido(56478903, "albarodri", "Alba", "Rodriguez", "rodriguez17", 1, 9);
		List<UsuarioExtendido> lista = GestionUsuarios.getAllUsuarios(bdTest);
		assertEquals(u1, lista.get(0));
		assertEquals(u2, lista.get(1));
		assertEquals(u3, lista.get(2));
		assertEquals(u4, lista.get(3));
	}
	
	@Test 
	public void testEliminar() {
		GestionUsuarios.eliminar(45236798,bdTest );
		List<UsuarioExtendido> lista = GestionUsuarios.getAllUsuarios(bdTest);
		List<UsuarioExtendido> listaComparar = new ArrayList<>();
		for (UsuarioExtendido u : lista) {
			if(u.getDni() == 45236798) {
				listaComparar.add(u);
			}
		}
		
		assertEquals(0, listaComparar.size());
	}

}
