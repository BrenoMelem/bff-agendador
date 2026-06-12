package com.melem.bffagendadortarefas.business.dtos.in;

import lombok.*;

//PARA CONTROLARMOS AS INFORMAÇÕES E LOGIN, PASSADAS NA REQUEST
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDto {
    private String email;
    private String senha;


}
