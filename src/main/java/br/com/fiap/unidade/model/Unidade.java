package br.com.fiap.unidade.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "TB_UNIDADE")
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Unidade {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNIDADE")
    @SequenceGenerator(name = "SQ_UNIDADE", sequenceName = "SQ_UNIDADE", initialValue = 1)
    @Column(name = "ID_UNIDADE")
    private Long id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "NM_UNIDADE")
    private String nome;

    @ManyToOne()
    @Getter
    @Setter
    @JoinColumn(name = "ID_UNIDADE_SEDE", referencedColumnName = "ID_UNIDADE", foreignKey = @ForeignKey(name = "FK_UNIDADE_SEDE", value = ConstraintMode.CONSTRAINT))
    private Unidade sede;

    public Unidade() {
    }
}
