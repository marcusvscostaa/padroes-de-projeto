package com.praticasprinfg.padroes_de_peojeto.gof.service.impl;

import com.praticasprinfg.padroes_de_peojeto.gof.model.Cliente;
import com.praticasprinfg.padroes_de_peojeto.gof.model.ClienteRepository;
import com.praticasprinfg.padroes_de_peojeto.gof.model.Endereco;
import com.praticasprinfg.padroes_de_peojeto.gof.model.EnderecoRepository;
import com.praticasprinfg.padroes_de_peojeto.gof.service.ClienteService;
import com.praticasprinfg.padroes_de_peojeto.gof.service.ViaCepService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    // Singleton: Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy: Implementar os métodos definidos na interface.
    // Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        // Buscar todos os Clientes.
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Buscar Cliente por ID.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    @Transactional
    public void atualizar(Long id, Cliente cliente) {
        // Buscar Cliente por ID, caso exista:
        Cliente dadosCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id + " para atualização."));

        System.out.println(dadosCliente);

        ;if(cliente.getNome() != null){
            System.out.println("Nome: " + cliente.getNome());
            dadosCliente.setNome(cliente.getNome());
        }

        if(cliente.getEndereco().getCep() != null){
            System.out.println("CEP Antigo"+dadosCliente.getEndereco().getCep());

            Endereco endereco = enderecoRepository.findById(cliente.getEndereco().getCep()).orElseGet(() -> {
                // Caso não exista, integrar com o ViaCEP e persistir o retorno.
                Endereco novoEndereco = viaCepService.consultarCep(cliente.getEndereco().getCep());
                System.out.println("CEP Passado"+novoEndereco);

                return enderecoRepository.save(novoEndereco);
            });

            dadosCliente.setEndereco(endereco);
            System.out.println("CEP Passado"+cliente.getEndereco().getCep());
            System.out.println("CEP Novo"+dadosCliente.getEndereco().getCep());
        }
        System.out.println("Dado Novo"+dadosCliente);

        clienteRepository.save(dadosCliente);

    }

    @Override
    public void deletar(Long id) {
        // Deletar Cliente por ID.
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereco do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            // Caso não exista, integrar com o ViaCEP e persistir o retorno.
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        // Inserir Cliente, vinculando o Endereco (novo ou existente).
        clienteRepository.save(cliente);
    }
}
