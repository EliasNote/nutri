package com.ifpr.nutri.dao;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PlanoAlimentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanoAlimentar planoAlimentar;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate dataFim;

    @Column(length = 255)
    private String observacoes;

    public PlanoAlimentar() {
    }

    public PlanoAlimentar(Long id, Pessoa pessoa, PlanoAlimentar planoAlimentar, LocalDate dataInicio, LocalDate dataFim, String observacoes) {
        this.id = id;
        this.pessoa = pessoa;
        this.planoAlimentar = planoAlimentar;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PlanoAlimentar getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(PlanoAlimentar planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
