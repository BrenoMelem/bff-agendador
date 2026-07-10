package com.melem.bffagendadortarefas.business;

import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import com.melem.bffagendadortarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void enviaEmail (TarefaDtoResponse tarefaDtoResponse){
        emailClient.enviarEmail(tarefaDtoResponse);
    }
}
