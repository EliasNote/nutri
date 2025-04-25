package com.ifpr.nutri.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.OneToMany;
import java.util.List;
import com.ifpr.nutri.dao.ItemAlimento;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @ManyToMany(mappedBy = "alimentos")
    private List<Refeicao> refeicoes;

    @OneToMany(mappedBy = "alimento")
    private List<ItemAlimento> itens;

    public enum Unidade {
        GRAMAS,
        UNIDADE,
        MILILITROS,
        COLHER_SOPA
    }
}
