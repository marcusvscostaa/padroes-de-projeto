package com.praticasprinfg.padroes_de_peojeto.gof.service;

import com.praticasprinfg.padroes_de_peojeto.gof.model.Vendas;

public interface VendaService {
    Iterable<Vendas> buscarTodos();

    Vendas buscarPorId(Long id);

    void inserir(Vendas vendas);

    void atualizar(Long id, Vendas vendas);

    void deletar(Long id);
}
