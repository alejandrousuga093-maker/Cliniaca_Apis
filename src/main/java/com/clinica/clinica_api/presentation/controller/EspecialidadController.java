package com.clinica.clinica_api.presentation.controller;

import com.clinica.clinica_api.domain.model.Especialidad;
import com.clinica.clinica_api.domain.repository.EspecialidadRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadRepository repo;

    public EspecialidadController(EspecialidadRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Especialidad> listar() {
        return repo.listarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Especialidad crear(@RequestBody Especialidad esp) {
        Especialidad nueva = new Especialidad(esp.getNombre(), esp.getDescripcion());
        repo.guardar(nueva);
        return nueva;
    }

    @PatchMapping("/{id}/estado")
    public Especialidad cambiarEstado(@PathVariable String id) {
        Especialidad esp = repo.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada"));
        if (esp.isActiva()) esp.desactivar();
        else esp.activar();
        return esp;
    }
}