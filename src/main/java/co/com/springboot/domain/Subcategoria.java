package co.com.springboot.domain;

import java.io.Serializable;
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
public class Subcategoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSubcategoria;

	@NotBlank(message = "Este Campo es Obligatorio")
	@Size(min = 3, max=20, message = "El Nombre debe  contener minimo 3 caracteres y maximo 20 ")
	@Pattern(regexp = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "El Nombre No debe contener caracteres especiales\r\n"
			+ "	y ningun digito,Debe contener minimo 3 caracteres y maximo 20")
	private String nombre;
	

	
	@ManyToOne
	@JoinColumn(name = "categoria_fk")
	private Categoria categoria;
}
