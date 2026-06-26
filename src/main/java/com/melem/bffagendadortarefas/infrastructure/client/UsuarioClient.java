package com.melem.bffagendadortarefas.infrastructure.client;

import com.melem.bffagendadortarefas.business.dtos.in.EnderecoDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.in.LoginRequestDto;
import com.melem.bffagendadortarefas.business.dtos.in.TelefoneDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.in.UsuarioDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.out.EnderecoDtoResponse;
import com.melem.bffagendadortarefas.business.dtos.out.TelefoneDtoResponse;
import com.melem.bffagendadortarefas.business.dtos.out.UsuarioDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
//Por conta de só termos uma controller na API de usuario, podemos apaenas utilizar a URI do serviço, sem a necessidade de colocar a URI do endpoint, mas caso tivesse mais de uma controller, precisaríamos colocar a URI do endpoint, exemplo: @FeignClient(name = "usuario", url = "${usuario.url}/usuario")
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDtoResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDtoResponse salvaUsuario(@RequestBody UsuarioDtoRequest usuarioDto);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDto usuarioDto); //Por conta do bff, não precisar do metodo de autenticação, vamos fazer um metodo na service

    @DeleteMapping
    void deletaUsuarioPorEmail(@PathVariable String email,
                               @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDtoResponse atualizaDadosUsuario(@RequestBody UsuarioDtoRequest dto,
                                            @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDtoResponse atualizaEndereco(@RequestBody EnderecoDtoRequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDtoResponse atualizaTelefone(@RequestBody TelefoneDtoRequest dto,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDtoResponse cadastraEndereco(@RequestBody EnderecoDtoRequest dto,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDtoResponse cadastraTelefone(@RequestBody TelefoneDtoRequest dto,
                                         @RequestHeader("Authorization") String token);

}