package co.com.springboot.Controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.springboot.Repository.MensajeRepository;
import co.com.springboot.domain.Mensaje;

@Controller
public class MensajeController {

	@Autowired
	private MensajeRepository mensajeRepo;
	
	
	@GetMapping("/accerderMensaje")
	   public String accerderMensaje(Mensaje mensaje) {
	       return "add-Mensaje";
	  }
	
	@PostMapping("/agregarMensaje")
	  public String agregarMensaje(@Valid Mensaje mensaje, BindingResult resultado, Model model) {

	    if (resultado.hasErrors()) {
	            return "add-Mensaje";
	     }
	        
	    mensajeRepo.save(mensaje);
	        model.addAttribute("mensajes", mensajeRepo.findAll());
	        return "indexMensaje";
	    }
	    
	
	 
	 @GetMapping("/eliminarMensaje/{idMensaje}")
	  public String eliminarMensaje(@PathVariable("idMensaje") int id, Model model) {
		 Mensaje mensaje = mensajeRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid mensaje Id:" + id));
		 mensajeRepo.delete(mensaje);
	        model.addAttribute("mensajes", mensajeRepo.findAll());
	        return "indexMensaje";
	    }
	
}
