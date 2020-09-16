package co.com.springboot.Controller;


import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import co.com.springboot.Repository.CategoriaRepository;
import co.com.springboot.Repository.SubCategoriaRepository;
import co.com.springboot.domain.Categoria;
import co.com.springboot.domain.Subcategoria;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepo;
	@Autowired
	private SubCategoriaRepository subcategoriaRepo;
	
	@GetMapping("/admin/c")
	   public String registrarCategoriaF(Categoria categoria, Model model) {
			model.addAttribute("categorias", categoriaRepo.findAll());
	       return "categoria/indexCategoria";
	  }

	@GetMapping("/admin/registroCategoria")
	   public String registrarCategoria(Categoria categoria) {
	       return "categoria/add-Categoria";
	  }
	
	@GetMapping("/admin/categoria")
	   public String categoria(Model model) {
	      model.addAttribute("categoria", new Subcategoria());
	      model.addAttribute("categorias", categoriaRepo.findAll() );
	      return "Categoria";
	  }
	
	@PostMapping("/admin/agregarCategoria")
	  public ModelAndView agregarCategoria(@Valid Categoria categoria, BindingResult resultado, Model model) {
		
		
		
	    if (resultado.hasErrors()) {

	        return new ModelAndView("categoria/add-Categoria");
	     }else {
	    	
		    Optional<Categoria> categoriaEx = categoriaRepo.findByNombre(categoria.getNombre());
		 	if(categoriaEx.isPresent()) {
	 			model.addAttribute("categoriaExistente","Categoria ya existe");
	 			return new ModelAndView("categoria/add-Categoria");
		 	}
	     }
	    
	    categoriaRepo.save(categoria);
	    model.addAttribute("categorias", categoriaRepo.findAll());
	    return new ModelAndView("redirect:"+ "/admin/c");
	}

	@GetMapping("/admin/editcategoria/{idCategoria}")
		public String editcategoria(@PathVariable("idCategoria") int id, Model model) {
		Categoria categoria = categoriaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
		model.addAttribute("categoria", categoria);
		return "categoria/update-Categoria";
	}

	 @PostMapping("/admin/actualizarCategoria/{idCategoria}")
	    public ModelAndView actualizarCategoria(@PathVariable("idCategoria") int id, @Valid Categoria categoria, BindingResult resultado, Model model) {
	        if (resultado.hasErrors()) {
	            return new ModelAndView("categoria/update-Categoria");
	        }else {
	        	 Optional<Categoria> categoriaEx = categoriaRepo.findByNombre(categoria.getNombre());
	 		 	if(categoriaEx.isPresent()) {
	 	 			model.addAttribute("categoriaExistente","Categoria ya existe");
	 	 			return new ModelAndView("categoria/update-Categoria");
	 		 	}
	        }
	        
	        categoriaRepo.save(categoria);
	        model.addAttribute("categorias", categoriaRepo.findAll());
	        return new ModelAndView("redirect:"+ "/admin/c");
	    }
	 
	 @GetMapping("/admin/eliminarCategoria/{idCategoria}")
	    public ModelAndView eliminarCategoria(@PathVariable("idCategoria") int id, Model model) {
		 Categoria categoria = categoriaRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid categoria Id:" + id));
		 categoriaRepo.delete(categoria);

	       model.addAttribute("categorias", categoriaRepo.findAll());
	        return new ModelAndView("redirect:"+ "/admin/c");
	  }
	  
}
