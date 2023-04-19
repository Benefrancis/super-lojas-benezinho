package br.com.fiap;

import br.com.fiap.funcionario.model.Funcionario;
import br.com.fiap.model.Categoria;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Pedido;
import br.com.fiap.model.Produto;
import br.com.fiap.pessoa.model.PF;
import br.com.fiap.pessoa.model.Pessoa;
import br.com.fiap.unidade.model.Unidade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("fiap");
        EntityManager manager = factory.createEntityManager();

//        salvar(manager);
//        var pedido = findPedidoByid(1L, manager);
//        System.out.println(pedido);


        PF bene = new PF();
        bene.setCPF("2488889948");
        bene.setNome("Benefrancis do Nascimento");
        bene.setNascimento(LocalDate.of(1977, 3, 8));


        Funcionario prof = new Funcionario();
        prof.setMatricula("21773");
        prof.setPessoa(bene);

        Funcionario gabriel = new Funcionario();
        gabriel.setMatricula("123456789");

        Funcionario ricardo = new Funcionario();
        ricardo.setMatricula("987654321");

        Funcionario gabriel2 = new Funcionario();
        gabriel2.setMatricula("666"); //sacanagem isso ai

        manager.getTransaction().begin();
        Arrays.asList(gabriel, prof, ricardo, gabriel2).forEach(manager::persist);
        salvaUnidades(manager);
        gabriel.addUnidade(manager.find(Unidade.class, 1));
        gabriel.addUnidade(manager.find(Unidade.class, 2));
        prof.addUnidade(manager.find(Unidade.class, 1));
        ricardo.addUnidade(manager.find(Unidade.class, 1));
        gabriel2.addUnidade(manager.find(Unidade.class, 1));
        manager.getTransaction().commit();

        manager.createQuery("FROM Pessoa").getResultList().forEach(System.out::println);

        manager.close();
        factory.close();

    }

    private static void salvaUnidades(EntityManager manager) {
        Unidade sede = new Unidade();
        sede.setSede(null);
        sede.setNome("Unidade Brasil");
        sede.setChefe(manager.find(Funcionario.class, 1));

        Unidade filialSP = new Unidade();
        filialSP.setSede(sede);
        filialSP.setNome("São Paulo");
        filialSP.setChefe(manager.find(Funcionario.class, 1));

        Unidade filialVilaMariana = new Unidade();
        filialVilaMariana.setNome("Vila Mariana");
        filialVilaMariana.setSede(filialSP);
        filialVilaMariana.setChefe(manager.find(Funcionario.class, 2));

        Unidade filialVilaSantana = new Unidade();
        filialVilaSantana.setNome("Santana");
        filialVilaSantana.setSede(filialSP);
        filialVilaSantana.setChefe(manager.find(Funcionario.class, 3));


        Unidade filialVilaSantoAmaro = new Unidade();
        filialVilaSantoAmaro.setNome("Santo Amaro");
        filialVilaSantoAmaro.setSede(filialSP);
        filialVilaSantoAmaro.setChefe(manager.find(Funcionario.class, 1));


        Arrays.asList(sede, filialSP, filialVilaMariana, filialVilaSantana, filialVilaSantoAmaro).forEach(unidade -> {
            manager.persist(unidade);
        });

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