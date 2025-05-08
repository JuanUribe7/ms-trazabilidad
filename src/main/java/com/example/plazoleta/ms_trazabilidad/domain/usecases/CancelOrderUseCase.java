package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.CancelOrderServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;

import java.time.Instant;
import java.util.List;

public class CancelOrderUseCase implements CancelOrderServicePort {

    private final OrderTracePersistencePort persistencePort;

    public CancelOrderUseCase(OrderTracePersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void execute(Long orderId) {
        OrderTraceability order = persistencePort.getByOrderId(orderId);

        List<OrderTrackingModel> track = order.getOrderTrack();
        OrderStates lastState = track.get(track.size() - 1).getState();

        if (lastState != OrderStates.PENDING) {
            throw new IllegalStateException("Only PENDING orders can be cancelled");
        }

        order.getOrderTrack().add(new OrderTrackingModel(OrderStates.CANCELLED, Instant.now()));
        persistencePort.save(order);
    }
}