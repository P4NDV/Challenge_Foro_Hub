package com.forohub.domain.topico;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    boolean existsByTituloAndMensajeAndIdNot(String titulo, String mensaje, Long id);

    List<Topico> findAllByOrderByFechaCreacionAsc(Pageable pageable);
}
