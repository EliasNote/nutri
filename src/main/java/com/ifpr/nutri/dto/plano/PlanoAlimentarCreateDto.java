package com.ifpr.nutri.dto.plano;

import java.time.LocalDate;
import java.util.List;

public record PlanoAlimentarCreateDto(
        String pessoaCpf,
        List<Long> refeicoesIds,
        LocalDate dataInicio,
        LocalDate dataFim,
        String observacoes
) {}