package co.com.springboot.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotBlank(message = "	Este Campo Es Obligatorio")
	@Size(min = 3, max=20, message = "	Nombre debe  contener minimo 3 caracteres y maximo 20 ")
	@Pattern(regexp = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "	Nombre No debe contener caracteres especiales\r\n"
			+ "	y ningun digito,Debe contener minimo 3 caracteres y maximo 20")
	private String nombre;
	
	@NotBlank(message = "	Este Campo Es Obligatorio")
	@Size(min = 3, max=20, message = "	Los apellidos deben  contener minimo 3 caracteres y maximo 20 ")
	@Pattern(regexp = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]+$", message = "	Los apellidos No debe contener caracteres especiales\r\n"
			+ "	y ningun digito")
	private String apellido;
		
	@NotBlank(message = "	Este Campo Es Obligatorio")
	@Size(min = 5, max=10, message = "	Nombre De Usuario  debe  contener minimo 5 caracteres y maximo 10 ")
	@Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*[._-]?[a-zA-Z0-9]+$", message = "1.Solo se permite un carácter especial y no debe estar en los extremos de la cuerda(._-)\r\n"
			+ "2.El primer carácter no puede ser un número\r\n 3.Todos los demás caracteres permitidos son letras y números.\r\n 4.Nombre De Usuario  debe  contener minimo 5 caracteres y maximo 10 ")
	private String userName;
	
	@NotBlank(message = "	Este Campo Es Obligatorio")
	@Size(min=7,max=16, message = "El telefono debe tener minimos 7 caracteres y maximo 15" )
	@Pattern(regexp ="^[0-9]+([., ][ 0-9,.]*)?$" , message = "Solo se puede introducir caracteres numericos.")
	private String telefono;
	
	@NotBlank(message = "	Este Campo Es Obligatorio")
	@Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$", message = "Debe ingresar una direccion de correo valida")
	private String email;
	
	@NotBlank(message = "	Este Campo Es Obligatorio")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,70}$", message = "   La contraseña debe tener minimo 8 , al menos un dígito,una minúscula y una mayúscula.\r\n" + 
		"Puede tener otros símbolos adicionales.")	
	private String contrasena;
	

	@NotBlank(message = "Este campo es obligatorio")
	@Size(min=3,max=20,message = "La direccion debe contener minimo 7 carateres y maximo 20")
	private String descripcion;
	
	@Transient
	private String conficontrasena;
	
	
}
