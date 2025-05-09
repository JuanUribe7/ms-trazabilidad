package com.example.plazoleta.ms_trazabilidad.domain.model;

public class OrderEfficiency {

    private final Long orderId;
    private final Long durationInMilliseconds;

    public OrderEfficiency(Long orderId, Long durationInMilliseconds) {
        this.orderId = orderId;
        this.durationInMilliseconds = durationInMilliseconds;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getDurationInMilliseconds() {
        return durationInMilliseconds;
    }
}
