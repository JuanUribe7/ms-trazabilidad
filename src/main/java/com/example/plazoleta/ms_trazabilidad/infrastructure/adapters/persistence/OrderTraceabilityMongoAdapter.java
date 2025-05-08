package com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_trazabilidad.domain.model.OrderStates;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTraceability;
import com.example.plazoleta.ms_trazabilidad.domain.model.OrderTrackingModel;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;
import com.example.plazoleta.ms_trazabilidad.infrastructure.documents.OrderTraceabilityDocument;
import com.example.plazoleta.ms_trazabilidad.infrastructure.mappers.OrderTraceabilityEntityMapper;
import com.example.plazoleta.ms_trazabilidad.infrastructure.repositories.OrderTraceabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderTraceabilityMongoAdapter implements OrderTracePersistencePort {

    private final OrderTraceabilityRepository repository;
    private final OrderTraceabilityEntityMapper entityMapper;

    @Override
    public void save(OrderTraceability traceability) {
        OrderTraceabilityDocument document = entityMapper.toDocument(traceability);
        repository.save(document);
    }

    @Override
    public boolean existsByOrderId(String orderId) {
        return repository.existsByOrderId(String.valueOf(orderId));
    }

    @Override
    public OrderTraceability getByOrderId(Long orderId) {
        OrderTraceabilityDocument document = repository.getByOrderId(String.valueOf(orderId));
        return entityMapper.toModel(document);
    }

    @Override
    public List<OrderTraceability> findAllDeliveredByEmployeeId(Long employeeId) {
        return repository.findByAssignedEmployeeId(String.valueOf(employeeId)).stream()
                .map(entityMapper::toModel)
                .filter(order -> {
                    List<OrderTrackingModel> track = order.getOrderTrack();
                    return !track.isEmpty() && track.get(track.size() - 1).getState() == OrderStates.DELIVERED;
                })
                .toList();
    }
}
