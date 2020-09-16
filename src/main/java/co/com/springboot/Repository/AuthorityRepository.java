package co.com.springboot.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.com.springboot.domain.Authority;


@Repository
public interface AuthorityRepository extends CrudRepository<Authority, Long>  {
    public Authority findByAuthority(String authority);
}