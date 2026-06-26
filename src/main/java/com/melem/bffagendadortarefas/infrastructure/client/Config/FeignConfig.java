package com.melem.bffagendadortarefas.infrastructure.client.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Indica que esta classe é uma classe de configuração do Spring. Ela pode conter definições de beans, configurações de propriedades e outras configurações relacionadas ao contexto da aplicação.
//Indica que o  metodo abaixo é um bean gerenciado pelo Spring. O Spring irá criar uma instância do objeto retornado por este metodo e gerenciá-la como um bean dentro do contexto da aplicação.
public class FeignConfig {
    //introduzir esse metodo que vamos apresentar dentro do Spring Security, para que o Feign possa pegar o token do contexto de segurança e passar para as requisições feitas pelos clientes Feign. O metodo é um interceptor que adiciona o token de autenticação às requisições feitas pelos clientes Feign.
    //Assim não precisamos chamar a classe de manualmente, está dentro do Spring.
    @Bean
    public FeignError feignError(){
        return new FeignError();
    }
}
