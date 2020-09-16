package co.com.springboot.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Anuncio;

@Repository
public interface AnuncioRepository extends CrudRepository<Anuncio, Integer> {

	Anuncio findByidAnuncio(int id_Anuncio);
	List<Anuncio> findByTitulo(String titulo);
	
}
