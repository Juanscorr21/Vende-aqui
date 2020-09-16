package co.com.springboot.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Departamento;
import co.com.springboot.domain.Municipio;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {
	Municipio findByidMunicipio(int id_municipio);
	List<Municipio> findByNombre(String nombre);
	
	
	

	
	 	
}
