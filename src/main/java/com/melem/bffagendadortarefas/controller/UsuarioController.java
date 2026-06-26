package com.melem.bffagendadortarefas.controller;


import com.melem.bffagendadortarefas.business.UsuarioService;
import com.melem.bffagendadortarefas.business.dtos.in.EnderecoDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.in.LoginRequestDto;
import com.melem.bffagendadortarefas.business.dtos.in.TelefoneDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.in.UsuarioDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.out.EnderecoDtoResponse;
import com.melem.bffagendadortarefas.business.dtos.out.TelefoneDtoResponse;
import com.melem.bffagendadortarefas.business.dtos.out.UsuarioDtoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//Não tá aplicando nenhuma regra de security, precisa apontar a necessidade de acesar os endpoints, colocando o header("Autorizathion")
@Tag(name = "Usuario" , description = " Cadastro e Login de Usuários") // Anotação, para o nome da controller e uma descrição se caso tiver mais de uma. Também para apontar para qual.
@RestController
@RequestMapping ("/usuario")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salvar Usuários" , description = "Cria um novo Usúario")     // Descrição para o que o método vai estar realizando;
    @ApiResponse(responseCode = "200", description = "Usuário Criado com sucesso")    //Descrição para o que o método vai retornar, exemplo: 200, 400, 500, etc.
    @ApiResponse (responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<UsuarioDtoResponse>salvaUsuario (@RequestBody UsuarioDtoRequest usuarioDto){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDto));
    }
    @PostMapping("/login")
    @Operation(summary = "Login de Usuários" , description = "Login do Usuários")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public  String login (@RequestBody LoginRequestDto usuarioDto) { //Por conta do bff, não precisar do metodo de autenticação, vamos fazer um metodo na service
       return usuarioService.loginUsuario(usuarioDto);
    }
    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por email" , description = "Buscar dados do Usuários")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse (responseCode = "403", description = "Usuário não cadastrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDtoResponse> buscaUsuarioPorEmail (@RequestParam ("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(token,email));
    }
    @DeleteMapping ("/{email}")
    @Operation(summary = "Deleta Usuários por Id" , description = "Deleta Usuários")
    @ApiResponse(responseCode = "200", description = "Usuário Deletado com sucesso")
    @ApiResponse (responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail (@PathVariable String email,
                                                       @RequestHeader(name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(token, email);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    @Operation(summary = "Atualiza dados do Usuário" , description = "Atualiza dados do Usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse (responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDtoResponse> atualizaDadosUsuario (@RequestBody UsuarioDtoRequest dto,
                                                                    @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosDeUsuario(token,dto));
    }
    @PutMapping ("/endereco")
    @Operation(summary = "Atualiza endereço do Usuário" , description = "Atualiza endereço do Usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse (responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDtoResponse> atualizaEndereco (@RequestBody EnderecoDtoRequest dto,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }
    @PutMapping ("/telefone")
    @Operation(summary = "Atualiza telefone do Usuário" , description = "Atualiza telefone do Usuário")
    @ApiResponse(responseCode = "200", description = " Telefone atualizado com sucesso")
    @ApiResponse (responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDtoResponse> atualizaTelefone (@RequestBody TelefoneDtoRequest dto,
                                                                 @RequestParam ("id") Long id,
                                                                 @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }
    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuário" , description = "Salva Endereço de Usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse (responseCode = "403", description = "Usúario não encontrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    public ResponseEntity<EnderecoDtoResponse> cadastraEndereco (@RequestBody EnderecoDtoRequest dto,
                                                                 @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }
    @PostMapping ("/telefone")
    @Operation(summary = "Salva Telefone de Usuários" , description = "Salva Telefone de Usuários")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse (responseCode = "403", description = "Usúario não encontrado")
    @ApiResponse (responseCode = "500", description = " Erro interno do servidor")
    @ApiResponse (responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDtoResponse> cadastraTelefone (@RequestBody TelefoneDtoRequest dto,
                                                                 @RequestHeader(name = "Authorization", required = false) String token){
        return  ResponseEntity.ok(usuarioService.cadastraTelefone(token,dto));
    }
}
