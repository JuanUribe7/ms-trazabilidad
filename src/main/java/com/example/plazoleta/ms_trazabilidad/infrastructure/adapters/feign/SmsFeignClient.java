package com.example.plazoleta.ms_trazabilidad.infrastructure.adapters.feign;

import com.example.plazoleta.ms_trazabilidad.application.dto.request.SmsRequestDto;
import com.example.plazoleta.ms_trazabilidad.infrastructure.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mensajeriaClient", url = "${messaging.url}", configuration = FeignConfig.class)
public interface SmsFeignClient {

    @PostMapping("/notifications/sms")
    void sendSms(@RequestBody SmsRequestDto dto);
}