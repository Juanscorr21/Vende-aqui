package co.com.springboot.Controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.springboot.Repository.DeptRepository;
import co.com.springboot.Repository.MunicipioRepository;
import co.com.springboot.domain.Municipio;

@Controller
@RequestMapping
public class MunicipioController {

	@Autowired
	private MunicipioRepository municipioRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	
	@GetMapping("/listaMunicipios")
	   public String inicioFormularioMunicipioF(Municipio municipio,Model model) {
		 model.addAttribute("departamentos", deptRepo.findAll() );
		model.addAttribute("municipios", municipioRepo.findAll() );
	       return "municipio/indexMunicipio";
	  }



	@GetMapping("/inicioMunicipio")
	   public String inicioFormularioMunicipio(Municipio municipio,Model model) {	  
		   List<String> pais = deptRepo.findAllPais();
		   model.addAttribute("paises", pais);
		   
	       return "municipio/add-Municipio";
	  }
	
	@RequestMapping( "/inicioMunicipio/ajax/paises")
	public  String ajaxDept(@RequestParam("pais") String pais,Model model) {
		List<String> departamentos =  deptRepo.findAllDepartamentoByPais(pais);
		model.addAttribute("departamentos", departamentos);
		return "municipio/add-Municipio :: departamentos";
	}
	

	@PostMapping("/agregarMunicipio")
	  public String agregarMunicipio(@Valid Municipio municipio, BindingResult resultado, Model model) {

	    if (resultado.hasErrors()) {
	    	 model.addAttribute("departamentos", deptRepo.findAll() );
	            return "municipio/add-Municipio";
	     }
	        
	    	municipioRepo.save(municipio);
	        model.addAttribute("municipios", municipioRepo.findAll());
	        return "municipio/indexMunicipio";
	    }
	    
	 
	 @GetMapping("/editDept/{idMunicipio}")
	    public String editDept(@PathVariable("idMunicipio") int id, Model model) {
		 Municipio municipio = municipioRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid municipio Id:" + id));
	        model.addAttribute("municipio", municipio);
	        model.addAttribute("departamentos", deptRepo.findAll() );
	        return "municipio/update-Municipio";
	    }
	 
	 @PostMapping("/actualizarMunicipio/{idPais}")
	    public String actulizarMunicipio(@PathVariable("idMunicipio") int id, @Valid Municipio municipio, BindingResult resultado, Model model) {
	        if (resultado.hasErrors()) {
	        	municipio.setIdMunicipio(id);
	        	model.addAttribute("departamentos", deptRepo.findAll() );
	            return "municipio/update-Municipio";
	        }
	        
	        municipioRepo.save(municipio);
	        model.addAttribute("municipio", municipioRepo.findAll());
	        return "municipio/indexMunicipio";
	    }
	 
	 @GetMapping("/eliminarMunicipio/{idMunicipio}")
	    public String eliminarMunicipio(@PathVariable("idMunicipio") int id, Model model) {
		 Municipio municipio = municipioRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid municipio Id:" + id));
		 municipioRepo.delete(municipio);
	        model.addAttribute("Paises", municipioRepo.findAll());
	        return "municipio/indexMunicipio";
	    }
	
}
