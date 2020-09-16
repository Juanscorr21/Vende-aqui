package co.com.springboot.Repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Mensaje;

@Repository
public interface MensajeRepository extends CrudRepository<Mensaje, Integer> {
	Mensaje findByIdMensaje(int id_mensaje); 
	List<Mensaje> findByFechaMensaje(Date fecha_mensaje);
	
}
