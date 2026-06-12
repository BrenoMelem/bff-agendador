package com.melem.bffagendadortarefas.infrastructure.client;

import com.melem.bffagendadortarefas.business.dtos.in.TarefaDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import com.melem.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefaDtoResponse gravarTarefa(@RequestBody TarefaDtoRequest dto,
                                   @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefaDtoResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefaDtoResponse> buscaTarefaPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefaDtoResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                              @RequestParam("id") String id,
                                              @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDtoResponse updateTarefa(@RequestBody TarefaDtoRequest dto,
                                   @RequestParam("id") String id,
                                   @RequestHeader("Authorization") String token);


}
