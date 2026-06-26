package com.melem.bffagendadortarefas.infrastructure.client.Config;

import com.melem.bffagendadortarefas.infrastructure.exceptions.BusinnessException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("Erro atributo já existente");
            case 403:
                return new ResourceNotFoundException("Erro atributo não encontrado");
            case 401:
                return new UnauthorizedException("Erro Usuário não autorizado");
            default:
                return new BusinnessException(("Erro de servidor " + response.status()));
        }
    }
}
