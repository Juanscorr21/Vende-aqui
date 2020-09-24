package co.com.springboot.Repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Anuncio;
import co.com.springboot.domain.Subcategoria;




@Repository
public interface SubCategoriaRepository extends CrudRepository<Subcategoria, Integer> {
	
	Subcategoria findByIdSubcategoria(int id_subcategoria);
	
	Optional<Subcategoria>  findByNombre(String nombre);
	
	@Query("SELECT distinct(s.categoria.nombre) FROM Subcategoria s")
	List<String> findAllCategoria();
	
	@Query("SELECT s From Subcategoria s WHERE s.categoria.nombre LIKE :nombre ")
	List<Subcategoria> findAllSubcategoriaByCategoria(@Param("nombre") String categoria);
	
	@Query("SELECT s From Subcategoria s WHERE s.categoria.idCategoria = :idCategoria")
	List<Subcategoria> findAllByCategoria(@Param("idCategoria")  int idCategoria);
	
	

}
