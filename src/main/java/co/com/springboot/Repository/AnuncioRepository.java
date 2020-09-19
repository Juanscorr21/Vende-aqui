package co.com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Anuncio;
import co.com.springboot.domain.Usuario;

@Repository
public interface AnuncioRepository extends CrudRepository<Anuncio, Integer> {

	Anuncio findByidAnuncio(int id_Anuncio);
	List<Anuncio> findByTitulo(String titulo);
	
	
	@Query("SELECT a FROM Anuncio a WHERE a.usuario = :usuario")
	List<Anuncio> findAllAnuncioByUsuario(@Param("usuario")  Usuario usuario);
	
}
