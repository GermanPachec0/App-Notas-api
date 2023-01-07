package com.app.notas.repository;

import com.app.notas.models.Nota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotasRepository extends CrudRepository<Nota,Long> {
        @Query(value = "SELECT * FROM nota where id_usuario = :id",nativeQuery = true)
        Page<Nota> findByUsuario(Long id, Pageable page);

        Optional<Nota>  findNotaByIdNotaAndIdUsuario(Long idNota, Long idUsuario);

}
