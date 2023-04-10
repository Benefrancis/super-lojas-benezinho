package br.com.fiap.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PEDIDO")
    @SequenceGenerator(name = "SQ_PEDIDO", sequenceName = "SQ_PEDIDO")
    @Column(name = "ID_PEDIDO")
    private
    long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_PEDIDO")
    private
    LocalDateTime data;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE", foreignKey = @ForeignKey(name = "FK_PEDIDO_CLIENTE", value = ConstraintMode.CONSTRAINT))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private Set<Produto> produtos = new LinkedHashSet<>();


    public Pedido addProduto(Produto p) {
        this.getProdutos().add(p);
        p.setPedido(this);
        return this;
    }

    public Pedido removeProduto(Produto p) {
        this.getProdutos().remove(p);
        p.setPedido(null);
        return this;
    }

}
