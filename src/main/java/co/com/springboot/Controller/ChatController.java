package co.com.springboot.Controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.com.springboot.Repository.ChatRepository;
import co.com.springboot.domain.Chat;

@Controller
public class ChatController {

	@Autowired
	private ChatRepository chatRepo;
	
	@GetMapping("/accerderChat")
	   public String accerderChat(Chat chat) {
	       return "add-chat";
	  }
	
	@PostMapping("/agregarChat")
	  public String agregarChat(@Valid Chat chat, BindingResult resultado, Model model) {

	    if (resultado.hasErrors()) {
	            return "add-chat";
	     }
	        
	    chatRepo.save(chat);
	        model.addAttribute("chats", chatRepo.findAll());
	        return "indexChat";
	    }
	    

	 
	 @GetMapping("/eliminarChat/{idChat}")
	  public String eliminarChat(@PathVariable("idChat") int id, Model model) {
		 Chat chat = chatRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid chat Id:" + id));
		 chatRepo.delete(chat);
	        model.addAttribute("mensajes", chatRepo.findAll());
	        return "indexcChat";
	    }
	
}
