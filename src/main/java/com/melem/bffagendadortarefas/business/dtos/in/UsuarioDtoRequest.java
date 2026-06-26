package com.melem.bffagendadortarefas.business.dtos.in;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoRequest {
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDtoRequest> enderecos;
    private List<TelefoneDtoRequest> telefones;
}
