package co.com.springboot.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.com.springboot.domain.Pais;

@Repository
public interface PaisRepository extends CrudRepository<Pais, Integer> {
	Pais findByIdPais(int id_Pais);
	//List<Pais> findBynombre(String nombre);
	/*
	@Query("SELECT p.nombre, d.nombre FROM Pais p JOIN p.departamento WHERE m.nombre= : nombre")
	public Pais findBynombre(@Param("nombre") String nombre); 
	*/
}
