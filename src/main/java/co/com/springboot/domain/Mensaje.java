package co.com.springboot.domain;

import java.io.Serializable;
import java.sql.Date;
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
	private int idMensaje;
	
	private Date fechaMensaje;
	
	private String hora;
	
	@ManyToOne
	@JoinColumn(name = "chat_fk")
	private Chat chat;
}
