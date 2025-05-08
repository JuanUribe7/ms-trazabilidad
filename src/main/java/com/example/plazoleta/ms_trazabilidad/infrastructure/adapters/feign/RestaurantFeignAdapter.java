package com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.feign;

import com.example.plazoleta.ms_trazabilidad.domain.ports.out.RestaurantValidationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantFeignAdapter implements RestaurantValidationPort {

    private final RestaurantFeignClient restaurant;

    @Override
    public List<Long> getEmployeeIdsByOwner(Long ownerId) {
        return restaurant.getEmployeeIdsByOwner(ownerId);
    }
}
