package co.com.springboot.domain;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Anuncio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAnuncio;
	
	@NotBlank(message = "Este campo es Obligatorio")
	@Size(min=3,max=25, message = "El titulo debe tener minimos 3 caracteres" )
	private String titulo;
	
	
	private String urlFoto;

	
	@NotBlank(message = "Este campo es Obligatorio")
	@Size(min=1,max=16, message = "El precio debe tener minimos 3 caracteres y maximo 10" )
	@Pattern(regexp ="^[0-9]+([., ][ 0-9,.]*)?$" , message = "Solo se puede introducir caracteres numericos,debe  contener minimo 1 caracter puede incluir caracteres como: . ,")
	private  String precio;
	
	@NotBlank(message = "Este campo es Obligatorio")
	@Size(max=282, message = "La descripcion solo puede contener maximo 280 caracteres")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "usuario_anuncio_fk")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "subAnuncio_fk")
	private Subcategoria subCategoria;
	
	
}
