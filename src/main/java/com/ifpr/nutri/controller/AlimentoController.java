package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dto.alimento.AlimentoResponseDto;
import com.ifpr.nutri.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @PostMapping
    public ResponseEntity<AlimentoResponseDto> criarAlimento(@RequestBody Alimento alimento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(alimentoService.create(alimento));
    }

    @GetMapping
    public ResponseEntity<List<AlimentoResponseDto>> buscarTodos() {
        return ResponseEntity.ok(alimentoService.findAll());
    }

}
