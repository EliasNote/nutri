package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dto.alimento.AlimentoResponseDto;

public class AlimentoMapper {

    public static AlimentoResponseDto toResponseDto(Alimento alimento) {
        return new AlimentoResponseDto(
                alimento.getId(),
                alimento.getNome(),
                alimento.getCalorias(),
                alimento.getProteinas(),
                alimento.getCarboidratos(),
                alimento.getGorduras(),
                alimento.getPorcao().toString()
        );
    }
}