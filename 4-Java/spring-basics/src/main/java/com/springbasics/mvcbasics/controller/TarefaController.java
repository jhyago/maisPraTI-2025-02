package com.springbasics.mvcbasics.controller;

import com.springbasics.mvcbasics.model.Tarefa;
import com.springbasics.mvcbasics.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return service.listarTarefas();
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(String titulo) {
        Tarefa t = service.criar(titulo);
        return ResponseEntity.ok(t);
    }

    @PutMapping
    public ResponseEntity<Tarefa> marcarConcluida(@RequestParam Long id) {
        service.marcarConcluida(id);
        return ResponseEntity.ok().build();
    }
}
