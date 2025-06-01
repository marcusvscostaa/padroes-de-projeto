package com.praticasprinfg.padroes_de_peojeto.gof.service.impl;

import com.praticasprinfg.padroes_de_peojeto.gof.model.*;
import com.praticasprinfg.padroes_de_peojeto.gof.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    VendasRepository vendasRepository;
    @Autowired
    ProdutosRepository produtosRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Iterable<Vendas> buscarTodos() {
        return vendasRepository.findAll();
    }

    @Override
    public Vendas buscarPorId(Long id) {
        Optional<Vendas> vendas = vendasRepository.findById(id);
        return vendas.get();
    }

    @Override
    public void inserir(Vendas vendas) {
        salvarVenda(vendas);
    }

    @Override
    public void atualizar(Long id, Vendas vendas) {

    }

    @Override
    public void deletar(Long id) {
        vendasRepository.deleteById(id);
    }

    public void salvarVenda(Vendas vendas){
        Long idProduto = vendas.getProduto().getId();
        Long idCliente = vendas.getCliente().getId();

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + idCliente + " não encontrado.")); // Ou uma exceção mais específica
        ;
        Produtos produto = produtosRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + idProduto + " não encontrado.")); // Ou uma exceção mais específica
        ;
        vendas.setProduto(produto);
        vendas.setCliente(cliente);
        int quantidade = vendas.getQuantidade();
        Long total = produto.getValor()*quantidade;
        System.out.println(total);
        vendas.setTotal(total);

        vendasRepository.save(vendas);
    }
}
