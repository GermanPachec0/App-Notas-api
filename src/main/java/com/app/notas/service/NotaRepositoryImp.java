package com.app.notas.service;

import com.app.notas.models.Nota;
import com.app.notas.repository.NotasRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaRepositoryImp implements NotaService {
    @Autowired
    NotasRepository notasRepository;

    @Override
    public List<Nota> findByUsuario(Long id) {
        return notasRepository.findByUsuario(id);
    }

    @Override
    public void deleteNotaById(Long id) {
          notasRepository.deleteById(id);
    }

    @Override
    public Nota editarNota(Nota nota) {
            return  notasRepository.save(nota);
    }

    @Override
    public Nota agregarNota(Nota nota) {
        return notasRepository.save(nota);
    }
}
