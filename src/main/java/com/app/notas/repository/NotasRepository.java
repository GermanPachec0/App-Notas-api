package com.app.notas.repository;

import com.app.notas.models.Nota;
import com.app.notas.models.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotasRepository extends CrudRepository<Nota,Long> {
        @Query(value = "SELECT * FROM nota where id_usuario = :id",nativeQuery = true)
        List<Nota> findByUsuario(Long id);

}
