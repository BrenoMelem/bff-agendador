package com.melem.bffagendadortarefas.business.dtos.in;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDtoRequest {
    private String numero;
    private String ddd;


}
