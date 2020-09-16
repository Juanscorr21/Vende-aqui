package co.com.springboot.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Entity
@Data

public class Administrador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdmin;
	
	
	private String nombre;

	private String email;

	private String apellido;
	
	private String userName;
	
	private String contrasena;
	
	private String telefono;
	
	
}
