package com.praticasprinfg.padroes_de_peojeto.gof.service;

import com.praticasprinfg.padroes_de_peojeto.gof.model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
