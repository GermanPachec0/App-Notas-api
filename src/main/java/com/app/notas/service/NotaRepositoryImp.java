package com.app.notas.service;

import com.app.notas.dto.Pagination;
import com.app.notas.models.Nota;
import com.app.notas.models.Usuario;
import com.app.notas.repository.NotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

@Service
public class NotaRepositoryImp implements NotaService {
    @Autowired
    NotasRepository notasRepository;


    private  Integer totalPages;
    private  Long totalElementos;

    @Override
    public List<Nota> findByUsuario(Long id,int numeroDePagina,int medidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina,medidaDePagina);
        Page<Nota> notas = notasRepository.findByUsuario(id, pageable);
        totalPages = notas.getTotalPages();
        totalElementos = notas.getTotalElements();
        List<Nota> listNotas = notas.getContent();

        return listNotas;
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
        TelegramBoot telegramBoot = new TelegramBoot();
        Nota notaSaved = notasRepository.save(nota);
        telegramBoot.notificarNotaCreada(notaSaved);
        return notaSaved;

    }

    public Optional<Nota> getNotaByIdAndIdUsuario(Nota nota, Optional<Usuario> usuario) {
        return  notasRepository.findNotaByIdNotaAndIdUsuario(nota.getIdNota(),usuario.get().getIdUsuario());
    }

    public Pagination getPagesAndElements(){
            Pagination pagination = new Pagination();
            pagination.setTotalPages(totalPages);
            pagination.setTotalElements(totalElementos);
            return  pagination;
    }
}
