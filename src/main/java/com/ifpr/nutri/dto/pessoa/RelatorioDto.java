package com.ifpr.nutri.dto.pessoa;

import java.time.LocalDate;

public record RelatorioDto(
        String nome,
        LocalDate inicio,
        LocalDate fim,
        TotaisNutricionaisDto totaisNutricionais,
) {
}
