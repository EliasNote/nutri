package com.ifpr.nutri.dto.pessoa;

import com.ifpr.nutri.dao.Alimento;

import java.time.LocalDate;
import java.util.List;

public record RelatorioDto(
        String nome,
        LocalDate inicio,
        LocalDate fim,
        TotaisNutricionaisDto totaisNutricionais,
        List<Alimento> alimentosCaloricos
) {
}
