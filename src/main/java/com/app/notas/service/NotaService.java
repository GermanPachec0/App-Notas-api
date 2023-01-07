package com.app.notas.service;

import com.app.notas.models.Nota;
import com.app.notas.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface NotaService {
    List<Nota> findByUsuario(Long id,int numeroDePagina,int medidaDePagina);

    void deleteNotaById(Long id);

    Nota editarNota(Nota nota);

    Nota agregarNota(Nota nota);

    Optional<Nota> getNotaByIdAndIdUsuario(Nota nota, Optional<Usuario> usuario);

}
