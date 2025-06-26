package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dao.ItemAlimento;
import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dto.pessoa.RelatorioDto;
import com.ifpr.nutri.dto.pessoa.TotaisNutricionaisDto;
import com.ifpr.nutri.repository.PessoaRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public RelatorioDto generate(String cpf, LocalDate date) {
        Pessoa pessoa = findByCpf(cpf);

        LocalDateTime inicio = date.atStartOfDay();
        LocalDateTime fim = LocalDateTime.now();

        List<Refeicao> refeicoesPeriodo = pessoa.getRefeicoes().stream()
                .filter(r -> r.getData().isAfter(inicio) && r.getData().isBefore(fim))
                .toList();

        double totalCalorias = 0, totalProteinas = 0, totalCarboidratos = 0, totalGorduras = 0;
        Map<Alimento, Double> caloriasPorAlimento = new HashMap<>();

        for (Refeicao refeicao : refeicoesPeriodo) {
            for (ItemAlimento item : refeicao.getItens()) {
                Alimento alimento = item.getAlimento();
                double qtd = item.getQuantidade();
                totalCalorias += alimento.getCalorias() * qtd;
                totalProteinas += alimento.getProteinas() * qtd;
                totalCarboidratos += alimento.getCarboidratos() * qtd;
                totalGorduras += alimento.getGorduras() * qtd;
                caloriasPorAlimento.merge(alimento, alimento.getCalorias() * qtd, Double::sum);
            }
        }

        // Top 10 caloric foods
        List<Alimento> alimentosCaloricos = caloriasPorAlimento.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();

        // Calculate BMI (IMC)
        String imc = "";
        if (pessoa.getPeso() != null && pessoa.getAltura() != null && pessoa.getAltura() > 0) {
            double valorImc = pessoa.getPeso() / (pessoa.getAltura() * pessoa.getAltura());
            imc = String.format("%.2f", valorImc);
        }

        TotaisNutricionaisDto totaisNutricionais = new TotaisNutricionaisDto(
                totalCalorias, totalProteinas, totalCarboidratos, totalGorduras, imc
        );

        return new RelatorioDto(
                pessoa.getNome(),
                date,
                fim.toLocalDate(),
                totaisNutricionais,
                alimentosCaloricos
        );
    }

    public List<Alimento> getCaloricFoods(Pessoa pessoa, int limit) {
        Map<Alimento, Double> caloriasPorAlimento = new HashMap<>();

        for (Refeicao refeicao : pessoa.getRefeicoes()) {
            for (ItemAlimento item : refeicao.getItens()) {
                Alimento alimento = item.getAlimento();
                double caloriasConsumidas = alimento.getCalorias() * item.getQuantidade();
                caloriasPorAlimento.merge(alimento, caloriasConsumidas, Double::sum);
            }
        }

        return caloriasPorAlimento.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .toList();
    }
}
