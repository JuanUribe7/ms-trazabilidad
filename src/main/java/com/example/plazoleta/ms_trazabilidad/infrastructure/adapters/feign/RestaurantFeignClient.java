package com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "plazoletaClient", url = "${plazoleta.url}")
public interface RestaurantFeignClient {

    @GetMapping("/restaurants/{ownerId}/employees")
    List<Long> getEmployeeIdsByOwner(@PathVariable Long ownerId);
}
