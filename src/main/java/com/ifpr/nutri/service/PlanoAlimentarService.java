package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.Pessoa;
import com.ifpr.nutri.dao.PlanoAlimentar;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dto.plano.PlanoAlimentarCreateDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarUpdateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import com.ifpr.nutri.mapper.PlanoMapper;
import com.ifpr.nutri.mapper.RefeicaoMapper;
import com.ifpr.nutri.repository.PlanoAlimentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoAlimentarService {

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private RefeicaoService refeicaoService;

    public PlanoAlimentarResponseDto create(PlanoAlimentarCreateDto dto) {
        Pessoa pessoa = pessoaService.findByCpf(dto.pessoaCpf());

        PlanoAlimentar plano = new PlanoAlimentar(
                null, pessoa, null, dto.dataInicio(), dto.dataFim(), dto.observacoes()
        );

        return PlanoMapper.toResponseDto(planoAlimentarRepository.save(plano));
    }

    public List<PlanoAlimentarResponseDto> findAll() {
        return planoAlimentarRepository.findAll().stream().map(PlanoMapper::toResponseDto).toList();
    }

    public void delete(Long id) {
        planoAlimentarRepository.deleteById(id);
    }

    public void update(Long id, PlanoAlimentarUpdateDto dto) {
        PlanoAlimentar plano = planoAlimentarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plano alimentar não encontrado"));

        List<Refeicao> refeicoes = refeicaoService.findAllByIdAndPessoaCpf(dto.refeicoesIds(), plano.getPessoa().getCpf());

        planoAlimentarRepository.save(
                PlanoMapper.updateFromDto(plano, refeicoes, dto)
        );
    }
}
