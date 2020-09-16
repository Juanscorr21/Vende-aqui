package co.com.springboot.Controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.springboot.Repository.AdminRepository;
import co.com.springboot.domain.Administrador;


@Controller
public class AdministradorController {

	@Autowired
	private AdminRepository  adminRepo;
	/*
	@GetMapping("/inicioAdminF")
	public String inicoFomularioAdministradorF(Administrador admin) {
	       return "administrador/indexAdmin";
	  }
	@GetMapping("/inicioAdminA")
	public String inicoFomularioAdministradorA(Administrador admin) {
	       return "administrador/update-admin";
	  }*/
	@GetMapping("/inicioAdmin")
	public String inicoFomularioAdministrador(Administrador admin) {
	       return "administrador/add-admin";
	  }
	@PostMapping("/agregarAdmin")
	  public String agregarAdmin(@Valid Administrador admin, BindingResult resultado, Model model) {

	    if (resultado.hasErrors()) {
	            return "administrador/add-admin";
	     }
	        
	    adminRepo.save(admin);
	        model.addAttribute("admin", adminRepo.findAll());
	        return "administrador/indexAdmin";
	    }
	    
	 
	 @GetMapping("/editAdmin/{idAdmin}")
	 public String editAdmin(@PathVariable("idAdmin") int id, Model model) {
		 Administrador admin = adminRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid administrador Id:" + id));
	        model.addAttribute("administrador", admin);
	        return "administrador/update-admin";
	    }
	 
	 @PostMapping("/actualizarAdmin/{idAdmin}")
	    public String actulizarAdministrador(@PathVariable("idAdmin") int id, @Valid Administrador admin, BindingResult resultado, Model model) {
	        if (resultado.hasErrors()) {
	        	admin.setIdAdmin(id);
	            return "administrador/update-admin";
	        }
	        
	        adminRepo.save(admin); 
	        model.addAttribute("administradores", adminRepo.findAll());
	        return "administrador/indexAdmin";
	    }
	 
	 @GetMapping("/eliminarAdmin/{idAdmin}")
	    public String eliminarAdministrador(@PathVariable("idAdmin") int id, Model model) {
		 Administrador admin = adminRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid administrador Id:" + id));
		 adminRepo.delete(admin);
	        model.addAttribute("administradores", adminRepo.findAll());
	        return "administrador/indexAdmin";
	    }
	 
	
}
