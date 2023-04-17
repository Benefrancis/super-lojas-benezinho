package br.com.fiap;

import br.com.fiap.model.Categoria;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Pedido;
import br.com.fiap.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        salvar(manager);

        var pedido = findPedidoByid(1L, manager);

        System.out.println(pedido);

    }

    private static Pedido findPedidoByid(long i, EntityManager manager) {
        return manager.find(Pedido.class, i);
    }

    private static void salvar(EntityManager manager) {
        var eletronicos = new Categoria();
        eletronicos.setNome("Eletrônicos");

        var mobile = new Categoria();
        mobile.setNome("Mobile");

        var cliente = new Cliente();
        cliente.setEmail("benefrancis@msn.com");
        cliente.setNome("Benefrancis");

        var tablet = new Produto();
        tablet.setNome("Ipad");
        tablet.addCategoria(eletronicos);
        tablet.addCategoria(mobile);


        var pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setCliente(cliente);
        pedido.addProduto(tablet);

        manager.getTransaction().begin();
        //  manager.persist(tablet);
        manager.persist(pedido);
        manager.getTransaction().commit();
    }
}