package com.reatividade.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reatividade.webflux.entity.Produto;

@Repository
public interface ProdutoRepository extends ReactiveMongoRepository<Produto, String>{
    
}
