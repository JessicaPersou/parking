package br.com.postech.parking.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Vehicle {

    private Long id;
    private String plate;
    private String model;
    private String color;
    private LocalDateTime inputDate;
    private LocalDateTime exitDate;
}
