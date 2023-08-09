package com.reatividade.webflux.service;

import com.reatividade.webflux.entity.Produto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProdutoService {
    Flux<Produto> findAll();
    Mono<Produto> findById(String id);
    Mono<Produto> save(Produto produto);
    Flux<Produto> findAllStream();
     
}
