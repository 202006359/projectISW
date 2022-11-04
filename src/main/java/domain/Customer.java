package domain;

import java.io.Serializable;

public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String contrasena;
	private String perfil;

	
	public Customer(String usuario, String contrasena) {
		this.contrasena = contrasena;
		this.usuario = usuario;
	}

	public Customer(String usuario, String contrasena, String perfil) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.perfil = perfil;
	}

	public String getUsuario() {
		return usuario;
	}


	public String getContrasena() {
		return contrasena;
	}

	public String getPerfil() {
		return perfil;
	}



	
}
