package com.example.plazoleta.ms_trazabilidad.domain.usecases;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.ports.in.UpdateTrackWithEmployeeServicePort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;

import java.time.Instant;

public class UpdateWithEmployeeUseCase implements UpdateTrackWithEmployeeServicePort {

    private final OrderTracePersistencePort persistencePort;

    public UpdateWithEmployeeUseCase(OrderTracePersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }



    @Override
    public void execute(Long orderId, Long employeeId) {
        OrderTraceability traceability = persistencePort.getByOrderId(orderId);

        OrderStates lastState = traceability.getOrderTrack()
                .get(traceability.getOrderTrack().size() - 1)
                .getState();

        if (lastState != OrderStates.PENDING) {
            throw new IllegalStateException("Only orders in PENDING state can be assigned to an employee");
        }
        traceability.setAssignedEmployeeId(String.valueOf(employeeId));
        traceability.getOrderTrack().add(new OrderTrackingModel(OrderStates.PREPARING, Instant.now()));

        persistencePort.save(traceability);
    }
}
