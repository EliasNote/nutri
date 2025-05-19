package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.dto.alimento.AlimentoResponseDto;
import com.ifpr.nutri.mapper.AlimentoMapper;
import com.ifpr.nutri.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ifpr.nutri.mapper.AlimentoMapper.*;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public AlimentoResponseDto create(Alimento alimento) {
        return toResponseDto(alimentoRepository.save(alimento));
    }

    public Alimento findById(Long id) {
        return alimentoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Alimento não encontrado")
        );
    }

    public List<AlimentoResponseDto> findAll() {
        return alimentoRepository.findAll().stream().map(AlimentoMapper::toResponseDto).toList();
    }
}
