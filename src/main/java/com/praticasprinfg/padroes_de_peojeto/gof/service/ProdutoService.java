package com.praticasprinfg.padroes_de_peojeto.gof.service;

import com.praticasprinfg.padroes_de_peojeto.gof.model.Produtos;

public interface ProdutoService {
    Iterable<Produtos> buscarTodos();

    Produtos buscarPorId(Long id);

    void inserir(Produtos produtos);

    void atualizar(Long id, Produtos produtos);

    void deletar(Long id);
}
