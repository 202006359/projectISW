package domain;

import java.io.Serializable;
import java.util.Date;

/**
 *	Clase para definir el objeto Customer
 */

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String contrasena;
	private String perfil;

	private Date descuento;

	public Customer(String usuario) {
		this.usuario = usuario;
	}
	
	public Customer(String usuario, String contrasena) {
		this.contrasena = contrasena;
		this.usuario = usuario;
	}

	public Customer(String usuario, String contrasena, String perfil) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.perfil = perfil;
	}

	public Customer(String usuario, String contrasena, String perfil, Date descuento) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.perfil = perfil;
		this.descuento=descuento;
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

	public Date getDescuento(){ return descuento;}

	public void setDescuento(Date descuento)
	{this.descuento = descuento;}




	
}
