package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.DeliverTrackServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class DeliverOrderUseCase implements DeliverTrackServicePort {

    private final OrderTracePersistencePort persistencePort;

    public DeliverOrderUseCase(OrderTracePersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public void execute(Long orderId, String pin) {
        OrderTraceability order = persistencePort.getByOrderId(orderId);

        List<OrderTrackingModel> trackings = order.getOrderTrack();
        OrderTrackingModel last = trackings.get(trackings.size() - 1);

        if (last.getState() != OrderStates.READY) {
            throw new IllegalStateException("Order must be READY to be delivered");
        }

        if (!Objects.equals(order.getSecurityPin(), pin)) {
            throw new IllegalArgumentException("Invalid security PIN");
        }


        Instant start = trackings.get(0).getTimeStamp();
        long duration = Duration.between(start, Instant.now()).toMillis();
        order.setOrderDurationInMilliseconds(duration);


        order.getOrderTrack().add(new OrderTrackingModel(OrderStates.DELIVERED, Instant.now()));

        persistencePort.save(order);
    }
}
