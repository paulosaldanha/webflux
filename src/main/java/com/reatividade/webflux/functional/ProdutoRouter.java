package com.reatividade.webflux.functional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ProdutoRouter {
    @Bean
    public RouterFunction<ServerResponse> route(ProdutoHandler handler){
        return RouterFunctions
            .route()
            .GET("/funcional/produtos", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::findAll)
		    .GET("/funcional/produtos/{id}", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::findById)
            .GET("/funcional/events",RequestPredicates.accept(MediaType.TEXT_EVENT_STREAM), handler::getProdutoEvents)
		    .POST("/funcional/produtos", RequestPredicates.accept(MediaType.APPLICATION_JSON), handler::save)
		    .build();
    }
}
