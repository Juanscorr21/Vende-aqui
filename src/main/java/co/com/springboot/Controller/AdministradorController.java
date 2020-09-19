package co.com.springboot.Controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.springboot.Repository.AdminRepository;
import co.com.springboot.Repository.AuthorityRepository;
import co.com.springboot.Repository.UsuarioRepository;
import co.com.springboot.domain.Administrador;
import co.com.springboot.domain.Authority;
import co.com.springboot.domain.Usuario;
import co.com.springboot.util.Passgenerator;


@Controller
public class AdministradorController {

	@Autowired
	private UsuarioRepository adminRepo;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	 @Autowired
	 Passgenerator passgenerator;
	
	/*
	@GetMapping("/listaAdmin")
	public String inicoFomularioAdministradorF(Administrador admin) {
	       return "administrador/indexAdmin";
	  }
	*/
	@GetMapping("admin/inicioAdmin")
	public String inicoFomularioAdministrador(Usuario usuario,Model model) {
	       return "administrador/add-admin";
	  }
	@PostMapping("admin/agregarAdmin")
	  public String agregarAdmin(@Valid Usuario usuario,BindingResult resultado, Model model) {
		
		/**Optional<Usuario> nombreExist = adminRepo.findByNombreUsuario(usuario.getNombreUsuario());		
		if(nombreExist.isPresent()) {
						
				model.addAttribute("nombreUsuario","Nombre de usuario ya esta en uso");				
				  return "administrador/add-admin";
			
			}*/
		
		if (!usuario.getContrasena().equals(usuario.getConficontrasena())) {			
			model.addAttribute("errorPassword", "sdsfsd");

			  return "administrador/add-admin";
	     }

	 	if (resultado.hasErrors()) {

	 		  return "administrador/add-admin";
	       
	    }
	 	
	 	 Authority autorizacion= authorityRepository.findByAuthority("ROLE_ADMIN");
       
	        Set<Authority> authority= new HashSet<Authority>();
	        authority.add(autorizacion);
	        
	        usuario.setAuthority(authority);
	            
	        // El String que mandamos al metodo encode es el password que queremos
			// encriptar.
	        usuario.setContrasena(passgenerator.enciptarPassword(usuario.getContrasena()));
	        
	        System.out.println("este es el usuario que vamos a registrar"+ usuario.toString());
		   
	        adminRepo.save(usuario);
			model.addAttribute("usuarios", adminRepo.findAll());
			 return "redirect:/";
	}
	    
	 
	 @GetMapping("admin/editAdmin/{idAdmin}")
	 public String editU(@PathVariable("dni") int dni, Model model) {
		 Usuario admin = adminRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Id: " + dni + " del usuario es invalido"));
		 model.addAttribute("usuario", admin);
	     return "administrador/update-admin";
	
	 }
	 
	 @PostMapping("admin/actualizarAdmin/{idAdmin}")
	    public String actulizarAdministrador(@PathVariable("idAdmin") int id, @Valid Usuario admin, BindingResult resultado, Model model) {
		 
		 	/**Optional<Usuario> nombreExist = adminRepo.findByNombreUsuario(admin.getNombreUsuario());		
			if(nombreExist.isPresent()) {
							
					model.addAttribute("nombreUsuario","Nombre de usuario ya esta en uso");				
					 return "administrador/update-admin";
				
				}*/
			
			if (!admin.getContrasena().equals(admin.getConficontrasena())) {			
				model.addAttribute("errorPassword", "sdsfsd");

				 return "administrador/update-admin";
		     }
	        if (resultado.hasErrors()) {
	        	admin.setDni(id);
	            return "administrador/update-admin";
	        }
	        
	        adminRepo.save(admin); 
	        model.addAttribute("administradores", adminRepo.findAll());
	        return "redirect:/";
	    }
	 
	 @GetMapping("admin/eliminarAdmin/{idAdmin}")
	    public String eliminarAdministrador(@PathVariable("idAdmin") int id, Model model) {
		 Usuario admin = adminRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid administrador Id:" + id));
		 adminRepo.delete(admin);
	        model.addAttribute("administradores", adminRepo.findAll());
	        return "administrador/indexAdmin";
	    }
	 
	
}
