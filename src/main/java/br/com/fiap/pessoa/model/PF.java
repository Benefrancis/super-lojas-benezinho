package br.com.fiap.pessoa.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_PF")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("PF")
public class PF extends Pessoa {


    @Column(name = "NR_CPF")
    private String CPF;

    public PF() {
        super();
    }

    public PF(Long id, String nome, LocalDate nascimento) {
        super(id, nome, nascimento);
    }

    public PF(Long id, String nome, LocalDate nascimento, String CPF) {
        super(id, nome, nascimento);
        this.CPF = CPF;
    }

    @Override
    public String toString() {
        return "PF{" +
                "CPF='" + CPF + '\'' +
                "} " + super.toString();
    }
}
