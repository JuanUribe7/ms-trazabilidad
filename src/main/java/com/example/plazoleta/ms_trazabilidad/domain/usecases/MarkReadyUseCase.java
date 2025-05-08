package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.SmsRequestDto;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.MarkReadyServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.MessagingPort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;

import java.time.Instant;

public class MarkReadyUseCase implements MarkReadyServicePort {

    private final OrderTracePersistencePort persistencePort;
    private final MessagingPort messagingPort;

    public MarkReadyUseCase(OrderTracePersistencePort persistencePort,
                            MessagingPort messagingPort) {
        this.persistencePort = persistencePort;
        this.messagingPort = messagingPort;
    }

    @Override
    public void execute(Long orderId, String phoneNumber) {

        System.out.println(orderId + " " + phoneNumber);
        if (!persistencePort.existsByOrderId(orderId.toString())) {
            throw new IllegalArgumentException("Order not found");
        }

        OrderTraceability order = persistencePort.getByOrderId(orderId);

        OrderStates lastState = order.getOrderTrack()
                .get(order.getOrderTrack().size() - 1)
                .getState();

        if (lastState != OrderStates.PREPARING) {
            throw new IllegalStateException("Order is not in PREPARING state");
        }
        String generatedPin = generatePin();


        order.setSecurityPin(generatedPin);
        messagingPort.sendSms(new SmsRequestDto(phoneNumber, "Your order is ready! PIN: " + generatedPin));
        order.getOrderTrack().add(new OrderTrackingModel(OrderStates.READY, Instant.now()));
        persistencePort.save(order);
        }
    private String generatePin() {
        return String.valueOf((int)(Math.random() * 900_000) + 100_000); // Ej: "763928"
    }
    }


