package com.ifpr.nutri.repository;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dao.Refeicao.Tipo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class RefeicaoRepositoryTests {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void deveSalvarRefeicao() {
        Pessoa pessoa = criarPessoa();
        Refeicao refeicao = new Refeicao();
        refeicao.setData(LocalDateTime.now());
        refeicao.setTipo(Tipo.ALMOCO);
        refeicao.setPessoa(pessoa);

        Refeicao salva = refeicaoRepository.save(refeicao);

        assertThat(salva.getId()).isNotNull();
        assertThat(salva.getPessoa()).isEqualTo(pessoa);
    }

    @Test
    void deveBuscarTodasRefeicoes() {
        Pessoa pessoa = criarPessoa();
        Refeicao r1 = new Refeicao();
        r1.setData(LocalDateTime.now());
        r1.setTipo(Tipo.JANTAR);
        r1.setPessoa(pessoa);

        Refeicao r2 = new Refeicao();
        r2.setData(LocalDateTime.now());
        r2.setTipo(Tipo.CAFE_DA_MANHA);
        r2.setPessoa(pessoa);

        refeicaoRepository.save(r1);
        refeicaoRepository.save(r2);

        List<Refeicao> lista = refeicaoRepository.findAll();
        assertThat(lista).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void deveAtualizarRefeicao() {
        Pessoa pessoa = criarPessoa();
        Refeicao refeicao = new Refeicao();
        refeicao.setData(LocalDateTime.now());
        refeicao.setTipo(Tipo.LANCHE);
        refeicao.setPessoa(pessoa);

        refeicao = refeicaoRepository.save(refeicao);
        refeicao.setTipo(Tipo.ALMOCO);
        Refeicao atualizada = refeicaoRepository.save(refeicao);

        assertThat(atualizada.getTipo()).isEqualTo(Tipo.ALMOCO);
    }

    @Test
    void deveExcluirRefeicao() {
        Pessoa pessoa = criarPessoa();
        Refeicao refeicao = new Refeicao();
        refeicao.setData(LocalDateTime.now());
        refeicao.setTipo(Tipo.ALMOCO);
        refeicao.setPessoa(pessoa);

        refeicao = refeicaoRepository.save(refeicao);
        refeicaoRepository.delete(refeicao);

        boolean existe = refeicaoRepository.findById(refeicao.getId()).isPresent();
        assertThat(existe).isFalse();
    }

    private Pessoa criarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");
        pessoa.setUsername("teste");
        pessoa.setSenha("123456");
        pessoa.setCpf("00011122233");
        return pessoaRepository.save(pessoa);
    }
}
