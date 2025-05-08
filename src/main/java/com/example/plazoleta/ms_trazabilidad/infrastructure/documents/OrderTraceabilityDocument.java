package com.example.plazoleta.ms_trazabilidad.infrastructure.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders_traceability")
public class OrderTraceabilityDocument {

    @Id
    private String id;
    private String assignedEmployeeId;
    private String clientId;
    private String orderId;
    private String securityPin;
    private Long orderDurationInMilliseconds;


    private List<OrderTracking> orderTrack = new ArrayList<>();


}
