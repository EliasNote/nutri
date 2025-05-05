package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoRepository alimentoRepository;

    // POST /alimentos
    @PostMapping
    public Alimento criarAlimento(@RequestBody Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    // GET /alimentos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Alimento> buscarPorId(@PathVariable Long id) {
        return alimentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
