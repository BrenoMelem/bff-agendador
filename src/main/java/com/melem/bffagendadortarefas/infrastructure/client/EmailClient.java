package com.melem.bffagendadortarefas.infrastructure.client;

import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {
    //Será TarefaDtoResponse pois precisa passar o email que somente está no response;
    @PostMapping
    void enviarEmail(@RequestBody TarefaDtoResponse dto);

}
