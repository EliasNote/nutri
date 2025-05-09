package com.ifpr.nutri.mapper;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dao.Refeicao;
import com.ifpr.nutri.dao.ItemAlimento;
import com.ifpr.nutri.dto.alimento.ItemAlimentoResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoResponseDto;
import com.ifpr.nutri.dto.refeicao.RefeicaoUpdateDto;

import java.util.List;

public class RefeicaoMapper {

    public static RefeicaoResponseDto toResponseDto(Refeicao refeicao) {
        List<ItemAlimentoResponseDto> itens = refeicao.getItens().stream()
                .map(x -> {
                    return new ItemAlimentoResponseDto(
                            x.getAlimento().getId(),
                            x.getQuantidade());
                }).toList();

        return new RefeicaoResponseDto(
                refeicao.getId(),
                refeicao.getPessoa().getCpf(),
                refeicao.getData(),
                refeicao.getTipo().name(),
                itens
        );
    }

    public static Refeicao updateFromDto(Refeicao refeicao, RefeicaoUpdateDto dto) {
        if (dto.data() != null) {
            refeicao.setData(dto.data());
        }
        if (dto.tipo() != null) {
            refeicao.setTipo(Refeicao.Tipo.valueOf(dto.tipo()));
        }
        if (dto.itens() != null) {
            List<ItemAlimento> itens = dto.itens().stream()
                    .map(itemDto -> {
                        ItemAlimento item = new ItemAlimento();
                        Alimento alimento = new Alimento();
                        alimento.setId(itemDto.alimentoId());
                        item.setAlimento(alimento);
                        item.setQuantidade(itemDto.quantidade());
                        item.setRefeicao(refeicao);
                        return item;
                    })
                    .toList();
            refeicao.setItens(itens);
        }
        return refeicao;
    }
}