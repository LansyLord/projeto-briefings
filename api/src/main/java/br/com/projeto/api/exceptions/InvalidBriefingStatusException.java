package br.com.projeto.api.exceptions;


public class InvalidBriefingStatusException extends Exception{
    public InvalidBriefingStatusException(String msg){
        super(msg);
    }
}
