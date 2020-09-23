package co.com.springboot.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import co.com.springboot.Repository.AnuncioRepository;
import co.com.springboot.Repository.CategoriaRepository;




@Controller
public class MenuController {



	@Autowired
	private AnuncioRepository anuncioRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping({"/signUp","/login"})
	   public String singUp(Model model) {
	       return "singUp";
	  }
	
	@GetMapping("/")
	   public String inicio(Model model) {

		model.addAttribute("anuncios", anuncioRepo.findAll());
		model.addAttribute("categorias", categoriaRepository.findAll());
	       return "Usuario/index";
	  }
	

	
	


		
	
}
