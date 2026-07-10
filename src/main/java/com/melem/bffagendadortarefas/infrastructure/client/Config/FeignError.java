package com.melem.bffagendadortarefas.infrastructure.client.Config;

import com.melem.bffagendadortarefas.infrastructure.exceptions.BusinnessException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String mensagemErro = mensagemErro(response);
        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Erro: " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro: " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro: " + mensagemErro);
            default:
                return new BusinnessException("Erro de servidor " + mensagemErro);
        }
    }

    private String mensagemErro(Response response) {
        //Estamos colocando para que possa pegar as mensagens de erro diretamente dos nosso micro serviços, caso tenhamos outras mensagens com o mesmo status HTTP;}
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException exception) {
            throw new RuntimeException((exception));
        }
    }
}
