package co.com.springboot.Controller;



import java.security.Principal;
import java.util.List;
import java.util.Map;



import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.cloudinary.utils.ObjectUtils;

import co.com.springboot.CloudinaryConfig;
import co.com.springboot.Repository.AnuncioRepository;
import co.com.springboot.Repository.AuthorityRepository;
import co.com.springboot.Repository.CategoriaRepository;

import co.com.springboot.Repository.SubCategoriaRepository;
import co.com.springboot.Repository.UsuarioRepository;
import co.com.springboot.domain.Anuncio;
import co.com.springboot.domain.Usuario;
import co.com.springboot.service.UserDetailsServiceImpl;







@SuppressWarnings("unused")
@Controller
public class AnuncioController {

	@Autowired
	private AnuncioRepository anuncioRepo;
	@Autowired
	private SubCategoriaRepository subCatRepo;
	@Autowired
	private UsuarioRepository usuarioRepo;
	@Autowired
	private CloudinaryConfig cloudinary;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private UserDetailsServiceImpl userDetails;
	

	@GetMapping("/user/anuncio")
	   public String anuncioForm(Anuncio anuncio,Model model) {
			model.addAttribute("subcategorias", subCatRepo.findAll() );
			
	       return "Anuncio/add-anuncio";
	  }
	
	@GetMapping("/findByTitulo")
	public String buscarPorTituloAnuncio(String titulo, Model model) {
	
			List<Anuncio> listaAnuncio = anuncioRepo.findByTitulo("%"+titulo.toUpperCase()+"%","%"+titulo.toUpperCase()+"%","%"+titulo.toUpperCase()+"%");
			model.addAttribute("anuncios", listaAnuncio);

			return "Usuario/index";
	}


	@GetMapping("/anuncioChat/{idAnuncio}")
			public String anuncioChat(@PathVariable("idAnuncio") int id, Model model) {
			Anuncio anuncio = anuncioRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid anuncio Id:" + id));
			List<Anuncio> anuncios =anuncioRepo.findAllAnuncioBySubcategoria(anuncio.getSubCategoria().getNombre());
			model.addAttribute("subcategorias", subCatRepo.findAll() );
			model.addAttribute("anuncios", anuncios );
			model.addAttribute("anuncio", anuncio);
			return "Anuncio/indexAnuncioChat";
		}

	

	
	@GetMapping("/user/ListAnuncio")
	   public String anuncio(Model model,Principal principal) {
		

		
		 List<Anuncio> anuncio = anuncioRepo.findAllAnuncioByUsuario(principal.getName());

	      model.addAttribute("anuncios", anuncio );
	      model.addAttribute("subcategorias", subCatRepo.findAll() );
	  	  model.addAttribute("usuarios", usuarioRepo.findAll() );
	      return "Anuncio/indexAnuncio";
	  }
	
	@PostMapping("/user/agregaranuncio")
	  public String agregarAnuncio(@Valid Anuncio anuncio, BindingResult resultado, Model model,@RequestParam("file") MultipartFile file) {

	    if (resultado.hasErrors()) {

	    	model.addAttribute("subcategorias", subCatRepo.findAll() );
	    	model.addAttribute("usuarios", usuarioRepo.findAll() );

	    	return "Anuncio/add-anuncio";
	     }
	  
	    
	    
	    anuncio.setUsuario(userDetails.getAppUser());
	    
	    
	   try {
	    	Map uploadResult= cloudinary.upload(file.getBytes(), ObjectUtils.asMap("resourcetype","auto"));
	    	System.out.print(uploadResult.get("url").toString());
	    	anuncio.setUrlFoto(uploadResult.get("url").toString());
	    }catch (Exception e) {
	    	System.out.print(e.getMessage());	
		}
	
	    anuncioRepo.save(anuncio);
	    model.addAttribute("anuncios", anuncioRepo.findAll());
	    return "redirect:/user/ListAnuncio";
	    }
	
	
	@GetMapping("/user/editanuncio/{idAnuncio}")
		public String editanuncio(@PathVariable("idAnuncio") int id, Model model) {
		Anuncio anuncio = anuncioRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid anuncio Id:" + id));
		model.addAttribute("subcategorias", subCatRepo.findAll() );
		
		model.addAttribute("anuncio", anuncio);
		return "Anuncio/update-anuncio";
	}
	
	
	
	 @PostMapping("/user/actualizarAnuncio/{idAnuncio}")
	    public String actulizarAnuncio(@PathVariable("idAnuncio") int id, @Valid Anuncio anuncio, BindingResult resultado, Model model,@RequestParam("file") MultipartFile file) {
	        if (resultado.hasErrors()) {
	        	anuncio.setIdAnuncio(id);
	         	model.addAttribute("subcategorias", subCatRepo.findAll() );

	         	return "Anuncio/update-anuncio";
	        }
	        anuncio.setUsuario(userDetails.getAppUser());
	        
	        try {
		    	Map uploadResult= cloudinary.upload(file.getBytes(), ObjectUtils.asMap("resourcetype","auto"));
		    	System.out.print(uploadResult.get("url").toString());
		    	anuncio.setUrlFoto(uploadResult.get("url").toString());
		    }catch (Exception e) {
		    	System.out.print(e.getMessage());	
			}
	        
	        anuncioRepo.save(anuncio);
	        model.addAttribute("anuncios", anuncioRepo.findAll());
	        return "redirect:/user/ListAnuncio";
	    }
	 
	 @GetMapping("/user/eliminarAnuncio/{idAnuncio}")
	 public String eliminarAnuncio(@PathVariable("idAnuncio") int id, Model model) {
		 Anuncio anuncio = anuncioRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid anuncio Id:" + id));
		 anuncioRepo.delete(anuncio);
	     model.addAttribute("anuncios", anuncioRepo.findAll());
	     return "redirect:/user/ListAnuncio";
	  }
}
