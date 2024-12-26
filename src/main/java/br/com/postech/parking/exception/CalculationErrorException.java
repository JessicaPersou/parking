package br.com.postech.parking.exception;

public class CalculationErrorException extends RuntimeException {

    public CalculationErrorException(String message) {
        super(message);
    }
}
