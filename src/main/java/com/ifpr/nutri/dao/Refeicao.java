package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    @ManyToMany
    @JoinTable(
        name = "refeicao_alimentos",
        joinColumns = @JoinColumn(name = "refeicao_id"),
        inverseJoinColumns = @JoinColumn(name = "alimento_id")
    )
    private List<Alimento> alimentos;

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
}
