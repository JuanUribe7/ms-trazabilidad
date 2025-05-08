package com.example.plazoleta.ms_trazabilidad.infrastructure.repositories;

import com.example.plazoleta.ms_trazabilidad.infrastructure.documents.OrderTraceabilityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderTraceabilityRepository extends MongoRepository<OrderTraceabilityDocument, String> {
    boolean existsByOrderId(String orderId);
    OrderTraceabilityDocument getByOrderId(String orderId);
    List<OrderTraceabilityDocument> findByAssignedEmployeeId(String employeeId);

}
