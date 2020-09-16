package co.com.springboot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.com.springboot.Repository.AnuncioRepository;
import co.com.springboot.Repository.CategoriaRepository;
import co.com.springboot.Repository.SubCategoriaRepository;
import co.com.springboot.Repository.UsuarioRepository;

@Controller
public class MenuController {

	
	@Autowired
	private UsuarioRepository userRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private SubCategoriaRepository subcategoriaRepo;
	@Autowired
	private AnuncioRepository anuncioRepo;
	
	@GetMapping({"/signUp","/login"})
	   public String singUp(Model model) {
	       return "singUp";
	  }
	
	@GetMapping("/")
	   public String inicio(Model model) {
		model.addAttribute("anuncios", anuncioRepo.findAll());
		model.addAttribute("categorias", categoriaRepo.findAll());
	       return "Usuario/index";
	  }

		
	
}
