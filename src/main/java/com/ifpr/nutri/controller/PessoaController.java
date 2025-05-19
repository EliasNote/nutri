package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dto.LoginDto;
import com.ifpr.nutri.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Pessoa pessoa) {
        pessoaService.create(pessoa);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(pessoaService.autenticar(loginDto.cpf(), loginDto.senha()));
    }
}
