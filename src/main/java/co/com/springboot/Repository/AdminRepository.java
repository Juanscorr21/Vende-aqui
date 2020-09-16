package co.com.springboot.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Administrador;

@Repository
public interface AdminRepository extends CrudRepository<Administrador, Integer> {

	Administrador findByidAdmin(int id_Administrador);
	List<Administrador> findByNombre(String nombre);
	List<Administrador> findByEmail(String email);
	List<Administrador> findByUserName(String userName);
	
}

