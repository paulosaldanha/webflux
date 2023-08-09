package com.reatividade.webflux.service;

import java.time.Duration;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reatividade.webflux.entity.Produto;
import com.reatividade.webflux.repository.ProdutoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProdutoServiceImp implements ProdutoService{

    @Autowired
    ProdutoRepository repository;

    @Override
    public Flux<Produto> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Produto> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Produto> save(Produto produto) {
        produto.setId(UUID.randomUUID().toString());
       return repository.save(produto);
    }

    @Override
    public Flux<Produto> findAllStream() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
        Flux<Produto> events = Flux.from(repository.findAll());
        return Flux.zip(events, interval, (key, value) -> key);
    }
    
}
