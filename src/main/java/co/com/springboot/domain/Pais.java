package co.com.springboot.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Pais implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPais;
	
	@NotBlank(message = "Este Campo es Obligatorio")
	@Size(min = 5, max=16, message = "	Pais debe  contener minimo 5 caracteres y maximo 16 ")
	@Pattern(regexp = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "El Nombre del pais no debe contener caracteres especiales\r\n"
			+ "	y ningun digito,Debe contener minimo 5 caracteres y maximo 16")
	private String nombre;
	
	
	

	
}
