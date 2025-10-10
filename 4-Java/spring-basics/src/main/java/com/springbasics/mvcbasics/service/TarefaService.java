package com.springbasics.mvcbasics.service;

import com.springbasics.mvcbasics.model.Tarefa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TarefaService {
    private final Map<Long, Tarefa> banco = new LinkedHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(1);

    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(banco.values());
    }

    public Tarefa criar(String titulo) {
        Long id = sequencia.getAndIncrement();
        Tarefa t = new Tarefa(id, titulo, false);
        banco.put(id, t);
        return t;
    }

    public Tarefa marcarConcluida(Long id) {
        Tarefa t = banco.get(id);
        t.setConcluida(true);
        return t;
    }
}
