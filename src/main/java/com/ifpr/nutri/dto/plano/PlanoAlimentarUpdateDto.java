package com.ifpr.nutri.dto.plano;

import java.time.LocalDate;
import java.util.List;

public record PlanoAlimentarUpdateDto(
        List<Long> refeicoesIds,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
) {}