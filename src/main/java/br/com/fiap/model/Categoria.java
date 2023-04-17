package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CATEGORIA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_NM_CATEGORIA", columnNames = "NM_CATEGORIA")
})
@NamedEntityGraph(name = "Categoria.produtos",
        attributeNodes = {
                @NamedAttributeNode(value = "produtos", subgraph = "Produto.pedido")
        },
        subgraphs = {
                @NamedSubgraph(name = "Produto.pedido",
                        attributeNodes = {
                                @NamedAttributeNode(value = "pedido")
                        }
                )
        }
)
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CATEGORIA")
    @SequenceGenerator(name = "SQ_CATEGORIA", sequenceName = "SQ_CATEGORIA")
    @Column(name = "ID_CATEGORIA")
    @Getter @Setter
    private long id;

    @Getter
    @Setter
    @Column(name = "NM_CATEGORIA")
    private String nome;

    @Getter
    @ManyToMany(mappedBy = "categorias")
    @OrderBy("nome asc")
    private Set<Produto> produtos = new LinkedHashSet<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categoria{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        //  sb.append(", produtos=").append(produtos);
        sb.append('}');
        return sb.toString();
    }
}
