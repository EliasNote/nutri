// src/main/java/com/ifpr/nutri/dao/Pessoa.java
package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}