package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.PlanoAlimentar;
import com.ifpr.nutri.dto.plano.PlanoAlimentarResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;

import java.util.List;

public class PlanoMapper {
    public static PlanoAlimentarResponseDto toResponseDto(
            PlanoAlimentar plano,
            List<RefeicaoResponseDto> refeicoesDto
    ) {
        return new PlanoAlimentarResponseDto(
                plano.getId(),
                plano.getPessoa().getCpf(),
                refeicoesDto,
                plano.getDataInicio(),
                plano.getDataFim(),
                plano.getObservacoes()
        );
    }

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
}
