package br.com.postech.parking.exception;

public class PlateInvalidFormatException extends RuntimeException{

    public PlateInvalidFormatException(String message){
        super(message);
    }
}
