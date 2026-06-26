package com.melem.bffagendadortarefas.business.dtos.out;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDtoResponse {
    private Long id;
    private String numero;
    private String ddd;


}
