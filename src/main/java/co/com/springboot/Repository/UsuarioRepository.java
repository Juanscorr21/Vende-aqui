package co.com.springboot.Repository;


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
	
	@Query("SELECT u.dni FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
	Usuario  findAllByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	
	String findAllByEmail(String string);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
	Usuario findAll(@Param("nombreUsuario")  String nombreUsuario);

}
