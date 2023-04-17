package br.com.fiap.unidade.model;

import br.com.fiap.funcionario.model.Funcionario;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "TB_UNIDADE")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNIDADE")
    @SequenceGenerator(name = "SQ_UNIDADE", sequenceName = "SQ_UNIDADE", initialValue = 1)
    @Column(name = "ID_UNIDADE")
    private Long id;

    @Column(name = "NM_UNIDADE")
    private String nome;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_UNIDADE_SEDE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(name = "FK_UNIDADE_SEDE", value = ConstraintMode.CONSTRAINT)
    )
    private Unidade sede;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_FUNCIONARIO_CHEFE",
            referencedColumnName = "ID_FUNCIONARIO",
            foreignKey = @ForeignKey(name = "FK_CHEFE_UNIDADE", value = ConstraintMode.CONSTRAINT)
    )
    private Funcionario chefe;

}
