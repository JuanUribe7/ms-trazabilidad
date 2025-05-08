package com.example.plazoleta.ms_trazabilidad.infrastructure.documents;


import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderTracking {

    private Instant timeStamp;
    private OrderStates state;

}
