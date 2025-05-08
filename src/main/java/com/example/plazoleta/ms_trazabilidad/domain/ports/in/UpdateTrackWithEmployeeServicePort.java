package com.example.plazoleta.ms_trazabilidad.domain.ports.in;

public interface UpdateTrackWithEmployeeServicePort {

    void execute(Long orderId, Long employeeId);
}
