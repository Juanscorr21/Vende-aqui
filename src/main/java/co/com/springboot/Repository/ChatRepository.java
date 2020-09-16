package co.com.springboot.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Chat;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {
	
	Chat findByIdChat(int id_Chat);



}
