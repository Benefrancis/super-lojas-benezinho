package br.com.fiap.pessoa.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("PF")
public class PF extends Pessoa {

    @Column(name = "NR_CPF")
    private String CPF;

    @Override
    public String toString() {
        return "PF{" +
                "CPF='" + CPF + '\'' +
                "} " + super.toString();
    }
}
