package com.praticasprinfg.padroes_de_peojeto.gof.service.impl;

import com.praticasprinfg.padroes_de_peojeto.gof.model.Produtos;
import com.praticasprinfg.padroes_de_peojeto.gof.model.ProdutosRepository;
import com.praticasprinfg.padroes_de_peojeto.gof.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoSreviceImpl implements ProdutoService {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Override
    public Iterable<Produtos> buscarTodos() {
        return produtosRepository.findAll();
    }

    @Override
    public Produtos buscarPorId(Long id) {
        Optional<Produtos> produto = produtosRepository.findById(id);
        return produto.get();
    }

    @Override
    public void inserir(Produtos produtos) {
        produtosRepository.save(produtos);
    }

    @Override
    public void atualizar(Long id, Produtos produtos) {
        Optional<Produtos> produtosBd = produtosRepository.findById(id);
        if (produtosBd.isPresent()) {
            produtosRepository.save(produtos);
        }
    }

    @Override
    public void deletar(Long id) {
        produtosRepository.deleteById(id);
    }
}
