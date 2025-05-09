package com.ifpr.nutri.dto.refeicao;

import com.ifpr.nutri.dto.alimento.ItemAlimentoResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record RefeicaoResponseDto(
        Long id,
        String pessoaCpf,
        LocalDateTime data,
        String tipo,
        List<ItemAlimentoResponseDto> itens
) {
}