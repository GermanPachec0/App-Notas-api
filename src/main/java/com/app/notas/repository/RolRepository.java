package com.app.notas.repository;

import com.app.notas.models.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepository extends CrudRepository<Rol,Long> {
    public Optional<Rol> findByNombre(String nombre);
}
