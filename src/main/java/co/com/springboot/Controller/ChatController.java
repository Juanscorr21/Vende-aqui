package co.com.springboot.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.springboot.Repository.AnuncioRepository;
import co.com.springboot.Repository.ChatRepository;
import co.com.springboot.Repository.MensajeRepository;
import co.com.springboot.domain.Anuncio;
import co.com.springboot.domain.Chat;
import co.com.springboot.domain.Mensaje;

@Controller
public class ChatController {

	@Autowired
	private ChatRepository chatRepo;
	
	@Autowired
	private AnuncioRepository anuncioRepo;
	
	@Autowired
	private MensajeRepository mensajeRepo;
		 
	 @PostMapping("/addMensaje/{idAnuncio}")
		public String agregar(@PathVariable("idAnuncio") String anuncio, String mensaje, Principal principla, Model model) {
			String[] a=anuncio.split("-");
			Anuncio anunciou = anuncioRepo.findById(Integer.parseInt(a[0]))
					.orElseThrow(() -> new IllegalArgumentException("Invalid anuncio Id:" + a[0]));
			
			
			List<Anuncio> anuncios =anuncioRepo.findAllAnuncioBySubcategoria(anunciou.getSubCategoria());
			model.addAttribute("anuncio", anunciou);
			Mensaje m = new Mensaje();
			m.setUsuarioDestino(a[1]);
			m.setUsuarioOrigen(principla.getName());
			m.setMensaje(mensaje);
			mensajeRepo.save(m);
			
		
			model.addAttribute("anuncios", anuncios );

			return "Anuncio/indexAnuncioChat";

		}
		
		@GetMapping("/consultarTodos")	
	    public String editarChat(Principal principal, Model model) {
		
			List<Mensaje> mEnviados=mensajeRepo.consultarTodosPorUsuarioDestino(principal.getName());
			List<Mensaje> mre=mensajeRepo.consultarTodosPorusuarioOrigen(principal.getName());	

			model.addAttribute("mensajesTodos",mEnviados);
			model.addAttribute("mensajesEnviado",mre);

			return "Mensajes/mensajes";
			
		}
		
		 @PostMapping("/reponderMensajes")
		 public String reponderMensaje(Model model,Principal principal,String destino,String mensaje) {
			 
			 Mensaje m = new Mensaje();
				m.setUsuarioOrigen(principal.getName());
				m.setUsuarioDestino(destino);
				m.setMensaje(mensaje);
				mensajeRepo.save(m);
			     return "redirect:/consultarTodos";
		 }
	
}
