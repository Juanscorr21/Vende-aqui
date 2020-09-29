package co.com.springboot.Repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Mensaje;

@Repository
public interface MensajeRepository extends CrudRepository<Mensaje, Integer> {

	@Query("SELECT m FROM Mensaje m WHERE m.usuarioDestino=?1 order by idMensaje")
	List<Mensaje> consultarTodosPorUsuarioDestino(String name);
	
	@Query("SELECT m FROM Mensaje m WHERE m.usuarioOrigen=?1 order by idMensaje")
	List<Mensaje> consultarTodosPorusuarioOrigen(String name);
	
	
	

}
