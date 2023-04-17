package br.com.fiap.funcionario.model;

import br.com.fiap.unidade.model.Unidade;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_FUNCIONARIO", uniqueConstraints = {@UniqueConstraint(name = "UK_MATRICULA", columnNames = "NR_MATRICULA")})
public class Funcionario {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNCIONARIO")
    @SequenceGenerator(name = "SQ_FUNCIONARIO", sequenceName = "SQ_FUNCIONARIO", initialValue = 1)
    @Column(name = "ID_FUNCIONARIO")
    Long id;

    @Getter
    @Setter
    @Column(name = "NR_MATRICULA")
    String matricula;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "TB_FUNCIONARIO_UNIDADE",
            joinColumns = {
                    @JoinColumn(name = "ID_FUNCIONARIO", foreignKey = @ForeignKey(name = "FK_FU_FUNCIONARIO"))
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ID_UNIDADE", foreignKey = @ForeignKey(name = "FK_FU_UNIDADE"))
            }
    )
    Set<Unidade> unidades = new LinkedHashSet<>();

    public Funcionario addUnidade(Unidade u) {
        this.unidades.add(u);
        return this;
    }

    public Funcionario removerUnidade(Unidade u) {
        this.unidades.remove(u);
        return this;
    }

    public List<Unidade> getUnidades() {
        return Collections.unmodifiableList(this.unidades.stream().toList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Funcionario{");
        sb.append("id=").append(id);
        sb.append(", matricula='").append(matricula).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
