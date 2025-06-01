package com.praticasprinfg.padroes_de_peojeto.gof.controller;

import com.praticasprinfg.padroes_de_peojeto.gof.model.Vendas;
import com.praticasprinfg.padroes_de_peojeto.gof.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vendas")
public class VendasRestController {
    @Autowired
    VendaService vendaService;

    @GetMapping
    public ResponseEntity<Iterable<Vendas>> buscarTodos() {
        return ResponseEntity.ok(vendaService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendas> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Vendas> inserir(@RequestBody Vendas vendas) {
        vendaService.inserir(vendas);
        return ResponseEntity.ok(vendas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendas> atualizar(@PathVariable Long id, @RequestBody Vendas vendas) {
        vendaService.atualizar(id, vendas);
        return ResponseEntity.ok(vendas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
