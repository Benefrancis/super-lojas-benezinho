package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PRODUTO")
@NamedEntityGraph(name = "Produto.categorias",
        attributeNodes = {
                @NamedAttributeNode(value = "categorias", subgraph = "Categoria.produtos")
        },
        subgraphs = {
                @NamedSubgraph(name = "Categoria.produtos",
                        attributeNodes = {
                                @NamedAttributeNode(value = "produtos")
                        }
                )
        }
)
public class Produto {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(name = "SQ_PRODUTO", sequenceName = "SQ_PRODUTO")
    @Column(name = "ID_PRODUTO")
    private long id;

    @NonNull
    @Getter
    @Setter
    @Column(name = "NM_PRODUTO")
    private
    String nome;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_PEDIDO", referencedColumnName = "ID_PEDIDO",
            foreignKey = @ForeignKey(name = "FK_PROD_PEDIDO", value = ConstraintMode.CONSTRAINT))
    private Pedido pedido;


    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "TB_PRODUTO_CATEGORIA",
            joinColumns = @JoinColumn(name = "ID_PRODUTO",
                    foreignKey = @ForeignKey(name = "FK_PRODUTO", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "ID_CATEGORIA",
                    foreignKey = @ForeignKey(name = "FK_CATEGORIA", value = ConstraintMode.CONSTRAINT))
    )
    private Set<Categoria> categorias = new LinkedHashSet<>();


    public Produto addCategoria(Categoria c) {
        this.getCategorias().add(c);
        c.getProdutos().add(this);
        return this;
    }

    public Produto removeCategoria(Categoria c) {
        this.getCategorias().remove(c);
        c.getProdutos().remove(this);
        return this;
    }

    public Produto() {
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("Produto{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        //  sb.append(", pedido=").append(pedido);
        //  sb.append(", categorias=").append(categorias);
        sb.append('}');
        return sb.toString();
    }

}
