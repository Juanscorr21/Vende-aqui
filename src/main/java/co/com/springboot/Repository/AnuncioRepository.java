package co.com.springboot.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Anuncio;
import co.com.springboot.domain.Usuario;


@Repository
public interface AnuncioRepository extends CrudRepository<Anuncio, Integer> {

	Anuncio findByidAnuncio(int id_Anuncio);
	List<Anuncio> findByTitulo(String titulo);
	
	@Query("SELECT a FROM Anuncio a WHERE UPPER(a.titulo) LIKE :titulo OR UPPER(a.descripcion) LIKE :descripcion OR UPPER(a.subCategoria.nombre) LIKE :subCategoria")
	List<Anuncio> findByTitulo (@Param("titulo") String titulo,@Param("descripcion") String descripcion,@Param("subCategoria") String subCategoria);
	
	
	@Query("SELECT a FROM Anuncio a WHERE a.usuario.nombreUsuario = :nombreUsuario")
	List<Anuncio> findAllAnuncioByUsuario(@Param("nombreUsuario")  String nombreUsuario);
	
	
	@Query("SELECT a FROM Anuncio a WHERE a.usuario.dni = :dni")
	List<Anuncio> findAllByUsuario(@Param("dni")  int dni);

	@Query("SELECT a FROM Anuncio a WHERE a.subCategoria.categoria.nombre = :categoria")
	List<Anuncio> findAllAnuncioBySubcategoria(@Param("categoria")  String categoria);
	
}
