package com.example.plazoleta.ms_trazabilidad.infrastructure.config;


import com.example.plazoleta.ms_trazabilidad.domain.ports.in.*;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.MessagingPort;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.OrderTracePersistencePort;
import com.example.plazoleta.ms_trazabilidad.domain.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public RegisterTrackServicePort registerTraceUseCase(
            OrderTracePersistencePort persistencePort
    ) {
        return new RegisterTrackUseCase(persistencePort);
    }
    @Bean
    public UpdateTrackWithEmployeeServicePort updateOrderWithEmployeeService(
            OrderTracePersistencePort persistencePort
    ) {
        return new UpdateWithEmployeeUseCase(persistencePort
    );
}
    @Bean
    public CancelOrderServicePort cancelOrderUseCase(OrderTracePersistencePort port) {
        return new CancelOrderUseCase(port);
    }

    @Bean
    public DeliverTrackServicePort deliverOrderUseCase(OrderTracePersistencePort port) {
        return new DeliverOrderUseCase(port);
    }

    @Bean
    public GetEmployeeEfficiencyServicePort getEmployeeEfficiencyUseCase(
            OrderTracePersistencePort port
    ) {
        return new GetEmployeeEfficiencyUseCase(port);
    }


    @Bean
    public MarkReadyServicePort markReadyUseCase(
            OrderTracePersistencePort port,
            MessagingPort messagingPort
    ) {
        return new MarkReadyUseCase(port, messagingPort);
    }

    @Bean
    public GetTraceServicePort getTraceUseCase(
            OrderTracePersistencePort port
    ) {
        return new GetTraceUseCase(port);
    }
}