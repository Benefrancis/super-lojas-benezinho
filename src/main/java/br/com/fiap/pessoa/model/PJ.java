package br.com.fiap.pessoa.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("PJ")
public class PJ extends Pessoa {

    @Column(name = "NR_CNPJ")
    private String CNPJ;

    @Override
    public String toString() {
        return "PF{" +
                "CNPJ='" + CNPJ + '\'' +
                "} " + super.toString();
    }
}
