package co.com.springboot.Controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import co.com.springboot.Repository.AuthorityRepository;

//import org.springframework.web.bind.annotation.RequestMapping;
import co.com.springboot.Repository.UsuarioRepository;
import co.com.springboot.domain.Authority;
import co.com.springboot.domain.Categoria;
import co.com.springboot.domain.Usuario;
import co.com.springboot.util.Passgenerator;


@Controller
//@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository userRepo;

	@Autowired
	private AuthorityRepository authorityRepository;
	
	 @Autowired
	 Passgenerator passgenerator;
	
	
	String Mensaje = "No coincide con la contrasena";
	
	
	
	
	@GetMapping("/inicioU")
	   public String inicoFomularioUsuario(Usuario usuario,Model model) {
		model.addAttribute("usuarios", userRepo.findAll());

	       return "Usuario/add-usuario2";
	  }

	@GetMapping("/inicioUser")
	   public String inicoFomu(Principal pricipal, Model model) {
		
		Usuario usuario = userRepo.findAll(pricipal.getName());
		model.addAttribute("usuario", usuario);
	       return "Usuario/perfilUser";
	  }

	 
	@PostMapping("/agregarU")
	  public String agregarUsuario(@Valid Usuario usuario,BindingResult resultado, Model model) {
		
		/**Optional<Usuario> nombreExist = userRepo.findByNombreUsuario(usuario.getNombreUsuario());		
		if(nombreExist.isPresent()) {
						
				model.addAttribute("nombreUsuario","Nombre de usuario ya esta en uso");				
				return  "Usuario/add-usuario2";
			
			}*/
		
		if (!usuario.getContrasena().equals(usuario.getConficontrasena())) {			
 			model.addAttribute("errorPassword", Mensaje);

			 return  "Usuario/add-usuario2";
	     }

	 	if (resultado.hasErrors()) {

	       return  "Usuario/add-usuario2";
	       
	     }
	 	
	 	
	     
	    	 
        Authority autorizacion= authorityRepository.findByAuthority("ROLE_USER");
                    
        Set<Authority> authority= new HashSet<Authority>();
        authority.add(autorizacion);
        
        usuario.setAuthority(authority);
            
        // El String que mandamos al metodo encode es el password que queremos
		// encriptar.
        usuario.setContrasena(passgenerator.enciptarPassword(usuario.getContrasena()));
        
        System.out.println("este es el usuario que vamos a registrar"+ usuario.toString());
	   
	    userRepo.save(usuario);
		model.addAttribute("usuarios", userRepo.findAll());
		 return "redirect:/login";
      
	       
	   }
	    
	
	 
	 @GetMapping("/user/editU/{dni}")
	    public String editU(@PathVariable("dni") int dni, Model model) {
		 Usuario usuario = userRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Id: " + dni + " del usuario es invalido"));
	        model.addAttribute("usuario", usuario);
	        model.addAttribute("formulario", "active");
	        return "Usuario/updateU";
	    }
	 
	 @PostMapping("/user/actualizarU/{dni}")
	    public ModelAndView actulizarUsurio(@PathVariable("dni") int dni, @Valid Usuario usuario, BindingResult resultado, Model model) {
		 
		 
		
		 
	        if (resultado.hasErrors()) {
	        	usuario.setDni(dni);
	        	
			
	        	return new ModelAndView("Usuario/updateU");
	           
	        }	        
	        userRepo.save(usuario);
	        model.addAttribute("usarios", userRepo.findAll());
	        return new ModelAndView("redirect:"+ "/inicioUser");
	    }
	 
	 @GetMapping("/admin/eliminarU/{dni}")
	    public ModelAndView eliminarUsuario(@PathVariable("dni") int dni, Model model) {
		 Usuario usuario = userRepo.findById(dni).orElseThrow(() -> new IllegalArgumentException("Id: " + dni + " del usuario es invalido"));
	        userRepo.delete(usuario);
	        model.addAttribute("usuarios", userRepo.findAll());
	        return new ModelAndView("redirect:"+ "/inicioUser");
	    }
	 
}
