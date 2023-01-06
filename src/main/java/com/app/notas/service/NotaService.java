package com.app.notas.service;

import com.app.notas.models.Nota;

import java.util.List;

public interface NotaService {
    List<Nota> findByUsuario(Long id);

    void deleteNotaById(Long id);

    Nota editarNota(Nota nota);

    Nota agregarNota(Nota nota);

}
