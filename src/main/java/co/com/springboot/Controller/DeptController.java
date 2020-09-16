package co.com.springboot.Controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.springboot.Repository.DeptRepository;
import co.com.springboot.Repository.PaisRepository;
import co.com.springboot.domain.Departamento;


@RequestMapping("/departamento")
@Controller
public class DeptController {

	@Autowired
	private DeptRepository deptRepo;
	
	@Autowired
	private PaisRepository paisRepo;
		
	
	
	@GetMapping("/ListaDept")
	   public String inicoFomularioDepartamentoF(Departamento dept,Model model) {
		 model.addAttribute("departamentos", deptRepo.findAll() );
		  model.addAttribute("paises", paisRepo.findAll() );
	       return "departamento/indexDept";
	  }
	
	/**@GetMapping("/iniciodeptA")
	   public String inicoFomularioDepartamentoA(Departamento dept) {
	       return "departamento/update-dept";
	  }*/
	
	@GetMapping("/iniciodept")
	   public String inicoFomularioDepartamento(Departamento dept,Model model) {
		  model.addAttribute("paises", paisRepo.findAll() );
	       return "departamento/add-dept";
	  }
	 
	@PostMapping("/agregarDept")
	  public String agregarDepartamento(@Valid Departamento dept, BindingResult resultado, Model model) {

	    if (resultado.hasErrors()) {
	    	model.addAttribute("paises", paisRepo.findAll() );
	            return "departamento/add-dept";
	     }
	        
	    	deptRepo.save(dept);
	        model.addAttribute("departamentos", deptRepo.findAll());
		    return "redirect:/departamento/ListaDept";
	    }
	    
	 
	 @GetMapping("/editDept/{idDept}")
	    public String editDept(@PathVariable("idDept") int id, Model model) {
		 Departamento dept = deptRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid departamento Id:" + id));
		 	model.addAttribute("departamento", dept);
		 	model.addAttribute("paises", paisRepo.findAll() );
	        return "departamento/update-dept";
	    }
	 
	 @PostMapping("/actualizarDept/{idDept}")
	    public String actulizarDepartamentos(@PathVariable("idDept") int id, @Valid Departamento dept, BindingResult resultado, Model model) {
	        if (resultado.hasErrors()) {
	        	dept.setIdDept(id);
	        	model.addAttribute("paises", paisRepo.findAll() );
	            return "departamento/update-dept";
	        }
	        
	        deptRepo.save(dept);
	        model.addAttribute("departamentos", deptRepo.findAll());
	        return "redirect:/departamento/ListaDept";
	    }
	 
	 @GetMapping("/eliminarDept/{idDept}")
	    public String eliminarDepartamentos(@PathVariable("idDept") int id, Model model) {
		 Departamento dept = deptRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid departamento Id:" + id));
		 deptRepo.delete(dept);
	        model.addAttribute("departamentos", deptRepo.findAll());
	        return "redirect:/departamento/ListaDept";
	    }
	 
}
