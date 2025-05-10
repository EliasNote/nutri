package com.ifpr.nutri.controller;

import com.ifpr.nutri.dto.plano.PlanoAlimentarCreateDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.service.PlanoAlimentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plano")
public class PlanoAlimentarController {

    @Autowired
    private PlanoAlimentarService planoAlimentarService;

    @PostMapping
    public ResponseEntity<PlanoAlimentarResponseDto> create(@RequestBody PlanoAlimentarCreateDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planoAlimentarService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PlanoAlimentarResponseDto>> findAll() {
        return ResponseEntity.ok(planoAlimentarService.findAll());
    }
}
