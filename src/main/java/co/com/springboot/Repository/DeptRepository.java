package co.com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Departamento;

@Repository
public interface DeptRepository extends CrudRepository<Departamento, Integer> {

	
	@Query("SELECT distinct(d.pais.nombre) FROM Departamento d")
	List<String> findAllPais();
	
	@Query("SELECT d.nombre FROM Departamento d WHERE d.pais.nombre = :pais")
	List<String> findAllDepartamentoByPais(@Param("pais")  String pais);
	
}
