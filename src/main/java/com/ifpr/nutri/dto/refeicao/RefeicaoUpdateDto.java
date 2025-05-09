package com.ifpr.nutri.dto.refeicao;

import java.time.LocalDateTime;
import java.util.List;

public record RefeicaoUpdateDto(
        String pessoaCpf,
        LocalDateTime data,
        String tipo,
        List<ItemAlimentoDto> itens
) {
    public record ItemAlimentoDto(
            Long alimentoId,
            Double quantidade
    ) {}
}