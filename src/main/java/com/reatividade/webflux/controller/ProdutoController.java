package com.reatividade.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reatividade.webflux.entity.Produto;
import com.reatividade.webflux.service.ProdutoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1")
public class ProdutoController {
    @Autowired
    ProdutoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Produto> getAll(){
        return service.findAll();
    }

    @GetMapping(value ="{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Produto> getById(@PathVariable String id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Produto> save(@RequestBody Produto produto){
        return service.save(produto);
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Produto> getAllByEvents(){
        return service.findAllStream();
    }
}
