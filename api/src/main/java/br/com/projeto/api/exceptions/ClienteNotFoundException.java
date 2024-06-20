package br.com.projeto.api.exceptions;


public class ClienteNotFoundException extends Exception{

    public ClienteNotFoundException(String msg){
        super(msg);
    }
}
