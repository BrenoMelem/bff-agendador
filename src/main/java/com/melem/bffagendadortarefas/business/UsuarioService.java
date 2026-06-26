package com.melem.bffagendadortarefas.business;


import com.melem.bffagendadortarefas.business.dtos.in.EnderecoDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.in.LoginRequestDto;
import com.melem.bffagendadortarefas.business.dtos.in.TelefoneDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.in.UsuarioDtoRequest;
import com.melem.bffagendadortarefas.business.dtos.out.EnderecoDtoResponse;
import com.melem.bffagendadortarefas.business.dtos.out.TelefoneDtoResponse;
import com.melem.bffagendadortarefas.business.dtos.out.UsuarioDtoResponse;
import com.melem.bffagendadortarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UsuarioService {
    private final UsuarioClient usuarioClient;

    public UsuarioDtoResponse salvaUsuario(UsuarioDtoRequest usuarioDto) {
        return usuarioClient.salvaUsuario(usuarioDto);
    }
    //criar metódo de Login  na service
    public String loginUsuario (LoginRequestDto usuarioDto){
       return usuarioClient.login(usuarioDto);
    }
    public UsuarioDtoResponse buscarUsuarioPorEmail(String email, String token) {
      return usuarioClient.buscaUsuarioPorEmail(token, email);
    }
    public void deletaUsuarioPorEmail(String email, String token) {
        usuarioClient.deletaUsuarioPorEmail(email, token);
    }
    public UsuarioDtoResponse atualizaDadosDeUsuario(String token, UsuarioDtoRequest dto) {
        return usuarioClient.atualizaDadosUsuario(dto, token);
    }
    public EnderecoDtoResponse atualizaEndereco(Long idEndereco, EnderecoDtoRequest enderecoDto, String token){
       return usuarioClient.atualizaEndereco(enderecoDto, idEndereco, token);
    }
    public TelefoneDtoResponse atualizaTelefone (Long idTelefone, TelefoneDtoRequest telefoneDto, String token){
       return usuarioClient.atualizaTelefone(telefoneDto, idTelefone, token);
    }
    public EnderecoDtoResponse cadastraEndereco (String token, EnderecoDtoRequest dto){
       return usuarioClient.cadastraEndereco(dto, token);
    }
    public TelefoneDtoResponse cadastraTelefone (String token, TelefoneDtoRequest dto) {
      return usuarioClient.cadastraTelefone(dto, token);
    }
}
