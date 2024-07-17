package com.proyectoDeAlura.FOROHUB.controller;

import com.proyectoDeAlura.FOROHUB.Topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody@Valid RegistroTopicos registroTopicos,
                                          UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = new Topico(registroTopicos);
        topico.setFechaDeCreacion(LocalDateTime.now());
        topicoRepository.save(topico);
        RespuestaTopico respuestaTopico = new RespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor(), topico.getCurso());

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuestaTopico);
    }

    @GetMapping
    public List<Topico> mostrarTopicos() {
        return topicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity mostrarTopicosPorId(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id).get();
        topico.setFechaDeCreacion(LocalDateTime.now());
        var topicoPorID = new ListadoTopico(topico.getTitulo(), topico.getMensaje(),
                topico.getAutor(), topico.getCurso(), topico.getFechaDeCreacion());
        return ResponseEntity.ok(topicoPorID);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody@Valid ListadoTopico listadoTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(listadoTopico);
        return ResponseEntity.ok(new RespuestaTopico(topico.getId(), topico.getTitulo(),
                topico.getMensaje(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

}
