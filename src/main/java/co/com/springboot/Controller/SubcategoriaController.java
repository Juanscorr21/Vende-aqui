package co.com.springboot.Controller;

import java.util.List;
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
public class SubcategoriaController {

	
	@Autowired
	private SubCategoriaRepository subCatRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@GetMapping("/admin/listaSubcategoria")
	   public String vecForm(Subcategoria subcategoria,Model model) {
		  model.addAttribute("subcategorias", subCatRepo.findAll() );
		  model.addAttribute("categorias", categoriaRepo.findAll() );
	       return "subcategoria/indexSub";
	  }

	
	@GetMapping("/admin/registroSubcategoria")
	   public String registrarSubcategoria(Subcategoria subcategoria,Model model) {
		   model.addAttribute("categorias", categoriaRepo.findAll() );
	       return "subcategoria/add-subcategoria";
	  }
	
	@GetMapping("/admin/subcategoria")
	   public String subcategoria(Model model) {
	      model.addAttribute("subcategoria", new Subcategoria());
	      model.addAttribute("subcategorias", subCatRepo.findAll() );
	      return "subcategoria";
	  }
	
	@PostMapping("/admin/agregarSub")
	  public ModelAndView agregarSubcategoria(@Valid Subcategoria subcategoria, BindingResult resultado, Model model) {
		
		
	    if (resultado.hasErrors()) {
	    	 model.addAttribute("categorias", categoriaRepo.findAll() );
	    	 return new ModelAndView( "subcategoria/add-subcategoria");
	     }else {
		    	
			    Optional<Subcategoria> subcategoriaExistente = subCatRepo.findByNombre(subcategoria.getNombre());
			 	if(subcategoriaExistente.isPresent()) {
			 		model.addAttribute("categorias", categoriaRepo.findAll() );
		 			model.addAttribute("subcategoriaExistente","subcategoria ya existe");
		 			return new ModelAndView("subcategoria/add-subcategoria");
			 	}
		     }
	        
	    subCatRepo.save(subcategoria);
	  
	    model.addAttribute("subcategorias", subCatRepo.findAll());	    
	    return new ModelAndView("redirect:"+ "/admin/listaSubcategoria");
	    }
	
	
	@GetMapping("/admin/editSub/{idSubcategoria}")
    public String editSub(@PathVariable("idSubcategoria") int id, Model model) {
	Subcategoria subcategoria = subCatRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subcategoria Id:" + id));
    model.addAttribute("subcategoria", subcategoria);
    model.addAttribute("categorias", categoriaRepo.findAll() );
        return "subcategoria/update-subcategoria";
    }
	
	
	
	 @PostMapping("/admin/actualizarSub/{idSubcategoria}")
	    public ModelAndView actulizarsubcategoria(@PathVariable("idSubcategoria") int id, @Valid Subcategoria subcategoria, BindingResult resultado, Model model) {
	        if (resultado.hasErrors()) {
	        	subcategoria.setIdSubcategoria(id);
	        	 model.addAttribute("categorias", categoriaRepo.findAll() );
	            return new ModelAndView("subcategoria/update-subcategoria");
	        }else {
		    	
			    Optional<Subcategoria> subcategoriaExistente = subCatRepo.findByNombre(subcategoria.getNombre());
			 	if(subcategoriaExistente.isPresent()) {
			 		 model.addAttribute("categorias", categoriaRepo.findAll() );
		 			model.addAttribute("subcategoriaExistente","subcategoria ya existe");
		 			return new ModelAndView("subcategoria/update-subcategoria");
			 	}
		     }
	        
	        subCatRepo.save(subcategoria);
	        model.addAttribute("subcategorias", subCatRepo.findAll());
	        
	        return new ModelAndView("redirect:"+ "/admin/listaSubcategoria");
	    }
	 
	 @GetMapping("/admin/eliminarSub/{idSubcategoria}")
	    public ModelAndView eliminarsubcategoria(@PathVariable("idSubcategoria") int id, Model model) {
		 Subcategoria subcategoria = subCatRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid subcategoria Id:" + id));
		 subCatRepo.delete(subcategoria);
	        model.addAttribute("subcategorias", subCatRepo.findAll());
	        return new ModelAndView("redirect:"+ "/admin/listaSubcategoria");
	  }
	
	 
}
