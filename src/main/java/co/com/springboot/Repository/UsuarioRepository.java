package co.com.springboot.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import co.com.springboot.domain.Usuario;


@Repository
public interface UsuarioRepository  extends CrudRepository<Usuario, Integer>{
	//Usuario findByNombreUsuario1(String username);
	Usuario findByDni(int id_usuario);
	Optional<Usuario> findByNombreUsuario(String username);
	Usuario findByEmail(String email);

}
