package co.com.springboot.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


@Entity
@Data
public class Municipio implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMunicipio;
	
	@NotBlank(message = "Este Campo es Obligatorio")
	@Size(min = 5, max=16, message = "El nombre debe  contener minimo 5 caracteres y maximo 16 ")
	@Pattern(regexp = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "El nombre No debe contener caracteres especiales\r\n"
			+ "	y ningun digito,Debe contener minimo 5 caracteres y maximo 16")
	private String nombre;
	 
	@ManyToOne
	@JoinColumn(name = "departamento_fk")
	private Departamento departamento;
	
	
	 
	 

}
