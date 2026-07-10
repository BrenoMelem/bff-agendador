package com.melem.bffagendadortarefas.business;
//fazer uma regra de negocio atraves de um CRON para ser executado automaticamente, buscar tarefas por periodo, vai retornar estar tarefas e pega uma para enviar o email, sem necessidade da controller;
// Buscar no Agendador de tarefas a cada 5 minutos todas as tarefas que serão realizadas daqui a uma hora, pra notificar com antecedencia
//No CRON é proibido ter parametro,tem que ser acionado sem nenhum tipo

import com.melem.bffagendadortarefas.business.dtos.in.LoginRequestDto;
import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import com.melem.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j //Anotação do Lombok para criar um logger, para que possamos logar as informações do CRON,OU OUTRAS FUNCIONALIDADES para saber quando ele foi executado, se teve algum erro, etc.
public class CronService {
    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;
    //Value serve para pegar o valor do application.properties, para que possa ser configurado o tempo de execução do CRON, sem precisar alterar o código, só alterando o valor no properties.
    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    //Para Utilizarmos o CRON iremos utilizar a anotação SCHEDULE QUE SERIA, @Scheduled(cron = "0 0/5 * * * ?") - A cada 5 minutos, o CRON irá ser executado, para buscar as tarefas que serão realizadas daqui a uma hora, para notificar com antecedencia.
    @Scheduled(cron = "${cron.horario}")
    // A cada 5 minutos, o CRON irá ser executado, para buscar as tarefas que serão realizadas daqui a uma hora, para notificar com antecedencia.
    public void buscaTarefasProximaHora() {
        log.info("Iniciado a busca de tarefas");
        String token = login(converterParaRequestDto());
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFuturaMaisUma = LocalDateTime.now().plusHours(1);
        List<TarefaDtoResponse> listaDeTarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFuturaMaisUma, token);
        log.info("Tarefas Encontradas " + listaDeTarefas);
        listaDeTarefas.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario " + tarefa.getEmailUsuario());
            tarefaService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);
        });
        log.info("Finalizado a busca de tarefas");
    }
    //Qualquer tarefa que fique entre a hora atual e a hora futura mais 1
    // Se agora é 22h- QUALQUER TAREFA ENTRE 22H  E 23H
    //No momento, é 22 -qualquer tarefa entre 23 e 23:05 -- Antes.
    //Como neste metodo está solicitando um token, precisamos criar um metodo para o nosso proprio sistema automatize o login para que possa enviar o token para o método.

    public String login(LoginRequestDto loginRequestDto) {
        return usuarioService.loginUsuario(loginRequestDto);
    }
    //REALIZANDO UM CONVERTER PARA REQUEST DTO
    public LoginRequestDto converterParaRequestDto() {
        return LoginRequestDto.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
