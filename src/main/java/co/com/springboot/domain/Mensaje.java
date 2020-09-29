package co.com.springboot.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data
public class Mensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mensaje")
	private Integer idMensaje;
	
	@Column(name = "usuario_destino")
	private String usuarioDestino;
	
	@Column(name = "usuario_origen")	
	private String usuarioOrigen;
	
	@Column(name = "mensaje")	
	private String mensaje;
}
