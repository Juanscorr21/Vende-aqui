package co.com.springboot.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Anuncio;


@Repository
public interface AnuncioRepository extends CrudRepository<Anuncio, Integer> {

	Anuncio findByidAnuncio(int id_Anuncio);
	List<Anuncio> findByTitulo(String titulo);
	
	
	
	
	@Query("SELECT a FROM Anuncio a WHERE a.usuario.nombreUsuario = :nombreUsuario")
	List<Anuncio> findAllAnuncioByUsuario(@Param("nombreUsuario")  String nombreUsuario);
	
}
