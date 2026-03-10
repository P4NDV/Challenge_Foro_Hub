package com.forohub.controller;

import com.forohub.domain.topico.Topico;
import com.forohub.domain.topico.TopicoRepository;
import com.forohub.domain.topico.dto.DatosActualizarTopico;
import com.forohub.domain.topico.dto.DatosRegistroTopico;
import com.forohub.domain.topico.dto.DatosRespuestaTopico;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un topico con el mismo titulo y mensaje");
        }

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso());
        topicoRepository.save(topico);

        URI location = URI.create("/topicos/" + topico.getId());
        return ResponseEntity.created(location).body(new DatosRespuestaTopico(topico));
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listar(
            @RequestParam(defaultValue = "10") int limite
    ) {
        int safeLimit = Math.min(Math.max(limite, 1), 100);
        List<DatosRespuestaTopico> respuesta = topicoRepository
                .findAllByOrderByFechaCreacionAsc(PageRequest.of(0, safeLimit))
                .stream()
                .map(DatosRespuestaTopico::new)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallar(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado"));

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(@PathVariable Long id,
                                                           @RequestBody @Valid DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado"));

        if (topicoRepository.existsByTituloAndMensajeAndIdNot(datos.titulo(), datos.mensaje(), id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un topico con el mismo titulo y mensaje");
        }

        topico.actualizar(datos.titulo(), datos.mensaje(), datos.autor(), datos.curso(), datos.status());

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Topico no encontrado");
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
