package co.com.springboot.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Subcategoria;




@Repository
public interface SubCategoriaRepository extends CrudRepository<Subcategoria, Integer> {
	
	Subcategoria findByIdSubcategoria(int id_subcategoria);
	
	Optional<Subcategoria>  findByNombre(String nombre);
	/**@Query("SELECT distinct(s.categoria.nombre) From Subcategoria s")
	List<String> findAllCategoria();*/
	
	

}
