package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.service.PessoaService;
import com.ifpr.nutri.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> create(Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.create(pessoa));
    }
    @PatchMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(pessoaService.autenticar(loginDTO.getCpf(), loginDTO.getSenha()));

    }
}
