package com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.feign;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.SmsRequestDto;
import com.example.plazoleta.ms_trazabilidad.domain.ports.out.MessagingPort;
import org.springframework.stereotype.Component;

@Component
public class SmsFeignAdapter implements MessagingPort {

    private final SmsFeignClient smsFeignClient;

    public SmsFeignAdapter(SmsFeignClient smsFeignClient) {
        this.smsFeignClient = smsFeignClient;
    }

    @Override
    public void sendSms(SmsRequestDto dto) {
        smsFeignClient.sendSms(dto);
    }
}
