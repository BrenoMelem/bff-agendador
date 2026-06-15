package com.melem.bffagendadortarefas.controller;


import com.melem.bffagendadortarefas.business.EmailService;
import com.melem.bffagendadortarefas.business.dtos.out.TarefaDtoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//como fazer uma regra de negocio atraves e um CRON para ser executado automaticamente, buascar tarefas por periodo, vai retornar estar tarefas e pega uma para enviar o email, sem necessidade da controller;
@RestController
@RequiredArgsConstructor
@RequestMapping ("/email")
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail (@RequestBody TarefaDtoResponse dto){
        emailService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }


}
