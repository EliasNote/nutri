package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.*;
import com.ifpr.nutri.dto.refeicao.RefeicaoCreateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoUpdateDto;
import com.ifpr.nutri.mapper.RefeicaoMapper;
import com.ifpr.nutri.repository.PlanoAlimentarRepository;
import com.ifpr.nutri.repository.RefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ifpr.nutri.mapper.RefeicaoMapper.*;

@Service
public class RefeicaoService {

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private AlimentoService alimentoService;

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;

    public RefeicaoResponseDto create(RefeicaoCreateDto dto) {
        Pessoa pessoa = pessoaService.findByCpf(dto.pessoaCpf());
        PlanoAlimentar planoAlimentar = planoAlimentarRepository.findById(dto.planoId()).orElseThrow(() -> new RuntimeException("Não achado"));

        Refeicao refeicao = new Refeicao(null, pessoa, null, planoAlimentar, dto.data(), Refeicao.Tipo.valueOf(dto.tipo()));

        List<ItemAlimento> itensAlimentos = dto.itens().stream().map(
                x -> {
                    Alimento temp = alimentoService.findById(x.alimentoId());
                    ItemAlimento item = new ItemAlimento(temp, x.quantidade());
                    item.setRefeicao(refeicao);
                    return item;
                }
        ).toList();

        refeicao.setItens(itensAlimentos);

        return toResponseDto(refeicaoRepository.save(refeicao));
    }

    public Refeicao findById(Long id) {
        return refeicaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Refeição não encontrada"));
    }

    public List<Refeicao> findAllByIdAndPessoaCpf(List<Long> ids, String cpf) {
        return refeicaoRepository.findAllByIdInAndPessoaCpf(ids, cpf);
    }

    public List<RefeicaoResponseDto> findAll() {
        return refeicaoRepository.findAll().stream().map(RefeicaoMapper::toResponseDto).toList();
    }

    public void update(Long id, RefeicaoUpdateDto dto) {
        Refeicao refeicao = findById(id);
        List<ItemAlimento> itensAlimentos = dto.itens().stream().map(
                x -> {
                    Alimento temp = alimentoService.findById(x.alimentoId());
                    ItemAlimento item = new ItemAlimento(temp, x.quantidade());
                    item.setRefeicao(refeicao);
                    return item;
                }
        ).toList();
        refeicaoRepository.save(updateFromDto(refeicao, itensAlimentos, dto));
    }

    public void delete(Long id) {
        refeicaoRepository.deleteById(id);
    }
}
