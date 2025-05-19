package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String senha;

    @Column
    private Integer idade;

    @Column
    private Double peso;

    @Column
    private Double altura;

    @ElementCollection
    @CollectionTable(
            name = "pessoa_objetivos",
            joinColumns = @JoinColumn(name = "pessoa_id")
    )
    @Column(length = 100)
    private List<String> objetivos;

    @ManyToMany
    @JoinTable(
            name = "pessoa_restricoes",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "alimento_id")
    )
    private List<Alimento> restricoesAlimentares;

    @OneToMany(mappedBy = "pessoa")
    private List<Refeicao> refeicoes;

    public Pessoa() {
    }

    public Pessoa(Long id, String username, String nome, String cpf, String senha, Integer idade, Double peso, Double altura, List<String> objetivos, List<Alimento> restricoesAlimentares, List<Refeicao> refeicoes) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.objetivos = objetivos;
        this.restricoesAlimentares = restricoesAlimentares;
        this.refeicoes = refeicoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public List<String> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<String> objetivos) {
        this.objetivos = objetivos;
    }

    public List<Alimento> getRestricoesAlimentares() {
        return restricoesAlimentares;
    }

    public void setRestricoesAlimentares(List<Alimento> restricoesAlimentares) {
        this.restricoesAlimentares = restricoesAlimentares;
    }

    public List<Refeicao> getRefeicoes() {
        return refeicoes;
    }

    public void setRefeicoes(List<Refeicao> refeicoes) {
        this.refeicoes = refeicoes;
    }
}