package com.melem.bffagendadortarefas.business;


import com.melem.bffagendadortarefas.business.dtos.in.TarefaDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import com.melem.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.melem.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TarefaService {
    private final TarefasClient tarefasClient;

    public TarefaDtoResponse gravarTarefa(String token, TarefaDtoRequest dto) {
        return tarefasClient.gravarTarefa(dto, token);
    }

    public List<TarefaDtoResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                   LocalDateTime dataFinal,
                                                                   String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefaDtoResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefaPorEmail(token);
    }

    public void deletarTarefaPorId(String id, String token) {
        tarefasClient.deletarTarefaPorId(id, token);
    }

    public TarefaDtoResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefaDtoResponse updateTarefa(TarefaDtoRequest dto, String id, String token) {
        return tarefasClient.updateTarefa(dto, id, token);
    }
}
