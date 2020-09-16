package co.com.springboot.domain;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data

public class Chat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChat;
	
	@ManyToOne
	@JoinColumn(name = "usuario_chat_fk")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "anuncio_chat_fk")
	private Anuncio anuncio;

}
