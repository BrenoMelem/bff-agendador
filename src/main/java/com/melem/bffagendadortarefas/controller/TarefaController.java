package com.melem.bffagendadortarefas.controller;

import com.melem.bffagendadortarefas.business.TarefaService;
import com.melem.bffagendadortarefas.business.dtos.in.TarefaDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import com.melem.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.melem.bffagendadortarefas.infrastructure.Security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@SecurityRequirement(name = SecurityConfig.SECURITY_SHEME) // // ANOTAÇÃO PARA APONTAR QUE OS ENDPOINTS PRECISAM DE AUTENTICAÇÃO, PARA APARECER O CADEADO NA DOCUMENTAÇÃO DO SWAGGER
@Tag(name = "Tarefas", description = "Cadastra Tarefas de Usuários")
@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuários" , description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<TarefaDtoResponse> gravarTarefa(@RequestBody TarefaDtoRequest dto,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas por período" , description = "Busca tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<List<TarefaDtoResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial, //PADRANIZAR O FORMATA DA DATA E HORA PARA O DB
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca listas de tarefas por email do Usuário" , description = "Busca de tarefas cadastradas por Usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas com sucesso")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<List<TarefaDtoResponse>> buscaTarefaPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok((tarefaService.buscaTarefasPorEmail(token)));
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefa por Id" , description = "Deleta tarefas cadastradas por id ")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas com sucesso")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        tarefaService.deletarTarefaPorId(id, token);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status das tarefas" , description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<TarefaDtoResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                     @RequestParam("id") String id,
                                                                     @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas" , description = "Altera dados de tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas com sucesso")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<TarefaDtoResponse> updateTarefa(@RequestBody TarefaDtoRequest dto,
                                                          @RequestParam("id") String id,
                                                          @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefaService.updateTarefa(dto, id, token));
    }
}
