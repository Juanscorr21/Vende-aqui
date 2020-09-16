package co.com.springboot.Repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.springboot.domain.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {

	//Categoria findByIdCategoria(int id__Categoria);

	Optional<Categoria> findByNombre(String nombre);
	
	

}
