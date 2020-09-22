package co.com.springboot.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import co.com.springboot.Repository.AnuncioRepository;




@Controller
public class MenuController {



	@Autowired
	private AnuncioRepository anuncioRepo;
	
	@GetMapping({"/signUp","/login"})
	   public String singUp(Model model) {
	       return "singUp";
	  }
	
	@GetMapping("/")
	   public String inicio(Model model) {

		model.addAttribute("anuncios", anuncioRepo.findAll());
	       return "Usuario/index";
	  }
	

	
	


		
	
}
