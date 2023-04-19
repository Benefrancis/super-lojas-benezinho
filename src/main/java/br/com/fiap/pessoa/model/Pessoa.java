package br.com.fiap.pessoa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data

@Entity
@Table(name = "TB_PESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_PESSOA")
public abstract class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(name = "SQ_PESSOA", sequenceName = "SQ_PESSOA", initialValue = 1)
    @Column(name = "ID_PESSOA")
    private Long id;

    @Column(name = "NM_PESSOA")
    private String nome;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate nascimento;

    public Pessoa(Long id, String nome, LocalDate nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Pessoa() {
    }
}
