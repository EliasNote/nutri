package com.ifpr.nutri.dto.refeicao;

import com.ifpr.nutri.dto.alimento.ItemAlimentoCreateDto;

import java.time.LocalDateTime;
import java.util.List;

public record RefeicaoResponseDto(
        Long id,
        LocalDateTime data,
        String tipo,
        List<ItemAlimentoCreateDto> itens
) {
}