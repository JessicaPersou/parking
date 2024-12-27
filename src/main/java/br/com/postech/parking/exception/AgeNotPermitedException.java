package br.com.postech.parking.exception;

public class AgeNotPermitedException extends RuntimeException {

    public AgeNotPermitedException(String message) {
        super(message);
    }
}
