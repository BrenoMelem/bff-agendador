package com.melem.bffagendadortarefas.controller;
//Para que consiggamos receber esses tratamentos pelo Feingcliente, lembrando que só temos os retornos, e não estamos tratando nenhuma exceção no client.
// Para isso, configurar duas classes. Para tratar no FeignCleinte
import com.melem.bffagendadortarefas.infrastructure.exceptions.ConflictException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.melem.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler (ConflictException.class)
    public ResponseEntity<String> handlerConflictException(ConflictException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler (UnauthorizedException.class)
    public ResponseEntity<String> handlerUnauthorizedException(UnauthorizedException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException (IllegalArgumentException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
