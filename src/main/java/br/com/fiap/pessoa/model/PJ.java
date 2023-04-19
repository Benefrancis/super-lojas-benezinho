package br.com.fiap.pessoa.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_PJ")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("PJ")
public class PJ extends Pessoa {

    @Column(name = "NR_CNPJ")
    private String CNPJ;

    public PJ() {
        super();
    }

    public PJ(Long id, String nome, LocalDate nascimento) {
        super(id, nome, nascimento);
    }

    public PJ(Long id, String nome, LocalDate nascimento, String CNPJ) {
        super(id, nome, nascimento);
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString() {
        return "PJ{" +
                "CNPJ='" + CNPJ + '\'' +
                "} " + super.toString();
    }
}
