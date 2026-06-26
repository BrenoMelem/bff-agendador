package com.melem.bffagendadortarefas.infrastructure.exceptions;
//Excecção de Regra de Négocio, excecção geral
public class BusinnessException extends RuntimeException{

    public BusinnessException (String mensagem){
        super (mensagem);
    }
    public BusinnessException (String mensagem, Throwable throwable){
        super (mensagem, throwable);
    }
}
