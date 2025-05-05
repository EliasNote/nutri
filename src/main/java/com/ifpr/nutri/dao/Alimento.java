package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double calorias;

    @Column(nullable = false)
    private Double proteinas;

    @Column(nullable = false)
    private Double carboidratos;

    @Column(nullable = false)
    private Double gorduras;

    @Column(name = "porcao", nullable = false)
    @Enumerated(EnumType.STRING)
    private Unidade porcao;

    @ManyToMany(mappedBy = "restricoesAlimentares")
    private List<Pessoa> pessoas;


    @OneToMany(mappedBy = "alimento")
    private List<ItemAlimento> itens;

    public enum Unidade {
        GRAMAS,
        UNIDADE,
        MILILITROS,
        COLHER_SOPA
    }

    public Alimento() {
    }

    public Alimento(Long id, String nome, Double calorias, Double proteinas, Double carboidratos, Double gorduras, Unidade porcao, List<Pessoa> pessoas, List<Refeicao> refeicoes, List<ItemAlimento> itens) {
        this.id = id;
        this.nome = nome;
        this.calorias = calorias;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.gorduras = gorduras;
        this.porcao = porcao;
        this.pessoas = pessoas;
        this.itens = itens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public Double getProteinas() {
        return proteinas;
    }

    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }

    public Double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(Double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public Double getGorduras() {
        return gorduras;
    }

    public void setGorduras(Double gorduras) {
        this.gorduras = gorduras;
    }

    public Unidade getPorcao() {
        return porcao;
    }

    public void setPorcao(Unidade porcao) {
        this.porcao = porcao;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<ItemAlimento> getItens() {
        return itens;
    }

    public void setItens(List<ItemAlimento> itens) {
        this.itens = itens;
    }
}
