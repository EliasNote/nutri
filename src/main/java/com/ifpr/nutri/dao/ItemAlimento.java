package com.ifpr.nutri.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class ItemAlimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "alimento_id", nullable = false)
    private Alimento alimento;
    
    @ManyToOne
    @JoinColumn(name = "refeicao_id", nullable = false)
    private Refeicao refeicao;
    
    private Double quantidade;

    public ItemAlimento() {
    }

    public ItemAlimento(Long id, Alimento alimento, Refeicao refeicao, Double quantidade) {
        this.id = id;
        this.alimento = alimento;
        this.refeicao = refeicao;
        this.quantidade = quantidade;
    }

    public ItemAlimento(Alimento alimento, Double quantidade) {
        this.alimento = alimento;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public Refeicao getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(Refeicao refeicao) {
        this.refeicao = refeicao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
