package br.com.fiap.funcionario.model;

import br.com.fiap.unidade.model.Unidade;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_FUNCIONARIO")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNCIONARIO")
    @SequenceGenerator(name = "SQ_FUNCIONARIO", sequenceName = "SQ_FUNCIONARIO", initialValue = 1)
    @Column(name = "ID_FUNCIONARIO")
    private Long id;

    @Column(name = "NR_MATRICULA")
    private String matricula;

    @ManyToMany
    @JoinTable(name = "TB_FUNCIONARIOS_UNIDADES",
            joinColumns = {@JoinColumn(name = "ID_FUNCIONARIO", foreignKey = @ForeignKey(name = "FK_FU_FUNC", value = ConstraintMode.CONSTRAINT))},
            inverseJoinColumns = {@JoinColumn(name = "ID_UNIDADE", foreignKey = @ForeignKey(name = "FK_FU_UNIDADE", value = ConstraintMode.CONSTRAINT))}
    )
    private Set<Unidade> unidades = new LinkedHashSet<>();


    public Funcionario addUnidade(Unidade u) {
        this.unidades.add(u);
        return this;
    }

    public Funcionario removeUnidade(Unidade u) {
        this.unidades.remove(u);
        return this;
    }
}
