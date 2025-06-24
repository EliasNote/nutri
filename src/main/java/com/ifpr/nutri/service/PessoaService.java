package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.repository.PessoaRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void create(Pessoa pessoa) {
        pessoa.setSenha(BCrypt.hashpw(pessoa.getSenha(), BCrypt.gensalt()));
        pessoaRepository.save(pessoa);
    }

    public boolean autenticar(String cpf, String senha) {
        Pessoa pessoa = findByCpf(cpf);
        if (pessoa != null) {
            return BCrypt.checkpw(senha, pessoa.getSenha());
        }
        return false;
    }

    public Pessoa findByCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }
}
