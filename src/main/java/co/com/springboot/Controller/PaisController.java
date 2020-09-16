package co.com.springboot.Controller;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import co.com.springboot.Repository.PaisRepository;
import co.com.springboot.domain.Pais;


@Controller
public class PaisController  {

	@Autowired
	private PaisRepository paisRepo;
	
	
	
	@GetMapping("/listaPaises")
	   public String inicioFormularioPaisF(Pais pais,Model model) {
		   model.addAttribute("paises", paisRepo.findAll());
	       return "pais/indexPais";
	  }
	

	
	@GetMapping("/inicioPais")
	   public String inicioFormularioPais(Pais pais) {
	       return "pais/add-pais";
	  }
	

	
	@PostMapping("/agregarPais")
	  public String agregarPais(@Valid Pais pais, BindingResult resultado, Model model) {

	    if (resultado.hasErrors()) {
	            return "pais/add-pais";
	     }
	        
	    	paisRepo.save(pais);
	      model.addAttribute("paises", paisRepo.findAll());
	      return "redirect:/listaPaises";
	    }
	    
	 
	 @GetMapping("/editPais/{idPais}")
	 public String editPais(@PathVariable("idPais") int id, Model model) {
		 Pais pais = paisRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pais Id:" + id));
	        model.addAttribute("pais", pais);
	        return "pais/update-pais";
	    }
	 
	 @PostMapping("/actualizarPais/{idPais}")
	    public String actulizarPais(@PathVariable("idPais") int id, @Valid Pais pais, BindingResult resultado, Model model) {
	        if (resultado.hasErrors()) {
	        	pais.setIdPais(id);
	            return "pais/update-pais";
	        }	
	        
	        paisRepo.save(pais);
	        model.addAttribute("paises", paisRepo.findAll());
	        return "redirect:/listaPaises";
	    }
	 
	 @GetMapping("/eliminarPais/{idPais}") 
	    public String eliminarPais(@PathVariable("idPais") int id, Model model) {
		 Pais pais = paisRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pais Id:" + id));
		 paisRepo.delete(pais);
	        model.addAttribute("paises", paisRepo.findAll());
	        return "redirect:/listaPaises";
	    }
}
