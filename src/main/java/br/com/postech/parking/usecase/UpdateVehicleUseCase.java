package br.com.postech.parking.usecase;


import br.com.postech.parking.application.gateway.VehicleGateway;
import br.com.postech.parking.domain.Vehicle;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateVehicleUseCase {

    private final VehicleGateway vehicleGateway;

    public Vehicle updateVehicle(String plate, Vehicle vehicle) {
        return vehicleGateway.updateVehicle(plate, vehicle);
    }
}
