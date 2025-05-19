package com.ifpr.nutri.dto.refeicao;

import com.ifpr.nutri.dto.alimento.ItemAlimentoDto;

import java.time.LocalDateTime;
import java.util.List;

public record RefeicaoUpdateDto(
        LocalDateTime data,
        String tipo,
        List<ItemAlimentoDto> itens
) {
}
