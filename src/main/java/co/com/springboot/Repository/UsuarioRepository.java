package co.com.springboot.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import co.com.springboot.domain.Usuario;


@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Integer>{
	//Usuario findByNombreUsuario1(String username);
	Usuario findByDni(int id_usuario);
	Usuario findByNombreUsuario(String username);
	String findAllByEmail(String string);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre")
	Usuario findAll(@Param("nombre")  Usuario usuario);

}
