package br.com.postech.parking.vehicle.domain.valueobject;

import br.com.postech.parking.exception.InvalidFormatException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VehiclePlate {

    private final String plate;

    private VehiclePlate(String plate) {
        this.plate = plateValidation(plate);
    }

    public static VehiclePlate createVehiclePlateFactory(String plateValue) {
        return new VehiclePlate(plateValue);
    }

    private String plateValidation(String plate) {
        log.info("Validating plate {}", plate);
        if (plate == null || plate.isBlank()) {
            log.info("Empty filed plate {}", plate);
            throw new InvalidFormatException("The plate field can't be empty");
        }

        String replacePlate = plate.replaceAll("[^A-Za-z0-9]", "").toUpperCase();

        if (!replacePlate.matches("[A-Z]{3}\\d{4}") && !replacePlate.matches("[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}")) {
            log.info("Invalid format plate {}", plate);
            throw new InvalidFormatException("Plate format not accepted!");
        }
        return replacePlate;
    }

    public String getValue() {
        return plate;
    }

    @Override
    public String toString() {
        return "VehiclePlate{" +
                "plate='" + plate + '\'' +
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
        VehiclePlate that = (VehiclePlate) o;
        return Objects.equals(plate, that.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plate);
    }
}
