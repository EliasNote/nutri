package com.ifpr.nutri.dao;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "refeicao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemAlimento> itens;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private PlanoAlimentar planoAlimentar;

    @Column(nullable = false)
    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tipo tipo;

    public enum Tipo {
        CAFE_DA_MANHA,
        ALMOCO,
        JANTAR,
        LANCHE
    }

    public Refeicao() {
    }

    public Refeicao(Long id, Pessoa pessoa, List<ItemAlimento> itens, PlanoAlimentar planoAlimentar, LocalDateTime data, Tipo tipo) {
        this.id = id;
        this.pessoa = pessoa;
        this.itens = itens;
        this.planoAlimentar = planoAlimentar;
        this.data = data;
        this.tipo = tipo;
    }

    public PlanoAlimentar getPlanoAlimentar() {
        return planoAlimentar;
    }

    public void setPlanoAlimentar(PlanoAlimentar planoAlimentar) {
        this.planoAlimentar = planoAlimentar;
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

    public List<ItemAlimento> getItens() {
        return itens;
    }

    public void setItens(List<ItemAlimento> itens) {
        this.itens = itens;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
