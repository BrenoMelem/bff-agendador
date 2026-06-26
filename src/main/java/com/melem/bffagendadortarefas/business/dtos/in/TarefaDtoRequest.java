package com.melem.bffagendadortarefas.business.dtos.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.melem.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDtoRequest {
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    //PARA SETAR A HORA DO JEITO QUE DESEJARMOS.
    private LocalDateTime dataEvento;
}
