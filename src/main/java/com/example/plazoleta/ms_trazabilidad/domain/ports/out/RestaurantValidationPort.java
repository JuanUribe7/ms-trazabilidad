package com.example.plazoleta.ms_trazabilidad.domain.ports.out;

import java.util.List;

public interface RestaurantValidationPort {
    List<Long> getEmployeeIdsByOwner(Long ownerId);
}
