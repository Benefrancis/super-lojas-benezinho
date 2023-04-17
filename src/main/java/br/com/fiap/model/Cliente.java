package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL_CLIENTE", columnNames = "EMAIL_CLIENTE")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE")
    @Column(name = "ID_CLIENTE")
    long id;

    @Column(name = "NM_CLIENTE")
    String nome;

    @Column(name = "EMAIL_CLIENTE")
    String email;



    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
