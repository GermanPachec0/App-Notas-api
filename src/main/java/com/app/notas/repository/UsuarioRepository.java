package com.app.notas.repository;

import com.app.notas.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    @Query(value ="Select * from usuario where email = :email",nativeQuery = true)
    Usuario findUserByEmail(String email);

    public Optional<Usuario> findByEmail(String email);

    public  Optional<Usuario> findByUsernameOrEmail(String username,String email);

    public  Optional<Usuario> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String username);


}
