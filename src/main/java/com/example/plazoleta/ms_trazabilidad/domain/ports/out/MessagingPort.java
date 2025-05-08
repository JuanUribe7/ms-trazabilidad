package com.example.plazoleta.ms_trazabilidad.domain.ports.out;


import com.example.plazoleta.ms_trazabilidad.application.dto.request.SmsRequestDto;

public interface MessagingPort {
    void sendSms(SmsRequestDto dto);
}