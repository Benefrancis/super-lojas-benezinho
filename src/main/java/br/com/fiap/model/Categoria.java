package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_CATEGORIA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_CATEGORIA", columnNames = "NM_CATEGORIA")
})
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORIA")
    @SequenceGenerator(name = "SQ_CATEGORIA", sequenceName = "SQ_CATEGORIA")
    @Column(name = "ID_CATEGORIA")
    private long id;

    @Column(name = "NM_CATEGORIA")
    private String nome;

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
