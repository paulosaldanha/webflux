package com.reatividade.webflux.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reatividade.webflux.entity.Produto;
import com.reatividade.webflux.service.ProdutoService;

import reactor.core.publisher.Mono;

@Component
public class ProdutoHandler {
    @Autowired
    ProdutoService produtoService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(produtoService.findAll(), Produto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(produtoService.findById(id), Produto.class);
    }

    public Mono<ServerResponse> save(ServerRequest request){
        final Mono<Produto> produto = request.bodyToMono(Produto.class);

        return produto.flatMap(product -> ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(produtoService.save(product), Produto.class));
    }

    public Mono<ServerResponse> getProdutoEvents(ServerRequest request) {
       
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(
                    produtoService.findAllStream()
                    , Produto.class
                );
    }


}
