package com.ifpr.nutri.controller;

import com.ifpr.nutri.dto.plano.PlanoAlimentarDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarUpdateDto;
import com.ifpr.nutri.service.PlanoAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarService planoAlimentarService;

    @PostMapping
    public ResponseEntity<PlanoAlimentarResponseDto> create(@RequestBody PlanoAlimentarDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanoAlimentarResponseDto>> findAll() {
        return ResponseEntity.ok(planoAlimentarService.findAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PlanoAlimentarUpdateDto dto) {
        planoAlimentarService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        planoAlimentarService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
