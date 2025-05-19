package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.PlanoAlimentar;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.dto.plano.PlanoAlimentarUpdateDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;

import java.util.List;

public class PlanoMapper {
    public static PlanoAlimentarResponseDto toResponseDto(PlanoAlimentar plano) {
        return new PlanoAlimentarResponseDto(
                plano.getId(),
                plano.getPessoa().getCpf(),
                plano.getRefeicoes().stream().map(RefeicaoMapper::toResponseDto).toList(),
                plano.getDataInicio(),
                plano.getDataFim(),
                plano.getObservacoes()
        );
    }

    public static PlanoAlimentar updateFromDto(PlanoAlimentar plano, List<Refeicao> refeicoes, PlanoAlimentarUpdateDto dto) {
        if (dto.dataInicio() != null) {
            plano.setDataInicio(dto.dataInicio());
        }
        if (dto.dataFim() != null) {
            plano.setDataFim(dto.dataFim());
        }
        if (dto.observacoes() != null) {
            plano.setObservacoes(dto.observacoes());
        }
        if (dto.refeicoesIds() != null && !dto.refeicoesIds().isEmpty()) {
            plano.getRefeicoes().clear();
            refeicoes.forEach(x -> plano.getRefeicoes().add(x));
        }
        return plano;
    }
}
