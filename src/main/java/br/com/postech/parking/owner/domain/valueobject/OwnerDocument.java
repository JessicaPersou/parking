package br.com.postech.parking.owner.domain.valueobject;

import br.com.postech.parking.exception.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class OwnerDocument {

    private final String cpf;

    private OwnerDocument(String cpf) {
        this.cpf = validationDocumentNumber(cpf);
    }

    public static OwnerDocument createOwnerDocumentFactory(String cpf) {
        return new OwnerDocument(cpf);
    }

    private String validationDocumentNumber(String cpf) {
        log.info("Validating CPF: {}", cpf);
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            log.info("CPF haven`t quantity of digits needed: {}, please insert 11 digits", cpf);
            throw new InvalidFormatException("CPF should be 11 digits");
        }

        boolean cpfCorrect = cpf.matches("\\d{11}|\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");

        if (!cpfCorrect) {
            log.info("format invald to CPF: {}", cpf);
            throw new InvalidFormatException("CPF in format invalid, please insert correct format.");
        }
        log.info("Validation by CPF: {} complete", cpf);
        return cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "OwnerDocument{" +
                "cpf='" + cpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OwnerDocument that = (OwnerDocument) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
