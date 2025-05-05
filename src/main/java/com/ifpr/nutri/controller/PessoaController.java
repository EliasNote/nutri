package com.ifpr.nutri.controller;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    public ResponseEntity<Pessoa> create(Pessoa pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.create(pessoa));
    }
}
