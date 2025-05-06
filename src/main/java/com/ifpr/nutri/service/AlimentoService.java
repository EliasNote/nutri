package com.ifpr.nutri.service;

import com.ifpr.nutri.dao.Alimento;
import com.ifpr.nutri.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public Alimento create(Alimento alimento) {
        return alimentoRepository.save(alimento);
    }

    public Alimento findById(Long id) {
        return alimentoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Alimento não encontrado")
        );
    }

    public List<Alimento> findAll() {
        return alimentoRepository.findAll();
    }
}
