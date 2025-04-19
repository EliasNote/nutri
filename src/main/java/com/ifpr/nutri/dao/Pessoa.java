package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.SequenceGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    
    @Column(name = "sobrenome", length = 50, nullable = false)
    private String sobrenome;
    
    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;
    
    @Column(name = "endereco", length = 100)
    private String endereco;
    
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
}
